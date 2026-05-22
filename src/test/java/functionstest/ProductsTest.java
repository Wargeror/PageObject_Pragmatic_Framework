package functionstest;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

@Epic("E-commerce")
@Feature("Product Management and Purchasing")
public class ProductsTest extends BaseTest {

    @Test(
            testName = "Checkout Navigation Test",
            description = "Tests the end-to-end flow of adding a product to the cart and navigating to the checkout page."
    )
    @Story("Product Checkout Flow")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the end-to-end flow of adding a product to the cart and navigating to the checkout page.")
    public void checkoutTest(){
        log.info("Starting checkout navigation test.");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());
        CheckoutPage checkoutPage =
                mainPage
                .orderAndGoCheckout();

        log.info("Asserting that the checkout page URL is correct.");
        Assert.assertTrue(checkoutPage.urlContains(), "Failure ProductsTest/checkoutTest: Checkout page URL does not contain expected string.");
    }

    @Test(
            testName = "Buy Product End-to-End Test",
            description = "Tests the complete process of buying a product, from adding it to the cart to successful checkout."
    )
    @Story("Product Purchase")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Tests the complete process of buying a product, from adding it to the cart to successful checkout.")
    public void buyProductTest() throws InterruptedException {
        log.info("Starting buy product end-to-end test.");
        Product4$Page product4$Page = webApp.product4$Page();
        getDriver().get(product4$Page.getCustomDesktop());

        CheckoutPage checkoutPage =
                product4$Page
                .addAndGoCheckout();

        log.info("Asserting that the checkout page URL is correct after adding product.");
        Assert.assertTrue(checkoutPage.urlContains(), "Failure ProductsTest/buyProductTest: Checkout page URL does not contain expected string after adding product.");

       SuccessfulCheckout successfulCheckout =
                checkoutPage
                .fillCheckoutForm();

        log.info("Asserting that the successful checkout page URL and H1 are correct.");
        Assert.assertTrue(successfulCheckout.urlContains(), "Failure ProductsTest/buyProductTest: Successful checkout page URL does not contain expected string.");
        Assert.assertTrue(successfulCheckout.isH1Displayed(), "Failure ProductsTest/buyProductTest: H1 element is not displayed on successful checkout page.");

    }

    @Test(
            testName = "Increase Product Quantity in Cart",
            description = "Verifies that the quantity of a product in the cart can be successfully increased."
    )
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the quantity of a product in the cart can be successfully increased.")
    public void increaseQuantityTest() {
        log.info("Starting increase product quantity in cart test.");
        Product4$Page product4$Page = webApp.product4$Page();
        getDriver().get(product4$Page.getCustomDesktop());

        CartPage cartPage =
                product4$Page
                .addAndGoCart();

        cartPage
                .updateQuantity("2");

        log.info("Asserting that the cart quantity was updated to '2'.");
        Assert.assertEquals(cartPage.getQuantityValue(), "2", "Failure ProductsTest/increaseQuantityTest: Cart quantity value is not '2' after update.");

    }

    @Test(
            testName = "Add and Delete Product",
            description = "Tests the full lifecycle of a product: adding a new product, verifying its existence, and then deleting it."
    )
    @Story("Product Lifecycle")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the full lifecycle of a product: adding a new product, verifying its existence, and then deleting it.")
    public void addProductTest() throws InterruptedException {
        log.info("Starting add and delete product test.");
        ProductsFormPage productsFormPage =
                login()
                .goToProdPage()
                .clickAddNewButton()
                .fillProductForm();

        log.info("Asserting that the success alert is displayed after adding the product.");
        Assert.assertTrue(productsFormPage.isAlertSuccessDisplayed(), "Failure ProductsTest/addProductTest: Success alert was not displayed after adding product.");

        ProductsPage productsPage =
                productsFormPage
                .clickAlertX()
                .navBar.clickProducts();

        log.info("Asserting that the new product exists in the product list.");
        Assert.assertTrue(productsPage.newProductExists(), "Failure ProductsTest/addProductTest: Newly added product does not exist in the products list.");

        log.info("Deleting the newly added product.");
        productsPage
                .deleteProduct();
    }

    @Test(
            testName = "Negative: Add Product with Invalid Data",
            description = "Verifies that a warning alert is displayed when attempting to add a product with invalid or incomplete data."
    )
    @Story("Product Lifecycle")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that a warning alert is displayed when attempting to add a product with invalid or incomplete data.")
    public void addProductAlertTest() throws InterruptedException {
        log.info("Starting negative test for adding a product with invalid data.");
        ProductsFormPage productsFormPage =
                login()
                .goToProdPage()
                .clickAddNewButton()
                .clickNotfCancelButton()
                .clickSaveButton();

        log.info("Asserting that the warning alert is displayed.");
        Assert.assertTrue(productsFormPage.isAlertWarningDisplayed(), "Failure ProductsTest/addProductAlertTest: Warning alert was not displayed for invalid product submission.");
    }

}