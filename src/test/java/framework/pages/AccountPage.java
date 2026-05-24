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

public class AccountPage extends BasePage {
    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private Input input;

    private String accountUrl;

    @FindBy(xpath = "//h2[text()='My Account']")
    private WebElement myAccountHeader;

    public AccountPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp); // Call the parent constructor
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        this.accountUrl = input.getUrl("account.url");
    }

    public boolean urlContains(){
        return super.urlContains(accountUrl); // Call the inherited method
    }

    public boolean isMyAccountHeaderDisplayed(){
        return isDisplayed(myAccountHeader);
    }
}