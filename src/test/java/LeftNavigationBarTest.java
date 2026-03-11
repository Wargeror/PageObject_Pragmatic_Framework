import base.BaseTest;
import components.LeftNavigationBar;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeftNavigationBarTest extends BaseTest {

    //Assert that the Left Navigation Bar is displayed
    @Test
    public void NavBarIsDisplayed(){
        login();

        LeftNavigationBar navBar = new LeftNavigationBar(driver,wait);
        wait.until(ExpectedConditions.visibilityOf(navBar.getDashboardLNavBar()));

        Assert.assertTrue(navBar.getDashboardLNavBar().isDisplayed());
    }
}
