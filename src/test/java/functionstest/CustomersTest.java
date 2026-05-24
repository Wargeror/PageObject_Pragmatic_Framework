package functionstest;

import framework.base.BaseTest;
import framework.pages.*;
import framework.utils.Utils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
                 adminLogin()
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

    @DataProvider(name = "customerNegativeScenarios")
    public Object[][] customerNegativeScenarios() {
        String validFirstName = Utils.nameGenerator(5);
        String validLastName = Utils.nameGenerator(5);
        String validPassword = Utils.passwordGenerator(10);
        String validEmail = Utils.emailGenerator();
        String longString = Utils.randomAlphaNumeric(33);


        return new Object[][]{
                {"", validLastName, validEmail, validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, "", validEmail, validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, validLastName, "not-valid-email", validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, validLastName, validEmail, "", "Warning: Please check the form carefully for errors!"},
                {longString, validLastName, validEmail, validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, longString, validEmail, validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, validLastName, longString, validPassword, "Warning: Please check the form carefully for errors!"},
                {validFirstName, validLastName, validEmail, longString, "Warning: Please check the form carefully for errors!"}
        };
    }

    @Test(
            testName = "Add Customer with invalid data from the admin side",
            dataProvider = "customerNegativeScenarios"
    )
    @Story("Customer Creation errors admin side")
    @Severity(SeverityLevel.NORMAL)
    @Description("Tests the error handling of the Customer creation process with various invalid inputs.")
    public void addCustomerNegativeTest(String firstName, String lastName, String email, String password, String expectedWarning) {
        log.info("Starting negative test case with email: " + email);
        CustomersFormPage cuFormPage =
                adminLogin()
                        .navigateToCuPage()
                        .clickAddButton();
        cuFormPage.fillForm(firstName, lastName, password, email, true, true, false);

        log.info("Asserting that the correct warning is displayed.");
        Assert.assertTrue(cuFormPage.isWarningDisplayed(), "Failure: Warning was not displayed for email: " + email);
        Assert.assertTrue(cuFormPage.getWarningText().contains(expectedWarning), "Failure: The warning text was not as expected for email: " + email);
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
        CustomersPage cuPage = adminLogin()
                .navigateToCuPage()
                .filterForNewCu(registrationPage.getEmail());

        log.info("Asserting that the new customer is listed.");
        Assert.assertTrue(cuPage.isCustomerListed(registrationPage.getEmail()), "Failure CustomersTest/registerCustomerTest: Newly added customer not found.");

        log.info("Deleting the new customer.");
        cuPage.deleteCustomer();

    }

    @DataProvider(name = "registrationNegativeScenarios")
    public Object[][] registrationNegativeScenarios() {
        String validFirstName = Utils.nameGenerator(5);
        String validLastName = Utils.nameGenerator(5);
        String validPassword = Utils.passwordGenerator(10);
        String validEmail = Utils.emailGenerator();
        String longString = Utils.randomAlphaNumeric(33);

        return new Object[][]{
                {"global", validFirstName, validLastName, validEmail, validPassword, false, "Warning: You must agree to the Privacy Policy!"},
                {"firstName", "", validLastName, validEmail, validPassword, true, "First Name must be between 1 and 32 characters!"},
                {"lastName", validFirstName, "", validEmail, validPassword, true, "Last Name must be between 1 and 32 characters!"},
                {"email", validFirstName, validLastName, "not-an-email", validPassword, true, "E-Mail Address does not appear to be valid!"},
                {"password", validFirstName, validLastName, validEmail, "123", true, "Password must be between 4 and 20 characters!"},
                {"firstName", longString, validLastName, validEmail, validPassword, true, "First Name must be between 1 and 32 characters!"},
                {"lastName", validFirstName, longString, validEmail, validPassword, true, "Last Name must be between 1 and 32 characters!"},
                {"email", validFirstName, validLastName, longString, validPassword, true, "E-Mail Address does not appear to be valid!"},
                {"password", validFirstName, validLastName, validEmail, longString, true, "Password must be between 4 and 20 characters!"}
        };
    }

    @Test(
            testName = "Register customer from user side with invalid data",
            dataProvider = "registrationNegativeScenarios"
    )
    @Story("Customer Creation error handling customer side")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Tests the error handling of the Customer creation process, from the customer side, with various invalid inputs.")
    public void registerCustomerNegativeTest(String errorType, String firstName, String lastName, String email, String password, boolean agreeToPolicy, String expectedWarning) {
        log.info("Navigating to the main page and click register button");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Filling registration form with invalid data: " + expectedWarning);
        RegistrationPage registrationPage =
                mainPage.clickRegisterButton();
        registrationPage.fillRegistrationForm(firstName, lastName, email, password, true, agreeToPolicy);

        registrationPage.clickContinueButton();

        log.info("Asserting that the correct warning is displayed.");
        if ("global".equals(errorType)) {
            Assert.assertTrue(registrationPage.isWarningDisplayed(), "Failure: Warning was not displayed for scenario: " + expectedWarning);
            Assert.assertTrue(registrationPage.getWarningText().contains(expectedWarning), "The warning text was not as expected for scenario: " + expectedWarning);
        } else if ("firstName".equals(errorType)) {
            Assert.assertTrue(registrationPage.getFirstNameError().contains(expectedWarning), "Failure: First name error mismatch");
        } else if ("lastName".equals(errorType)) {
            Assert.assertTrue(registrationPage.getLastNameError().contains(expectedWarning), "Failure: Last name error mismatch");
        } else if ("email".equals(errorType)) {
            Assert.assertTrue(registrationPage.getEmailError().contains(expectedWarning), "Failure: Email error mismatch");
        } else if ("password".equals(errorType)) {
            Assert.assertTrue(registrationPage.getPasswordError().contains(expectedWarning), "Failure: Password error mismatch");
        }
    }

}