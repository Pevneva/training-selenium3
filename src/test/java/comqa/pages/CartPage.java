package comqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by tigra on 16.12.2016.
 */
public class CartPage extends Page {

    public CartPage(WebDriver driver){
        super(driver);
    }

    By byAllThumbnails = By.xpath("//li[@class='shortcut']/a");
    By byAllRemoveButtons = By.name("remove_cart_item");
    By byOrderTableCheckoutPage = By.id("box-checkout-summary");
    By byBackLink=By.xpath("//div[@id='checkout-cart-wrapper']//a[contains(text(),'Back')]");


    public void removeSingleProductFromCart(){
        WebElement orderTable = driver.findElement(byOrderTableCheckoutPage);
        driver.findElements(byAllRemoveButtons).get(0).click();
        wait.until(ExpectedConditions.stalenessOf(orderTable));
        wait.until(presenceOfElementLocated(byBackLink));
    }

    public void removeFirstProductFromCart() {

        driver.findElements(byAllThumbnails).get(0).click();
        WebElement orderTable = driver.findElement(byOrderTableCheckoutPage);
        driver.findElements(byAllRemoveButtons).get(0).click();
        wait.until(ExpectedConditions.stalenessOf(orderTable));
        wait.until(presenceOfElementLocated(byOrderTableCheckoutPage));

    }
}
