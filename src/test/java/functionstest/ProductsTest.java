package functionstest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.CustomRandomStringGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class ProductsTest extends BaseTest {

    //Test adding product and going to checkout page
    @Test
    public void checkoutTest(){
        MainPage mainPage = new MainPage(driver,wait);
        driver.get(mainPage.mainUrl());
        mainPage.clickMacBookImg();
        Product4$Page product4$Page = new Product4$Page(driver,wait);
        product4$Page.clickAddToCart();
        product4$Page.clickAlertX();
        product4$Page.clickCart();
        product4$Page.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver,wait);
        Assert.assertTrue(checkoutPage.urlContains());
    }

    //Test for buying a product
    @Test
    public void buyProductTest() throws InterruptedException {
        Product4$Page product4$Page = new Product4$Page(driver, wait);
        driver.get(product4$Page.getCustomDesktop());

        product4$Page.clickAddToCart();
        product4$Page.clickAlertX();
        product4$Page.clickCart();
        product4$Page.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        Assert.assertTrue(checkoutPage.urlContains());
        checkoutPage.clickGuessRadioButton();
        checkoutPage.typeFirstNameField(CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)));
        checkoutPage.typeLastNameField(CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)));
        checkoutPage.typeEmailField(CustomRandomStringGenerator.generateEmail());
        checkoutPage.typeAddressField(CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)));
        checkoutPage.typeCityField(CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)));
        checkoutPage.typeZipCodeField(CustomRandomStringGenerator.randomNumeric(4));
        checkoutPage.scrollToRegisterButton();
        checkoutPage.selectCountrySelect("United Kingdom");
        checkoutPage.selectRegionSelect("Norfolk");
        checkoutPage.clickRegisterButton();
        checkoutPage.clickAlertX();
        checkoutPage.clickShippingMethodButton();
        Thread.sleep(400);
        checkoutPage.clickFlatShippingRadioButton();
        checkoutPage.clickShippingEndButton();
        checkoutPage.clickPaymentMethodButton();
        Thread.sleep(400);
        checkoutPage.clickCashRadioButton();
        checkoutPage.clickPaymentEndButton();
        checkoutPage.clickConfirmButton();

        SuccessfulCheckout successfulCheckout = new SuccessfulCheckout(driver,wait);
        successfulCheckout.w8ForH1();
        Assert.assertTrue(successfulCheckout.urlContains());
        Assert.assertTrue(successfulCheckout.isH1Displayed());

    }

    //Increase the quantity in the cart test
    @Test
    public void increaseQuantityTest() {
        Product4$Page product4$Page = new Product4$Page(driver, wait);

        //Needs to be finished

    }

    //Adding a Product
    @Test
    public void addProductTest() throws InterruptedException {
        login();
        closeDriver = false;
        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        navBar.clickMenuCatalog();
        navBar.clickProducts();

        ProductsPage productsPage = new ProductsPage(driver,wait);
        productsPage.clickAddNewButton();

        ProductsFormPage productsFormPage = new ProductsFormPage(driver,wait);
        productsFormPage.typeProductName("Custom-Built Desktop PC1");
        productsFormPage.clickNotfCancelButton();
        productsFormPage.typeDescription(productsFormPage.getDescription());
        productsFormPage.typeMetaTitle("Custom PC");
        productsFormPage.typeProductTag("Desktop PC");
        productsFormPage.topBar.scrollToTop();
        productsFormPage.clickDataField();
        productsFormPage.typeModel("Custom-built 01");
        productsFormPage.scrollToTaxClassSelect();
        productsFormPage.typePrice("300");
        productsFormPage.scrollToMinQuant();
        productsFormPage.typeQuantity("200000");
        Select orderStatus = new Select(productsFormPage.getTaxClassSelect());
        orderStatus.selectByVisibleText("Taxable Goods");
        productsFormPage.topBar.scrollToTop();
        productsFormPage.clickImgForm();
        productsFormPage.clickEditImg();
        productsFormPage.typeImgSearchField("MomchilPCImg");
        productsFormPage.clickImgSearchButton();
        productsFormPage.w84MomchilPCImgVisibility();
        Thread.sleep(400); // Wait for the image to be fully loaded/ready
        productsFormPage.clickMomchilPCImg();
        productsFormPage.clickSeoForm();
        productsFormPage.typeSeoWord("custom-PC2");
        productsFormPage.clickSaveButton();
        Assert.assertTrue(productsFormPage.isAlertSuccessDisplayed());
        productsFormPage.clickAlertX();
        navBar.clickProducts();
        Assert.assertTrue(productsPage.newProductExists());
        productsPage.selectNewProduct(true);
        productsPage.clickDeleteButton();
        productsFormPage.clickAlert(true);
    }

    //Negative Test For Adding A Product
    @Test
    public void addProductAlertTest() throws InterruptedException {
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        navBar.clickMenuCatalog();
        navBar.clickProducts();

        ProductsPage productsPage = new ProductsPage(driver,wait);
        productsPage.clickAddNewButton();

        ProductsFormPage productsFormPage = new ProductsFormPage(driver,wait);
        productsFormPage.clickNotfCancelButton();
        productsFormPage.clickSaveButton();
        Assert.assertTrue(productsFormPage.isAlertWarningDisplayed());
    }

}
