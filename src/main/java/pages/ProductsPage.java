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

    @FindBy(xpath = "//*[@id=\"form-product\"]/div[1]/table/tbody/tr[4]/td[3]")
    private WebElement newProduct;

    @FindBy(xpath = "//*[@id=\"form-product\"]/div[1]/table/tbody/tr[4]/td[1]/input")
    private WebElement getNewProductCheckbox;


    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBar = new TopBar(driver, wait);
        this.navBar = new LeftNavigationBar(driver, wait);
        productsUrl = "https://auto.pragmatic.bg/manage/index.php?route=catalog/product";
    }

    public boolean urlContains(){
        return urlContains(productsUrl);
    }

    public void clickAddNewButton(){
        clickWebElement(addNewButton);
    }

    public boolean newProductExists() {
        return isDisplayed(newProduct);
    }

    public void selectNewProduct(Boolean selected){
        selectCheckbox(getNewProductCheckbox, selected);
    }

    public void clickDeleteButton(){
        clickWebElement(deleteButton);
    }

}
