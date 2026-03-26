package pagetest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageTest extends BaseTest {

    @Test
    public void displayTest(){
        MainPage mainPage = new MainPage(driver,wait);
        driver.get(mainPage.mainUrl());

        Assert.assertTrue(mainPage.highBar.isHighBarDisplayed());
        Assert.assertTrue(mainPage.cmp.isLogoDisplayed());
        Assert.assertTrue(mainPage.cmp.isSearchFieldDisplayed());
        Assert.assertTrue(mainPage.cmp.isSearchButtonDisplayed());
        Assert.assertTrue(mainPage.cmp.isCartDisplayed());
        Assert.assertTrue(mainPage.topBarM.isTopBarDisplayed());
        Assert.assertTrue(mainPage.isBannerDisplayed());
        Assert.assertTrue(mainPage.isMacBookDisplayed());
        Assert.assertTrue(mainPage.isiPhoneDisplayed());
        Assert.assertTrue(mainPage.isCinema30Displayed());

        mainPage.scrollToSecondBanner();

        Assert.assertTrue(mainPage.isCanonDisplayed());
        Assert.assertTrue(mainPage.isSecondBannerDisplayed());
    }
}
