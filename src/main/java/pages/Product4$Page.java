package pages;

import base.BasePage;
import base.WebApp;
import data.Input; // Added import
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product4$Page extends BasePage {

    private String macBookUrl;

    private  String customDesktop;

    private Input input;

    @FindBy(css = "[class='btn btn-lg btn-inverse btn-block dropdown-toggle']")
    private WebElement cart;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/ul/li/div/p/a[1]")
    private WebElement viewCart;

    @FindBy(xpath = "//p[@class='text-end']/a[2]")
    private WebElement checkout;

    @FindBy(id = "button-cart")
    private WebElement addToCart;

    @SuppressWarnings("SpellCheckingInspection")
    public final static String succAddNotf = "[class='alert alert-success alert-dismissible']";

    public Product4$Page(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.input = new Input();
        macBookUrl = input.getUrl("macbook.url");
        customDesktop = input.getUrl("customdesktop.url");
    }

    public boolean urlContains() {
        return urlContains(macBookUrl);
    }

    public Product4$Page clickAlertX(){
        clickWebElement(alertX);
        return this;
    }

    public Product4$Page clickCart(){
        clickWebElement(cart);
        return this;
    }

    public CartPage clickViewCart(){
        clickWebElement(viewCart);
        return webApp.cartPage();
    }

    public CheckoutPage clickCheckout(){
        clickWebElement(checkout);
        return webApp.checkoutPage();
    }

    public Product4$Page clickAddToCart(){
        clickWebElement(addToCart);
        return this;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void waitSuccAddNotf(){
        waitUntilElementIsInvisible(By.cssSelector(succAddNotf));
    }

    public String getCustomDesktop(){
        return customDesktop;
    }

    public CheckoutPage addAndGoCheckout(){
        return
                clickAddToCart()
                .clickAlertX()
                .clickCart()
                .clickCheckout();
    }

    public CartPage addAndGoCart(){
        return
                clickAddToCart()
                .clickAlertX()
                .clickCart()
                .clickViewCart();
    }

}
