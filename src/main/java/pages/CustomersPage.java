package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void clickAddButton(){
        clickWebElement(addButton);
    }

    public void typeEmailInputField(String text){
        typeText(emailInputField, text);
    }

    public void clickFilterButton(){
        clickWebElement(filterButton);
    }

    public void clickCuCheckbox(Boolean selected){
        selectCheckbox(cuCheckbox, selected);
    }

    public void clickDeleteButton(){
        clickWebElement(deleteButton);
    }
}
