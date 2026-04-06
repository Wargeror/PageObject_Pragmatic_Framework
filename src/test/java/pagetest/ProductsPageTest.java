package pagetest;

import base.BaseTest;
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
        ProductsPage prodPage =
                goTo()
                .typeProductName("Custom-Built Desktop PC")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());


    }

    //Asserts if the Product Model Filter Works
    @Test
    public void productModelFilterTest(){
        ProductsPage prodPage =
                goTo()
                .typeProductModel("Custom-built 01")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());
    }

    //Asserts if the Product Price Filter Works
    @Test
    public void productPriceFilterTest(){
        ProductsPage prodPage =
                goTo()
                .typeProductPrice("300")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists());

    }

    public ProductsPage goTo(){
        login();

        ProductsPage productsPage = new ProductsPage(getDriver(), getWait());
        getDriver().get(productsPage.newUrl());
        return new ProductsPage(getDriver(), getWait());
    }
}
