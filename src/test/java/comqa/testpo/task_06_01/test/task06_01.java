package comqa.testpo.task_06_01.test;

import comqa.testpo.task_06_01.pages.CreateAccountPage;
import comqa.testpo.task_06_01.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by tigra on 16.11.2016.
 */
public class task06_01 {

    private WebDriver driver;
    private WebDriverWait wait;
    public CreateAccountPage createAccountPage;

    String startPage="http://localhost:70/litecart";
    String titleStartPage="Online Store | My Store";
    String titleCreateAccountPage="Create Account | My Store";

    String testEmail="lyudmila_test_accountant3@mail.ru";
    String testDesiredPassword="test12345!";
    String testTaxid="test tax";
    String testCompany="test company";
    String testFirstName="Ivanov";
    String testLastName="Ivan";
    String testAddress1="test address 1";
    String testAddress2="test address 2";
    String testPostcode="220020";
    String testCity="Minsk";
    String testPhone="+375295001122";

    User testuser1 = new User().setEmail("lyudmila_test_accountant5@mail.ru")
                                .setPassword("test12345!")
            .setTaxid("test tax")
            .setCompany("test company")
            .setAddress1("test address 1")
            .setAddress2("test address 2")
            .setFirstname("Ivanov")
            .setLastname("Ivan")
            .setCity("Minsk")
            .setPhone("+375295001020")
            .setPostcode("200025");

    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


    @Test
    public void testFirstProduct()  {

        createAccountPage = new CreateAccountPage(driver);

        createAccountPage.openUrl(startPage,titleStartPage);
        createAccountPage.createAccount(testuser1);
        createAccountPage.clickLogoutLink();
        createAccountPage.login(testEmail,testDesiredPassword);

    }

    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;

    }
}
