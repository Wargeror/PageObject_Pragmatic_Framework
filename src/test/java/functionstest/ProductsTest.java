package functionstest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ProductsTest extends BaseTest {

    @Test(
            testName = "Checkout Navigation Test",
            description = "Tests the end-to-end flow of adding a product to the cart and navigating to the checkout page."
    )
    public void checkoutTest(){
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());
        CheckoutPage checkoutPage =
                mainPage
                .orderAndGoCheckout();

        Assert.assertTrue(checkoutPage.urlContains(), "Failure ProductsTest/checkoutTest: Checkout page URL does not contain expected string.");
    }

    @Test(
            testName = "Buy Product End-to-End Test",
            description = "Tests the complete process of buying a product, from adding it to the cart to successful checkout."
    )
    public void buyProductTest() throws InterruptedException {
        Product4$Page product4$Page = webApp.product4$Page();
        getDriver().get(product4$Page.getCustomDesktop());

        CheckoutPage checkoutPage =
                product4$Page
                .addAndGoCheckout();

        Assert.assertTrue(checkoutPage.urlContains(), "Failure ProductsTest/buyProductTest: Checkout page URL does not contain expected string after adding product.");

       SuccessfulCheckout successfulCheckout =
                checkoutPage
                .fillCheckoutForm();

        Assert.assertTrue(successfulCheckout.urlContains(), "Failure ProductsTest/buyProductTest: Successful checkout page URL does not contain expected string.");
        Assert.assertTrue(successfulCheckout.isH1Displayed(), "Failure ProductsTest/buyProductTest: H1 element is not displayed on successful checkout page.");

    }

    @Test(
            testName = "Increase Product Quantity in Cart",
            description = "Verifies that the quantity of a product in the cart can be successfully increased."
    )
    public void increaseQuantityTest() {
        Product4$Page product4$Page = webApp.product4$Page();
        getDriver().get(product4$Page.getCustomDesktop());

        CartPage cartPage =
                product4$Page
                .addAndGoCart();

        cartPage
                .updateQuantity("2");

        Assert.assertEquals(cartPage.getQuantityValue(), "2", "Failure ProductsTest/increaseQuantityTest: Cart quantity value is not '2' after update.");

    }

    @Test(
            testName = "Add and Delete Product",
            description = "Tests the full lifecycle of a product: adding a new product, verifying its existence, and then deleting it."
    )
    public void addProductTest() throws InterruptedException {
        ProductsFormPage productsFormPage =
                login()
                .goToProdPage()
                .clickAddNewButton()
                .fillProductForm();

        Assert.assertTrue(productsFormPage.isAlertSuccessDisplayed(), "Failure ProductsTest/addProductTest: Success alert was not displayed after adding product.");

        ProductsPage productsPage =
                productsFormPage
                .clickAlertX()
                .navBar.clickProducts();

        Assert.assertTrue(productsPage.newProductExists(), "Failure ProductsTest/addProductTest: Newly added product does not exist in the products list.");

        productsPage
                .deleteProduct();
    }

    @Test(
            testName = "Negative: Add Product with Invalid Data",
            description = "Verifies that a warning alert is displayed when attempting to add a product with invalid or incomplete data."
    )
    public void addProductAlertTest() throws InterruptedException {
        ProductsFormPage productsFormPage =
                login()
                .goToProdPage()
                .clickAddNewButton()
                .clickNotfCancelButton()
                .clickSaveButton();

        Assert.assertTrue(productsFormPage.isAlertWarningDisplayed(), "Failure ProductsTest/addProductAlertTest: Warning alert was not displayed for invalid product submission.");
    }

}
