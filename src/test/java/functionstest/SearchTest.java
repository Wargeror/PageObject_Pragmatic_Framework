package functionstest;

import framework.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.MainPage;
import framework.pages.SearchPage;

@Epic("Search")
@Feature("Product Search")
public class SearchTest extends BaseTest {

    @Test(
            testName = "iMac Search Test",
            description = "Searches for 'iMac' and asserts that the corresponding product image is displayed on the search results page."
    )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Searches for 'iMac' and asserts that the corresponding product image is displayed on the search results page.")
    public void iMacSearch(){
        log.info("Navigating to the main page and searching for 'iMac'.");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());
        SearchPage searchPage =
                mainPage
                .typeSearchField("iMac")
                .clickSearchButton();

        log.info("Asserting that the MacBook image is displayed on the search results page.");
        Assert.assertTrue(searchPage.isMacBookImgDisplayed(), "Failure SearchTest/iMacSearch: MacBook image was not displayed after searching for 'iMac'.");
    }

}