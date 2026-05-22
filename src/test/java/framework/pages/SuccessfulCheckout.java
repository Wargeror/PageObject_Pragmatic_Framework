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

public class SuccessfulCheckout extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    public String SucCheckUrl;

    private Input input;

    @FindBy(xpath = "//div[@id='content']/h1[contains(text(), \"Your order has been placed!\")]")
    private WebElement h1;

    public SuccessfulCheckout(WebDriver driver, WebDriverWait wait, WebApp webApp) {
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        SucCheckUrl = input.getUrl("successfull.checkout.url");
        SucCheckUrl = "https://auto.pragmatic.bg/index.php?route=checkout/success";
    }

    public boolean urlContains() {
        return urlContains(SucCheckUrl);
    }

    public SuccessfulCheckout waitForH1(){
        waitForVisibility(h1);
        return this;
    }

    public boolean isH1Displayed(){
        return isDisplayed(h1);
    }

}
