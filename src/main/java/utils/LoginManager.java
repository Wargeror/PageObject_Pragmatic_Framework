package utils;

import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import base.WebApp;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class LoginManager {

    private static final Logger log = LogManager.getLogger(LoginManager.class);

    // ConcurrentHashMap to store the session cookie per thread ID to ensure thread safety during parallel execution
    private static final ConcurrentHashMap<Long, Cookie> sessionCookies = new ConcurrentHashMap<>();
    private static final String SESSION_COOKIE_NAME = "OCSESSID";

    public static DashboardPage loginWithCookieOrCredentials(WebDriver driver, WebApp webApp, User user) {
        long currentThreadId = Thread.currentThread().getId();
        Cookie storedCookie = sessionCookies.get(currentThreadId);

        if (storedCookie != null && isCookieValid(storedCookie)) {
            log.info("Valid session cookie found for thread " + currentThreadId + ". Attempting login via cookie injection.");

            // Navigate to a domain page first to set the cookie
            driver.get(user.getSiteURL());
            driver.manage().addCookie(storedCookie);

            // Navigate to dashboard to verify session
            DashboardPage dashboardPage = webApp.dashboardPage();
            driver.get(dashboardPage.getUrlDashboard());

            // Basic check to see if we landed on the dashboard successfully
            if (driver.getCurrentUrl().contains(dashboardPage.getUrlDashboard())) {
                log.info("Successfully logged in using session cookie.");
                return dashboardPage;
            } else {
                log.warn("Login with cookie failed (session might be invalidated server-side). Falling back to credentials.");
                // Clear the invalid cookie from our map
                sessionCookies.remove(currentThreadId);
            }
        } else {
            log.info("No valid session cookie found for thread " + currentThreadId + ".");
        }

        // Fallback: Login with credentials
        return performLoginAndStoreCookie(driver, webApp, user, currentThreadId);
    }

    private static DashboardPage performLoginAndStoreCookie(WebDriver driver, WebApp webApp, User user, long threadId) {
        log.info("Logging in with credentials for user: " + user.getUsername());
        driver.get(user.getSiteURL());
        
        DashboardPage dashboardPage = webApp.loginPage()
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .waitUserNameToBeDisplayed();

        // Retrieve and store the session cookie after a successful login
        Cookie newSessionCookie = driver.manage().getCookieNamed(SESSION_COOKIE_NAME);
        if (newSessionCookie != null) {
            sessionCookies.put(threadId, newSessionCookie);
            log.info("Stored new session cookie for thread " + threadId + ".");
        } else {
            log.warn("Could not find session cookie '" + SESSION_COOKIE_NAME + "' after login.");
        }

        return dashboardPage;
    }

    private static boolean isCookieValid(Cookie cookie) {
        Date expiryDate = cookie.getExpiry();
        // If expiry is null, it's a session cookie and valid for the current browser session.
        // If it has an expiry date, check if it's in the future.
        boolean isValid = expiryDate == null || expiryDate.after(new Date());
        if(!isValid){
            log.debug("Stored cookie has expired.");
        }
        return isValid;
    }

    // Optional utility method to clear cookies for a thread, useful for tests that explicitly need to test login
    public static void clearSessionCookieForCurrentThread() {
        long currentThreadId = Thread.currentThread().getId();
        sessionCookies.remove(currentThreadId);
        log.info("Cleared stored session cookie for thread " + currentThreadId + ".");
    }
}