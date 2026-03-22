package pages;

import base.BasePage;
import components.Components;
import components.HighBar;
import components.TopBarMain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessfulCheckout extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    public String SucCheckUrl;

    @FindBy(xpath = "//div[@id='content']/h1[contains(text(), \"Your order has been placed!\")]")
    private WebElement h1;

    public SuccessfulCheckout(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBarM = new TopBarMain(driver, wait);
        this.highBar = new HighBar(driver, wait);
        this.cmp = new Components(driver, wait);
        SucCheckUrl = "https://auto.pragmatic.bg/index.php?route=checkout/success";
    }

    public boolean urlContains() {
        return urlContains(SucCheckUrl);
    }

    public void w8ForH1(){
        w8ForVisibility(h1);
    }

    public boolean isH1Displayed(){
        return isDisplayed(h1);
    }

}
