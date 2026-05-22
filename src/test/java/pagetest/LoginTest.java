package pagetest;

import base.BaseTest;
import data.User;
import io.qameta.allure.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v144.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.Set;

@Epic("Authentication")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Test(
            testName = "Successful Login Test",
            description = "Verifies that a user can log in with valid credentials and is redirected to the dashboard."
    )
    @Story("Positive Case: Valid Credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest(){
        log.info("Executing successful login test.");
        DashboardPage dashboardPage = login();

        User user = input.getUser(0);
        log.info("Asserting dashboard username and URL.");
        Assert.assertEquals(dashboardPage.usernameGetText(), user.getExpectedDashboardUsername(), "Failure LoginTest/loginTest: Dashboard username does not match expected username.");
        Assert.assertTrue(dashboardPage.urlContains(), "Failure LoginTest/loginTest: Dashboard page URL does not contain expected string after successful login.");
    }

    @Test(
            testName = "Unsuccessful Login Test",
            description = "Verifies that an appropriate error message is displayed when a user attempts to log in with invalid credentials."
    )
    @Story("Negative Case: Invalid Credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void unsuccessfulLoginTest() {
        log.info("Executing unsuccessful login test.");
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = webApp.loginPage();
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        log.info("Asserting presence of login error alert.");
        Assert.assertEquals(loginPage.alertGetText(), LoginPage.EXPECTED_ALERT_TEXT, "Failure LoginTest/unsuccessfulLoginTest: Alert message for unsuccessful login does not match expected text.");
    }


    @Test(
            testName = "Console Logs on Unsuccessful Login",
            description = "Captures and prints console logs during an unsuccessful login attempt for debugging purposes."
    )
    @Story("Debugging and Diagnostics")
    @Severity(SeverityLevel.MINOR)
    public void consoleLogsOnUnsuccessfulLoginTest(){
        log.info("Setting up DevTools to capture console logs.");
        ChromeDriver chromeDriver = (ChromeDriver) getDriver();
        DevTools devTools = chromeDriver.getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());

        devTools.addListener(Log.entryAdded(), logEntry -> {
                    log.debug("Console Log: " + logEntry.getText());
                });

        log.info("Executing unsuccessful login to trigger console logs.");
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = webApp.loginPage();
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Test(
            testName = "Cookie Presence on Successful Login",
            description = "Verifies that the 'OCSESSID' session cookie is present after a successful login."
    )
    @Story("Session Management")
    @Severity(SeverityLevel.NORMAL)
    public void cookiesOnSuccessfulLoginTest() {
        log.info("Logging in to verify cookie presence.");
        login();

        Set<Cookie> cookies = printCookies();
        
        log.info("Asserting that 'OCSESSID' cookie is present.");
        boolean isCookiePresent = isCookiePresent(cookies, "OCSESSID");

        Assert.assertTrue(isCookiePresent, "Failure LoginTest/cookiesOnSuccessfulLoginTest: Cookie 'OCSESSID' was not found after successful login.");
    }

    @Test(
            testName = "Negative Cookie Injection Test",
            description = "Confirms that injecting a session cookie into a new tab does not grant access, preventing session hijacking."
    )
    @Story("Security: Session Hijacking")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeCookiesInjectionTest(){
        log.info("Logging in and obtaining session cookie.");
        DashboardPage dashboardPage = login();

        Cookie sessionCookie = getCookieByName(printCookies(), "OCSESSID");
        String sucLoginURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(sessionCookie, "Failure LoginTest/negativeCookiesInjectionTest: Session cookie 'OCSESSID' not found after successful login!");

        log.info("Opening new tab and attempting cookie injection.");
        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get(dashboardPage.getUrlDashboard());

        getDriver().manage().deleteAllCookies();
        injectCookie(sessionCookie);
        getDriver().navigate().refresh();
        String failedLoginURL = getDriver().getCurrentUrl();

        log.info("Asserting that cookie injection did not grant access.");
        Assert.assertFalse(sucLoginURL.equals(failedLoginURL), "Failure LoginTest/negativeCookiesInjectionTest: URL remained the same after attempting cookie injection, indicating a potential vulnerability.");
    }

}
