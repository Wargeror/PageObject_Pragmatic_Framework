package utils;

import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import base.WebApp;

import java.io.*;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LoginManager {

    private static final Logger log = LogManager.getLogger(LoginManager.class);

    // Store a set of cookies per thread ID to ensure thread safety
    private static final ConcurrentHashMap<Long, Set<Cookie>> sessionCookies = new ConcurrentHashMap<>();
    private static final String SESSION_COOKIE_NAME = "OCSESSID";

    // Toggle file-based cookie storage
    private static final boolean saveCookieToFile = true;
    private static final String COOKIE_DIR = "resources/cookies/";

    public static DashboardPage loginWithCookieOrCredentials(WebDriver driver, WebApp webApp, User user) {
        long currentThreadId = Thread.currentThread().getId();
        
        // Attempt to load from file if not in memory and file storage is enabled
        if (saveCookieToFile && !sessionCookies.containsKey(currentThreadId)) {
            Set<Cookie> fileCookies = loadCookiesFromFile(currentThreadId);
            if (fileCookies != null) {
                sessionCookies.put(currentThreadId, fileCookies);
            }
        }

        Set<Cookie> storedCookies = sessionCookies.get(currentThreadId);

        if (storedCookies != null && !storedCookies.isEmpty() && isSessionStillValid(storedCookies)) {
            log.info("Valid session cookies found for thread " + currentThreadId + ". Attempting login via cookie injection.");

            // Navigate to a domain page first to set the cookies
            driver.get(user.getSiteURL());
            storedCookies.forEach(driver.manage()::addCookie);

            // Navigate to dashboard to verify session
            DashboardPage dashboardPage = webApp.dashboardPage();
            driver.get(dashboardPage.getUrlDashboard());

            if (driver.getCurrentUrl().contains(dashboardPage.getUrlDashboard())) {
                log.info("Successfully logged in using session cookies.");
                return dashboardPage;
            } else {
                log.warn("Login with cookies failed (session might be invalidated server-side). Falling back to credentials.");
                sessionCookies.remove(currentThreadId);
                if (saveCookieToFile) {
                    deleteCookieFile(currentThreadId);
                }
            }
        } else {
            log.info("No valid session cookies found for thread " + currentThreadId + ".");
        }

        // Fallback: Login with credentials
        return performLoginAndStoreCookies(driver, webApp, user, currentThreadId);
    }

    private static DashboardPage performLoginAndStoreCookies(WebDriver driver, WebApp webApp, User user, long threadId) {
        log.info("Logging in with credentials for user: " + user.getUsername());
        driver.get(user.getSiteURL());
        
        DashboardPage dashboardPage = webApp.loginPage()
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .waitUserNameToBeDisplayed();

        // Retrieve and store all cookies after a successful login
        Set<Cookie> newSessionCookies = driver.manage().getCookies();
        if (newSessionCookies != null && !newSessionCookies.isEmpty()) {
            sessionCookies.put(threadId, newSessionCookies);
            log.info("Stored " + newSessionCookies.size() + " session cookies for thread " + threadId + ".");
            
            if (saveCookieToFile) {
                saveCookiesToFile(threadId, newSessionCookies);
            }
        } else {
            log.warn("Could not find any cookies after login.");
        }

        return dashboardPage;
    }

    private static void saveCookiesToFile(long threadId, Set<Cookie> cookies) {
        File dir = new File(COOKIE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(COOKIE_DIR + "cookies-thread-" + threadId + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(cookies);
            log.info("Saved cookies to file: " + file.getPath());
        } catch (IOException e) {
            log.error("Failed to save cookies to file for thread " + threadId, e);
        }
    }

    @SuppressWarnings("unchecked")
    private static Set<Cookie> loadCookiesFromFile(long threadId) {
        File file = new File(COOKIE_DIR + "cookies-thread-" + threadId + ".ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Set<Cookie> cookies = (Set<Cookie>) ois.readObject();
                log.info("Loaded cookies from file: " + file.getPath());
                return cookies;
            } catch (IOException | ClassNotFoundException e) {
                log.error("Failed to load cookies from file for thread " + threadId, e);
            }
        }
        return null;
    }

    private static void deleteCookieFile(long threadId) {
        File file = new File(COOKIE_DIR + "cookies-thread-" + threadId + ".ser");
        if (file.exists() && file.delete()) {
            log.info("Deleted invalid cookie file for thread " + threadId);
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

    // Utility method to clear cookies for a thread
    public static void clearSessionCookiesForCurrentThread() {
        long currentThreadId = Thread.currentThread().getId();
        sessionCookies.remove(currentThreadId);
        if (saveCookieToFile) {
            deleteCookieFile(currentThreadId);
        }
        log.info("Cleared stored session cookies for thread " + currentThreadId + ".");
    }
}
