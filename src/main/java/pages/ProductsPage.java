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

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBar = new TopBar(driver, wait);
        this.navBar = new LeftNavigationBar(driver, wait);
        productsUrl = "https://auto.pragmatic.bg/manage/index.php?route=catalog/product";
    }

    public void clickProductUrl(){
        clickWebElement(addNewButton);
    }

}
