package pages;

import base.BasePage;
import base.WebApp;
import components.LeftNavigationBar;
import components.TopBar;
import data.Input; // Added import
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String urlDashboard;

    private Input input;

    //The WebElement for the username
    @FindBy(css = "#nav-profile span")
    private WebElement username; // Changed to private


    @FindBy(xpath = "//*[@id=\"button-setting\"]/i")
    public WebElement settingWheel;

    //Orders Widget
    @FindBy(xpath = "//div[@class='tile-heading'][contains(., 'Total Orders')]")
    private WebElement totalOrdersPercentage; // Changed to private

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/div[2]/h2")
    public WebElement totalOrdersNumber;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/div[3]/a")
    private WebElement totalOrdersViewMore;

    //Sales Widget
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div/div[1]")
    private WebElement totalSalesPercentage; // Changed to private

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
    private WebElement salesAnalytics; // Changed to private

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
    public DashboardPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBar = new TopBar(driver,wait, webApp);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait, webApp);
        this.input = new Input();
        urlDashboard = input.getUrl("dashboard.url");
    }

    public String getUrlDashboard(){
        return urlDashboard;
    }

    public DashboardPage waitUserNameToBeDisplayed(){
        waitForVisibility(username);
        return this;
    }

    public boolean isUserNameDisplayed(){
        waitForVisibility(username);
        return isDisplayed(username);
    }

    //Method used to get the username
    public String usernameGetText(){
        return waitAndGetText(username);
    }

    // Getter for username
    public WebElement getUsername() {
        return username;
    }

    // Getter for totalOrdersPercentage
    public WebElement getTotalOrdersPercentage() {
        return totalOrdersPercentage;
    }

    // Getter for totalSalesPercentage
    public WebElement getTotalSalesPercentage() {
        return totalSalesPercentage;
    }

    // Getter for salesAnalytics
    public WebElement getSalesAnalytics() {
        return salesAnalytics;
    }

    public OrdersPage clickViewMoreOrders() {
        clickWebElement(totalOrdersViewMore);
        return webApp.ordersPage();
    }

    public OrdersPage clickViewMoreSales() {
        clickWebElement(totalSalesViewMore);
        return webApp.ordersPage();
    }

    public CustomersPage clickViewMoreCustomers() {
        clickWebElement(totalCustomersViewMore);
        return webApp.customersPage();
    }

    public OnlineReportPage clickViewMorePeopleOnline() {
        clickWebElement(peopleOnlineViewMore);
        return webApp.onlineReportPage();
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
        waitUntilElementIsInvisible(By.cssSelector(SalesAnalyticsTimeFrameListSelector(index)));
    }

    public OrderPage clickLatestOrder(){
        clickWebElement(latestOrder);
        return webApp.orderPage();
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
