package pagetest;

import base.BaseTest;
import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    //Successful login test
    @Test
    public void loginTest(){
        login();

        User user = input.getUser(0); // Get user for assertion
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        Assert.assertEquals(dashboardPage.usernameGetText(), user.getExpectedDashboardUsername());
        Assert.assertTrue(dashboardPage.urlContains());
    }

    //Unsuccessful login test
    @Test
    public void unsuccessfulLoginTest() {
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField("");
        loginPage.typeTextPasswordField("");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.alertGetText(), LoginPage.EXPECTED_ALERT_TEXT);
    }

    //Performance test for login page load time
    @Test
    public void measureLoginLoadTime() {
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField(user.getUsername());
        loginPage.typeTextPasswordField(user.getPassword());
        //Record the current time - Test start time
        long startTime = System.currentTimeMillis();
        loginPage.clickLoginButton();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        // Wait for a key element on the dashboard to be visible to ensure the page has loaded
        dashboardPage.leftNavigationBar.w8NavBarToBeDisplayed();
        //Record the current time - Test end time
        long endTime = System.currentTimeMillis();

        // Calculate the load time
        long loadTime = endTime - startTime;

        // Print the load time
        System.out.println("Login Page Load Time: " + loadTime + " ms");

        // Assert that the load time is within an acceptable limit (e.g., 5 seconds)
        long acceptableLoadTime = 5000; // milliseconds
        Assert.assertTrue(loadTime < acceptableLoadTime, "Login page took too long to load! Time: " + loadTime + " ms");
    }
}
