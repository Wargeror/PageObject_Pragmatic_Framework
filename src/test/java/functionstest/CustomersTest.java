package functionstest;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import pages.CustomersFormPage;
import pages.CustomersPage;
import org.testng.annotations.Test;

@Epic("Customer Management")
@Feature("Customer Lifecycle")
public class CustomersTest extends BaseTest {

    @Test(
            testName = "Add and Delete Customer",
            description = "Tests the full lifecycle of a customer: adding a new customer, verifying their existence, and then deleting them."
    )
    @Story("Customer Creation and Deletion")
    @Severity(SeverityLevel.CRITICAL)
    public void addCustomerTest() {
        log.info("Starting add and delete customer test.");
        CustomersFormPage cuFormPage =
                 login()
                .navigateToCuPage()
                .clickAddButton()
                .fillForm();

        log.info("Asserting that the success alert is displayed after adding the customer.");
        Assert.assertTrue(cuFormPage.isAlertDisplayed(), "Failure CustomersTest/addCustomerTest: Success alert was not displayed after adding a customer.");
        
        log.info("Filtering for the new customer.");
        CustomersPage cuPage =
                cuFormPage
                .filterForNewCu();

        log.info("Asserting that the new customer is listed.");
        Assert.assertTrue(cuPage.isCustomerListed(cuFormPage.getRandomEmailAddress()), "Failure CustomersTest/addCustomerTest: Newly added customer was not found in the customer list.");

        log.info("Deleting the new customer.");
        cuPage.deleteCustomer();
    }

}
