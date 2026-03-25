package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopBar extends BasePage {

    @FindBy(css = ".container-fluid > h1:nth-child(2)")
    public WebElement h1PageName;

    @FindBy(css = "ol.breadcrumb > li.breadcrumb-item:nth-child(1) > a")
    public WebElement homeLinkPath;

    @FindBy(css = "ol.breadcrumb > li.breadcrumb-item:nth-child(2) > a")
    public WebElement secondPageLinkPath;

    public TopBar(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public TopBar scrollToTop(){
        scrollToElement(h1PageName);
        return this;
    }
}
