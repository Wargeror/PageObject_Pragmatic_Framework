package pagetest;

import framework.base.BaseTest;
import framework.pages.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Epic("Dashboard")
@Feature("Dashboard Widgets and Navigation")
public class DashboardTest extends BaseTest {

    @Test(
            testName = "Order 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Orders widget redirects to the Orders page."
    )
    @Story("Orders Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that clicking the 'View More' link on the Total Orders widget redirects to the Orders page.")
    public void orderViewMoreTest() {
        log.info("Logging in and clicking 'View More' on the Orders widget.");
        OrdersPage ordersPage =
                login()
                .clickViewMoreOrders();

        log.info("Asserting redirection to the Orders page.");
        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/orderViewMoreTest: Orders page URL does not contain expected string.");
    }

    @Test(
            testName = "Sales 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Sales widget redirects to the Orders page."
    )
    @Story("Sales Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that clicking the 'View More' link on the Total Sales widget redirects to the Orders page.")
    public void salesViewMoreTest() {
        log.info("Logging in and clicking 'View More' on the Sales widget.");
        OrdersPage ordersPage =
                login()
                .clickViewMoreSales();

        log.info("Asserting redirection to the Orders page.");
        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/SalesViewMoreTest: Sales page URL does not contain expected string.");
    }

    @Test(
            testName = "Customers 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Customers widget redirects to the Customers page."
    )
    @Story("Customers Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that clicking the 'View More' link on the Total Customers widget redirects to the Customers page.")
    public void customersViewMoreTest() {
        log.info("Logging in and clicking 'View More' on the Customers widget.");
        CustomersPage customersPage =
                login()
                .clickViewMoreCustomers();

        log.info("Asserting redirection to the Customers page.");
        Assert.assertTrue(customersPage.urlContains(), "Failure DashboardTest/CustomersViewMoreTest: Customers page URL does not contain expected string.");
    }

    @Test(
            testName = "People Online 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the People Online widget redirects to the Online Report page."
    )
    @Story("People Online Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that clicking the 'View More' link on the People Online widget redirects to the Online Report page.")
    public void peopleOnlineViewMoreTest() {
        log.info("Logging in and clicking 'View More' on the People Online widget.");
        OnlineReportPage onlineReportPage =
                login()
                .clickViewMorePeopleOnline();

        log.info("Asserting redirection to the Online Report page.");
        Assert.assertTrue(onlineReportPage.urlContains(), "Failure DashboardTest/PeopleOnlineViewMoreTest: Online Report page URL does not contain expected string.");
    }

    @Test(
            testName = "World Map Russia Selection Test",
            description = "Verifies that clicking on Russia in the world map widget correctly highlights it."
    )
    @Story("World Map Interaction")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies that clicking on Russia in the world map widget correctly highlights it.")
    public void worldMapRussiaTest(){
        log.info("Logging in and clicking on Russia in the world map.");
        DashboardPage dashboardPage =
                 login()
                .clickWorldMapRussia();

        log.info("Asserting that Russia is highlighted on the map.");
        Assert.assertEquals(dashboardPage.getFillWorldMapRussia(),"#666666", "Failure DashboardTest/WorldMapRussiaTest: World map Russia fill color is not as expected.");
    }

    @Test(
            testName = "Sales Analytics Filter Test",
            description = "Verifies that the time frame filter in the Sales Analytics widget functions correctly."
    )
    @Story("Sales Analytics Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the time frame filter in the Sales Analytics widget functions correctly.")
    public void salesAnaliticsFilterTest() {
        log.info("Logging in to test the Sales Analytics filter.");
        DashboardPage dashboardPage = login();

        List<String> exp_options = Arrays.asList(new String[]{"Today", "Week", "Month","Year"});

        for (int i = 1; i < 4; i++) {
            log.info("Testing filter option: " + exp_options.get(i-1));
            dashboardPage.clicksAlesAnalyticsTimeFrameButton();
            getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(dashboardPage.SalesAnalyticsTimeFrameListSelector(i)))));
            WebElement selected = getDriver().findElement(By.cssSelector(dashboardPage.SalesAnalyticsTimeFrameListSelector(i)));
            Assert.assertEquals(selected.getText(), exp_options.get(i-1), "Failure DashboardTest/SalesAnaliticsFilterTest: Sales analytics time frame option text mismatch for index " + (i-1));
            dashboardPage.clickSalesAnalyticsTimeFrameList(i);
            dashboardPage.invisibilityOfAlesAnalyticsTimeFrameList(i);
        }

        dashboardPage.clicksAlesAnalyticsTimeFrameButton();
        dashboardPage.clickSalesAnalyticsTimeFrameList(4);
    }

    @Test(
            testName = "Latest Order Redirect Test",
            description = "Verifies that clicking on the latest order in the Latest Orders widget redirects to the correct order page."
    )
    @Story("Latest Orders Widget Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that clicking on the latest order in the Latest Orders widget redirects to the correct order page.")
    public void LatestOrderRedirectTest(){
        log.info("Logging in and clicking on the latest order.");
        OrderPage orderPage =
                 login()
                .clickLatestOrder();

        log.info("Asserting redirection to the Order page.");
        Assert.assertTrue(orderPage.urlContains(), "Failure DashboardTest/LatestOrderRedirectTest: Order page URL does not contain expected string.");
    }

}