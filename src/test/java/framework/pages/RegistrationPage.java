package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.Components;
import framework.components.HighBar;
import framework.components.TopBarMain;
import framework.data.Input;
import framework.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;

import java.util.concurrent.ThreadLocalRandom;


public class RegistrationPage extends BasePage {
    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private String registrationUrl;

    private Input input;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;


    @FindBy(xpath = "//div[@id='alert']//*[contains(@class, 'alert-danger') and contains(., 'Warning: You must agree to the Privacy Policy!')]")
    private WebElement warningAlert;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "error-firstname") // Placeholder
    private WebElement firstNameError;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "error-lastname") // Placeholder
    private WebElement lastNameError;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "error-email") // Placeholder
    private WebElement emailError;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "error-password") // Placeholder
    private WebElement passwordError;

    @FindBy(xpath = "//*[@id='input-newsletter']")
    private WebElement subscribeRadioButton;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement policyRadioButton;

    @FindBy(css = "button[type='submit'].btn.btn-primary")
    private WebElement continueButton;


    public RegistrationPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        this.firstName = Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        this.lastName = Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        this.email = Utils.emailGenerator();
        this.password = Utils.passwordGenerator(ThreadLocalRandom.current().nextInt(5, 19));
        registrationUrl = input.getUrl("registration.url");
    }

    public String registrationUrl() {
        return registrationUrl;
    }

    public boolean urlContains() {
        return urlContains(registrationUrl);
    }

    public RegistrationPage typeFirstNameField(String firstName){
        typeText(firstNameField, firstName);
        return this;
    }

    public RegistrationPage typeLastNameField(String lastName){
        typeText(lastNameField, lastName);
        return this;
    }

    public RegistrationPage typeEmailField(String email) {
        typeText(emailField, email);
        return this;
    }

    public RegistrationPage typePasswordField(String password) {
        typeText(passwordField, password);
        return this;
    }

    public RegistrationPage clickSubscribeRadioButton(boolean subscribe) {
        if (subscribe) {
            clickWebElement(subscribeRadioButton);
        }
        return this;
    }

    public RegistrationPage clickPolicyRadioButton(boolean policy) {
        if (policy) {
            clickWebElement(policyRadioButton);
        }
        return this;
    }

    public RegisteredPage clickContinueButton() {
        clickWebElement(continueButton);
        return webApp.registeredPage();
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }

    public boolean isWarningDisplayed() {
        return isDisplayed(warningAlert);
    }

    public String getWarningText() {
        return waitAndGetText(warningAlert);
    }

    public String getFirstNameError() {
        return waitAndGetText(firstNameError);
    }

    public String getLastNameError() {
        return waitAndGetText(lastNameError);
    }

    public String getEmailError() {
        return waitAndGetText(emailError);
    }

    public String getPasswordError() {
        return waitAndGetText(passwordError);
    }

    public RegistrationPage fillRegistrationForm( String firstName, String lastName, String email, String password, boolean subscribe, boolean policy){
        return typeFirstNameField(firstName)
               .typeLastNameField(lastName)
               .typeEmailField(email)
               .typePasswordField(password)
               .clickSubscribeRadioButton(subscribe)
               .clickPolicyRadioButton(policy);
    }

}