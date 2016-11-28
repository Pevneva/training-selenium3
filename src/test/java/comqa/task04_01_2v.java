package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task04_01_2v {

    private WebDriver driver;
    private WebDriverWait wait;

    By usernameField=By.name("username");
    By passwordFieldField=By.name("password");
    By loginButton=By.name("login");
    By headerOnPage=By.xpath("//td[@id='content']/h1");

    By allLeftMenuFirstLevel=By.xpath("//ul[@id='box-apps-menu']/li");

    String startTitle="My Store";
    String startUrl="http://localhost:70/litecart/admin";

    @Test
    public void testOpenAllLeftMenu()  {

        try{

            List<WebElement> listMenu1 = driver.findElements(allLeftMenuFirstLevel);
            int amountMenu1 = listMenu1.size();
            System.out.println("The amount of the first menu: " + amountMenu1);

            for (int i=1;i<=amountMenu1;i++) {
                By actualMenuFirstLevel=By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/a/span[@class='name']");

                System.out.println("==================================================================================");
                System.out.println(i + ": '" + driver.findElement(actualMenuFirstLevel).getText()+"';");

                if (i>1) {
                    WebElement headerElement = driver.findElement(headerOnPage);
                    driver.findElement(actualMenuFirstLevel).click();
                    wait.until(ExpectedConditions.stalenessOf(headerElement));
                    wait.until(presenceOfElementLocated(headerOnPage));
                } else {
                    driver.findElement(actualMenuFirstLevel).click();
                    wait.until(presenceOfElementLocated(headerOnPage));
                }

                By allLeftMenuSecondLevel=By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/ul/li");
                List<WebElement> listMenu2 = driver.findElements(allLeftMenuSecondLevel);
                int amountMenu2 = listMenu2.size();

                System.out.println("The amount of the second menu: " + amountMenu2);

                if (amountMenu2>0) {
                    wait.until(presenceOfElementLocated(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/ul")));
                    for (int j = 1; j <= amountMenu2; j++) {

                        By actualMenuSecondLevel = By.xpath("//ul[@id='box-apps-menu']/li[" + i + "]/ul/li[" + j + "]/a/span[@class='name']");
                        driver.findElement(actualMenuSecondLevel).click();
                        wait.until(presenceOfElementLocated(headerOnPage));

                        System.out.println(i + "-" + j +": '"+ driver.findElement(actualMenuSecondLevel).getText()+"';");
                    }
                    System.out.println("==================================================================================");
                }
            }

        Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod
    public void mayBeLogin(){
        driver.get(startUrl);
        if (driver.findElements(loginButton).size()>0) {
            driver.findElement(usernameField).sendKeys("admin");
            driver.findElement(passwordFieldField).sendKeys("admin");
            driver.findElement(loginButton).click();
            wait.until(titleIs(startTitle));
        }
    }

    @BeforeTest
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();

        //   DesiredCapabilities caps=new DesiredCapabilities();
        //    caps.setCapability(FirefoxDriver.MARIONETTE,false);
        //    driver = new FirefoxDriver(caps);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
