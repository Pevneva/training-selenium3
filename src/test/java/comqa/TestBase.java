package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by tigra on 30.11.2016.
 */

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;


    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @AfterClass
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void selectValueInSelectbox(By bySelectBox, By bySelectBoxOptions, String value){
        driver.findElement(bySelectBox).click();
        for (int i=0;i<driver.findElements(bySelectBoxOptions).size();i++){
            if (driver.findElements(bySelectBoxOptions).get(i).getText().equals(value)){
                driver.findElements(bySelectBoxOptions).get(i).click();
            }
        }
    }
}