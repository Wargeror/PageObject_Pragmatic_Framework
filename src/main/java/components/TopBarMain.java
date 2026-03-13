package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopBarMain extends BasePage {

    @FindBy(xpath = "//*[@id=\"narbar-menu\"]")
    private WebElement topBar;

       @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/a")
       private WebElement menuDesktops;

         @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/div/div/ul/li[1]/a")
         private WebElement optionPC;

         @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/div/div/ul/li[2]/a")
         private WebElement optionMac;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[2]/a")
      private WebElement menuLaptops;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[3]/a")
      private WebElement menuComponents;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[4]/a")
      private WebElement menuTablets;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[5]/a")
      private WebElement menuSoftware;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[6]/a")
      private WebElement menuPhones;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[7]/a")
      private WebElement menuCameras;

      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[8]/a")
      private WebElement menuMP3;

   public TopBarMain(WebDriver driver, WebDriverWait wait){
       super(driver, wait);
   }

   public boolean isTopBarDisplayed(){
       return isDisplayed(topBar);
   }

}
