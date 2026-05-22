package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.LeftNavigationBar;
import framework.components.TopBar;
import framework.data.Input; // Added import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String orderUrl;

    private Input input;

    public OrderPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBar = new TopBar(driver,wait, webApp);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait, webApp);
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
