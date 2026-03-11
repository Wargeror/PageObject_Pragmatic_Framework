package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String w8AndGetText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // Scrolls to the specified element and waits until it is visible in the viewport.
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            wait.until(d -> {
                return (Boolean) ((JavascriptExecutor) d)
                        .executeScript("const rect = arguments[0].getBoundingClientRect();"
                                        + "return (rect.top >= 0 && rect.bottom <= window.innerHeight);"
                                , element);
            });
        } catch (TimeoutException e) {
            throw new TimeoutException("Failed to scroll element into view: " + element.toString(), e);
        }
    }

    // Waits for an element to be either invisible or not present on the DOM.
    public void w8UntilElementIsInvisible(By locator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Checks if the current URL contains the specified page URL.
    public boolean urlContains(String pageUrl) {
        try {
            return driver.getCurrentUrl().contains(pageUrl);
        } catch (NullPointerException e) {
            // Re-throw the exception with a more informative message
            throw new NullPointerException("The URL does not contain the specified page URL:");
        }
    }

    public String getFill(WebElement element){
        return element.getAttribute("fill");
    }

}
