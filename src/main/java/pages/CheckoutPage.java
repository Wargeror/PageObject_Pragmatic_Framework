package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    private String checkoutUrl;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        checkoutUrl = "https://auto.pragmatic.bg/index.php?route=checkout/checkout&language=en-gb";
    }

    public boolean urlContains() {
        return urlContains(checkoutUrl);
    }

}
