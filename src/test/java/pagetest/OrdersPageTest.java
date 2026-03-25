package pagetest;

import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;

import java.util.ArrayList;
import java.util.List;

public class OrdersPageTest extends BaseTest {

    //Check Orders Status Values
    @Test
    public void orderStatusCheck(){
        OrdersPage ordersPage =
                login()
                .leftNavigationBar.clickMenuSales()
                                  .clickOrders();

        Select orderStatus = new Select(ordersPage.getOrderStatus());
        List<String> act_options = new ArrayList();

        for(WebElement option : orderStatus.getOptions()) {
            act_options.add(option.getText());
        }

        Assert.assertEquals(act_options, ordersPage.getExp_options());
    }
}
