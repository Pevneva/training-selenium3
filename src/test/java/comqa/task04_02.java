package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task04_02 {

    private WebDriver driver;
    private WebDriverWait wait;

    By allMostPopularProducts=By.xpath("//div[@id='box-most-popular']/div[@class='content']/ul/li");
    By allCompaingsProducts=By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li");
    By allLatestsProducts=By.xpath("//div[@id='box-latest-products']/div[@class='content']/ul/li");

    String startTitle="Online Store | My Store";

    @Test
    public void testStickers()  {

        try{

            driver.get("http://localhost:70/litecart/en/");
            wait.until(titleIs(startTitle));

            int amountMostPopular = driver.findElements(allMostPopularProducts).size();
            int amountCompaings = driver.findElements(allCompaingsProducts).size();
            int amountLatestProducts = driver.findElements(allLatestsProducts).size();

            for (int i=1;i<=amountMostPopular;i++) {
                By currentMostPopularProduct=By.xpath("//div[@id='box-most-popular']/div[@class='content']/ul/li["+i+"]/a[@class='link']/div[@class='image-wrapper']/div");
                List<WebElement> listCurrentMostPopular = driver.findElements(currentMostPopularProduct);
                System.out.println("Checking most popular products " + i + "...");
                assertTrue(listCurrentMostPopular.size()==1);
                System.out.println(listCurrentMostPopular.size()==1);
            }
            for (int i=1;i<=amountCompaings;i++) {
                By currentCompaings=By.xpath("//div[@id='box-campaigns']/div[@class='content']/ul/li["+i+"]/a[@class='link']/div[@class='image-wrapper']/div");
                List<WebElement> listCompaings = driver.findElements(currentCompaings);
                System.out.println("Checking compaings " + i + "...");
                assertTrue(listCompaings.size()==1);
                System.out.println(listCompaings.size()==1);

            }
            for (int i=1;i<=amountLatestProducts;i++) {
                By currentLatestsProducts=By.xpath("//div[@id='box-latest-products']/div[@class='content']/ul/li["+i+"]/a[@class='link']/div[@class='image-wrapper']/div");
                List<WebElement> listCurrentLatestsProducts = driver.findElements(currentLatestsProducts);
                System.out.println("Checking latests products " + i + "...");
                assertTrue(listCurrentLatestsProducts.size()==1);
                System.out.println(listCurrentLatestsProducts.size()==1);
            }




        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }

    }

    @BeforeTest
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();

        //    DesiredCapabilities caps=new DesiredCapabilities();
        //    caps.setCapability(FirefoxDriver.MARIONETTE,false);
        //    driver = new FirefoxDriver(caps);

            driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
