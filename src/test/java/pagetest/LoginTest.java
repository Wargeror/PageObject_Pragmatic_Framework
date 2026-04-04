package pagetest;

import base.BaseTest;
import data.User;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v144.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.Optional;

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
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField("")
                 .typeTextPasswordField("")
                 .clickLoginButton();

        Assert.assertEquals(loginPage.alertGetText(), LoginPage.EXPECTED_ALERT_TEXT);
    }


    @Test
    public void consoleLogsOnUnsuccessfulLoginTest(){
        // Cast driver to ChromeDriver to access DevTools
        ChromeDriver chromeDriver = (ChromeDriver) driver;
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

        // Perform the unsuccessful login action
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField("invalidUser")
                 .typeTextPasswordField("invalidPass")
                 .clickLoginButton();

        // Small wait to ensure logs are captured before the test ends
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
