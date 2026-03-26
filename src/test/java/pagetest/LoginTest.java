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

    //Performance test for login page load time
    @Test
    public void measureLoginLoadTime() {
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait)
                 .typeTextUsernameField(user.getUsername())
                 .typeTextPasswordField(user.getPassword());

        //Record the current time - Test start time
        long startTime = System.currentTimeMillis();

        // Click the login button
                loginPage
                .clickLoginButton()
                .leftNavigationBar.w8NavBarToBeDisplayed();

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
