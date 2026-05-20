package pagetest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class ProductsPageTest extends BaseTest {

    @Test(
            testName = "Products Page Element Display Test",
            description = "Asserts that the main elements on the products page, such as the product form and filter form, are displayed."
    )
    public void productsPageTest(){
        ProductsPage prodPage = goTo();
        Assert.assertTrue(prodPage.isProductFormDisplayed(), "Failure ProductsPageTest/productsPageTest: Product form is not displayed.");
        Assert.assertTrue(prodPage.isFilterFormDisplayed(), "Failure ProductsPageTest/productsPageTest: Filter form is not displayed.");
    }

    @Test(
            testName = "Product Name Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product name."
    )
    public void productNameFilterTest(){
        ProductsPage prodPage =
                goTo()
                .typeProductName("Custom-Built Desktop PC")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productNameFilterTest: Product 'Custom-Built Desktop PC' not found after filtering by name.");
    }

    @Test(
            testName = "Product Model Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product model."
    )
    public void productModelFilterTest(){
        ProductsPage prodPage =
                goTo()
                .typeProductModel("Custom-built 01")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productModelFilterTest: Product 'Custom-built 01' not found after filtering by model.");
    }

    @Test(
            testName = "Product Price Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product price."
    )
    public void productPriceFilterTest(){
        ProductsPage prodPage =
                goTo()
                .typeProductPrice("300")
                .clickFilterButton();

        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productPriceFilterTest: Product with price '300' not found after filtering by price.");
    }

    public ProductsPage goTo(){
        login();

        ProductsPage productsPage = new ProductsPage(getDriver(), getWait());
        getDriver().get(productsPage.newUrl());
        return new ProductsPage(getDriver(), getWait());
    }
}
