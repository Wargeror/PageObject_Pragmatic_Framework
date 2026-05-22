package framework.pages;

import framework.base.BasePage;
import framework.base.WebApp;
import framework.components.LeftNavigationBar;
import framework.components.TopBar;
import framework.data.Input; // Added import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineReportPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar leftNavigationBar;

    private String onlineReportUrl;

    private Input input;

    public OnlineReportPage(WebDriver driver, WebDriverWait wait, WebApp webApp){
        super(driver, wait, webApp);
        this.topBar = new TopBar(driver,wait, webApp);
        this.leftNavigationBar = new LeftNavigationBar(driver,wait, webApp);
        this.input = new Input();
        onlineReportUrl = input.getUrl("online.report.url");
    }

    public boolean urlContains() {
        return urlContains(onlineReportUrl);
    }

}
