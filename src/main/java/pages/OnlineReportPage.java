package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineReportPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String onlineReportUrl;

    public OnlineReportPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.topBar = new TopBar(driver,wait);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait);
        onlineReportUrl = "https://auto.pragmatic.bg/manage/index.php?route=report/online";
    }

    public boolean urlContains() {
        return urlContains(onlineReportUrl);
    }

}
