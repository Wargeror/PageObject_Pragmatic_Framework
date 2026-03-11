package pages;

import base.BasePage;
import data.Input;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    //The WebElement for the username input field
    @FindBy(id = "input-username")
    private WebElement usernameField;

    //The WebElement for the password input field
    @FindBy(id = "input-password")
    private WebElement passwordField;

    //The WebElement for the login button
    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    //The WebElement for the alert
    @FindBy(css = "#alert .alert-danger")
    private WebElement alert;

    //Expected text for the alert
    public static final String EXPECTED_ALERT_TEXT = "No match for Username and/or Password.";



    //Constructor used to pass the existing WebDrive and wait to this object
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //Method used to input text into the username input field
    public void typeTextUsernameField(String text) {
        typeText(usernameField, text);
    }

    //Method used to input text into the password input field
    public void typeTextPasswordField(String text) {
        typeText(passwordField, text);
    }

    //Method used to click the login button
    public void clickLoginButton() {
        clickWebElement(loginButton);
    }

    public String alertGetText(){
       return w8AndGetText(alert);
    }

}
