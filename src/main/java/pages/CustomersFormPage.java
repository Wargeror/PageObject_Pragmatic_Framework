package pages;

import base.BasePage;
import components.LeftNavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class CustomersFormPage extends BasePage {

    public LeftNavigationBar leftNavigationBar;

    private String firstName;
    private String lastName;
    private String password;
    private String randomEmailAddress;

    private String customerFormUrl;

    @FindBy(xpath = "//*[@id=\"alert\"]/div")
    private WebElement alert;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement confirmField;

    @FindBy(id = "input-newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "input-safe")
    private WebElement safeAntiFraud;

    @FindBy(xpath = "//i[@class='fa-solid fa-floppy-disk']/ ..")
    private WebElement saveButton;

    public CustomersFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.leftNavigationBar = new LeftNavigationBar(driver, wait);
        this.firstName = Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        this.lastName = Utils.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        this.password = Utils.passwordGenerator(ThreadLocalRandom.current().nextInt(5, 19));
        this.randomEmailAddress = Utils.generateEmail();
        customerFormUrl = "https://auto.pragmatic.bg/manage/index.php?route=customer/customer.form";
    }


    public boolean isAlertDisplayed(){
        return isDisplayed(alert);
    }

    public CustomersFormPage clickAlertX(){
        clickWebElement(alertX);
        return this;
    }

    public CustomersFormPage typeFirstName(String firstName) {
        typeText(firstNameField, firstName);
        return this;
    }

    public CustomersFormPage typeLastName(String lastName) {
        typeText(lastNameField, lastName);
        return this;
    }

    public CustomersFormPage typeEmail(String email) {
        typeText(emailField, email);
        return this;
    }

    public CustomersFormPage typePassword(String password) {
        typeText(passwordField, password);
        return this;
    }

    public CustomersFormPage typeConfirmPassword(String password) {
        typeText(confirmField, password);
        return this;
    }

    public CustomersFormPage setNewsletter(boolean subscribe) {
        if (newsletterCheckbox.isSelected() != subscribe) {
            newsletterCheckbox.click();
        }
        return this;
    }

    public CustomersFormPage setSafeAntiFraud(boolean subscribe) {
        if (safeAntiFraud.isSelected() != subscribe) {
            safeAntiFraud.click();
        }
        return this;
    }

    public CustomersFormPage clickSave() {
        clickWebElement(saveButton);
        return this;
    }

    public CustomersFormPage scrollToNewsletterCheckbox() {
        scrollToElement(newsletterCheckbox);
        return this;
    }

    public CustomersFormPage scrollSafeAntiFraud() {
        scrollToElement(safeAntiFraud);
        return this;
    }

    public CustomersFormPage scrollToSaveButton() {
        scrollToElement(saveButton);
        return this;
    }

    public boolean urlContains() {
        return urlContains(customerFormUrl);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomEmailAddress() {
        return randomEmailAddress;
    }

    public void setRandomEmailAddress(String randomEmailAddress) {
        this.randomEmailAddress = randomEmailAddress;
    }

    public CustomersFormPage fillForm(){
        this.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(randomEmailAddress)
                .typePassword(password)
                .typeConfirmPassword(password)
                .scrollToNewsletterCheckbox()
                .setNewsletter(true)
                .scrollSafeAntiFraud()
                .setSafeAntiFraud(true)
                .scrollToSaveButton()
                .clickSave();
        return this;
    }

   public CustomersPage filterForNewCu(){
       return
               clickAlertX()
               .leftNavigationBar.clickCustomers()
               .typeEmailInputField(getRandomEmailAddress())
               .clickFilterButton();

   }
}
