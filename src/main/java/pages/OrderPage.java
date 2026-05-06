package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import data.Input; // Added import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String orderUrl;

    private Input input;

    public OrderPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        this.input = new Input();
        orderUrl = input.getUrl("order.url");
    }

    public String GetUrl(){
        return orderUrl;
    }

    public boolean urlContains() {
        return urlContains(orderUrl);
    }

}
