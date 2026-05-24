package framework.base;

import framework.data.Input;
import framework.data.User;
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
import framework.pages.DashboardPage;
import framework.pages.LoginPage;
import framework.pages.AccountPage;
import framework.pages.MainPage;
import framework.pages.CustomerLoginPage;
import framework.utils.LoginManager;

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

        // Check for headless property
        String headless = System.getProperty("headless");
        if (headless != null && headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new"); // Use the new headless mode
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080"); // Important for headless
            log.info("Running browser in headless mode.");
        }

        WebDriver localDriver = new ChromeDriver(options);
        if (headless == null || !headless.equalsIgnoreCase("true")) {
            localDriver.manage().window().maximize(); // Maximize if not headless
        }

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

                Path destinationDir = Paths.get("test/resources/screenshots");
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
        //LoginManager.clearSessionCookiesForCurrentThread();
        WebDriver currentDriver = getDriver();
        if (currentDriver != null && closeDriver) {
            currentDriver.quit();
            driver.remove();
            wait.remove();
        }
    }

    public DashboardPage adminLogin() {
        DashboardPage dashboardPage = adminLoginAs(input.getUser(0));
        return dashboardPage;
    }

    public DashboardPage cookieLogin(){
        return LoginManager.loginWithCookieOrCredentials(getDriver(), webApp, input.getUser(0));
    }

    public DashboardPage adminLoginAs(User user) {
        log.info("Logging in as admin user: " + user.getUsername());
        getDriver().get(user.getSiteURL());
        LoginPage loginPage = webApp.loginPage();
        DashboardPage dashboardPage = loginPage
                .typeTextUsernameField(user.getUsername())
                .typeTextPasswordField(user.getPassword())
                .clickLoginButton()
                .waitUserNameToBeDisplayed();
        log.info("Admin login successful.");
        return dashboardPage;
    }

    public AccountPage customerLogin() {
        return customerLoginAs(input.getCustomer(0));
    }

    public AccountPage customerLoginAs(User customer) {
        log.info("Logging in as customer: " + customer.getUsername());
        getDriver().get(customer.getSiteURL()); // This should be the main URL

        MainPage mainPage = webApp.mainPage();
        CustomerLoginPage loginPage = mainPage.highBar.clickLoginButton();

        AccountPage accountPage = loginPage
                .typeTextEmailField(customer.getUsername())
                .typeTextPasswordField(customer.getPassword())
                .clickLoginButton();

        log.info("Customer login successful.");
        return accountPage;
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