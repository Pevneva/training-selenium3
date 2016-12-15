package comqa;

import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;


/**
 * Created by tigra on 16.11.2016.
 */

public class task10_02 extends TestBase {


    By byallProductsLinks = By.xpath("//table[@class='dataTable']//tr[@class='row']//a[contains(@href,'edit_product')][@title='Edit']");
    String urlCatalogCategory = "http://localhost:70/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    List<Boolean> listResults = new ArrayList<Boolean>();


    @Test
    public void getProxy() {

        try {
            proxy.newHar();
            proxy.getHar();
            loginToAdminApp();
            listResults.clear();
            checkAllProductsFromCatalogCategory();
            Har har = proxy.endHar();
            har.getLog().getEntries().forEach(l -> {
                        System.out.println(l.getResponse().getStatus()+":"+l.getRequest().getUrl());
                    }
            );
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
                System.out.println("Browser errors were found!");
                System.out.println(driver.getCurrentUrl());
            }

            driver.get(urlCatalogCategory);
        }
    }

    public boolean isBrowserNotErrors() {
        if (driver.manage().logs().get("browser").filter(Level.ALL).size() > 0) {
            driver.manage().logs().get("browser").forEach(l -> {
                System.out.println(l);
            });
        }
        return driver.manage().logs().get("browser").filter(Level.ALL).size() == 0;
    }


}
