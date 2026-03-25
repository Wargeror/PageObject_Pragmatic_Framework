package functionstest;

import base.BaseTest;
import org.testng.Assert;
import pages.CustomersFormPage;
import pages.CustomersPage;
import utils.CustomRandomStringGenerator;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CustomersTest extends BaseTest {

    //Add a customer and confirms that the user exists in the system and deletes it.
    @Test
    public void addCustomerTest() {

        CustomersFormPage cuField =
                 login()
                .leftNavigationBar.clickMenuCustomers()
                .clickCustomers()
                .clickAddButton()
                .fillForm();

        Assert.assertTrue(cuField.isAlertDisplayed());
        
        CustomersPage cuPage =
                cuField
                .clickAlertX()
                .leftNavigationBar.clickCustomers()
                .typeEmailInputField(cuField.getRandomEmailAddress())
                .clickFilterButton();

        Assert.assertTrue(cuPage.isCustomerListed(cuField.getRandomEmailAddress()));

        cuPage.deleteCustomer();
    }

}
