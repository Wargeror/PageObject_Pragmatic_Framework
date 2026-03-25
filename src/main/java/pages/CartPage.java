package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private String cartUrl;

    @FindBy(xpath = "//div[@class='input-group']/input[@name='quantity']")
    private WebElement quantityField;

    @FindBy(xpath = "//*[@id=\"shopping-cart\"]/div/table/tbody/tr/td[4]/form/div/button[1]")
    private WebElement updateButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    private WebElement alertSuccess;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.cartUrl = "https://auto.pragmatic.bg/index.php?route=checkout/cart&language=en-gb";
    }

    public boolean urlContains() {
        return urlContains(cartUrl);
    }

    public CartPage clickAlertX(){
        clickWebElement(alertX);
        return this;
    }

    public CartPage typeQuantity(String quantity) {
        cleanField(quantityField);
        typeText(quantityField, quantity);
        return this;
    }

    public CartPage clickUpdate() {
        clickWebElement(updateButton);
        return this;
    }

    public boolean isAlertSuccessDisplayed() {
        return isDisplayed(alertSuccess);
    }

    public String getQuantityValue() {
        return quantityField.getAttribute("value");
    }

    public CartPage updateQuantity(String quantity){
        typeQuantity(quantity);
        clickUpdate();
        isAlertSuccessDisplayed();
        clickAlertX();
        return this;
    }

}
