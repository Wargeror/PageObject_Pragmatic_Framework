package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.LeftNavigationBar;
import framework.components.TopBar;
import framework.data.Input; // Added import
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersPage extends BasePage { public String orderUrl;

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String customerUrl;

    private Input input;

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

    public CustomersPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBar = new TopBar(driver,wait, webApp);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait, webApp);
        this.input = new Input();
        customerUrl = input.getUrl("customer.url");
    }

    public boolean urlContains() {
        return urlContains(customerUrl);
    }

    public CustomersFormPage clickAddButton(){
        clickWebElement(addButton);
        return webApp.customersFormPage();
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

    public CustomersPage filterForNewCu(String email){
        return
                typeEmailInputField(email)
                .clickFilterButton();
    }
}
