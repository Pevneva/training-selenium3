package comqa;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertTrue;

/**
 * Created by tigra on 16.11.2016.
 */

public class task06_02 extends TestBase {

    String startUrl = "http://localhost:70/litecart";
    String startAdminUrl = "http://localhost:70/litecart/admin";
    String startTitle = "My Store";

    By usernameField = By.name("username");
    By passwordFieldField = By.name("password");
    By loginButton = By.name("login");
    By catalogMenu=By.xpath("//div[@id='box-apps-menu-wrapper']//a[contains(@href,'app=catalog&doc=catalog')]");
    By addNewProductButton=By.xpath("//a[@class='button'][contains(@href,'edit_product')]");
    By enableStatusRadioButton=By.xpath("//div[@id='tab-general']//label[contains(text(),'Enabled')]/input");
    By disableStatusRadioButton=By.xpath("//div[@id='tab-general']//label[contains(text(),'Disabled')]/input");
    By nameField=By.name("name[en]");
    By codeField=By.name("code");
    By allCategoryNames=By.xpath("//div[@id='tab-general']//strong[contains(text(),'Categories')]/following-sibling::div//tr/td[2]");
    By allCategoryCheckboxes=By.xpath("//div[@id='tab-general']//strong[contains(text(),'Categories')]/following-sibling::div//input[@type='checkbox']");

    By allGenderNames=By.xpath("//div[@id='tab-general']//strong[contains(text(),'Product Groups')]/following-sibling::div//tr/td[2]");
    By allGenderCheckboxes=By.xpath("//div[@id='tab-general']//strong[contains(text(),'Product Groups')]/following-sibling::div//input[@type='checkbox']");

    By defaultCategorySelectBox=By.name("default_category_id");
    By defaultCategorySelectBoxOptions=By.xpath("//*[@name='default_category_id']/option");

    By quantityField=By.name("quantity");
    By quntityunitSelectBox=By.name("quantity_unit_id");
    By quntityunitSelectBoxOptions=By.xpath("//*[@name='quantity_unit_id']/option");
    By deliverystatusSelectBox=By.name("delivery_status_id");
    By deliverystatusSelectBoxOptions=By.xpath("//*[@name='delivery_status_id']/option");
    By soldoutstatusSelectBox=By.name("sold_out_status_id");
    By soldoutstatusSelectBoxOptions=By.xpath("//*[@name='sold_out_status_id']/option");

    By uploadimageInput=By.name("new_images[]");
    By dateValidFromField=By.name("date_valid_from");
    By dateValidToField=By.name("date_valid_to");

    By informationTabLink=By.xpath("//a[@href='#tab-information']");
    By manufacturerSelectBox=By.name("manufacturer_id");
    By manufacturerSelectBoxOptions=By.xpath("//*[@name='manufacturer_id']/option");
    By supplierSelectBox=By.name("supplier_id");
    By supplierSelectBoxOptions=By.xpath("//*[@name='supplier_id']/option");
    By keywordField=By.name("keywords");
    By shortdescriptionField=By.name("short_description[en]");
    By descriptionField=By.xpath("//div[@class='trumbowyg-editor']");
    By headtitleField=By.name("head_title[en]");
    By metsdescriptionField=By.name("meta_description[en]");

    By priceTabLink=By.xpath("//a[@href='#tab-prices']");

    By purchasepriceField=By.name("purchase_price");
    By purchasepriceSelectBox=By.name("purchase_price_currency_code");
    By purchasepriceSelectBoxOptions=By.xpath("//*[@name='purchase_price_currency_code']/option");
    By priceUSDField=By.name("prices[USD]");
    By priceEURField=By.name("prices[EUR]");

    By saveButton=By.name("save");

    By allProducts=By.xpath("//table[@class='dataTable']//td[3]/a[contains(@href,'product_id')]");


    @Test
    public void testCreatingProduct() {

        loginToAdminApp();
        createNewProduct();
        assertTrue(isExistsProduct("Product Test 02"));

    }

