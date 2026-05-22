package pagetest;

import framework.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.OrdersPage;

import java.util.ArrayList;
import java.util.List;

@Epic("Sales Management")
@Feature("Orders Page Functionality")
public class OrdersPageTest extends BaseTest {

    @Test(
            testName = "Order Status Dropdown Verification",
            description = "Verifies that the order status dropdown on the Orders page contains all expected values."
    )
    @Story("Order Status Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the order status dropdown on the Orders page contains all expected values.")
    public void orderStatusCheck(){
        log.info("Navigating to the Orders page to check status values.");
        OrdersPage ordersPage =
                login()
                .goToOrdersPage();

        Select orderStatus = new Select(ordersPage.getOrderStatus());
        List<String> act_options = new ArrayList<>();

        log.info("Extracting options from the order status dropdown.");
        for(WebElement option : orderStatus.getOptions()) {
            act_options.add(option.getText());
        }

        log.info("Asserting that the actual options match the expected options.");
        Assert.assertEquals(act_options, ordersPage.getExp_options(), "Failure OrdersPageTest/orderStatusCheck: Actual order status options do not match expected options.");
    }
}