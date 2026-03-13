package pageTest;

import base.BasePage;
import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrderPage;
import pages.OrdersPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdersPageTest extends BaseTest {

    //Check Orders Status Values
    @Test
    public void orderStatusCheck(){
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        navBar.clickMenuSales();
        navBar.clickOrders();

        OrdersPage ordersPage = new OrdersPage(driver,wait);
        Select orderStatus = new Select(ordersPage.getOrderStatus());
        List<String> act_options = new ArrayList();

        for(WebElement option : orderStatus.getOptions()) {
            act_options.add(option.getText());
        }

        Assert.assertEquals(act_options, ordersPage.getExp_options());
    }
}
