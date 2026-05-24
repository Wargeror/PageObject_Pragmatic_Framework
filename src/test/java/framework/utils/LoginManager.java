package framework.utils;

import framework.data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import framework.pages.DashboardPage;
import framework.base.WebApp;

import java.io.*;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LoginManager {

    private static final Logger log = LogManager.getLogger(LoginManager.class);

    // Inner class to hold both cookies and the URL token
    private static class SessionData implements Serializable {
        private static final long serialVersionUID = 1L;
        Set<Cookie> cookies;
        String userToken;

        SessionData(Set<Cookie> cookies, String userToken) {
            this.cookies = cookies;
            this.userToken = userToken;
        }
    }

    // Store SessionData per thread ID to ensure thread safety
    private static final ConcurrentHashMap<Long, SessionData> sessionCache = new ConcurrentHashMap<>();
    private static final String SESSION_COOKIE_NAME = "OCSESSID";

    // Toggle file-based cookie storage
    private static final boolean saveCookieToFile = true;
    private static final String COOKIE_DIR = "resources/cookies/";

    public static DashboardPage loginWithCookieOrCredentials(WebDriver driver, WebApp webApp, User user) {
        long currentThreadId = Thread.currentThread().getId();
        
        // Attempt to load from file if not in memory and file storage is enabled
        if (saveCookieToFile && !sessionCache.containsKey(currentThreadId)) {
            SessionData fileSession = loadSessionFromFile(currentThreadId);
            if (fileSession != null) {
                sessionCache.put(currentThreadId, fileSession);
            }
        }

        SessionData storedSession = sessionCache.get(currentThreadId);

        if (storedSession != null && storedSession.cookies != null && !storedSession.cookies.isEmpty() && isSessionStillValid(storedSession.cookies)) {
            log.info("Valid session found for thread " + currentThreadId + ". Attempting login via cookie and token injection.");

            // Navigate to a domain page first to set the cookies
            driver.get(user.getSiteURL());
            storedSession.cookies.forEach(driver.manage()::addCookie);

            // Construct the dashboard URL with the token
            DashboardPage dashboardPage = webApp.dashboardPage();
            String targetUrl = dashboardPage.getUrlDashboard();
            if (storedSession.userToken != null && !storedSession.userToken.isEmpty()) {
                targetUrl += (targetUrl.contains("?") ? "&" : "?") + storedSession.userToken;
            }
            
            // Navigate to dashboard to verify session
            driver.get(targetUrl);

            // Basic check to see if we landed on the dashboard successfully (ignoring exact token match in check)
            if (driver.getCurrentUrl().contains("route=common/dashboard")) {
                log.info("Successfully logged in using cached session.");
                return dashboardPage;
            } else {
                log.warn("Login with cached session failed. Falling back to credentials.");
                sessionCache.remove(currentThreadId);
                if (saveCookieToFile) {
                    deleteCookieFile(currentThreadId);
                }
            }
        } else {
            log.info("No valid cached session found for thread " + currentThreadId + ".");
        }

        // Fallback: Login with credentials
        return performLoginAndStoreSession(driver, webApp, user, currentThreadId);
    }

    private static DashboardPage performLoginAndStoreSession(WebDriver driver, WebApp webApp, User user, long threadId) {
        log.info("Logging in with credentials for user: " + user.getUsername());
        driver.get(user.getSiteURL());
        
        DashboardPage dashboardPage = webApp.loginPage()
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .waitUserNameToBeDisplayed();

        // Retrieve and store all cookies
        Set<Cookie> newSessionCookies = driver.manage().getCookies();
        
        // Extract the user_token from the URL
        String currentUrl = driver.getCurrentUrl();
        String extractedToken = "";
        try {
            int tokenIndex = currentUrl.indexOf("user_token=");
            if (tokenIndex != -1) {
                extractedToken = currentUrl.substring(tokenIndex);
                if (extractedToken.contains("&")) {
                    extractedToken = extractedToken.substring(0, extractedToken.indexOf("&"));
                }
            }
        } catch (Exception e) {
            log.warn("Could not extract user_token from URL after login: " + currentUrl);
        }

        if (newSessionCookies != null && !newSessionCookies.isEmpty()) {
            SessionData newSession = new SessionData(newSessionCookies, extractedToken);
            sessionCache.put(threadId, newSession);
            log.info("Stored " + newSessionCookies.size() + " cookies and token '" + extractedToken + "' for thread " + threadId + ".");
            
            if (saveCookieToFile) {
                saveSessionToFile(threadId, newSession);
            }
        } else {
            log.warn("Could not find any cookies after login.");
        }

        return dashboardPage;
    }

    private static void saveSessionToFile(long threadId, SessionData sessionData) {
        File dir = new File(COOKIE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(COOKIE_DIR + "session-thread-" + threadId + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(sessionData);
            log.info("Saved session data to file: " + file.getPath());
        } catch (IOException e) {
            log.error("Failed to save session to file for thread " + threadId, e);
        }
    }

    private static SessionData loadSessionFromFile(long threadId) {
        File file = new File(COOKIE_DIR + "session-thread-" + threadId + ".ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                SessionData sessionData = (SessionData) ois.readObject();
                log.info("Loaded session data from file: " + file.getPath());
                return sessionData;
            } catch (IOException | ClassNotFoundException e) {
                log.error("Failed to load session from file for thread " + threadId, e);
            }
        }
        return null;
    }

    private static void deleteCookieFile(long threadId) {
        File file = new File(COOKIE_DIR + "session-thread-" + threadId + ".ser");
        if (file.exists() && file.delete()) {
            log.info("Deleted invalid session file for thread " + threadId);
        }
    }

    private static boolean isSessionStillValid(Set<Cookie> cookies) {
        return cookies.stream()
                .filter(c -> SESSION_COOKIE_NAME.equals(c.getName()))
                .findFirst()
                .map(LoginManager::isCookieValid)
                .orElse(false);
    }

    private static boolean isCookieValid(Cookie cookie) {
        Date expiryDate = cookie.getExpiry();
        // If expiry is null, it's a session cookie and valid for the current browser session.
        // If it has an expiry date, check if it's in the future.
        boolean isValid = expiryDate == null || expiryDate.after(new Date());
        if(!isValid){
            log.debug("Stored session cookie '" + SESSION_COOKIE_NAME + "' has expired.");
        }
        return isValid;
    }

    // Utility method to clear session for a thread
    public static void clearSessionCookiesForCurrentThread() {
        long currentThreadId = Thread.currentThread().getId();
        sessionCache.remove(currentThreadId);
        if (saveCookieToFile) {
            deleteCookieFile(currentThreadId);
        }
        log.info("Cleared stored session data for thread " + currentThreadId + ".");
    }
}