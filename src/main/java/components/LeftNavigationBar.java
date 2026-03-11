package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

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

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[7]/a")
       private WebElement reviews;

       @FindBy(xpath = "//*[@id=\"collapse-1\"]/li[8]/a")
       private WebElement information;

    //Extensions Menu and Options
    @FindBy(css = "//*[@id=\"menu-extension\"]/a")
    private WebElement menuExtensions;

    //Customers Menu and Options
    @FindBy(css = "//*[@id=\"menu-customer\"]/a")
    private WebElement menuCustomers;

       @FindBy(css = "//*[@id=\"collapse-5\"]/li[1]/a")
       private WebElement customers;


    //System Menu and Options
    @FindBy(css = "//*[@id=\"menu-system\"]/a")
    private WebElement menuSystem;

       @FindBy(css = "//*[@id=\"collapse-7\"]/li[1]/a")
       private WebElement settings;

       @FindBy(css = "//*[@id=\"collapse-7\"]/li[2]/a")
       private WebElement menuUsers;

          @FindBy(css = "//*[@id=\"collapse-7-1\"]/li[1]/a")
          private WebElement users;

       @FindBy(css = "//*[@id=\"collapse-7\"]/li[3]/a")
       private WebElement menuLocalization;

    //Reports Menu and Options
    @FindBy(css = "//*[@id=\"menu-report\"]/a")
    private WebElement menuReports;

    public LeftNavigationBar(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getDashboardLNavBar(){
        return dashboardLNavBar;
    }

}