package performance;

import base.BaseTest;
import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class PerformanceTests extends BaseTest {

    //Performance test for login page load time
    @Test
    public void measureLoginLoadTime() {
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(getDriver(), getWait())
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
