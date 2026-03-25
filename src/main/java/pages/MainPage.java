package pages;

import base.BasePage;
import components.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private String mainUrl;

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

    public MainPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBarM = new TopBarMain(driver, wait);
        this.highBar = new HighBar(driver, wait);
        this.cmp = new Components(driver, wait);
        mainUrl = "https://auto.pragmatic.bg/index.php?route=common/home&language=en-gb";
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

    public MainPage typeSearchField(String text){
        typeText(cmp.getSearchField(), text);
        return this;
    }

    public SearchPage clickSearchButton(){
        clickWebElement(cmp.getSearchButton());
        return new SearchPage(driver,wait);
    }

    public Product4$Page clickMacBookImg(){
        clickWebElement(macBookImg);
        return new Product4$Page(driver,wait);
    }

    public CheckoutPage orderAndGoCheckout(){
        CheckoutPage checkoutPage =
                clickMacBookImg()
                .clickAddToCart()
                .clickAlertX()
                .clickCart()
                .clickCheckout();
        return checkoutPage;
    }
}
