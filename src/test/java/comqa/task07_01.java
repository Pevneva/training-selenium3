package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.List;
//import static ExpectedCondition<java.lang.Boolean> not(ExpectedCondition<?> condition)


/**
 * Created by tigra on 16.11.2016.
 */

public class task07_01 extends TestBase {

    String startUrl = "http://localhost:70/litecart";

    By allProductsOnSearchPage = By.xpath("//div[@id='box-logotypes']/following-sibling::*//div[@class='content']/ul/li/a");
    By bySizeSelectbox = By.xpath("//select[@name='options[Size]']");
    By bySizeSelectboxValues = By.xpath("//select[@name='options[Size]']/option");
    By byAddToCartButton = By.xpath("//td[@class='quantity']/button[@type='submit']");
    By byAddingToCartPopup = By.xpath("//div[@id='fancybox-wrap']/following-sibling::div");
    By byCheckoutLink = By.xpath("//a[@class='link'][contains(@href,'checkout')]");
    By byAllThumbnails = By.xpath("//li[@class='shortcut']/a");
    By byAllRemoveButtons = By.name("remove_cart_item");
    By byOrderTableCheckoutPage = By.id("box-checkout-summary");
    By byBackLink=By.xpath("//div[@id='checkout-cart-wrapper']//a[contains(text(),'Back')]");


    @Test
    public void testOrderProducts() {

        openStartPage();
        openProductDetails("Yellow Duck");
        addProductToCartFromProductDetailsPage();

        openStartPage();
        openProductDetails("Blue Duck");
        addProductToCartFromProductDetailsPage();

        openStartPage();
        openProductDetails("Red Duck");
        addProductToCartFromProductDetailsPage();

        goToCart();

        removeFirstProductFromCart();
        removeFirstProductFromCart();
        removeSingleProductFromCart();

    }

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

    public void goToCart() {
        driver.findElement(byCheckoutLink).click();
    }

    public void openStartPage() {
        driver.get(startUrl);
    }

    public void openProductDetails(String productName) {
        for (int i = 0; i < driver.findElements(allProductsOnSearchPage).size(); i++) {
            if (driver.findElements(allProductsOnSearchPage).get(i).getAttribute("title").equals(productName)) {
                driver.findElements(allProductsOnSearchPage).get(i).click();
                break;
            }
        }


    }

    public void addProductToCartFromProductDetailsPage() {

        if (driver.findElements(bySizeSelectbox).size() > 0) {
            selectValueInSelectbox(bySizeSelectbox, bySizeSelectboxValues, "Medium +$2.50");
        }

        driver.findElement(byAddToCartButton).click();

        WebElement popup=driver.findElement(byAddingToCartPopup);
        wait.until(visibilityOf(popup));
        wait.until(ExpectedConditions.stalenessOf(popup));

    }


}
