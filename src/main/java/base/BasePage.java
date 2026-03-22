package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        wait.until(ExpectedConditions.visibilityOf(element));
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

    public void w8ForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Waits for an element to be either invisible or not present on the DOM.
    public void w8UntilElementIsInvisible(By locator) {
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

    public void w8iFrameAndMoveToIt(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void w8PopUpAndMoveToIt(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        driver.switchTo().frame(element);
    }

    public String getFill(WebElement element) {
        return element.getAttribute("fill");
    }

    public boolean isDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public void selectCheckbox(WebElement element, Boolean selected) {
        if (selected) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    public void clickAlert(Boolean accept) {
        if (accept) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }

    public String newUrl(String url) {
        String currentUrl = driver.getCurrentUrl();
        String token = "";

        // Find the user_token in the current URL
       try{ int tokenIndex = currentUrl.indexOf("user_token=");
        if (tokenIndex != -1) {
            token = currentUrl.substring(tokenIndex);
            // If there are other parameters after the token, cut them off
            if (token.contains("&")) {
                token = token.substring(0, token.indexOf("&"));
            }
        } else {
            // Handle case where no token is found, maybe throw an exception or return the original url
            return url;
        }}catch (NullPointerException e){
            return url;
       }

        // Append the token to the new URL
        if (url.contains("?")) {
            return url + "&" + token;
        } else {
            return url + "?" + token;
        }
    }

    public void clickRadioButton(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        if(!element.isSelected()){
            element.click();
        }
    }

    public void selectFromSelect(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void cleanField(WebElement element){
        element.clear();
    }
}
