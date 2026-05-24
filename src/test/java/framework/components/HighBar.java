package framework.components;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.pages.CustomerLoginPage;
import framework.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HighBar extends BasePage {

    @FindBy(id = "top")
    private WebElement highBar;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[1]/a")
    private WebElement registerButton;

    @FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[2]/a")
    private WebElement loginButton;

    public HighBar(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
    }

    public boolean isHighBarDisplayed(){
        return isDisplayed(highBar);
    }

    public RegistrationPage clickRegisterButton(){
        clickWebElement(myAccount);
        clickWebElement(registerButton);
        return webApp.registerPage();
    }

    public CustomerLoginPage clickLoginButton(){
        clickWebElement(myAccount);
        clickWebElement(loginButton);
        return webApp.customerLoginPage();
    }
}