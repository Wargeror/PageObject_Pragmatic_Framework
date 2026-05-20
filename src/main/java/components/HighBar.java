package components;

import base.BasePage;
import base.WebApp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HighBar extends BasePage {

    @FindBy(id = "top")
    private WebElement highBar;

    public HighBar(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
    }

    public boolean isHighBarDisplayed(){
        return isDisplayed(highBar);
    }
}
