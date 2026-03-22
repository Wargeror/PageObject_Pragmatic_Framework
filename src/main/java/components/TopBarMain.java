package components;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopBarMain extends BasePage {

    @SuppressWarnings("SpellCheckingInspection")
    @FindBy(xpath = "//*[@id=\"narbar-menu\"]")
    private WebElement topBar;

       @SuppressWarnings("SpellCheckingInspection")
       @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/a")
       private WebElement menuDesktops;

         @SuppressWarnings("SpellCheckingInspection")
         @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/div/div/ul/li[1]/a")
         private WebElement optionPC;

         @SuppressWarnings("SpellCheckingInspection")
         @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[1]/div/div/ul/li[2]/a")
         private WebElement optionMac;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[2]/a")
      private WebElement menuLaptops;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[3]/a")
      private WebElement menuComponents;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[4]/a")
      private WebElement menuTablets;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[5]/a")
      private WebElement menuSoftware;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[6]/a")
      private WebElement menuPhones;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[7]/a")
      private WebElement menuCameras;

      @SuppressWarnings("SpellCheckingInspection")
      @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[8]/a")
      private WebElement menuMP3;

   public TopBarMain(WebDriver driver, WebDriverWait wait){
       super(driver, wait);
   }

   public boolean isTopBarDisplayed(){
       return isDisplayed(topBar);
   }

}
