package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    //The WebElement for the username
    @FindBy(css = "#nav-profile span")
    public WebElement username;

    //Locator for the username
    private static final By USER_NAME = By.cssSelector("#nav-profile span");

    //Constructor used to pass the existing WebDrive and wait to this object
    public DashboardPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    //Method used to get the username
    public String usernameGetText(){
        return getText(username, USER_NAME);
    }
}
