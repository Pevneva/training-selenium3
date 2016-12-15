package comqa;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


/**
 * Created by tigra on 30.11.2016.
 */

public class TestBase {

    public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();

    public EventFiringWebDriver driver;
    public WebDriverWait wait;

    public BrowserMobProxy proxy;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-" + System.currentTimeMillis()+".png");
            try {
                Files.copy(tmp,screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File name:" + screen);
        }
    }

    String startAdminUrl = "http://localhost:70/litecart/admin";
    String startTitle = "My Store";

    By usernameField = By.name("username");
    By passwordFieldField = By.name("password");
    By loginButton = By.name("login");


    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
//        driver = new ChromeDriver();

//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", proxy);
//        WebDriver driver = new ChromeDriver(caps);

//        driver = new EventFiringWebDriver(new ChromeDriver());
        driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
//        driver = new EventFiringWebDriver(new ChromeDriver(cap));
        driver.register(new MyListener());
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

    public void loginToAdminApp() {
        driver.get(startAdminUrl);
        if (driver.findElements(loginButton).size() > 0) {
            driver.findElement(usernameField).sendKeys("admin");
            driver.findElement(passwordFieldField).sendKeys("admin");
            driver.findElement(loginButton).click();
            wait.until(titleIs(startTitle));
        }
    }

    public boolean isAllresultsOk(List<Boolean> list) {
        boolean result = true;
        for (int i = 0; i < list.size(); i++) {
            result = result & list.get(i);
        }
        return result;
    }
}