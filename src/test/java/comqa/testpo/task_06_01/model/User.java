package comqa.testpo.task_06_01.model;

/**
 * Created by tigra on 30.11.2016.
 */
public class User {

    private String email;
    private String password;
    private String taxid;
    private String company;
    private String firstname;
    private String lastname;
    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String phone;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTaxid() {
        return taxid;
    }

    public User setTaxid(String taxid) {
        this.taxid = taxid;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public User setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public User setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public User setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public User setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

}
