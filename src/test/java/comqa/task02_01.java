package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task02_01 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testTempExample()  {
        try{
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        Thread.sleep(1000);
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Пошук Google"));
        Thread.sleep(3000);
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
