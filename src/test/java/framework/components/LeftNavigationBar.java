package framework.components;

import framework.base.BasePage;
import framework.base.WebApp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.pages.CustomersPage;
import framework.pages.OrdersPage;
import framework.pages.ProductsPage;

public class LeftNavigationBar extends BasePage {

    //Left Navigation Bar h1
    @FindBy(xpath = "//*[@id=\"menu-dashboard\"]/a")
    private WebElement dashboardLNavBar;

    //Catalog Menu and Options
    @FindBy(css = "li#menu-catalog > a.parent.collapsed")
    private WebElement menuCatalog;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[1]/a")
       private WebElement categories;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[2]/a")
       private WebElement products;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[3]/a")
       private WebElement subscriptionPlan;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[4]/a")
       private WebElement filters;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[5]/a")
       private WebElement menuAttributes;

          @FindBy(xpath = "//*[@id=\"collapse-1-4\"]/li[1]/a")
           private WebElement attributes;

          @FindBy(xpath = "//*[@id=\"collapse-1-4\"]/li[2]/a")
           private WebElement attributeGroups;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[6]/a")
       private WebElement options;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[7]/a")
       private WebElement manufacturers;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[8]/a")
       private WebElement downloads;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[9]/a")
       private WebElement reviews;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[10]/a")
       private WebElement information;

    //Extensions Menu and Options
    @FindBy(xpath = "//*[@id=\"menu-extension\"]/a")
    private WebElement menuExtensions;

    //Customers Menu and Options
    @FindBy(xpath = "//*[@id=\"menu-customer\"]/a")
    private WebElement menuCustomers;

       @FindBy(xpath = "//*[@id=\"collapse-5\"]/li[1]/a")
       private WebElement customers;

    //System Menu and Options
    @FindBy(xpath = "//*[@id=\"menu-sale\"]/a")
    private WebElement menuSales;

       @FindBy(xpath = "//*[@id=\"collapse-4\"]/li[1]/a")
       private WebElement orders;



    //System Menu and Options
    @FindBy(xpath = "//*[@id=\"menu-system\"]/a")
    private WebElement menuSystem;

       @FindBy(xpath = "//*[@id=\"collapse-7\"]/li[1]/a")
       private WebElement settings;

       @FindBy(xpath = "//*[@id=\"collapse-7\"]/li[2]/a")
       private WebElement menuUsers;

          @FindBy(xpath = "//*[@id=\"collapse-7-1\"]/li[1]/a")
          private WebElement users;

       @FindBy(xpath = "//*[@id=\"collapse-7\"]/li[3]/a")
       private WebElement menuLocalization;

    //Reports Menu and Options
    @FindBy(xpath = "//*[@id=\"menu-report\"]/a")
    private WebElement menuReports;

    public LeftNavigationBar(WebDriver driver, WebDriverWait wait, WebApp webApp) {
        super(driver, wait, webApp);
    }

    public void waitNavBarToBeDisplayed(){
        waitForVisibility(dashboardLNavBar);
    }

    public WebElement getDashboardLNavBar(){
        return dashboardLNavBar;
    }

    public LeftNavigationBar clickMenuCatalog(){
        clickWebElement(menuCatalog);
        return this;
    }

    public ProductsPage clickProducts(){
        clickWebElement(products);
        return webApp.productsPage();
    }

    public void clickMenuExtensions(){
        clickWebElement(menuExtensions);
    }

    public LeftNavigationBar clickMenuCustomers(){
        clickWebElement(menuCustomers);
        return this;
    }

    public CustomersPage clickCustomers(){
        clickWebElement(customers);
        return webApp.customersPage();
    }

    public LeftNavigationBar clickMenuSales(){
        clickWebElement(menuSales);
        return this;
    }

    public OrdersPage clickOrders(){
        clickWebElement(orders);
        return webApp.ordersPage();
    }

    public void clickMenuSystem(){
        clickWebElement(menuSystem);

    }
}
