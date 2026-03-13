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
        productsPage.clickProductUrl();

        ProductsFormPage productsFormPage = new ProductsFormPage(driver,wait);
        productsFormPage.typeProductName("Custom-Built Desktop PC");
        productsFormPage.clickNotfCancelButton();
        driver.switchTo().frame(productsFormPage.getiFrameDescription());
        productsFormPage.typeDescription(productsFormPage.getDescription());
        driver.switchTo().defaultContent();
        productsFormPage.typeMetaTitle("Meta Title");
        productsFormPage.typeProductTag("Desktop PC");
        productsFormPage.topBar.scrollToTop();
        productsFormPage.clickDataField();
        productsFormPage.typeModel("Custom-built 01");
        productsFormPage.scrollToTaxClassSelect();
        productsFormPage.typePrice("300");
        Select orderStatus = new Select(productsFormPage.getTaxClassSelect());
        orderStatus.selectByVisibleText("Taxable Goods");
        productsFormPage.topBar.scrollToTop();
        productsFormPage.clickImgForm();
        productsFormPage.clickEditImg();
        productsFormPage.typeImgSearchField("MomchilPCImg");
        productsFormPage.clickImgSearchButton();
        productsFormPage.w84MomchilPCImgVisibility();
        Thread.sleep(1000); // Wait for the image to be fully loaded/ready
        productsFormPage.clickMomchilPCImg();
        productsFormPage.clickSeoForm();
        productsFormPage.typeSeoWord("custom-PC");
        productsFormPage.clickSaveButton();
    }

}
