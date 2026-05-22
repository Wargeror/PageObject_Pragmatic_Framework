package performance;

import framework.base.BaseTest;
import framework.data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.LoginPage;

@Epic("Performance")
@Feature("Load Time Measurements")
public class PerformanceTests extends BaseTest {

    @Test(
            testName = "Login Page Load Time Performance Test",
            description = "Measures the time it takes for the login page to load and asserts that it is within an acceptable limit."
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Measures the time it takes for the login page to load and asserts that it is within an acceptable limit.")
    public void measureLoginLoadTime() {
        log.info("Starting login load time performance test.");
        User user = input.getUser(0);
        getDriver().get(user.getSiteURL());

        LoginPage loginPage = webApp.loginPage()
                 .typeTextUsernameField(user.getUsername())
                 .typeTextPasswordField(user.getPassword());

        log.info("Recording start time and clicking login.");
        long startTime = System.currentTimeMillis();

        loginPage
                .clickLoginButton()
                .leftNavigationBar.waitNavBarToBeDisplayed();

        long endTime = System.currentTimeMillis();
        log.info("Recording end time.");

        long loadTime = endTime - startTime;
        log.info("Login Page Load Time: " + loadTime + " ms");

        long acceptableLoadTime = 5000; // milliseconds
        log.info("Asserting that load time is less than " + acceptableLoadTime + " ms.");
        Assert.assertTrue(loadTime < acceptableLoadTime, "Failure PerformanceTests/measureLoginLoadTime: Login page took too long to load! Time: " + loadTime + " ms");
    }
}