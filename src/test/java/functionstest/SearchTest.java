package functionstest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test(
            testName = "iMac Search Test",
            description = "Searches for 'iMac' and asserts that the corresponding product image is displayed on the search results page."
    )
    public void iMacSearch(){
        MainPage mainPage = new MainPage(getDriver(), getWait());
        getDriver().get(mainPage.mainUrl());
        SearchPage searchPage =
                mainPage
                .typeSearchField("iMac")
                .clickSearchButton();

        Assert.assertTrue(searchPage.isMacBookImgDisplayed(), "Failure SearchTest/iMacSearch: MacBook image was not displayed after searching for 'iMac'.");
    }

}
