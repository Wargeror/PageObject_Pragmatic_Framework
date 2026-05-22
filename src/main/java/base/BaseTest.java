package base;

import data.Input;
import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import utils.LoginManager;

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
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    protected Input input;
    protected boolean closeDriver;
    protected WebApp webApp;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp(ITestResult result) {
        log.info("----- Starting test: " + result.getMethod().getMethodName() + " -----");
        closeDriver = true;

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();
        driver.set(localDriver);
        
        wait.set(new WebDriverWait(localDriver, Duration.ofSeconds(10)));
        input = new Input();
        webApp = new WebApp(getDriver(), getWait());
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }

    @AfterMethod
    public void takeScreenshot2(ITestResult result) {
        WebDriver currentDriver = getDriver();
        if (currentDriver != null) {
            if (result.getStatus() == ITestResult.FAILURE){
                log.error("!!! Test failed: " + result.getMethod().getMethodName() + " !!!");
                var camera = (TakesScreenshot) currentDriver;
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                
                Path destinationDir = Paths.get("resources/screenshots");
                Path destinationFile = destinationDir.resolve(result.getName() + ".png");

                try {
                    if (!Files.exists(destinationDir)) {
                        Files.createDirectories(destinationDir);
                    }
                    Files.move(screenshot.toPath(), destinationFile);
                    log.info("Screenshot saved to: " + destinationFile.toAbsolutePath());
                } catch (IOException e){
                    log.error("Failed to save screenshot.", e);
                }
            }
        }
    }

    @AfterMethod(dependsOnMethods = "takeScreenshot2", alwaysRun = true)
    public void tearDown(ITestResult result) {
        log.info("----- Finished test: " + result.getMethod().getMethodName() + " -----");
        LoginManager.clearSessionCookieForCurrentThread();
        WebDriver currentDriver = getDriver();
        if (currentDriver != null && closeDriver) {
            currentDriver.quit();
            driver.remove();
            wait.remove();
        }
    }

    public DashboardPage login() {
        DashboardPage dashboardPage = loginAs(input.getUser(0));
        return dashboardPage;
    }

    public DashboardPage cookieLogin(){
        return LoginManager.loginWithCookieOrCredentials(getDriver(), webApp, input.getUser(0));
    }

    public DashboardPage loginAs(User user) {
        log.info("Logging in as user: " + user.getUsername());
        getDriver().get(user.getSiteURL());
        LoginPage loginPage = webApp.loginPage();
        DashboardPage dashboardPage = loginPage
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .waitUserNameToBeDisplayed();
        log.info("Login successful.");
        return dashboardPage;
    }

    public void printDome(){
        System.out.println(getDriver().getPageSource());
    }

    public Set<Cookie> printCookies() {
        Set<Cookie> cookies = getDriver().manage().getCookies();
        log.debug("Total cookies: " + cookies.size());
        for (Cookie cookie : cookies) {
            log.debug(cookie.toString());
        }
        return cookies;
    }

    public boolean isCookiePresent(Set<Cookie> cookies, String cookieName){
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return true;
            }
        }
        return false;
    }

    
    public Cookie getCookieByName(Set<Cookie> cookies, String cookieName) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }


    public void injectCookie(Cookie cookie) {
        getDriver().manage().addCookie(cookie);
    }
}