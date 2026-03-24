package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomRandomStringGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class CustomersPage extends BasePage { public String orderUrl;

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String customerUrl;

    @FindBy(xpath = "//i[@class='fa-solid fa-plus']/ ..")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div[1]/div/div/button[2]")
    private WebElement deleteButton;

    @FindBy(css = "input#input-email")
    private WebElement emailInputField;

    @FindBy(css = "button#button-filter")
    private WebElement filterButton;

    @SuppressWarnings("SpellCheckingInspection")
    @FindBy(xpath = "//*[@id=\"form-customer\"]/div[1]/table/tbody/tr/td[1]/input")
    private WebElement cuCheckbox;

    public CustomersPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        customerUrl = "https://auto.pragmatic.bg/manage/index.php?route=customer/customer";
    }

    public boolean urlContains() {
        return urlContains(customerUrl);
    }

    public CustomersFormPage clickAddButton(){
        clickWebElement(addButton);
        return new CustomersFormPage(driver,wait);
    }

    public CustomersPage typeEmailInputField(String text){
        typeText(emailInputField, text);
        return this;
    }

    public CustomersPage clickFilterButton(){
        clickWebElement(filterButton);
        return this;
    }

    public CustomersPage clickCuCheckbox(Boolean selected){
        selectCheckbox(cuCheckbox, selected);
        return this;
    }

    public CustomersPage clickDeleteButton(){
        clickWebElement(deleteButton);
        return this;
    }

    public boolean isCustomerListed(String email) {
        String xpath = "//td[@class='text-start' and contains(text(), '" + email + "')]";
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public CustomersPage deleteCustomer(){
        clickCuCheckbox(true)
                .clickDeleteButton()
                .clickAlert(true);
        return this;
    }
}
