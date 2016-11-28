package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.sql.Array;
import java.util.*;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task05_01 {

    private WebDriver driver;
    private WebDriverWait wait;

    By usernameField=By.name("username");
    By passwordFieldField=By.name("password");
    By loginButton=By.name("login");
    By allCountries=By.xpath("//table[@class='dataTable']/tbody/tr/td[5]/a");
    By allZones=By.xpath("//table[@class='dataTable']/tbody/tr/td[6]");
    By allZonesEditCountryPage=By.xpath("//table[@id='table-zones']/tbody/tr/td[3]");
    By allCountriesGeo=By.xpath("//table[@class='dataTable']//tr/td[3]/a");
    By allZonesEditGeoZonePage=By.xpath("//table[@id='table-zones']//tr/td[3]/select/option[@selected='selected']");
    String startUrl="http://localhost:70/litecart/admin";
    String urlContries="http://localhost:70/litecart/admin/?app=countries&doc=countries";
    String urlZones="http://localhost:70/litecart/admin/?app=geo_zones&doc=geo_zones";
    String startTitle="My Store";
    String countriesTitle="Countries | My Store";
    String zonesTitle="Geo Zones | My Store";
    String editCountriesTitle="Edit Country | My Store";
    String editZonesTitle="Edit Geo Zone | My Store";
    String countriesAttribute="innerText";
    String zonesAttribute="textContent";
    List<Boolean> listResults=new ArrayList<Boolean>();

    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void testSortingOfCountries_1()  {


        try {
            login();
            listResults.clear();
            driver.get(urlContries);
            wait.until(titleIs(countriesTitle));

            List<WebElement> listContries = driver.findElements(allCountries);
            checkSortingAndPrintIfError(listContries, countriesAttribute);

            List<WebElement> listZones=driver.findElements(allZones);

            for (int i=0;i<listZones.size();i++){
                int j = Integer.parseInt(listZones.get(i).getAttribute(countriesAttribute));
                if (j>0) {
                    listContries.get(i).click();
                    wait.until(titleIs(editCountriesTitle));
                    List<WebElement> listZonesCountryEditPage=driver.findElements(allZonesEditCountryPage);
                    listZonesCountryEditPage.remove(listZonesCountryEditPage.size()-1);

                    checkSortingAndPrintIfError(listZonesCountryEditPage, countriesAttribute);

                    driver.navigate().back();
                    wait.until(titleIs(countriesTitle));
                    listZones=driver.findElements(allZones);
                    listContries = driver.findElements(allCountries);

                }
            }
            assertTrue(isAllresultsOk(listResults));
        } catch (NumberFormatException e) {
            System.out.println("No all data were sorted!");

            e.printStackTrace();
        }
    }

    @Test
    public void testSortingOfZones_2() {

        try {
        login();
        listResults.clear();
        driver.get(urlZones);
        wait.until(titleIs(zonesTitle));
        List<WebElement> listCountriesGeo = driver.findElements(allCountriesGeo);
        for (int i = 0; i < listCountriesGeo.size(); i++) {
            listCountriesGeo.get(i).click();
            wait.until(titleIs(editZonesTitle));
            List<WebElement> listZones = driver.findElements(allZonesEditGeoZonePage);
            checkSortingAndPrintIfError(listZones, zonesAttribute);
            driver.navigate().back();
            listCountriesGeo = driver.findElements(allCountriesGeo);
            assertTrue(isAllresultsOk(listResults));
            }
        } catch (NumberFormatException e) {
            System.out.println("No all data were sorted!");

            e.printStackTrace();
        }
    }


    public boolean isAllresultsOk(List<Boolean> list){
        boolean result=true;
        for (int i=0;i<list.size();i++){
            result = result&list.get(i);
        }
        return result;
    }

    public void checkSortingAndPrintIfError(List<WebElement> list, String attrName){
        boolean res = isSorted(list, attrName);
        listResults.add(res);
        if (!res) {
            System.out.println("The data have not been sorted on the page:");
            System.out.println(driver.getCurrentUrl());

        }
    }

    public boolean isSorted(List<WebElement> list, String attName){
        TreeSet <String>tree = new TreeSet<String>();
        for (int i=0;i<list.size();i++){
            tree.add(list.get(i).getAttribute(attName));
        }
        Iterator it=tree.iterator();
        boolean myResult=false;
        while (it.hasNext()) {
            for (int i=0;i<list.size();i++){
                    myResult=list.get(i).getAttribute(attName).equals(it.next());
            }
        }
        return myResult;

    }


    public void login(){
        driver.get(startUrl);
        if (driver.findElements(loginButton).size()>0) {
            driver.findElement(usernameField).sendKeys("admin");
            driver.findElement(passwordFieldField).sendKeys("admin");
            driver.findElement(loginButton).click();
            wait.until(titleIs(startTitle));
        }
    }


    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;

    }
}
