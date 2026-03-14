package base;

import data.Input;
import data.Slavov;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Input input;
    protected boolean closeDriver = true;

    @BeforeMethod
    public void setUp() {
        System.out.println(Slavov.MS());
        closeDriver = true;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        input = new Input();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null && closeDriver) {
            driver.quit();
        }
    }

    public void login() {
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField(user.getUsername());
        loginPage.typeTextPasswordField(user.getPassword());
        loginPage.clickLoginButton();
    }

    public void printDome(){
        System.out.println(driver.getPageSource());
    }
}
