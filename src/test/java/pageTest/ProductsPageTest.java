package pageTest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class ProductsPageTest extends BaseTest {

    //Asserts if the main products page element load
    @Test
    public void productsPageTest(){
        ProductsPage prodPage = goTo();
        Assert.assertTrue(prodPage.isProductFormDisplayed());
        Assert.assertTrue(prodPage.isFilterFormDisplayed());
    }

    //Asserts if the Product Name Filter Works
    @Test
    public void productNameFilterTest(){
        ProductsPage prodPage = goTo();

        prodPage.typeProductName("Custom-Built Desktop PC");
        prodPage.clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());


    }

    //Asserts if the Product Model Filter Works
    @Test
    public void productModelFilterTest(){
        ProductsPage prodPage = goTo();

        prodPage.typeProductModel("Custom-built 01");
        prodPage.clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());


    }

    //Asserts if the Product Price Filter Works
    @Test
    public void productPriceFilterTest(){
        ProductsPage prodPage = goTo();

        prodPage.typeProductPrice("300");
        prodPage.clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());


    }

    public ProductsPage goTo(){
        login();

        ProductsPage productsPage = new ProductsPage(driver,wait);
        driver.get(productsPage.newUrl());
        return new ProductsPage(driver, wait);
    }
}
