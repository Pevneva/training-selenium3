package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
//import static ExpectedCondition<java.lang.Boolean> not(ExpectedCondition<?> condition)


/**
 * Created by tigra on 16.11.2016.
 */

public class task11_01 extends TestBase {

    @Test
    public void testOrderProducts() {

        app.openStartPage();
        app.openProductFromHomePage("Yellow Duck");
        app.addProductToCartFromProductDetailsPage();

        app.openStartPage();
        app.openProductFromHomePage("Blue Duck");
        app.addProductToCartFromProductDetailsPage();

        app.openStartPage();
        app.openProductFromHomePage("Red Duck");
        app.addProductToCartFromProductDetailsPage();

        app.goToCartFromProductDetailsPage();
        app.removeAllProductsFromCart();

    }

}