    public void createNewProduct(){

        driver.findElement(catalogMenu).click();
        driver.findElement(addNewProductButton).click();
        driver.findElement(enableStatusRadioButton).click();
        driver.findElement(nameField).sendKeys("Product Test 02");
        driver.findElement(codeField).sendKeys("Code Test 01");

        setCategoryEditProductPage("Subcategory");

        selectValueInSelectbox(defaultCategorySelectBox,defaultCategorySelectBoxOptions,"Subcategory");
        setGenderEditProductPage("Male");
        setGenderEditProductPage("Unisex");

        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys("15");

        selectValueInSelectbox(quntityunitSelectBox,quntityunitSelectBoxOptions,"pcs");
        selectValueInSelectbox(deliverystatusSelectBox,deliverystatusSelectBoxOptions,"3-5 days");
        selectValueInSelectbox(soldoutstatusSelectBox,soldoutstatusSelectBoxOptions,"Sold out");

        driver.findElement(uploadimageInput).sendKeys("e:\\WORK\\Pictures\\Koala.jpg");

        driver.findElement(dateValidFromField).click();
        setDateTodateFiled("31","12","2016");
        driver.findElement(dateValidToField).click();
        setDateTodateFiled("01","06","2017");

        driver.findElement(informationTabLink).click();

        selectValueInSelectbox(manufacturerSelectBox,manufacturerSelectBoxOptions,"ACME Corp.");

        driver.findElement(keywordField).sendKeys("test keywords");
        driver.findElement(shortdescriptionField).sendKeys("test short description");

        fillDescriptionField("test description !!!");

        driver.findElement(headtitleField).sendKeys("test head title");
        driver.findElement(metsdescriptionField).sendKeys("test meta description");

        driver.findElement(priceTabLink).click();
        driver.findElement(purchasepriceField).click();
        driver.findElement(purchasepriceField).clear();
        driver.findElement(purchasepriceField).sendKeys("222");

        selectValueInSelectbox(purchasepriceSelectBox,purchasepriceSelectBoxOptions,"US Dollars");

        driver.findElement(priceUSDField).sendKeys("130");
        driver.findElement(priceEURField).sendKeys("100");

        driver.findElement(saveButton).click();

    }

   public boolean isExistsProduct(String productName){
       boolean result=false;
       for (int i=0;i<driver.findElements(allProducts).size();i++){
           if (driver.findElements(allProducts).get(i).getText().equals(productName)){
               result=true;
           }
       }
       return result;
   }

    public void fillDescriptionField(String text){
        driver.findElement(descriptionField).click();
        WebElement test=driver.findElement(descriptionField);

        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor)driver;
        }

       js.executeScript("return document.getElementsByClassName('trumbowyg-editor')[0].textContent='"+text+"'");
       new Actions(driver)
                .sendKeys(Keys.HOME)
                .perform();
    }

    public void setDateTodateFiled(String dd, String mm, String yyyy){
       new Actions(driver)
               .sendKeys(Keys.HOME)
               .perform();

       new Actions(driver)
               .sendKeys(yyyy)
               .sendKeys(Keys.ARROW_LEFT)
               .perform();

       new Actions(driver)
               .sendKeys(dd)
               .sendKeys(Keys.ARROW_LEFT)
               .sendKeys(Keys.ARROW_LEFT)
               .perform();


       new Actions(driver)
               .sendKeys(mm)
               .sendKeys(Keys.ESCAPE)
               .perform();
   }

    public void setCategoryEditProductPage(String categoryName){
        for (int i=1; i<driver.findElements(allCategoryNames).size();i++){
            if (driver.findElements(allCategoryNames).get(i).getText().equals(categoryName)){
                driver.findElements(allCategoryCheckboxes).get(i).click();
            }
        }

    }

    public void setGenderEditProductPage(String genderName){
        for (int i=0; i<driver.findElements(allGenderNames).size();i++){
            if (driver.findElements(allGenderNames).get(i).getText().equals(genderName)){
                driver.findElements(allGenderCheckboxes).get(i).click();
            }
        }

    }



    public void loginToAdminApp() {
        driver.get(startAdminUrl);
        if (driver.findElements(loginButton).size() > 0) {
            driver.findElement(usernameField).sendKeys("admin");
            driver.findElement(passwordFieldField).sendKeys("admin");
            driver.findElement(loginButton).click();
            wait.until(titleIs(startTitle));
        }
    }
}
