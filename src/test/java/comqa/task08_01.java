package comqa;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */

public class task08_01 extends TestBase {

    String startAdminUrl = "http://localhost:70/litecart/admin";
    String startTitle = "My Store";

    By usernameField = By.name("username");
    By passwordFieldField = By.name("password");
    By loginButton = By.name("login");

    By byCountriesMenu = By.xpath("//span[@class='name'][contains(text(),'Countries')]");
    By byAddNewCountryButton = By.xpath("//a[@class='button'][contains(@href,'edit_country')]");
    By byAllLinksOpenedNewwindow = By.xpath("//a[@target='_blank']");


    @Test
    public void testCreatingProduct() {

        loginToAdminApp();
        clickOnCountriesMenu();
        clickAddNewCountryButton();
        checkNewWindows();

    }

    public void checkNewWindows() {
        for (int i=0;i<driver.findElements(byAllLinksOpenedNewwindow).size();i++){
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            driver.findElements(byAllLinksOpenedNewwindow).get(i).click();

            String newWindow = wait.until((WebDriver d) ->
            {
                Set<String> newExistingWindows = d.getWindowHandles();
                newExistingWindows.removeAll(existingWindows);
                Iterator it = newExistingWindows.iterator();
                return it.next().toString();
            });

            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);

        }

    }

    public void clickOnCountriesMenu() {
        driver.findElement(byCountriesMenu).click();
    }

    public void clickAddNewCountryButton() {
        driver.findElement(byAddNewCountryButton).click();
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
}
