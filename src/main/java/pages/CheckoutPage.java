package pages;

import base.BasePage;
import components.Components;
import components.HighBar;
import components.TopBarMain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class CheckoutPage extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    @FindBy(xpath = "//*[@id=\"alert\"]/div")
    private WebElement alert;

    @FindBy(xpath = "//div[contains(@class, 'alert')][normalize-space()='Warning: Please check the form carefully for errors!']")
    private WebElement alertWarning;

    @FindBy(xpath = "//div[contains(@class, 'alert')][normalize-space()='Success: You have modified products!']")
    private WebElement alertSuccess;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    @FindBy(id = "input-guest")
    private WebElement guessRadioButton;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-shipping-address-1")
    private WebElement addressField;

    @FindBy(id = "input-shipping-city")
    private WebElement cityField;

    @FindBy(id = "input-shipping-postcode")
    private WebElement zipCodeField;

    @FindBy(id = "input-shipping-country")
    private WebElement countrySelect;

    @FindBy(id = "input-shipping-zone")
    private WebElement regionSelect;

    @FindBy(id = "button-register")
    private WebElement registerButton;

    @FindBy(id = "button-shipping-methods")
    private WebElement shippingMethodButton;

    @FindBy(id = "input-shipping-method-flat-flat")
    private WebElement flatShippingRadioButton;

    @FindBy(id = "button-shipping-method")
    private WebElement shippingEndButton;

    @FindBy(id = "button-payment-methods")
    private WebElement paymentMethodButton;

    @FindBy(id = "input-payment-method-cod-cod")
    private WebElement cashRadioButton;

    @FindBy(id = "button-payment-method")
    private WebElement paymentEndButton;

    @FindBy(id = "button-confirm")
    private WebElement confirmButton;


    private String checkoutUrl;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBarM = new TopBarMain(driver, wait);
        this.highBar = new HighBar(driver, wait);
        this.cmp = new Components(driver, wait);
        checkoutUrl = "https://auto.pragmatic.bg/index.php?route=checkout/checkout";
    }

    public boolean urlContains() {
        return urlContains(checkoutUrl);
    }


    public CheckoutPage clickGuessRadioButton(){
        clickRadioButton(guessRadioButton);
        return this;
    }

    public CheckoutPage typeFirstNameField(String text){
        typeText(firstNameField, text);
        return this;
    }

    public void cleanFirstNameField(){
        cleanField(firstNameField);
    }

    public CheckoutPage typeLastNameField(String text){
        typeText(lastNameField, text);
        return this;
    }

    public void cleanLastNameField(){
        cleanField(lastNameField);
    }

    public CheckoutPage typeEmailField(String text){
        typeText(emailField, text);
        return this;
    }

    public void cleanEmailField(){
        cleanField(emailField);
    }

    public CheckoutPage typeAddressField(String text){
        typeText(addressField, text);
        return this;
    }

    public void cleanAddressField(){
        cleanField(addressField);
    }

    public CheckoutPage typeCityField(String text){
        typeText(cityField, text);
        return this;
    }

    public void cleanCityField(){
        cleanField(cityField);
    }

    public CheckoutPage typeZipCodeField(String text){
        typeText(zipCodeField, text);
        return this;
    }

    public void cleanZipCodeField(){
        cleanField(zipCodeField);
    }

    public void cleanAllFields() {
        cleanFirstNameField();
        cleanLastNameField();
        cleanEmailField();
        cleanAddressField();
        cleanCityField();
        cleanZipCodeField();
    }

    public CheckoutPage selectCountrySelect(String text){
        selectFromSelect(countrySelect, text);
        return this;
    }

    public CheckoutPage selectRegionSelect(String text){
        selectFromSelect(regionSelect, text);
        return this;
    }

    public CheckoutPage scrollToRegisterButton(){
        scrollToElement(registerButton);
        return this;
    }

    public CheckoutPage clickRegisterButton(){
        clickWebElement(registerButton);
        return this;
    }

    public boolean isAlertDisplayed(){
        return isDisplayed(alert);
    }

    public CheckoutPage clickAlertX(){
        clickWebElement(alertX);
        return this;
    }

    public CheckoutPage clickShippingMethodButton(){
        clickWebElement(shippingMethodButton);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public CheckoutPage clickFlatShippingRadioButton(){
        clickRadioButton(flatShippingRadioButton);
        return this;
    }

    public CheckoutPage clickShippingEndButton(){
        clickWebElement(shippingEndButton);
        return this;
    }

    public CheckoutPage clickPaymentMethodButton(){
        clickWebElement(paymentMethodButton);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public CheckoutPage clickCashRadioButton(){
        clickRadioButton(cashRadioButton);
        return this;
    }

    public CheckoutPage clickPaymentEndButton(){
        clickWebElement(paymentEndButton);
        return this;
    }

    public SuccessfulCheckout clickConfirmButton(){
        clickWebElement(confirmButton);
        return new SuccessfulCheckout(driver,wait);
    }

    public SuccessfulCheckout fillCheckoutForm(){
        return
                clickGuessRadioButton()
                .typeFirstNameField(Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)))
                .typeLastNameField(Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)))
                .typeEmailField(Utils.generateEmail())
                .typeAddressField(Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)))
                .typeCityField(Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7)))
                .typeZipCodeField(Utils.randomNumeric(4))
                .scrollToRegisterButton()
                .selectCountrySelect("United Kingdom")
                .selectRegionSelect("Norfolk")
                .clickRegisterButton()
                .clickAlertX()
                .clickShippingMethodButton()
                .clickFlatShippingRadioButton()
                .clickShippingEndButton()
                .clickPaymentMethodButton()
                .clickCashRadioButton()
                .clickPaymentEndButton()
                .clickConfirmButton()
                .w8ForH1();
    }
}
