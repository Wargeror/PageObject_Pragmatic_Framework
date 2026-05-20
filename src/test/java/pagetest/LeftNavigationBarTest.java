package pagetest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;
import pages.ProductsPage;

public class LeftNavigationBarTest extends BaseTest {

    @Test(
            testName = "Left Navigation Bar Display Test",
            description = "Asserts that the Left Navigation Bar is displayed after a successful login."
    )
    public void NavBarIsDisplayed(){
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(getDriver(), getWait(), webApp);
        getWait().until(ExpectedConditions.visibilityOf(navBar.getDashboardLNavBar()));

        Assert.assertTrue(navBar.getDashboardLNavBar().isDisplayed(), "Failure LeftNavigationBarTest/NavBarIsDisplayed: Left Navigation Bar is not displayed.");
    }

    @Test(
            testName = "Orders Page Navigation Test",
            description = "Asserts that the Orders Page loads correctly when accessed from the Left Navigation Bar."
    )
    public void orderStatusCheck() {
        OrdersPage ordersPage =
                 login()
                .goToOrdersPage();

        Assert.assertTrue(ordersPage.urlContains(), "Failure LeftNavigationBarTest/orderStatusCheck: Orders page URL does not contain expected string.");
    }

    @Test(
            testName = "Products Page Navigation Test",
            description = "Asserts that the Products Page loads correctly when accessed from the Left Navigation Bar."
    )
    public void addProductTest() throws InterruptedException {
        ProductsPage productsPage =
                 login()
                .goToProdPage();

        Assert.assertTrue(productsPage.urlContains(), "Failure LeftNavigationBarTest/addProductTest: Products page URL does not contain expected string.");
    }
}
