package comqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by tigra on 16.12.2016.
 */
public class ProductDetailsPage extends Page {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    By bySizeSelectbox = By.xpath("//select[@name='options[Size]']");
    By bySizeSelectboxValues = By.xpath("//select[@name='options[Size]']/option");
    By byAddToCartButton = By.xpath("//td[@class='quantity']/button[@type='submit']");
    By byAddingToCartPopup = By.xpath("//div[@id='fancybox-wrap']/following-sibling::div");
    By byCheckoutLink = By.xpath("//a[@class='link'][contains(@href,'checkout')]");

    public void goToCart() {
        driver.findElement(byCheckoutLink).click();
    }

    public void addProductToCart() {

        if (driver.findElements(bySizeSelectbox).size() > 0) {
            selectValueInSelectbox(bySizeSelectbox, bySizeSelectboxValues, "Medium +$2.50");
        }

        driver.findElement(byAddToCartButton).click();

        WebElement popup = driver.findElement(byAddingToCartPopup);
        wait.until(visibilityOf(popup));
        wait.until(ExpectedConditions.stalenessOf(popup));

    }

    public void selectValueInSelectbox(By bySelectBox, By bySelectBoxOptions, String value) {
        driver.findElement(bySelectBox).click();
        for (int i = 0; i < driver.findElements(bySelectBoxOptions).size(); i++) {
            if (driver.findElements(bySelectBoxOptions).get(i).getText().equals(value)) {
                driver.findElements(bySelectBoxOptions).get(i).click();
            }
        }
    }
}
