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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertTrue;

/**
 * Created by tigra on 16.11.2016.
 */
public class task05_02 {

    private WebDriver driver;
    private WebDriverWait wait;

    int widthRegularPriceStartPage = 24;
    int heightRegularPriceStartPage = 16;
    int widthCompaingPriceStartPage = 30;
    int heightCompaingPriceStartPage = 20;
    int widthRegularPriceProductDetailsPage = 27;
    int heightRegularPriceProductDetailsPage = 17;
    int widthCompaingPriceProductDetailsPage = 37;
    int heightCompaingPriceProductDetailsPage = 25;

    String startPage = "http://localhost:70/litecart";
    String titleStartPage = "Online Store | My Store";
    String cssColor = "color";
    String cssTextdecoration = "text-decoration";
    String cssFontweight = "font-weight";
    String regularColorStartPage = "rgba(119, 119, 119, 1)";
    String compaignColorStartPage = "rgba(204, 0, 0, 1)";
    String regularColorProductdetailstPage = "rgba(102, 102, 102, 1)";
    String compaignColorProductDetailsPage = "rgba(204, 0, 0, 1)";
    String reqularFont = "line-through";
    String compaignFont = "bold";

    By allCompaingsProductNames = By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li//div[@class='name']");
    By productDetailsPage = By.xpath("//div[@id='box-product']");
    By productNameDetailsPage = By.xpath("//div[@id='box-product']//h1[@class='title']");
    By regularPricesCompaingsProducts = By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li//s[@class='regular-price']");
    By compaignPricesCompaingsProducts = By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li//strong[@class='campaign-price']");
    By regularPrices = By.xpath("//div[@class='information']//s[@class='regular-price']");
    By compaignPrices = By.xpath("//div[@class='information']//strong[@class='campaign-price']");

    List<Boolean> listResults = new ArrayList<Boolean>();
    String[] productData = new String[3];

    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void testFirstProduct() {

        openUrl(startPage, titleStartPage);
        checkAllPropertiesStartPage(regularPricesCompaingsProducts, compaignPricesCompaingsProducts);

        String productName = driver.findElements(allCompaingsProductNames).get(0).getText();
        String regularPrice = driver.findElements(regularPricesCompaingsProducts).get(0).getText();
        String campaingPrice = driver.findElements(compaignPricesCompaingsProducts).get(0).getText();

        openFirtsProductCampaingSection(driver.findElements(compaignPricesCompaingsProducts).get(0), productDetailsPage);
        checkAllPropertiesProductDetailsPage(regularPrices, compaignPrices);
        checkProduct(productName, regularPrice, campaingPrice);
        assertTrue(isAllresultsOk(listResults));

    }

    public boolean isAllresultsOk(List<Boolean> list) {
        boolean result = true;
        for (int i = 0; i < list.size(); i++) {
            result = result & list.get(i);
        }
        return result;
    }

    public boolean isProductOk(String data1, String data2) {
        boolean res = data1.equals(data2);
        if (!res) {
            System.out.println("We see '" + data2 + "' data but EXPECTED " + data1 + "!");
        }
        return res;
    }

    public void checkProduct(String name, String regPrice, String campPrice) {
        listResults.add(isProductOk(name, driver.findElements(productNameDetailsPage).get(0).getText()));
        listResults.add(isProductOk(regPrice, driver.findElements(regularPrices).get(0).getText()));
        listResults.add(isProductOk(campPrice, driver.findElements(compaignPrices).get(0).getText()));
    }


    public void checkAllPropertiesStartPage(By regularPrice, By campaingPrice) {

        listResults.add(isStyleOk(driver.findElements(regularPrice).get(0), cssColor, regularColorStartPage));
        listResults.add(isStyleOk(driver.findElements(campaingPrice).get(0), cssColor, compaignColorStartPage));
        listResults.add(isStyleOk(driver.findElements(regularPrice).get(0), cssTextdecoration, reqularFont));
        listResults.add(isStyleOk(driver.findElements(campaingPrice).get(0), cssFontweight, compaignFont));
        listResults.add(isWidthOk(driver.findElements(regularPrice).get(0), widthRegularPriceStartPage));
        listResults.add(isWidthOk(driver.findElements(campaingPrice).get(0), widthCompaingPriceStartPage));
        listResults.add(isHeigthOk(driver.findElements(regularPrice).get(0), heightRegularPriceStartPage));
        listResults.add(isHeigthOk(driver.findElements(campaingPrice).get(0), heightCompaingPriceStartPage));

    }

    public void checkAllPropertiesProductDetailsPage(By regularPrice, By campaingPrice) {

        listResults.add(isStyleOk(driver.findElements(regularPrice).get(0), cssColor, regularColorProductdetailstPage));
        listResults.add(isStyleOk(driver.findElements(campaingPrice).get(0), cssColor, compaignColorProductDetailsPage));
        listResults.add(isStyleOk(driver.findElements(regularPrice).get(0), cssTextdecoration, reqularFont));
        listResults.add(isStyleOk(driver.findElements(campaingPrice).get(0), cssFontweight, compaignFont));
        listResults.add(isWidthOk(driver.findElements(regularPrice).get(0), widthRegularPriceProductDetailsPage));
        listResults.add(isWidthOk(driver.findElements(campaingPrice).get(0), widthCompaingPriceProductDetailsPage));
        listResults.add(isHeigthOk(driver.findElements(regularPrice).get(0), heightRegularPriceProductDetailsPage));
        listResults.add(isHeigthOk(driver.findElements(campaingPrice).get(0), heightCompaingPriceProductDetailsPage));

    }

    public boolean isStyleOk(WebElement el, String cssAttr, String cssValue) {
        boolean res = el.getCssValue(cssAttr).equals(cssValue);
        if (!res) {
            System.out.println(cssAttr + " is wrong!");
            System.out.println(cssAttr + "=" + el.getCssValue(cssAttr) + "; EXPECTED: " + cssValue);
            System.out.println("WebElement=" + el);
        }
        return res;
    }

    public boolean isWidthOk(WebElement el, int width) {
        boolean res = el.getSize().getWidth() == width;
        if (!res) {
            System.out.println("Width is wrong!");
            System.out.println("Width=" + el.getSize().getWidth() + "; EXPECTED: " + width);
            System.out.println("WebElement=" + el);
        }
        return res;
    }

    public boolean isHeigthOk(WebElement el, int heigth) {
        boolean res = el.getSize().getHeight() == heigth;
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

    private void openFirtsProductCampaingSection(WebElement el, By by) {
        el.click();
        wait.until(presenceOfElementLocated(by));
    }

    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;

    }
}
