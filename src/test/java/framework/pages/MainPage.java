package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.*;
import framework.data.Input; // Added import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private String mainUrl;

    private Input input;

    @FindBy(css = ".img-fluid")
    private WebElement logo;


    @FindBy(id = "carousel-banner-0")
    private WebElement banner;

    @FindBy(css = ".image > a > img[alt*='MacBook']")
    private WebElement macBookImg;

    @FindBy(css = ".image > a > img[alt*='iPhone']")
    private WebElement iPhoneImg;

    @FindBy(css = ".image > a > img[alt*='Apple Cinema 30\\\"']")
    private WebElement cinema30Img;

    @FindBy(css = ".image > a > img[alt*='Canon EOS 5D']")
    private WebElement canonImg;

    @FindBy(id = "carousel-banner-1")
    private WebElement secondBanner;

    public MainPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
        this.input = new Input();
        mainUrl = input.getUrl("main.url");
    }

    public String mainUrl() {
        return mainUrl;
    }

    public boolean isBannerDisplayed(){
        return isDisplayed(banner);
    }

    public boolean isMacBookDisplayed(){
        return isDisplayed(macBookImg);
    }

    public boolean isiPhoneDisplayed(){
        return isDisplayed(iPhoneImg);
    }

    public boolean isCinema30Displayed(){
        return isDisplayed(cinema30Img);
    }

    public boolean isCanonDisplayed(){
        return isDisplayed(canonImg);
    }

    public void scrollToSecondBanner(){
        scrollToElement(secondBanner);
    }

    public boolean isSecondBannerDisplayed(){
        return isDisplayed(secondBanner);
    }

    public WebElement getLogo(){
        return logo;
    }

    public MainPage typeSearchField(String text){
        typeText(cmp.getSearchField(), text);
        return this;
    }

    public SearchPage clickSearchButton(){
        clickWebElement(cmp.getSearchButton());
        return webApp.searchPage();
    }

    public Product4SalePage clickMacBookImg(){
        clickWebElement(macBookImg);
        return webApp.product4$Page();
    }

    public CheckoutPage orderAndGoCheckout(){
        return
                clickMacBookImg()
                .clickAddToCart()
                .clickAlertX()
                .clickCart()
                .clickCheckout();

    }
}
