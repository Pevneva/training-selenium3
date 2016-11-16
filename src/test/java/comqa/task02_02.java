package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by tigra on 16.11.2016.
 */
public class task02_02 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testTempExample()  {
        driver.get("http://localhost:70/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;

    }
}
