package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task02_01 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
    //    DesiredCapabilities caps=new DesiredCapabilities();
    //    caps.setCapability(FirefoxDriver.MARIONETTE,false);
     //   driver = new FirefoxDriver(caps);

    //    DesiredCapabilities caps=new DesiredCapabilities();
    //    driver = new FirefoxDriver(new FirefoxBinary(new File("E:\\Program Files\\Nightly\\firefox.exe")),new FirefoxProfile(),caps);

        //driver = new ChromeDriver();

        driver = new InternetExplorerDriver();

  //      DesiredCapabilities caps = new DesiredCapabilities();
  //      caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
  //      WebDriver driver = new InternetExplorerDriver(caps);

        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testTempExample()  {
        try{
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        Thread.sleep(10000);
    //    driver.findElement(By.name("btnK")).click();
    //    wait.until(titleIs("webdriver - Пошук Google"));
    //    Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;

    }
}
