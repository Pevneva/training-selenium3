package comqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertTrue;

/**
 * Created by tigra on 16.11.2016.
 */
public class task06_01 {

    private WebDriver driver;
    private WebDriverWait wait;

    String startPage="http://localhost:70/litecart";
    String titleStartPage="Online Store | My Store";
    String titleCreateAccountPage="Create Account | My Store";

    By newAccountLink=By.xpath("//div[@id='box-account-login']//a[contains(@href,'create_account')]");
    By logoutLink=By.xpath("//div[@id='box-account']//a[contains(@href,'logout')]");
    By emailAddressField=By.name("email");
    By passwordField=By.name("password");
    By loginButon=By.name("login");

    By taxidFieldCreateAccountPage=By.name("tax_id");
    By companyFieldCreateAccountPage=By.name("company");
    By firstnameFieldCreateAccountPage=By.name("firstname");
    By lastnameFieldCreateAccountPage=By.name("lastname");
    By address1FieldCreateAccountPage=By.name("address1");
    By address2FieldCreateAccountPage=By.name("address2");
    By postcodeFieldCreateAccountPage=By.name("postcode");
    By cityFieldCreateAccountPage=By.name("city");
    By emailFieldCreateAccountPage=By.name("email");
    By phoneFieldCreateAccountPage=By.name("phone");
    By desiredPasswordFieldCreateAccountPage=By.name("password");
    By confirmPasswordFieldCreateAccountPage=By.name("confirmed_password");
    By createAccountButtonCreateAccountPage=By.name("create_account");

    String testTaxid="test tax";
    String testCompany="test company";
    String testFirstName="Ivanov";
    String testLastName="Ivan";
    String testAddress1="test address 1";
    String testAddress2="test address 2";
    String testPostcode="220020";
    String testCity="Minsk";
    String testEmail="lyudmila_test_accountant3@mail.ru";
    String testPhone="+375295001122";
    String testDesiredPassword="test12345!";
    String testConfirmPassword="test12345!";

    @BeforeClass
    public void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        }


    @Test
    public void testFirstProduct()  {

        openUrl(startPage, titleStartPage);
        createAccount();
        clickLogoutLink(logoutLink);
        login(testEmail,testDesiredPassword);

    }

    public void login(String emailAddress, String password){
        setEmailLogin(emailAddress);
        setPassword(password);
        clickLoginButton(loginButon);
    }

    public void createAccount(){
        clickNewAccountLink(newAccountLink);
        setTaxid(testTaxid);
        setCompany(testCompany);
        setFirstname(testFirstName);
        setLastname(testLastName);
        setAddress1(testAddress1);
        setAddress2(testAddress2);
        setPostcode(testPostcode);
        setCity(testCity);
        setEmail(testEmail);
        setPhone(testPhone);
        setDisiredPassword(testDesiredPassword);
        setConfirmPassword(testConfirmPassword);
        clickCreateAccountButton(createAccountButtonCreateAccountPage);
    }

    private void setEmailLogin(String emailLogin) {
        driver.findElement(emailAddressField).sendKeys(emailLogin);
    }

    private void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    private void setTaxid(String taxid) {
        driver.findElement(taxidFieldCreateAccountPage).sendKeys(taxid);
    }

    private void setCompany(String company) {
        driver.findElement(companyFieldCreateAccountPage).sendKeys(company);
    }

    private void setFirstname(String firstname) {
        driver.findElement(firstnameFieldCreateAccountPage).sendKeys(firstname);
    }

    private void setLastname(String lastname) {
        driver.findElement(lastnameFieldCreateAccountPage).sendKeys(lastname);
    }

    private void setAddress1(String address1) {
        driver.findElement(address1FieldCreateAccountPage).sendKeys(address1);
    }

    private void setAddress2(String address2) {
        driver.findElement(address2FieldCreateAccountPage).sendKeys(address2);
    }

    private void setPostcode(String postcode) {
        driver.findElement(postcodeFieldCreateAccountPage).sendKeys(postcode);
    }

    private void setCity(String city) {
        driver.findElement(cityFieldCreateAccountPage).sendKeys(city);
    }

    private void setEmail(String email) {
        driver.findElement(emailFieldCreateAccountPage).sendKeys(email);
    }

    private void setPhone(String phone) {
        driver.findElement(phoneFieldCreateAccountPage).sendKeys(phone);
    }

    private void setDisiredPassword(String password) {
        driver.findElement(desiredPasswordFieldCreateAccountPage).sendKeys(password);
    }

    private void setConfirmPassword(String password) {
        driver.findElement(confirmPasswordFieldCreateAccountPage).sendKeys(password);
    }

    public void clickLoginButton(By loginButtonLocator){
        driver.findElement(loginButtonLocator).click();
        wait.until(presenceOfElementLocated(logoutLink));
    }

    public void clickLogoutLink(By logoutLinkLocator){
        driver.findElement(logoutLinkLocator).click();
        wait.until(presenceOfElementLocated(newAccountLink));
    }

    public void clickCreateAccountButton(By createAccountButtonLocator){
        driver.findElement(createAccountButtonLocator).click();
        wait.until(titleIs(titleStartPage));
    }

    public void clickNewAccountLink(By newAccountLinkLocator){
        driver.findElement(newAccountLinkLocator).click();
        wait.until(titleIs(titleCreateAccountPage));
    }

    private void openUrl(String url, String title) {
        driver.get(url);
        wait.until(titleIs(title));
    }

    @AfterClass
    public void stop(){
        driver.quit();
        driver = null;

    }
}
