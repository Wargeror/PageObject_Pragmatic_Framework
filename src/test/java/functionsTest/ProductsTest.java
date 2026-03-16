package functionsTest;

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
        mainPage.clickmacBookImg();
        Product4$Page prodPage = new Product4$Page(driver,wait);
        prodPage.clickAddToCart();
        prodPage.clickAlertX();
        prodPage.clickCart();
        prodPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver,wait);
        Assert.assertTrue(checkoutPage.urlContains());
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
