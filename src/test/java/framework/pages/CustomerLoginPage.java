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

public class CustomerLoginPage extends BasePage {
    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private Input input;

    private String customerLoginUrl;

    private String email;

    private String password;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    public CustomerLoginPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        this.customerLoginUrl = input.getUrl("customer.login.url");
        this.email = Utils.emailGenerator();
        this.password = Utils.passwordGenerator(6);
    }

    public String customerLoginUrl() {
        return customerLoginUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public CustomerLoginPage typeTextEmailField(String text){
        typeText(emailField, text);
        return this;
    }

    public CustomerLoginPage typeTextPasswordField(String text){
        typeText(passwordField, text);
        return this;
    }

    public AccountPage  clickLoginButton(){
        clickWebElement(loginButton);
        return webApp.accountPage();
    }
}
