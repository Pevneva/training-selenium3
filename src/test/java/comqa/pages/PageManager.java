package comqa.pages;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by tigra on 02.12.2016.
 */
public class PageManager {

    private WebDriver driver;
    public EditProductPage editProductPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
   //     editProductPage = initElements(new EditProductPage(this));
    }

    private <T extends Page> T initElements(T page) {
         PageFactory.initElements(driver, page);
         return page;
        }

        public WebDriver getWebDriver() {
            return driver;
        }

}
