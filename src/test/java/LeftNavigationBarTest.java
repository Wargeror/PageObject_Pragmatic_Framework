import components.LeftNavigationBar;
import data.Input;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.beans.Visibility;
import java.time.Duration;

public class LeftNavigationBarTest {
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

    //Assert that the Left Navigation Bar is displayed
    @Test
    public void NavBarIsDisplayed(){
        getToDashboard();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        wait.until(ExpectedConditions.visibilityOf(navBar.getDashboardLNavBar()));

        Assert.assertTrue(navBar.getDashboardLNavBar().isDisplayed());
    }

    @Test


    //Quit WebDriver after each test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    //Login method
    public void getToDashboard(){
            User user = input.getUser(0);
            driver.get(user.getSiteURL());

            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.typeTextUsernameField(user.getUsername());
            loginPage.typeTextPasswordField(user.getPassword());
            loginPage.clickLoginButton();
    }

}
