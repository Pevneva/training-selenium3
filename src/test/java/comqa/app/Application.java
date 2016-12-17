package comqa.app;

import comqa.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by tigra on 16.12.2016.
 */
public class Application {

    private WebDriver driver;

    private CreateAccountPage createAccountPage;
    private EditProductPage editProductPage;
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;

    String startUrl = "http://localhost:70/litecart";

    public Application() {
        driver = new ChromeDriver();
        createAccountPage = new CreateAccountPage(driver);
        homePage = new HomePage(driver);
        editProductPage = new EditProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void removeAllProductsFromCart() {
        cartPage.removeFirstProductFromCart();
        cartPage.removeFirstProductFromCart();
        cartPage.removeSingleProductFromCart();
    }

    public void goToCartFromProductDetailsPage() {
        productDetailsPage.goToCart();
    }

    public void openStartPage() {
        driver.get(startUrl);
    }

    public void openProductFromHomePage(String productName) {
        homePage.openProduct(productName);
    }

    public void addProductToCartFromProductDetailsPage() {
        productDetailsPage.addProductToCart();
    }
}
