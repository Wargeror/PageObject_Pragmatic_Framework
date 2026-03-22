package base;

import data.Input;
import data.Slavov;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Input input;
    protected boolean closeDriver;

    @BeforeMethod
    public void setUp() {
        System.out.println(Slavov.MS());
        closeDriver = true;

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
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
        loginAs(input.getUser(0)); // Default login
    }

    public void loginAs(User user) {
        driver.get(user.getSiteURL());
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField(user.getUsername());
        loginPage.typeTextPasswordField(user.getPassword());
        loginPage.clickLoginButton();

        DashboardPage dashPage = new DashboardPage(driver,wait);
        dashPage.leftNavigationBar.w8NavBarToBeDisplayed();
    }

    public void printDome(){
        System.out.println(driver.getPageSource());
    }
}
