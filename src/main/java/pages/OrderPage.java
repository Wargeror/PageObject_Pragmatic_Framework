package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String orderUrl;

    public OrderPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        orderUrl = "https://auto.pragmatic.bg/manage/index.php?route=sale/order.info";
    }

    public String GetUrl(){
        return orderUrl;
    }

    public boolean urlContains() {
        return urlContains(orderUrl);
    }

}

