package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage  extends BasePage {

    TopBar topBar;

    LeftNavigationBar navBar;

    private String productsUrl;

    @FindBy(css = "a.btn.btn-primary:nth-child(2)")
    private WebElement addNewButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div/div/button[3]")
    private WebElement deleteButton;

    @FindBy(id = "product")
    private WebElement productForm;


    @SuppressWarnings("SpellCheckingInspection")
    @FindBy(xpath = "//tbody//td[contains(normalize-space(), \"Custom-Built Desktop PC\")]")
    private WebElement customPcProduct;

    @SuppressWarnings("SpellCheckingInspection")
    @FindBy(xpath = "//*[@id=\"form-product\"]/div[1]/table/tbody/tr[4]/td[1]/input")
    private WebElement getNewProductCheckbox;

    @FindBy(xpath = "//*[@id=\"filter-product\"]/div")
    private WebElement filterForm;

       @FindBy(xpath = "//*[@id=\"input-name\"]")
       private WebElement productNameField;

       @FindBy(xpath = "//*[@id=\"input-model\"]")
       private WebElement productModelField;

       @FindBy(xpath = "//*[@id=\"input-price\"]")
       private WebElement productPriceField;

       @FindBy(xpath = "//*[@id=\"input-quantity\"]")
       private WebElement productQuantityField;

       @FindBy(xpath = "//*[@id=\"button-filter\"]")
       private WebElement filterButton;


    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBar = new TopBar(driver, wait);
        this.navBar = new LeftNavigationBar(driver, wait);
        productsUrl = "https://auto.pragmatic.bg/manage/index.php?route=catalog/product";
    }

    public String getProductsUrl(){
        return productsUrl;
    }

    public String newUrl() {
       return newUrl(productsUrl);
    }

    public boolean urlContains(){
        return urlContains(productsUrl);
    }

    public ProductsFormPage clickAddNewButton(){
        clickWebElement(addNewButton);
        return new ProductsFormPage(driver,wait);
    }

    public boolean newProductExists() {
        return isDisplayed(customPcProduct);
    }

    public void selectNewProduct(Boolean selected){
        selectCheckbox(getNewProductCheckbox, selected);
    }

    public void clickDeleteButton(){
        clickWebElement(deleteButton);
    }

    public boolean isProductFormDisplayed(){
        return isDisplayed(productForm);
    }

    public boolean isFilterFormDisplayed(){
        return isDisplayed(filterForm);
    }

    public ProductsPage typeProductName(String productName){
        typeText(productNameField, productName);
        return this;
    }

    public ProductsPage typeProductModel(String productModel){
        typeText(productModelField, productModel);
        return this;
    }

    public ProductsPage typeProductPrice(String productPrice){
        typeText(productPriceField, productPrice);
        return this;
    }

    public void typeProductQuantity(String productQuantity){
        typeText(productQuantityField, productQuantity);
    }

    public ProductsPage clickFilterButton(){
        clickWebElement(filterButton);
        return this;
    }

    public ProductsPage deleteProduct(){
        selectNewProduct(true);
        clickDeleteButton();
        clickAlert(true);
        return this;
    }

}
