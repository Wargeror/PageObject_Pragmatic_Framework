package pagetest;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.List;

public class DashboardTest extends BaseTest {

    @Test(
            testName = "Order 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Orders widget redirects to the Orders page."
    )
    public void orderViewMoreTest() {
        OrdersPage ordersPage =
                login()
                .clickViewMoreOrders();

        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/orderViewMoreTest: Orders page URL does not contain expected string.");
    }

    @Test(
            testName = "Sales 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Sales widget redirects to the Orders page."
    )
    public void SalesViewMoreTest() {
        OrdersPage ordersPage =
                login()
                .clickViewMoreSales();

        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/SalesViewMoreTest: Sales page URL does not contain expected string.");
    }

    @Test(
            testName = "Customers 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the Total Customers widget redirects to the Customers page."
    )
    public void CustomersViewMoreTest() {
        CustomersPage customersPage =
                login()
                .clickViewMoreCustomers();

        Assert.assertTrue(customersPage.urlContains(), "Failure DashboardTest/CustomersViewMoreTest: Customers page URL does not contain expected string.");
    }

    @Test(
            testName = "People Online 'View More' Redirect Test",
            description = "Verifies that clicking the 'View More' link on the People Online widget redirects to the Online Report page."
    )
    public void PeopleOnlineViewMoreTest() {
        OnlineReportPage onlineReportPage =
                login()
                .clickViewMorePeopleOnline();

        Assert.assertTrue(onlineReportPage.urlContains(), "Failure DashboardTest/PeopleOnlineViewMoreTest: Online Report page URL does not contain expected string.");
    }

    @Test(
            testName = "World Map Russia Selection Test",
            description = "Verifies that clicking on Russia in the world map widget correctly highlights it."
    )
    public void WorldMapRussiaTest(){
        DashboardPage dashboardPage =
                 login()
                .clickWorldMapRussia();

        Assert.assertEquals(dashboardPage.getFillWorldMapRussia(),"#666666", "Failure DashboardTest/WorldMapRussiaTest: World map Russia fill color is not as expected.");
    }

    @Test(
            testName = "Sales Analytics Filter Test",
            description = "Verifies that the time frame filter in the Sales Analytics widget functions correctly."
    )
    public void SalesAnaliticsFilterTest() {

        DashboardPage dashboardPage = login();


        List<String> exp_options = Arrays.asList(new String[]{"Today", "Week", "Month","Year"});

        for (int i = 1; i < 4; i++) {
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
    public void LatestOrderRedirectTest(){
        OrderPage orderPage =
                 login()
                .clickLatestOrder();

        Assert.assertTrue(orderPage.urlContains(), "Failure DashboardTest/LatestOrderRedirectTest: Order page URL does not contain expected string.");
    }

}
