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

}
