package functionstest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    //Search for an object to prove that the search works
    @Test
    public void iMacSearch(){
        MainPage mainPage = new MainPage(driver,wait);
        driver.get(mainPage.mainUrl());
        SearchPage searchPage =
                mainPage
                .typeSearchField("iMac")
                .clickSearchButton();

        Assert.assertTrue(searchPage.isMacBookImgDisplayed());
    }

}
