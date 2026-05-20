package pagetest;

import base.BaseTest;
import data.User;
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

public class LoginTest extends BaseTest {

    @Test(
            testName = "Successful Login Test",
            description = "Verifies that a user can log in with valid credentials and is redirected to the dashboard."
    )
    public void loginTest(){
        //Get to the DashboardPage By Login
        DashboardPage dashboardPage = login();

        User user = input.getUser(0); // Get user for assertion
        Assert.assertEquals(dashboardPage.usernameGetText(), user.getExpectedDashboardUsername(), "Failure LoginTest/loginTest: Dashboard username does not match expected username.");
        Assert.assertTrue(dashboardPage.urlContains(), "Failure LoginTest/loginTest: Dashboard page URL does not contain expected string after successful login.");
    }

    @Test(
            testName = "Unsuccessful Login Test",
            description = "Verifies that an appropriate error message is displayed when a user attempts to log in with invalid credentials."
    )
    public void unsuccessfulLoginTest() {
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        Assert.assertEquals(loginPage.alertGetText(), LoginPage.EXPECTED_ALERT_TEXT, "Failure LoginTest/unsuccessfulLoginTest: Alert message for unsuccessful login does not match expected text.");
    }


    @Test(
            testName = "Console Logs on Unsuccessful Login",
            description = "Captures and prints console logs during an unsuccessful login attempt for debugging purposes."
    )
    public void consoleLogsOnUnsuccessfulLoginTest(){
        // Cast driver to ChromeDriver to access DevTools
        ChromeDriver chromeDriver = (ChromeDriver) getDriver();
        DevTools devTools = chromeDriver.getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());

        devTools.addListener(Log.entryAdded(), logEntry -> {
                    System.out.println("----- Console Log Entry -----");
                    System.out.println("Text: " + logEntry.getText());
                    System.out.println("Level: " + logEntry.getLevel());
                    System.out.println("Timestamp: " + logEntry.getTimestamp());
                    System.out.println("URL: " + logEntry.getUrl().orElse("N/A"));
                    System.out.println("-----------------------------");
                });

        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        // Small wait to ensure logs are captured before the test ends
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Test(
            testName = "Cookie Presence on Successful Login",
            description = "Verifies that the 'OCSESSID' session cookie is present after a successful login."
    )
    public void cookiesOnSuccessfulLoginTest() {
        login();

        Set<Cookie> cookies = printCookies();
        
        boolean isCookiePresent = isCookiePresent(cookies, "OCSESSID");

        Assert.assertTrue(isCookiePresent, "Failure LoginTest/cookiesOnSuccessfulLoginTest: Cookie 'OCSESSID' was not found after successful login.");
    }

    @Test(
            testName = "Negative Cookie Injection Test",
            description = "Confirms that injecting a session cookie into a new tab does not grant access, preventing session hijacking."
    )
    public void negativeCookiesInjectionTest(){
        closeDriver = false; // Keep browser open for manual inspection if needed
        DashboardPage dashboardPage = login();

        Cookie sessionCookie = getCookieByName(printCookies(), "OCSESSID");
        String sucLoginURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(sessionCookie, "Failure LoginTest/negativeCookiesInjectionTest: Session cookie 'OCSESSID' not found after successful login!");

        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get(dashboardPage.getUrlDashboard());

        getDriver().manage().deleteAllCookies();
        injectCookie(sessionCookie);
        getDriver().navigate().refresh();
        String failedLoginURL = getDriver().getCurrentUrl();

        Assert.assertFalse(sucLoginURL.equals(failedLoginURL), "Failure LoginTest/negativeCookiesInjectionTest: URL remained the same after attempting cookie injection, indicating a potential vulnerability.");
    }

}
