package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersPage extends BasePage { public String orderUrl;

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String customerUrl;

    public CustomersPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        customerUrl = "https://auto.pragmatic.bg/manage/index.php?route=customer/customer";
    }

    public boolean urlContains() {
        return urlContains(customerUrl);
    }
}
