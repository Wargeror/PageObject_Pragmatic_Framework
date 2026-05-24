package pagetest;

import framework.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.ProductsPage;

@Epic("Catalog Management")
@Feature("Products Page Functionality")
public class ProductsPageTest extends BaseTest {

    @Test(
            testName = "Products Page Element Display Test",
            description = "Asserts that the main elements on the products page, such as the product form and filter form, are displayed."
    )
    @Story("Element Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Asserts that the main elements on the products page, such as the product form and filter form, are displayed.")
    public void productsPageTest(){
        log.info("Navigating to the Products page to verify element display.");
        ProductsPage prodPage = goTo();
        log.info("Asserting visibility of product form and filter form.");
        Assert.assertTrue(prodPage.isProductFormDisplayed(), "Failure ProductsPageTest/productsPageTest: Product form is not displayed.");
        Assert.assertTrue(prodPage.isFilterFormDisplayed(), "Failure ProductsPageTest/productsPageTest: Filter form is not displayed.");
    }

    @Test(
            testName = "Product Name Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product name."
    )
    @Story("Product Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the product filter works correctly when filtering by product name.")
    public void productNameFilterTest(){
        log.info("Navigating to the Products page and filtering by name.");
        ProductsPage prodPage =
                goTo()
                .typeProductName("Custom-Built Desktop PC")
                .clickFilterButton();

        log.info("Asserting that the filtered product exists.");
        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productNameFilterTest: Product 'Custom-Built Desktop PC' not found after filtering by name.");
    }

    @Test(
            testName = "Product Model Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product model."
    )
    @Story("Product Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the product filter works correctly when filtering by product model.")
    public void productModelFilterTest(){
        log.info("Navigating to the Products page and filtering by model.");
        ProductsPage prodPage =
                goTo()
                .typeProductModel("Custom-built 01")
                .clickFilterButton();

        log.info("Asserting that the filtered product exists.");
        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productModelFilterTest: Product 'Custom-built 01' not found after filtering by model.");
    }

    @Test(
            testName = "Product Price Filter Test",
            description = "Verifies that the product filter works correctly when filtering by product price."
    )
    @Story("Product Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the product filter works correctly when filtering by product price.")
    public void productPriceFilterTest(){
        log.info("Navigating to the Products page and filtering by price.");
        ProductsPage prodPage =
                goTo()
                .typeProductPrice("300")
                .clickFilterButton();

        log.info("Asserting that the filtered product exists.");
        Assert.assertTrue(prodPage.newProductExists(), "Failure ProductsPageTest/productPriceFilterTest: Product with price '300' not found after filtering by price.");
    }

    public ProductsPage goTo(){
        adminLogin();

        ProductsPage productsPage = webApp.productsPage();
        getDriver().get(productsPage.newUrl());
        return webApp.productsPage();
    }
}