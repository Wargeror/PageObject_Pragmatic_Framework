package functionstest;

import base.BaseTest;
import org.testng.Assert;
import pages.CustomersFormPage;
import pages.CustomersPage;
import org.testng.annotations.Test;

public class CustomersTest extends BaseTest {

    //Add a customer and confirms that the user exists in the system and deletes it.
    @Test
    public void addCustomerTest() {

        CustomersFormPage cuFormPage =
                 login()
                .navigateToCuPage()
                .clickAddButton()
                .fillForm();

        Assert.assertTrue(cuFormPage.isAlertDisplayed());
        
        CustomersPage cuPage =
                cuFormPage
                .filterForNewCu();

        Assert.assertTrue(cuPage.isCustomerListed(cuFormPage.getRandomEmailAddress()));

        cuPage.deleteCustomer();
    }

}
