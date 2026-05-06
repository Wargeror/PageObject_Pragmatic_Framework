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

    //Order "View more..." Redirect
    @Test
    public void orderViewMoreTest() {
        OrdersPage ordersPage =
                login()
                .clickViewMoreOrders();

        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/orderViewMoreTest: Orders page URL does not contain expected string.");
    }

    //Sales "View more..." Redirect
    @Test
    public void SalesViewMoreTest() {
        OrdersPage ordersPage =
                login()
                .clickViewMoreSales();

        Assert.assertTrue(ordersPage.urlContains(), "Failure DashboardTest/SalesViewMoreTest: Sales page URL does not contain expected string.");
    }

    //Customers "View more..." Redirect
    @Test
    public void CustomersViewMoreTest() {
        CustomersPage customersPage =
                login()
                .clickViewMoreCustomers();

        Assert.assertTrue(customersPage.urlContains(), "Failure DashboardTest/CustomersViewMoreTest: Customers page URL does not contain expected string.");
    }

    //People Online "View more..." Redirect
    @Test
    public void PeopleOnlineViewMoreTest() {
        OnlineReportPage onlineReportPage =
                login()
                .clickViewMorePeopleOnline();

        Assert.assertTrue(onlineReportPage.urlContains(), "Failure DashboardTest/PeopleOnlineViewMoreTest: Online Report page URL does not contain expected string.");
    }

    //World Map Russia Select Test
    @Test
    public void WorldMapRussiaTest(){
        DashboardPage dashboardPage =
                 login()
                .clickWorldMapRussia();

        Assert.assertEquals(dashboardPage.getFillWorldMapRussia(),"#666666", "Failure DashboardTest/WorldMapRussiaTest: World map Russia fill color is not as expected.");
    }

    //Sales Analytics Filter Test
    @Test
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

    //Latest Order Redirect Test
    @Test
    public void LatestOrderRedirectTest(){
        OrderPage orderPage =
                 login()
                .clickLatestOrder();

        Assert.assertTrue(orderPage.urlContains(), "Failure DashboardTest/LatestOrderRedirectTest: Order page URL does not contain expected string.");
    }

}
