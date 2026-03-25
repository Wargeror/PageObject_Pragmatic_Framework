package pages;

import base.BasePage;
import components.LeftNavigationBar;
import components.TopBar;
import data.Description;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class ProductsFormPage extends BasePage {

    public TopBar topBar;

    public LeftNavigationBar navBar;

    private String productsFormUrl;

    private Description description;

    @FindBy(xpath = "//*[@id=\"alert\"]/div")
    private WebElement alert;

    @FindBy(xpath = "//div[contains(@class, 'alert')][normalize-space()='Warning: Please check the form carefully for errors!']")
    private WebElement alertWarning;

    @FindBy(xpath = "//div[contains(@class, 'alert')][normalize-space()='Success: You have modified products!']")
    private WebElement alertSuccess;

    @FindBy(xpath = "//*[@id=\"alert\"]/div/button")
    private WebElement alertX;

    @FindBy(css = ".float-end > button.btn.btn-primary:nth-child(1)")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"form-product\"]/ul/li[1]/a")
    private WebElement generalForm;

       @SuppressWarnings("SpellCheckingInspection")
       @FindBy(xpath = "/html/body/div[3]/div/a")
       private WebElement notfCancelButton;


       @FindBy(id = "input-name-1")
       private WebElement productNameField;

       @FindBy(css = "iframe.cke_wysiwyg_frame")
       private WebElement iFrameDescription;

       @SuppressWarnings("SpellCheckingInspection")
       @FindBy(css = "body.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_blocks.cke_show_borders > p:only-child")
       private WebElement decriptionField;

       private final static String descriptionPath = "C:\\Users\\Momchil\\IdeaProjects\\TestFramework\\product";

       @FindBy(xpath = "//*[@id=\"input-meta-title-1\"]")
       private WebElement metaTitleField;

       @FindBy(xpath = "//*[@id=\"input-tag-1\"]")
       private WebElement productTagField;

       private final static String excelPath = "C:\\Users\\Momchil\\IdeaProjects\\TestFramework\\product\\tags.xlsx";

    @FindBy(xpath = "//*[@id=\"form-product\"]/ul/li[2]/a")
    private WebElement dataForm;

       @FindBy(xpath = "//*[@id=\"input-model\"]")
       private WebElement modelField;

       @FindBy(xpath = "//*[@id=\"input-price\"]")
       private WebElement priceField;

       @FindBy(xpath = "//*[@id=\"input-tax-class\"]")
       private WebElement taxClassSelect;

       @FindBy(xpath = "//*[@id=\"input-quantity\"]")
       private WebElement quantityField;

       @FindBy(xpath = "//*[@id=\"input-minimum\"]")
       private WebElement minimumQField;

    @FindBy(xpath = "//*[@id=\"form-product\"]/ul/li[9]/a")
    private WebElement imgForm;

       @FindBy(xpath = "//*[@id=\"image\"]/div/button[1]")
       private WebElement editImg;

          @FindBy(id = "modal-image")
          private WebElement popUPWindow;

          @SuppressWarnings("SpellCheckingInspection")
          @FindBy(id = "filemanager")
          private WebElement fileManager;

          @SuppressWarnings("SpellCheckingInspection")
          @FindBy(xpath = "//*[@id=\"filemanager\"]/div")
          private WebElement fileManagerD;

          @FindBy(css = "#button-upload")
          private WebElement uploadImgButton;

          @FindBy(id = "input#input-directory")
          private WebElement inputFile;

          private final static String filePath = "C:\\Users\\Momchil\\IdeaProjects\\TestFramework\\product\\MomchilPCImag.png";

          @FindBy(css = ".input-group > input#input-search")
          private WebElement imgSearchField;

          @FindBy(xpath = "//*[@id=\"button-search\"]/i")
          private WebElement imgSearchButton;

          @SuppressWarnings("SpellCheckingInspection")
          @FindBy(xpath = "//*[@id=\"filemanager\"]/div/div[2]/div[3]/div/div[1]/a/img")
          private WebElement momchilPCImg;

   @FindBy(xpath = "//*[@id=\"form-product\"]/ul/li[11]/a")
   private WebElement seoForm;

      @FindBy(xpath = "//*[@id=\"input-keyword-0-1\"]")
      private WebElement seoKeywordField;

    public ProductsFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.topBar = new TopBar(driver, wait);
        this.navBar = new LeftNavigationBar(driver, wait);
        this.description = new Description();
        productsFormUrl = "https://auto.pragmatic.bg/manage/index.php?route=catalog/product.form";
    }

    public boolean isAlertWarningDisplayed(){
        return isDisplayed(alertWarning);
    }

    public boolean isAlertSuccessDisplayed(){
        return isDisplayed(alertSuccess);
    }

    public ProductsFormPage clickAlertX(){
        clickWebElement(alertX);
        return this;
    }

    public boolean isAlertDisplayed(){
        return isDisplayed(alert);
    }


    public boolean urlContains() {
        return urlContains(productsFormUrl);
    }

    public ProductsFormPage clickSaveButton(){
        clickWebElement(saveButton);
        return this;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public ProductsFormPage clickNotfCancelButton(){
        clickWebElement(notfCancelButton);
        return this;
    }


    public ProductsFormPage typeProductName(String productName){
        typeText(productNameField, productName);
        return this;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public WebElement getiFrameDescription(){
        return iFrameDescription;
    }

    public ProductsFormPage typeFromDescription(){
        String description = getDescription();
        driver.switchTo().frame(iFrameDescription); // Switch inside the method
        typeText(decriptionField, description);
        driver.switchTo().defaultContent(); // Switch back
        return this;
    }

    //This method reads the file content into of a txt file and then returns a String
    public String getDescription() {
        try {
            return Files.readString(Paths.get(descriptionPath));
        } catch (IOException e) {
            System.err.println("Could not read the file at: " + descriptionPath);
            e.printStackTrace();
            return description.getPcDescription();
        }
    }

    public ProductsFormPage typeMetaTitle(String metaTitle){
        typeText(metaTitleField, metaTitle);
        return this;
    }

    public ProductsFormPage typeProductTag(String productTag){
        typeText(productTagField, productTag);
        return this;
    }

    //This method iterates through every row and every column in the first sheet of your Excel file, joining them with a comma.
    public String getProductTags() {
        StringJoiner tagsJoiner = new StringJoiner(",");

        try (FileInputStream fis = new FileInputStream(new File(excelPath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get first sheet

            for (Row row : sheet) {
                for (Cell cell : row) {
                    // DataFormatter ensures we get the text regardless of cell type (String, Numeric, etc.)
                    DataFormatter formatter = new DataFormatter();
                    String cellValue = formatter.formatCellValue(cell).trim();

                    if (!cellValue.isEmpty()) {
                        tagsJoiner.add(cellValue);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            return "default,tags"; // Fallback
        }

        return tagsJoiner.toString();
    }

    public void scrollToDataField(){
        scrollToElement(dataForm);
    }

    public void clickDataField(){
        clickWebElement(dataForm);
    }

    public void typeModel(String model){
        typeText(modelField, model);
    }

    public void typePrice(String price){
        typeText(priceField, price);
    }

    public void scrollToTaxClassSelect(){
        scrollToElement(taxClassSelect);
    }

    public WebElement getTaxClassSelect(){
        return taxClassSelect;
    }

    public void typeQuantity(String quantity){
        typeText(quantityField, quantity);
    }

    public void scrollToMinQuant(){
        scrollToElement(minimumQField);
    }

    public void clickImgForm(){
        clickWebElement(imgForm);
    }

    public void clickEditImg(){
        clickWebElement(editImg);
    }

    public void typeImgSearchField(String search){
        typeText(imgSearchField, search);
    }

    public void clickImgSearchButton(){
        clickWebElement(imgSearchButton);
    }

    public void clickUploadImgButton(){
        clickWebElement(uploadImgButton);
    }

    public void w84MomchilPCImgVisibility(){
        w8ForVisibility(momchilPCImg);
    }

    public void clickMomchilPCImg(){
        clickWebElement(momchilPCImg);
    }

    public void clickSeoForm(){
        clickWebElement(seoForm);
    }

    public void typeSeoWord(String seoWord){
        typeText(seoKeywordField, seoWord);
    }


    //We send the filepath of the image directly to the WebElement so we can bypass the Explore window.
    //In this case the input#input-directory is the correct element, but it is type="hidden" so we cannot use it
    //But otherwise this would be the code for it.
    public void sendPath(){
        inputFile.sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alert")));
    }

    public ProductsFormPage fillProductForm(){
        typeProductName("Custom-Built Desktop PC1");
        clickNotfCancelButton();
        typeFromDescription();
        typeMetaTitle("Custom PC");
        typeProductTag("Desktop PC");
        topBar.scrollToTop();
        clickDataField();
        typeModel("Custom-built 01");
        scrollToTaxClassSelect();
        typePrice("300");
        scrollToMinQuant();
        typeQuantity("200000");
        Select orderStatus = new Select(getTaxClassSelect());
        orderStatus.selectByVisibleText("Taxable Goods");
        topBar.scrollToTop();
        clickImgForm();
        clickEditImg();
        typeImgSearchField("MomchilPCImg");
        clickImgSearchButton();
        w84MomchilPCImgVisibility();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickMomchilPCImg();
        clickSeoForm();
        typeSeoWord("custom-PC2");
        clickSaveButton();
        return this;
    }

}
