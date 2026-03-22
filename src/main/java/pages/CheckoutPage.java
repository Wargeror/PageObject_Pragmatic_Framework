package pages;

import base.BasePage;
import components.Components;
import components.HighBar;
import components.TopBarMain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    public void clickGuessRadioButton(){
        clickRadioButton(guessRadioButton);
    }

    public void typeFirstNameField(String text){
        typeText(firstNameField, text);
    }

    public void cleanFirstNameField(){
        cleanField(firstNameField);
    }

    public void typeLastNameField(String text){
        typeText(lastNameField, text);
    }

    public void cleanLastNameField(){
        cleanField(lastNameField);
    }

    public void typeEmailField(String text){
        typeText(emailField, text);
    }

    public void cleanEmailField(){
        cleanField(emailField);
    }

    public void typeAddressField(String text){
        typeText(addressField, text);
    }

    public void cleanAddressField(){
        cleanField(addressField);
    }

    public void typeCityField(String text){
        typeText(cityField, text);
    }

    public void cleanCityField(){
        cleanField(cityField);
    }

    public void typeZipCodeField(String text){
        typeText(zipCodeField, text);
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

    public void selectCountrySelect(String text){
        selectFromSelect(countrySelect, text);
    }

    public void selectRegionSelect(String text){
        selectFromSelect(regionSelect, text);
    }

    public void scrollToRegisterButton(){
        scrollToElement(registerButton);
    }

    public void clickRegisterButton(){
        clickWebElement(registerButton);
    }

    public void clickAlertX(){
        clickWebElement(alertX);
    }

    public void clickShippingMethodButton(){
        clickWebElement(shippingMethodButton);
    }

    public void clickFlatShippingRadioButton(){
        clickRadioButton(flatShippingRadioButton);
    }

    public void clickShippingEndButton(){
        clickWebElement(shippingEndButton);
    }

    public void clickPaymentMethodButton(){
        clickWebElement(paymentMethodButton);
    }

    public void clickCashRadioButton(){
        clickRadioButton(cashRadioButton);
    }

    public void clickPaymentEndButton(){
        clickWebElement(paymentEndButton);
    }

    public void clickConfirmButton(){
        clickWebElement(confirmButton);
    }
}
