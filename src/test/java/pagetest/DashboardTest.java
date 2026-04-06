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

        Assert.assertTrue(ordersPage.urlContains());
    }

    //Sales "View more..." Redirect
    @Test
    public void SalesViewMoreTest() {
        OrdersPage ordersPage =
                login()
                .clickViewMoreSales();

        Assert.assertTrue(ordersPage.urlContains());
    }

    //Customers "View more..." Redirect
    @Test
    public void CustomersViewMoreTest() {
        CustomersPage customersPage =
                login()
                .clickViewMoreCustomers();

        Assert.assertTrue(customersPage.urlContains());
    }

    //People Online "View more..." Redirect
    @Test
    public void PeopleOnlineViewMoreTest() {
        OnlineReportPage onlineReportPage =
                login()
                .clickViewMorePeopleOnline();

        Assert.assertTrue(onlineReportPage.urlContains());
    }

    //World Map Russia Select Test
    @Test
    public void WorldMapRussiaTest(){
        DashboardPage dashboardPage =
                 login()
                .clickWorldMapRussia();

        Assert.assertEquals(dashboardPage.getFillWorldMapRussia(),"#666666");
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
            Assert.assertEquals(selected.getText(), exp_options.get(i-1));
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

        Assert.assertTrue(orderPage.urlContains());
    }

}