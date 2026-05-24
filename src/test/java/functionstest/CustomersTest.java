package functionstest;

import framework.base.BaseTest;
import framework.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Customer Management")
@Feature("Customer Lifecycle")
public class CustomersTest extends BaseTest {

    @Test(
            testName = "Add and Delete Customer from the admin side"
    )
    @Story("Customer Creation and Deletion admin side")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the full lifecycle of a customer: adding a new customer, verifying their existence, and then deleting them.")
    public void addCustomerTest() {
        log.info("Starting adding of the new customer.");
        CustomersFormPage cuFormPage =
                 login()
                .navigateToCuPage()
                .clickAddButton();
                cuFormPage.fillForm(cuFormPage.getFirstName(), cuFormPage.getLastName(), cuFormPage.getPassword(), cuFormPage.getRandomEmailAddress(), true, true, true);

        log.info("Asserting that the success alert is displayed after adding the customer.");
        Assert.assertTrue(cuFormPage.isAlertDisplayed(), "Failure CustomersTest/addCustomerTest: Success alert was not displayed after adding a customer.");
        
        log.info("Filtering for the new customer to delete it.");
        CustomersPage cuPage =
                cuFormPage
                .filterForNewCu(cuFormPage.getRandomEmailAddress());

        log.info("Asserting that the new customer is listed.");
        Assert.assertTrue(cuPage.isCustomerListed(cuFormPage.getRandomEmailAddress()), "Failure CustomersTest/addCustomerTest: Newly added customer was not found in the customer list.");

        log.info("Deleting the new customer.");
        cuPage.deleteCustomer();
    }

    @Test(
            testName = "Add Customer wit invalid email from the admin side"
    )
    @Story("Customer Creation errors admin side")
    @Severity(SeverityLevel.NORMAL)
    @Description("Tests the error handling of the Customer creation process, from the admin side")
    public void addCustomerNegativeTest() {
        log.info("Starting adding of the new customer.");
        CustomersFormPage cuFormPage =
                login()
                        .navigateToCuPage()
                        .clickAddButton();
        cuFormPage.fillForm(cuFormPage.getFirstName(), cuFormPage.getLastName(), cuFormPage.getPassword(), "not_an_email", true, true, false);

        log.info("Asserting that a warning is displayed.");
        Assert.assertTrue(cuFormPage.isWarningDisplayed(), "Failure: Warning was not displayed after trying to register customer with invalid email.");
    }


    @Test(
            testName = "Register customer from user side and Delete Customer from the admin side"
    )
    @Story("Customer Creation and Deletion customer side")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the full lifecycle of a customer: registering as new customer from user side, verifying their existence on admin panel, and then deleting them.")
    public void registerCustomerTest() {
        log.info("Navigating to the main page and click register button");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Filling registration form");
        RegistrationPage registrationPage =
                mainPage.clickRegisterButton();
                registrationPage.fillRegistrationForm( registrationPage.getFirstName(), registrationPage.getLastName(), registrationPage.getEmail(), registrationPage.getPassword(), true, true );

        RegisteredPage registeredPage =
                registrationPage.clickContinueButton();

        log.info("Asserting that the success header is displayed after adding the customer.");
        Assert.assertTrue(registeredPage.isAccountCreatedHeaderDisplayed(), "Failure CustomersTest/registerCustomerTest: Success header was not found after registering.");

        log.info("Search customer by email in admin panel.");
        CustomersPage cuPage = login()
                .navigateToCuPage()
                .filterForNewCu(registrationPage.getEmail());

        log.info("Asserting that the new customer is listed.");
        Assert.assertTrue(cuPage.isCustomerListed(registrationPage.getEmail()), "Failure CustomersTest/registerCustomerTest: Newly added customer not found.");

        log.info("Deleting the new customer.");
        cuPage.deleteCustomer();

    }

    @Test(
            testName = "Register customer from user side without agreeing to Private Policy"
    )
    @Story("Customer Creation error handling customer side")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the error handling of the Customer creation process, from the customer side.")
    public void registerCustomerNegativeTest() {
        log.info("Navigating to the main page and click register button");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Filling registration form");
        RegistrationPage registrationPage =
                mainPage.clickRegisterButton();
        registrationPage.fillRegistrationForm( registrationPage.getFirstName(), registrationPage.getLastName(), registrationPage.getEmail(), registrationPage.getPassword(), true, false );

        RegisteredPage registeredPage =
                registrationPage.clickContinueButton();

        log.info("Asserting that a warning is displayed.");
        Assert.assertTrue(registrationPage.isWarningDisplayed(), "Failure: Warning was not displayed after trying to register customer without accepting Privacy agreement.");

    }

}