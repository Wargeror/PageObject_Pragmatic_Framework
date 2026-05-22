package pagetest;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;
import pages.ProductsPage;

@Epic("Navigation")
@Feature("Left Navigation Bar")
public class LeftNavigationBarTest extends BaseTest {

    @Test(
            testName = "Left Navigation Bar Display Test",
            description = "Asserts that the Left Navigation Bar is displayed after a successful login."
    )
    @Story("Visibility of Navigation Elements")
    @Severity(SeverityLevel.CRITICAL)
    public void NavBarIsDisplayed(){
        log.info("Logging in to verify Left Navigation Bar display.");
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(getDriver(), getWait(), webApp);
        getWait().until(ExpectedConditions.visibilityOf(navBar.getDashboardLNavBar()));

        log.info("Asserting that the Left Navigation Bar is displayed.");
        Assert.assertTrue(navBar.getDashboardLNavBar().isDisplayed(), "Failure LeftNavigationBarTest/NavBarIsDisplayed: Left Navigation Bar is not displayed.");
    }

    @Test(
            testName = "Orders Page Navigation Test",
            description = "Asserts that the Orders Page loads correctly when accessed from the Left Navigation Bar."
    )
    @Story("Navigation to Orders Page")
    @Severity(SeverityLevel.NORMAL)
    public void orderStatusCheck() {
        log.info("Logging in and navigating to the Orders page.");
        OrdersPage ordersPage =
                 login()
                .goToOrdersPage();

        log.info("Asserting that the Orders page URL is correct.");
        Assert.assertTrue(ordersPage.urlContains(), "Failure LeftNavigationBarTest/orderStatusCheck: Orders page URL does not contain expected string.");
    }

    @Test(
            testName = "Products Page Navigation Test",
            description = "Asserts that the Products Page loads correctly when accessed from the Left Navigation Bar."
    )
    @Story("Navigation to Products Page")
    @Severity(SeverityLevel.NORMAL)
    public void addProductTest() throws InterruptedException {
        log.info("Logging in and navigating to the Products page.");
        ProductsPage productsPage =
                 login()
                .goToProdPage();

        log.info("Asserting that the Products page URL is correct.");
        Assert.assertTrue(productsPage.urlContains(), "Failure LeftNavigationBarTest/addProductTest: Products page URL does not contain expected string.");
    }
}
