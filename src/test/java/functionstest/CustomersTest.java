package functionstest;

import base.BaseTest;
import org.testng.Assert;
import pages.CustomersFormPage;
import pages.CustomersPage;
import org.testng.annotations.Test;

public class CustomersTest extends BaseTest {

    @Test(
            testName = "Add and Delete Customer",
            description = "Tests the full lifecycle of a customer: adding a new customer, verifying their existence, and then deleting them."
    )
    public void addCustomerTest() {

        CustomersFormPage cuFormPage =
                 login()
                .navigateToCuPage()
                .clickAddButton()
                .fillForm();

        Assert.assertTrue(cuFormPage.isAlertDisplayed(), "Failure CustomersTest/addCustomerTest: Success alert was not displayed after adding a customer.");
        
        CustomersPage cuPage =
                cuFormPage
                .filterForNewCu();

        Assert.assertTrue(cuPage.isCustomerListed(cuFormPage.getRandomEmailAddress()), "Failure CustomersTest/addCustomerTest: Newly added customer was not found in the customer list.");

        cuPage.deleteCustomer();
    }

}
