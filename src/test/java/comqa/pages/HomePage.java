package comqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by tigra on 16.12.2016.
 */
public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By allProductsOnSearchPage = By.xpath("//div[@id='box-logotypes']/following-sibling::*//div[@class='content']/ul/li/a");

    public void openProduct(String productName) {
        for (int i = 0; i < driver.findElements(allProductsOnSearchPage).size(); i++) {
            if (driver.findElements(allProductsOnSearchPage).get(i).getAttribute("title").equals(productName)) {
                driver.findElements(allProductsOnSearchPage).get(i).click();
                break;
            }
        }
    }

}
