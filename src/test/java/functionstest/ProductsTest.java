package functionstest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ProductsTest extends BaseTest {

    //Test adding product and going to checkout page
    @Test
    public void checkoutTest(){
        MainPage mainPage = new MainPage(getDriver(), getWait());
        getDriver().get(mainPage.mainUrl());
        CheckoutPage checkoutPage =
                mainPage
                .orderAndGoCheckout();

        Assert.assertTrue(checkoutPage.urlContains(), "Failure ProductsTest/checkoutTest: Checkout page URL does not contain expected string.");
    }

    //Test for buying a product
    @Test
    public void buyProductTest() throws InterruptedException {
        Product4$Page product4$Page = new Product4$Page(getDriver(), getWait());
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

    //Increase the quantity in the cart test
    @Test
    public void increaseQuantityTest() {
        Product4$Page product4$Page = new Product4$Page(getDriver(), getWait());
        getDriver().get(product4$Page.getCustomDesktop());

        CartPage cartPage =
                product4$Page
                .addAndGoCart();

        cartPage
                .updateQuantity("2");

        Assert.assertEquals(cartPage.getQuantityValue(), "2", "Failure ProductsTest/increaseQuantityTest: Cart quantity value is not '2' after update.");

    }

    //Adding a Product
    @Test
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

    //Negative Test For Adding A Product
    @Test
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
