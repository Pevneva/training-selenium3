package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;


/**
 * Created by tigra on 16.11.2016.
 */

public class task10_01 extends TestBase {


    By byallProductsLinks = By.xpath("//table[@class='dataTable']//tr[@class='row']//a[contains(@href,'edit_product')][@title='Edit']");
    String urlCatalogCategory = "http://localhost:70/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    List<Boolean> listResults = new ArrayList<Boolean>();


    @Test
    public void getBrowserLogs() {

        try {
            loginToAdminApp();
            listResults.clear();
            checkAllProductsFromCatalogCategory();
            tempVoid();
            assertTrue(isAllresultsOk(listResults));
        } catch (NumberFormatException e) {
            System.out.println("Some pages contains browser errors!");
            e.printStackTrace();
        }
    }

    public void checkAllProductsFromCatalogCategory() {

        driver.get(urlCatalogCategory);
        for (int i = 0; i < driver.findElements(byallProductsLinks).size(); i++) {
            driver.findElements(byallProductsLinks).get(i).click();

            boolean res = isBrowserNotErrors();
            listResults.add(res);
            if (!res) {
                System.out.println("Browser errors were found on the following page:");
                System.out.println(driver.getCurrentUrl());
            }

            driver.get(urlCatalogCategory);

        }
    }

    public void tempVoid() {

        driver.get("http://localhost:70/litecart/admin/?app=orders&doc=orders");
        driver.findElement(By.xpath("//a[@class='button']")).click();

        boolean res = isBrowserNotErrors();
        listResults.add(res);
        if (!res) {
            System.out.println("Browser errors were found on the following page:");
            System.out.println(driver.getCurrentUrl());
        }

    }

    public boolean isBrowserNotErrors() {

        List<LogEntry> listLogs = driver.manage().logs().get("browser").getAll();
        System.out.println("SIZE:" + listLogs.size());
        if (listLogs.size() > 0) {
            for (int i = 0; i < listLogs.size(); i++) {
                System.out.println("MESSAGE:" + listLogs.get(i));
            }
        }

        return listLogs.size() == 0;
    }


}
