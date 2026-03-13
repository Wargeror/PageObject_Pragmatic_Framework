package pageTest;

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

    //World Map Russia Select Test
    @Test
    public void WorldMapRussiaTest(){
        login();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickWorldMapRussia();

        Assert.assertEquals(dashboardPage.getFillWorldMapRussia(),"#666666");
    }

    //Sales Analytics Filter Test
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

}