package pagetest;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;

import java.util.ArrayList;
import java.util.List;

public class OrdersPageTest extends BaseTest {

    @Test(
            testName = "Order Status Dropdown Verification",
            description = "Verifies that the order status dropdown on the Orders page contains all expected values."
    )
    public void orderStatusCheck(){
        OrdersPage ordersPage =
                login()
                .goToOrdersPage();

        Select orderStatus = new Select(ordersPage.getOrderStatus());
        List<String> act_options = new ArrayList<>();

        for(WebElement option : orderStatus.getOptions()) {
            act_options.add(option.getText());
        }

        Assert.assertEquals(act_options, ordersPage.getExp_options(), "Failure OrdersPageTest/orderStatusCheck: Actual order status options do not match expected options.");
    }
}
