package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.Components;
import framework.components.HighBar;
import framework.components.TopBarMain;
import framework.data.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisteredPage extends BasePage {
    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private String registeredUrl;

    private Input input;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private WebElement accountCreatedHeader;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement continueButton;

    public RegisteredPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        registeredUrl = input.getUrl("registered.url");
    }

    public String registrationUrl() {
        return registeredUrl;
    }

    public boolean urlContains() {
        return urlContains(registeredUrl);
    }

    public RegisteredPage clickContinueButton() {
        clickWebElement(continueButton);
        return this;
    }

    public boolean isAccountCreatedHeaderDisplayed() {
        return isDisplayed(accountCreatedHeader);
    }
}
