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

    //Successful login test
    @Test
    public void loginTest(){
        //Get to the DashboardPage By Login
        DashboardPage dashboardPage = login();

        User user = input.getUser(0); // Get user for assertion
        Assert.assertEquals(dashboardPage.usernameGetText(), user.getExpectedDashboardUsername());
        Assert.assertTrue(dashboardPage.urlContains());
    }

    //Unsuccessful login test
    @Test
    public void unsuccessfulLoginTest() {
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        Assert.assertEquals(loginPage.alertGetText(), LoginPage.EXPECTED_ALERT_TEXT);
    }


    //Console Logs On Unsuccessful Login
    @Test
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

    //Tests that the correct cookies are loaded on successful login.
    @Test
    public void cookiesOnSuccessfulLoginTest() {
        login();

        Set<Cookie> cookies = printCookies();
        
        boolean isCookiePresent = isCookiePresent(cookies, "OCSESSID");

        Assert.assertTrue(isCookiePresent, "Cookie 'OCSESSID' was not found after successful login.");
    }

    //Confirms that cookie injection doesn't work
    @Test
    public void negativeCookiesInjectionTest(){
        closeDriver = false; // Keep browser open for manual inspection if needed
        DashboardPage dashboardPage = login();

        Cookie sessionCookie = getCookieByName(printCookies(), "OCSESSID");
        String sucLoginURL = getDriver().getCurrentUrl();
        Assert.assertNotNull(sessionCookie, "Session cookie 'OCSESSID' not found!");

        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get(dashboardPage.getUrlDashboard());

        getDriver().manage().deleteAllCookies();
        injectCookie(sessionCookie);
        getDriver().navigate().refresh();
        String failedLoginURL = getDriver().getCurrentUrl();

        Assert.assertFalse(sucLoginURL.equals(failedLoginURL));
    }

}
