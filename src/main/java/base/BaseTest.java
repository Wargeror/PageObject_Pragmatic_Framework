package base;

import data.Input;
import data.Slavov;
import data.User;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public void takeScreenshot2(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE){
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            
            Path destinationDir = Paths.get("resources/screenshots");
            Path destinationFile = destinationDir.resolve(result.getName() + ".png");

            try {
                if (!Files.exists(destinationDir)) {
                    Files.createDirectories(destinationDir);
                }
                Files.move(screenshot.toPath(), destinationFile);
                System.out.println("The taken screenshot was saved to: " + destinationFile.toAbsolutePath());
            } catch (IOException e){
                e.printStackTrace();
            }
            }
        }
    }

    @AfterMethod(dependsOnMethods = "takeScreenshot2", alwaysRun = true)
    public void tearDown() {
        if (driver != null && closeDriver) {
            driver.quit();
        }
    }

    public DashboardPage login() {
       DashboardPage dashboardPage = loginAs(input.getUser(0));
       return dashboardPage;
    }

    public DashboardPage loginAs(User user) {
        driver.get(user.getSiteURL());
        LoginPage loginPage = new LoginPage(driver, wait);
        DashboardPage dashboardPage = loginPage
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .w8UserNameToBeDisplayed();
        return dashboardPage;
    }

    public void printDome(){
        System.out.println(driver.getPageSource());
    }

    public void printCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Total cookies: " + cookies.size());
        for (Cookie cookie : cookies) {
            System.out.println(cookie.toString());
        }
    }
}
