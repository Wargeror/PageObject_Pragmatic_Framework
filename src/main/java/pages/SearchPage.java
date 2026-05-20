package pages;

import base.BasePage;
import base.WebApp;
import components.Components;
import components.HighBar;
import components.TopBarMain;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    public TopBarMain topBarM;

    public HighBar highBar;

    public Components cmp;

    private String searchPageUrl;

    @FindBy(xpath = "//*[@id=\"product-list\"]/div/div/div[1]/a/img")
    private WebElement macBookImg;

    public SearchPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBarM = new TopBarMain(driver, wait, webApp);
        this.highBar = new HighBar(driver, wait, webApp);
        this.cmp = new Components(driver, wait, webApp);
    }

    public boolean isMacBookImgDisplayed(){
        return isDisplayed(macBookImg);
    }
}
