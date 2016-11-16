package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by tigra on 16.11.2016.
 */
public class task02_01 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testTempExample()  {
        driver.get("http://www.ya.ru/");
        driver.findElement(By.id("text")).sendKeys("webdriver");
        driver.findElement(By.xpath("//*[@class='button__text']")).click();

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;

    }
}
