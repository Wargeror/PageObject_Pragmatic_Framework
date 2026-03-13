package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Components extends BasePage {

    @FindBy(xpath = "//*[@id=\"logo\"]/a/img")
    private WebElement logo;

    @FindBy(xpath = "//*[@id=\"search\"]/input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id=\"search\"]/button")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/button")
    private WebElement cart;

    public Components(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickLogo(){
        clickWebElement(logo);
    }

    public void typeSearchField(String text){
        typeText(searchField, text);
    }

    public void clickSearchButton(){
        clickWebElement(searchButton);
    }

    public void clickCart(){
        clickWebElement(cart);
    }

    public boolean isLogoDisplayed(){
        return isDisplayed(logo);
    }

    public boolean isSearchFieldDisplayed(){
        return isDisplayed(searchField);
    }

    public boolean isSearchButtonDisplayed(){
        return isDisplayed(searchButton);
    }

    public boolean isCartDisplayed(){
        return isDisplayed(cart);
    }
}
