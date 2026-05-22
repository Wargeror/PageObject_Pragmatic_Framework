package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.LeftNavigationBar;
import framework.components.TopBar;
import framework.data.Input; // Added import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class OrdersPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String ordersUrl;

    private List<String> exp_options;

    private Input input;

    @FindBy(id = "input-order-status")
    private WebElement orderStatus;


    public OrdersPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBar = new TopBar(driver,wait, webApp);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait, webApp);
        this.input = new Input();
        ordersUrl = input.getUrl("orders.url");
        exp_options = Arrays.asList("", "Missing Orders", "Canceled", "Canceled Reversal", "Chargeback", "Complete", "Denied", "Expired", "Failed", "Pending", "Processed", "Processing", "Refunded", "Reversed", "Shipped", "Voided");
    }

    public String GetUrl(){
        return ordersUrl;
    }

    public boolean urlContains() {
        return urlContains(ordersUrl);
    }

    public List<String> getExp_options() {
        return exp_options;
    }

    public WebElement getOrderStatus(){
        return orderStatus;
    }

    public void clickOrderStatus(){
        clickWebElement(orderStatus);
    }

}
