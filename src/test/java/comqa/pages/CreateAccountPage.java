package comqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import comqa.models.User;

/**
 * Created by tigra on 16.11.2016.
 */
public class CreateAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    String startPage = "http://localhost:70/litecart";
    String titleStartPage = "Online Store | My Store";
    String titleCreateAccountPage = "Create Account | My Store";

    By newAccountLink = By.xpath("//div[@id='box-account-login']//a[contains(@href,'create_account')]");
    By logoutLink = By.xpath("//div[@id='box-account']//a[contains(@href,'logout')]");
    By emailAddressField = By.name("email");
    By passwordField = By.name("password");
    By loginButon = By.name("login");

    By taxidFieldCreateAccountPage = By.name("tax_id");
    By companyFieldCreateAccountPage = By.name("company");
    By firstnameFieldCreateAccountPage = By.name("firstname");
    By lastnameFieldCreateAccountPage = By.name("lastname");
    By address1FieldCreateAccountPage = By.name("address1");
    By address2FieldCreateAccountPage = By.name("address2");
    By postcodeFieldCreateAccountPage = By.name("postcode");
    By cityFieldCreateAccountPage = By.name("city");
    By emailFieldCreateAccountPage = By.name("email");
    By phoneFieldCreateAccountPage = By.name("phone");
    By desiredPasswordFieldCreateAccountPage = By.name("password");
    By confirmPasswordFieldCreateAccountPage = By.name("confirmed_password");
    By createAccountButtonCreateAccountPage = By.name("create_account");

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String emailAddress, String password) {
        setEmailLogin(emailAddress);
        setPassword(password);
        clickLoginButton();
    }

    public void createAccount(User user) {
        clickNewAccountLink(newAccountLink);
        setTaxid(user.getTaxid());
        setCompany(user.getCompany());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setAddress1(user.getAddress1());
        setAddress2(user.getAddress2());
        setPostcode(user.getPostcode());
        setCity(user.getCity());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setDisiredPassword(user.getPassword());
        setConfirmPassword(user.getPassword());
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

    public void clickLoginButton() {
        driver.findElement(loginButon).click();
//        wait.until(presenceOfElementLocated(logoutLink));
    }

    public void clickLogoutLink() {
        driver.findElement(logoutLink).click();
//        wait.until(presenceOfElementLocated(newAccountLink));
    }

    public void clickCreateAccountButton(By createAccountButtonLocator) {
        driver.findElement(createAccountButtonLocator).click();
//        wait.until(titleIs(titleStartPage));
    }

    public void clickNewAccountLink(By newAccountLinkLocator) {
        driver.findElement(newAccountLinkLocator).click();
//        wait.until(titleIs(titleCreateAccountPage));
    }

    public void openUrl(String url, String title) {
        //WebDriver driver = driver = new ChromeDriver();
        driver.get(url);
//        wait.until(titleIs(title));
    }

}
