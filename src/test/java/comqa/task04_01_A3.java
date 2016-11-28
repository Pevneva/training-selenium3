package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task04_01_A3 {

    private WebDriver driver;
    private WebDriverWait wait;

    By usernameField=By.name("username");
    By passwordFieldField=By.name("password");
    By loginButton=By.name("login");
    By headerOnPage=By.xpath("//td[@id='content']/h1");

    By allLeftMenuFirstLevel=By.xpath("//ul[@id='box-apps-menu']/li");

    String startTitle="My Store";

    @Test
    public void testOpenAllLeftMenu()  {

            login();
            int amountMenu1 = driver.findElements(allLeftMenuFirstLevel).size();

            for (int i=1;i<=amountMenu1;i++) {
                By actualMenuFirstLevel=By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/a/span[@class='name']");
                System.out.println(i + ": '" + driver.findElement(actualMenuFirstLevel).getText()+"';");

                //WebElement headerElement = driver.findElement(headerOnPage);
                driver.findElement(actualMenuFirstLevel).click();
                wait.until(presenceOfElementLocated(headerOnPage));
                if (i>1) {
                    //wait.until(ExpectedConditions.stalenessOf(headerElement));
                }
                    wait.until(presenceOfElementLocated(headerOnPage));

                By allLeftMenuSecondLevel=By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/ul/li");
                int amountMenu2 = driver.findElements(allLeftMenuSecondLevel).size();

                System.out.println("The amount of the second menu: " + amountMenu2);

                if (amountMenu2>0) {
                    wait.until(presenceOfElementLocated(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]/ul")));
                    for (int j = 1; j <= amountMenu2; j++) {

                        By actualMenuSecondLevel = By.xpath("//ul[@id='box-apps-menu']/li[" + i + "]/ul/li[" + j + "]/a/span[@class='name']");
                        driver.findElement(actualMenuSecondLevel).click();
                        wait.until(presenceOfElementLocated(headerOnPage));

                        System.out.println(i + "-" + j +": '"+ driver.findElement(actualMenuSecondLevel).getText()+"';");
                    }
                }
            }
    }

    public void login(){
        driver.get("http://localhost:70/litecart/admin/");
        driver.findElement(usernameField).sendKeys("admin");
        driver.findElement(passwordFieldField).sendKeys("admin");
        driver.findElement(loginButton).click();
        wait.until(titleIs(startTitle));
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
