import data.Input;
import data.User;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;
    Input input;

    //Set up WebDriver before each test
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        input = new Input();
    }

    //Successful login test
    @Test
    public void loginTest(){
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField(user.getUsername());
        loginPage.typeTextPasswordField(user.getPassword());
        loginPage.clickLoginButton();
        
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        //assert profileName.getText().equals("   John Doe");
        Assert.assertEquals(dashboardPage.usernameGetText(), user.getExpectedDashboardUsername());
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

    //Quit WebDriver after each test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
