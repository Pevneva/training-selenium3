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
    By allLeftMenuFirstLevel=By.xpath("//ul[@id='box-apps-menu']/li/a/span[@class='name']");
    By allLeftMenuSecondLevel2=By.xpath("//ul[@id='box-apps-menu']/li/ul/li/a/span[@class='name']");

    String startTitle="My Store";
    String startUrl="http://localhost:70/litecart/admin";

    @Test
    public void testOpenAllLeftMenu()  {

        testAllLeftMenu(allLeftMenuFirstLevel, allLeftMenuSecondLevel2, headerOnPage);

    }

    public void testAllLeftMenu(By locatorAllMenuFirstLevel, By locatorAllMenuSecondLevel, By locatorHeader){

        for (int i=0;i<driver.findElements(locatorAllMenuFirstLevel).size();i++) {
            checkHeader(i,locatorAllMenuFirstLevel,locatorHeader);
            clickMenuFirstLevel(i);
            testAllSecondLevelMenu(locatorAllMenuSecondLevel);
        }
    }

    public void checkHeader(int menuNumber, By locatorAllMenuFirstLevel, By locatorHeader){
        if (menuNumber>1) {
            WebElement headerElement = driver.findElement(locatorHeader);
            driver.findElements(locatorAllMenuFirstLevel).get(menuNumber).click();
            wait.until(ExpectedConditions.stalenessOf(headerElement));
            wait.until(presenceOfElementLocated(locatorHeader));
        } else {
            driver.findElements(locatorAllMenuFirstLevel).get(menuNumber).click();
            wait.until(presenceOfElementLocated(locatorHeader));
        }
    }

    public void clickMenuFirstLevel(int numberMenu){
        driver.findElements(allLeftMenuFirstLevel).get(numberMenu).click();
        wait.until(presenceOfElementLocated(headerOnPage));
    }

    public void testAllSecondLevelMenu(By locatorAllMenuSecondLevel){
        if (driver.findElements(locatorAllMenuSecondLevel).size()>0) {
            wait.until(presenceOfElementLocated(headerOnPage));
            for (int j = 0; j < driver.findElements(locatorAllMenuSecondLevel).size(); j++) {
                driver.findElements(locatorAllMenuSecondLevel).get(j).click();
                wait.until(presenceOfElementLocated(headerOnPage));
            }
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
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
