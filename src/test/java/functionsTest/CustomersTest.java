package functionsTest;

import base.BaseTest;
import components.LeftNavigationBar;
import pages.CustomersFieldPage;
import pages.CustomersPage;
import utils.CustomRandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CustomersTest extends BaseTest {

    //Add a customer and confirms that the user exists in the system
    @Test
    public void addCustomerTest() {
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        navBar.clickMenuCustomers();
        navBar.clickCustomers();

        CustomersPage cuPage = new CustomersPage(driver, wait);
        cuPage.clickAddButton();

        CustomersFieldPage cuField = new CustomersFieldPage(driver, wait);
        String firstName = CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        String lastName = CustomRandomStringGenerator.nameGenerator(ThreadLocalRandom.current().nextInt(3, 7));
        cuField.typeFirstName(firstName);
        cuField.typeLastName(lastName);
        String randomEmailAddress = CustomRandomStringGenerator.generateEmail();
        cuField.typeEmail(randomEmailAddress);
        String password = CustomRandomStringGenerator.passwordGenerator(ThreadLocalRandom.current().nextInt(6, 12));
        cuField.typePassword(password);
        cuField.typeConfirmPassword(password);
        cuField.scrollToNewsletterCheckbox();
        cuField.setNewsletter(true);
        cuField.scrollSafeAntiFraud();
        cuField.setSafeAntiFraud(true);
        cuField.scrollToSaveButton();
        cuField.clickSave();

        //Notification assert needed

        navBar.clickCustomers();
        cuPage.typeEmailInputField(randomEmailAddress);
        cuPage.clickFilterButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='text-start' and contains(text(), '" + randomEmailAddress + "')]")));
        WebElement newUser = driver.findElement(By.xpath("//td[@class='text-start' and contains(text(), '" + randomEmailAddress + "')]"));
        Assert.assertTrue(newUser != null);
    }


}
