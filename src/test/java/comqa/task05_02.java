package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertTrue;

/**
 * Created by tigra on 16.11.2016.
 */
public class task05_02 {

    private WebDriver driver;
    private WebDriverWait wait;

   // int widthRegularPriceStartPage=24;
    int widthRegularPriceStartPage=23;
    int heightRegularPriceStartPage=16;
    int widthCompaingPriceStartPage=30;
    int heightCompaingPriceStartPage=20;
    int widthRegularPriceProductDetailsPage=27;
    int heightRegularPriceProductDetailsPage=17;
    int widthCompaingPriceProductDetailsPage=37;
    int heightCompaingPriceProductDetailsPage=25;

    String startPage="http://localhost:70/litecart";
    String titleStartPage="Online Store | My Store";
    String titleFirstCompaingProductDetailsPage="Yellow Duck | Subcategory | Rubber Ducks | My Store";
    String regularColorStartPage="rgba(119, 119, 119, 1)";
    String compaignColorStartPage="rgba(204, 0, 0, 1)";
    String regularColorProductdetailstPage="rgba(102, 102, 102, 1)";
    String compaignColorProductDetailsPage="rgba(204, 0, 0, 1)";
    By regularPricesCompaingsProducts=By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li//s[@class='regular-price']");
    By compaignPricesCompaingsProducts=By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li//strong[@class='campaign-price']");
    By regularPrices=By.xpath("//div[@class='information']//s[@class='regular-price']");
    By compaignPrices=By.xpath("//div[@class='information']//strong[@class='campaign-price']");

    List<Boolean> listResults=new ArrayList<Boolean>();

    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void testFirstProduct()  {

        openUrl(startPage, titleStartPage);
        checkAllPropertiesStartPage(regularPricesCompaingsProducts,compaignPricesCompaingsProducts);
        openFirtsProductCampaingSection(driver.findElements(compaignPricesCompaingsProducts).get(0),titleFirstCompaingProductDetailsPage);
        checkAllPropertiesProductDetailsPage(regularPrices, compaignPrices);
        assertTrue(isAllresultsOk(listResults));

    }

    public boolean isAllresultsOk(List<Boolean> list){
        boolean result=true;
        for (int i=0;i<list.size();i++){
            result = result&list.get(i);
        }
        return result;
    }

    public void checkAllPropertiesStartPage(By regularPrice, By campaingPrice){

        listResults.add(isColorOk2(driver.findElements(regularPrice).get(0),regularColorStartPage));
        listResults.add(isColorOk2(driver.findElements(campaingPrice).get(0),compaignColorStartPage));
        listResults.add(isWidthOk2(driver.findElements(regularPrice).get(0),widthRegularPriceStartPage));
        listResults.add(isWidthOk2(driver.findElements(campaingPrice).get(0),widthCompaingPriceStartPage));
        listResults.add(isHeigthOk2(driver.findElements(regularPrice).get(0),heightRegularPriceStartPage));
        listResults.add(isHeigthOk2(driver.findElements(campaingPrice).get(0),heightCompaingPriceStartPage));


    }
    public void checkAllPropertiesProductDetailsPage(By regularPrice, By campaingPrice){

        listResults.add(isColorOk2(driver.findElements(regularPrice).get(0),regularColorProductdetailstPage));
        listResults.add(isColorOk2(driver.findElements(campaingPrice).get(0),compaignColorStartPage));
        listResults.add(isWidthOk2(driver.findElements(regularPrice).get(0),widthRegularPriceProductDetailsPage));
        listResults.add(isWidthOk2(driver.findElements(campaingPrice).get(0),widthCompaingPriceProductDetailsPage));
        listResults.add(isHeigthOk2(driver.findElements(regularPrice).get(0),heightRegularPriceProductDetailsPage));
        listResults.add(isHeigthOk2(driver.findElements(campaingPrice).get(0),heightCompaingPriceProductDetailsPage));

    }

    public boolean isColorOk(WebElement el, String color){
        return el.getCssValue("color").equals(color);
    }

    public boolean isColorOk2(WebElement el, String color){
        boolean res = el.getCssValue("color").equals(color);
        if (!res) {
            System.out.println("Color is wrong!");
            System.out.println("Color=" + el.getCssValue("color") + "; EXPECTED: " + color);
            System.out.println("WebElement=" + el);
        }
        return res;
    }

    public boolean isWidthOk(WebElement el, int width){
        return el.getSize().getWidth()==width;
    }

    public boolean isWidthOk2(WebElement el, int width){
        boolean res = el.getSize().getWidth()==width;
        if (!res) {
            System.out.println("Width is wrong!");
            System.out.println("Width=" + el.getSize().getWidth() + "; EXPECTED: " + width);
            System.out.println("WebElement=" + el);
        }
        return res;
    }

    public boolean isHeigthOk(WebElement el, int heigth){
        return el.getSize().getHeight()==heigth;
    }

    public boolean isHeigthOk2(WebElement el, int heigth){
        boolean res = el.getSize().getHeight()==heigth;
        if (!res) {
            System.out.println("Height is wrong!");
            System.out.println("Height=" + el.getSize().getHeight() + "; EXPECTED: " + heigth);
            System.out.println("WebElement=" + el);
        }
        return res;
    }

    private void openUrl(String url, String title) {
        driver.get(url);
        wait.until(titleIs(title));
    }

    private void openFirtsProductCampaingSection(WebElement el, String title) {
        el.click();
        wait.until(titleIs(title));
    }

    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;

    }
}
