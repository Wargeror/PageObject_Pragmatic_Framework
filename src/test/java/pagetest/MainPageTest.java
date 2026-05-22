package pagetest;

import framework.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.MainPage;

@Epic("Main Page")
@Feature("Element Visibility")
public class MainPageTest extends BaseTest {

    @Test(
            testName = "Main Page Element Display Test",
            description = "Verifies that all critical elements on the main page are displayed correctly upon loading."
    )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that all critical elements on the main page are displayed correctly upon loading.")
    public void displayTest(){
        log.info("Navigating to the main page to verify element display.");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Asserting visibility of main page elements.");
        Assert.assertTrue(mainPage.highBar.isHighBarDisplayed(), "Failure MainPageTest/displayTest: High bar is not displayed.");
        Assert.assertTrue(mainPage.cmp.isLogoDisplayed(), "Failure MainPageTest/displayTest: Logo is not displayed.");
        Assert.assertTrue(mainPage.cmp.isSearchFieldDisplayed(), "Failure MainPageTest/displayTest: Search field is not displayed.");
        Assert.assertTrue(mainPage.cmp.isSearchButtonDisplayed(), "Failure MainPageTest/displayTest: Search button is not displayed.");
        Assert.assertTrue(mainPage.cmp.isCartDisplayed(), "Failure MainPageTest/displayTest: Cart is not displayed.");
        Assert.assertTrue(mainPage.topBarM.isTopBarDisplayed(), "Failure MainPageTest/displayTest: Top bar is not displayed.");
        Assert.assertTrue(mainPage.isBannerDisplayed(), "Failure MainPageTest/displayTest: Main banner is not displayed.");
        Assert.assertTrue(mainPage.isMacBookDisplayed(), "Failure MainPageTest/displayTest: MacBook image is not displayed.");
        Assert.assertTrue(mainPage.isiPhoneDisplayed(), "Failure MainPageTest/displayTest: iPhone image is not displayed.");
        Assert.assertTrue(mainPage.isCinema30Displayed(), "Failure MainPageTest/displayTest: Apple Cinema 30\" image is not displayed.");

        log.info("Scrolling to the second banner.");
        mainPage.scrollToSecondBanner();

        log.info("Asserting visibility of elements after scroll.");
        Assert.assertTrue(mainPage.isCanonDisplayed(), "Failure MainPageTest/displayTest: Canon EOS 5D image is not displayed after scrolling.");
        Assert.assertTrue(mainPage.isSecondBannerDisplayed(), "Failure MainPageTest/displayTest: Second banner is not displayed after scrolling.");
    }
}