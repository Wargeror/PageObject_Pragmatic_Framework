package pagetest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;
import pages.ProductsPage;

public class LeftNavigationBarTest extends BaseTest {

    //Assert that the Left Navigation Bar is displayed
    @Test
    public void NavBarIsDisplayed(){
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        wait.until(ExpectedConditions.visibilityOf(navBar.getDashboardLNavBar()));

        Assert.assertTrue(navBar.getDashboardLNavBar().isDisplayed());
    }

    //Assert Orders Page Loads From Left Navigation Bar
    @Test
    public void orderStatusCheck() {
        OrdersPage ordersPage =
                 login()
                .leftNavigationBar.clickMenuSales()
                                  .clickOrders();

        Assert.assertTrue(ordersPage.urlContains());
    }

    //Asserts that Products Page Loads From Left Navigation Bar
    @Test
    public void addProductTest() throws InterruptedException {
        ProductsPage productsPage =
                 login()
                .leftNavigationBar.clickMenuCatalog()
                                  .clickProducts();

        Assert.assertTrue(productsPage.urlContains());
    }
}
