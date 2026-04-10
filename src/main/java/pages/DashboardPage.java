package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String urlDashboard;

    //The WebElement for the username
    @FindBy(css = "#nav-profile span")
    public WebElement username;


    @FindBy(css = "//*[@id=\"button-setting\"]/i")
    public WebElement settingWheel;

    //Orders Widget
    @FindBy(xpath = "//div[@class='tile-heading'][contains(., 'Total Orders')]")
    public WebElement totalOrdersPercentage;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/div[2]/h2")
    public WebElement totalOrdersNumber;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/div[3]/a")
    private WebElement totalOrdersViewMore;

    //Sales Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div/div[1]")
    public WebElement totalSalesPercentage;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div/div[2]/h2")
    public WebElement totalSalesNumber;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div/div[3]/a")
    private WebElement totalSalesViewMore;

    //Customers Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[3]/div/div[1]")
    public WebElement totalCustomersPercentage;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[3]/div/div[2]/h2")
    public WebElement totalCustomersNumber;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[3]/div/div[3]/a")
    private WebElement totalCustomersViewMore;

    //People Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[4]/div/div[1]")
    public WebElement peopleOnline;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[4]/div/div[2]/h2")
    private WebElement totalPeopleOnlineNumber;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[4]/div/div[3]/a")
    private WebElement peopleOnlineViewMore;

    //World Map Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[2]/div[1]/div/div[1]")
    private WebElement worldMap;

    @FindBy(css = "#jqvmap1_ru")
    private WebElement worldMapRussia;

    //Sales Analytics Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[2]/div[2]/div/div[1]/i")
    public WebElement salesAnalytics;

    //Sales Analytics Time Frame Button
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[2]/div[2]/div/div[1]/div/a/i[2]")
    private WebElement salesAnalyticsTimeFrameButton;

    //Sales Analytics Time Frame List Selector
    public final static String SALES_ANALYTICS_TIME_FRAME_LIST_SELECTOR = "div#range > a.dropdown-item:nth-of-type(";

    //Recent Activity Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[3]/div[1]/div/div")
    public WebElement recentActivity;

    //Latest Orders Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[3]/div[1]/div/div")
    public WebElement latestOrders;

    //Latest order redirect
    @SuppressWarnings("SpellCheckingInspection")
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[3]/div[2]/div/div[2]/table/tbody/tr[1]/td[6]/a/i")
    private WebElement latestOrder;

    //Constructor used to pass the existing WebDrive and wait to this object
    public DashboardPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        urlDashboard = "https://auto.pragmatic.bg/manage/index.php?route=common/dashboard";
    }

    public String getUrlDashboard(){
        return urlDashboard;
    }

    public DashboardPage w8UserNameToBeDisplayed(){
        w8ForVisibility(username);
        return this;
    }

    public boolean isUserNameDisplayed(){
        w8ForVisibility(username);
        return isDisplayed(username);
    }

    //Method used to get the username
    public String usernameGetText(){
        return w8AndGetText(username);
    }

    public OrdersPage clickViewMoreOrders() {
        clickWebElement(totalOrdersViewMore);
        return new OrdersPage(driver,wait);
    }

    public OrdersPage clickViewMoreSales() {
        clickWebElement(totalSalesViewMore);
        return new OrdersPage(driver,wait);
    }

    public CustomersPage clickViewMoreCustomers() {
        clickWebElement(totalCustomersViewMore);
        return new CustomersPage(driver,wait);
    }

    public OnlineReportPage clickViewMorePeopleOnline() {
        clickWebElement(peopleOnlineViewMore);
        return new OnlineReportPage(driver,wait);
    }

    public boolean urlContains() {
        return urlContains(urlDashboard);

    }

    public void clicksAlesAnalyticsTimeFrameButton(){
        clickWebElement(salesAnalyticsTimeFrameButton);
    }

    public String SalesAnalyticsTimeFrameListSelector(int index){
        return SALES_ANALYTICS_TIME_FRAME_LIST_SELECTOR + index + ")";
    }
    public void clickSalesAnalyticsTimeFrameList(int index){
        driver.findElement(By.cssSelector(SalesAnalyticsTimeFrameListSelector(index))).click();
    }

    public void invisibilityOfAlesAnalyticsTimeFrameList(int index){
        w8UntilElementIsInvisible(By.cssSelector(SalesAnalyticsTimeFrameListSelector(index)));
    }

    public OrderPage clickLatestOrder(){
        clickWebElement(latestOrder);
        return new OrderPage(driver,wait);
    }

    public DashboardPage clickWorldMapRussia(){
        clickWebElement(worldMapRussia);
        return this;
    }

    public  CustomersPage navigateToCuPage(){
         return leftNavigationBar.clickMenuCustomers()
                 .clickCustomers();
    }

    public ProductsPage goToProdPage(){
        return leftNavigationBar.clickMenuCatalog()
                .clickProducts();
    }

    public OrdersPage goToOrdersPage(){
        return  leftNavigationBar.clickMenuSales()
                .clickOrders();
    }

    public String getFillWorldMapRussia(){
        return getFill(worldMapRussia);
    }
}
