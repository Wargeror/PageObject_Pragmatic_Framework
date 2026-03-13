package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersFieldPage extends BasePage {

    private String customerFormUrl;

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

    public CustomersFieldPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        customerFormUrl = "https://auto.pragmatic.bg/manage/index.php?route=customer/customer.form";
    }

    public void typeFirstName(String firstName) {
        typeText(firstNameField, firstName);
    }

    public void typeLastName(String lastName) {
        typeText(lastNameField, lastName);
    }

    public void typeEmail(String email) {
        typeText(emailField, email);
    }

    public void typePassword(String password) {
        typeText(passwordField, password);
    }

    public void typeConfirmPassword(String password) {
        typeText(confirmField, password);
    }

    public void setNewsletter(boolean subscribe) {
        if (newsletterCheckbox.isSelected() != subscribe) {
            newsletterCheckbox.click();
        }
    }

    public void setSafeAntiFraud(boolean subscribe) {
        if (safeAntiFraud.isSelected() != subscribe) {
            safeAntiFraud.click();
        }
    }

    public void clickSave() {
        clickWebElement(saveButton);
    }

    public void scrollToNewsletterCheckbox() {
        scrollToElement(newsletterCheckbox);

    }

    public void scrollSafeAntiFraud() {
        scrollToElement(safeAntiFraud);

    }

    public void scrollToSaveButton() {
        scrollToElement(saveButton);
    }

    public boolean urlContains() {
        return urlContains(customerFormUrl);
    }
}
