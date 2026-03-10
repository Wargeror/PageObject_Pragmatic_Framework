package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftNavigationBar extends BasePage {

    @FindBy(xpath = "//*[@id=\"menu-dashboard\"]/a")
    private WebElement dashboardLNavBar;

    public LeftNavigationBar(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
}