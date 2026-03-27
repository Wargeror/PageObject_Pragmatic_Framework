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
        MainPage mainPage = new MainPage(driver,wait);
        driver.get(mainPage.mainUrl());
        CheckoutPage checkoutPage =
                mainPage
                .orderAndGoCheckout();

        Assert.assertTrue(checkoutPage.urlContains());
    }

    //Test for buying a product
    @Test
    public void buyProductTest() throws InterruptedException {
        Product4$Page product4$Page = new Product4$Page(driver, wait);
        driver.get(product4$Page.getCustomDesktop());

        CheckoutPage checkoutPage =
                product4$Page
                .addAndGoCheckout();

        Assert.assertTrue(checkoutPage.urlContains());

       SuccessfulCheckout successfulCheckout =
                checkoutPage
                .fillCheckoutForm();

        Assert.assertTrue(successfulCheckout.urlContains());
        Assert.assertTrue(successfulCheckout.isH1Displayed());

    }

    //Increase the quantity in the cart test
    @Test
    public void increaseQuantityTest() {
        Product4$Page product4$Page = new Product4$Page(driver, wait);
        driver.get(product4$Page.getCustomDesktop());

        CartPage cartPage =
                product4$Page
                .addAndGoCart();

        cartPage
                .updateQuantity("2");

        Assert.assertEquals(cartPage.getQuantityValue(), "2");

    }

    //Adding a Product
    @Test
    public void addProductTest() throws InterruptedException {
        ProductsFormPage productsFormPage =
                login()
                .goToProdPage()
                .clickAddNewButton()
                .fillProductForm();

        Assert.assertTrue(productsFormPage.isAlertSuccessDisplayed());

        ProductsPage productsPage =
                productsFormPage
                .clickAlertX()
                .navBar.clickProducts();

        Assert.assertTrue(productsPage.newProductExists());

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

        Assert.assertTrue(productsFormPage.isAlertWarningDisplayed());
    }

}
