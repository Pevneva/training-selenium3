package comqa.pages;

/**
 * Created by tigra on 02.12.2016.
 */
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

//    public WebDriver getWebDriver() {
//        return driver;
//    }

//    public String getTitle() {
//        return driver.getTitle();
//    }
//
//    public Page ensurePageLoaded() {
//        return this;
//    }
//
//    public boolean waitPageLoaded() {
//        try {
//            ensurePageLoaded();
//            return true;
//        } catch (TimeoutException to) {
//            return false;
//        }
//    }
}
