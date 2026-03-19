package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product4$Page extends BasePage {

    private String macBookUrl;

    private  String customDesktop;

    @FindBy(css = "[class='btn btn-lg btn-inverse btn-block dropdown-toggle']")
    private WebElement cart;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    @FindBy(xpath = "//p[@class='text-end']/a[2]")
    private WebElement checkout;

    @FindBy(id = "button-cart")
    private WebElement addToCart;

    public final static String succAddNotf = "[class='alert alert-success alert-dismissible']";

    public Product4$Page(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        macBookUrl = "https://auto.pragmatic.bg/index.php?route=product/product&language=en-gb&product_id=43";
        customDesktop = "https://auto.pragmatic.bg/index.php?route=product/product&language=en-gb&product_id=53&path=20_26";
    }

    public boolean urlContains() {
        return urlContains(macBookUrl);
    }

    public void clickAlertX(){
        clickWebElement(alertX);
    }

    public void clickCart(){
        clickWebElement(cart);
    }

    public void clickCheckout(){
        clickWebElement(checkout);
    }

    public void clickAddToCart(){
        clickWebElement(addToCart);
    }

    public void waitSuccAddNotf(){
        w8UntilElementIsInvisible(By.cssSelector(succAddNotf));
    }

    public String getCustomDesktop(){
        return customDesktop;
    }

}
