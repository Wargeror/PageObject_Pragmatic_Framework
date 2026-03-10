import data.Input;
import data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class DashboardTest {
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

    //Order "View more..." Redirect
    @Test
    public void orderViewMoreTest() {
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickViewMoreOrders();

        OrdersPage ordersPage = new OrdersPage(driver,wait);
        Assert.assertTrue(ordersPage.urlContains());
    }

    //Sales "View more..." Redirect
    @Test
    public void SalesViewMoreTest() {
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickViewMoreSales();

        OrdersPage ordersPage = new OrdersPage(driver,wait);
        Assert.assertTrue(ordersPage.urlContains());
    }

    //Customers "View more..." Redirect
    @Test
    public void CustomersViewMoreTest() {
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickViewMoreCustomers();

        CustomersPage customersPage = new CustomersPage(driver,wait);
        Assert.assertTrue(customersPage.urlContains());
    }

    //People Online "View more..." Redirect
    @Test
    public void PeopleOnlineViewMoreTest() {
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickViewMorePeopleOnline();

        OnlineReportPage onlineReportPage = new OnlineReportPage(driver,wait);
        Assert.assertTrue(onlineReportPage.urlContains());
    }


    @Test
    public void SalesAnaliticsFilterTest() {
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        List<String> exp_options = Arrays.asList(new String[]{"Today", "Week", "Month","Year"});

        for (int i = 1; i < 4; i++) {
            dashboardPage.clicksalesAnalyticsTimeFrameButton();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(dashboardPage.SalesAnalyticsTimeFrameListSelector(i)))));
            WebElement selected = driver.findElement(By.cssSelector(dashboardPage.SalesAnalyticsTimeFrameListSelector(i)));
            Assert.assertEquals(selected.getText(), exp_options.get(i-1));
            dashboardPage.clickSalesAnalyticsTimeFrameList(i);
            dashboardPage.invisibilityOfalesAnalyticsTimeFrameList(i);
        }

        dashboardPage.clicksalesAnalyticsTimeFrameButton();
        dashboardPage.clickSalesAnalyticsTimeFrameList(4);


}

    //Latest Order Redirect Test
    @Test
    public void LatestOrderRedirectTest(){
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickLatestOrder();

        OrderPage orderPage = new OrderPage(driver,wait);
        Assert.assertTrue(orderPage.urlContains());
    }

    //Quit WebDriver after each test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    //Login method
    public void login(){
        User user = input.getUser(0);
        driver.get(user.getSiteURL());

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.typeTextUsernameField(user.getUsername());
        loginPage.typeTextPasswordField(user.getPassword());
        loginPage.clickLoginButton();
    }


}

////Get the Dropdown as a Select using it's name attribute
//Select timeFrame = new Select(dashboardPage.salesAnalyticsTimeFrameList);
//
////Verify Dropdown does not support multiple selection
//assertFalse(timeFrame.isMultiple());
//
////Verify Dropdown has four options for selection
//assertEquals(timeFrame.getOptions().size(), 4);
//
////We will verify Dropdown has expected values as listed in our expected options array
//
////Expected Options
//List<String> exp_options = Arrays.asList(new String[]{"Today", "Week", "Month","Year"});
//
////List that we will populate with the Actual Options
//List<String> act_options = new ArrayList<String>();
//List<WebElement> allOptions = timeFrame.getOptions();
//
////Retrieve the option values from Dropdown using getOptions() method
//        for(WebElement option : allOptions) {
//        act_options.add(option.getText());
//        }
//
////Verify expected options array and actual options array match
//assertEquals(act_options.toArray(), exp_options.toArray()); // both are the same
//
//assertEquals(act_options, exp_options); // both are the same
//
////        timeFrame.selectByVisibleText("Today");
////        assertEquals(timeFrame.getFirstSelectedOption().getText(), "Today");
//
////We are using a For loop, using the index to select the options of timeFrame and compare them to the expected values of exp_options.
//        for (int i = 0; i < exp_options.size(); i++) {
//        timeFrame.selectByIndex(i);
//assertEquals(timeFrame.getFirstSelectedOption().getText(), exp_options.get(i));
//        }
//
//        //Selecting the last option using index
//        timeFrame.selectByIndex(3);
//assertEquals(timeFrame.getFirstSelectedOption().getText(), "Year");