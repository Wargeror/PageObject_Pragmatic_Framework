package framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.pages.*;

public class WebApp {
    private WebDriver driver;
    private WebDriverWait wait;

    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CustomersFormPage customersFormPage;
    private CustomersPage customersPage;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private OnlineReportPage onlineReportPage;
    private OrderPage orderPage;
    private OrdersPage ordersPage;
    private Product4SalePage product4SalePage;
    private ProductsFormPage productsFormPage;
    private ProductsPage productsPage;
    private SearchPage searchPage;
    private SuccessfulCheckout successfulCheckout;
    private RegistrationPage registerPage;
    private RegisteredPage registeredPage;


    public WebApp(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public CartPage cartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver, wait, this);
        }
        return cartPage;
    }

    public CheckoutPage checkoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver, wait, this);
        }
        return checkoutPage;
    }

    public CustomersFormPage customersFormPage() {
        if (customersFormPage == null) {
            customersFormPage = new CustomersFormPage(driver, wait, this);
        }
        return customersFormPage;
    }

    public CustomersPage customersPage() {
        if (customersPage == null) {
            customersPage = new CustomersPage(driver, wait, this);
        }
        return customersPage;
    }

    public DashboardPage dashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(driver, wait, this);
        }
        return dashboardPage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver, wait, this);
        }
        return loginPage;
    }

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = new MainPage(driver, wait, this);
        }
        return mainPage;
    }

    public OnlineReportPage onlineReportPage() {
        if (onlineReportPage == null) {
            onlineReportPage = new OnlineReportPage(driver, wait, this);
        }
        return onlineReportPage;
    }

    public OrderPage orderPage() {
        if (orderPage == null) {
            orderPage = new OrderPage(driver, wait, this);
        }
        return orderPage;
    }

    public OrdersPage ordersPage() {
        if (ordersPage == null) {
            ordersPage = new OrdersPage(driver, wait, this);
        }
        return ordersPage;
    }

    public Product4SalePage product4$Page() {
        if (product4SalePage == null) {
            product4SalePage = new Product4SalePage(driver, wait, this);
        }
        return product4SalePage;
    }

    public ProductsFormPage productsFormPage() {
        if (productsFormPage == null) {
            productsFormPage = new ProductsFormPage(driver, wait, this);
        }
        return productsFormPage;
    }

    public ProductsPage productsPage() {
        if (productsPage == null) {
            productsPage = new ProductsPage(driver, wait, this);
        }
        return productsPage;
    }

    public SearchPage searchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage(driver, wait, this);
        }
        return searchPage;
    }

    public SuccessfulCheckout successfulCheckout() {
        if (successfulCheckout == null) {
            successfulCheckout = new SuccessfulCheckout(driver, wait, this);
        }
        return successfulCheckout;
    }

    public RegistrationPage registerPage() {
        if (registerPage == null) {
            registerPage = new RegistrationPage(driver, wait, this);
        }
        return registerPage;
    }

    public RegisteredPage registeredPage() {
        if (registeredPage == null) {
            registeredPage = new RegisteredPage(driver, wait, this);
        }
        return registeredPage;
    }
}
