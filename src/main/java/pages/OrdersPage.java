package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrdersPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String ordersUrl;

    public OrdersPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        ordersUrl = "https://auto.pragmatic.bg/manage/index.php?route=sale/order";
    }

    public String GetUrl(){
        return ordersUrl;
    }

    public boolean urlContains() {
        return urlContains(ordersUrl);
    }

}
