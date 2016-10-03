package model;

/**
 * Created by Ollie Foo on 9/20/2016.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    public String address;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String gender;
    private String birthday;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String fn, String ln) {
        firstname = fn;
        lastname = ln;
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setAddress(String str, String sta, String cit, String z) {
        address = str + ", " + cit + ", " + sta + ", " + z;
        street = str;
        city = cit;
        state = sta;
        zip = z;

    }

    public void setPhone(String p) {
        phone = p;
    }

    public void setGender(String g) {
        gender = g;
    }

    public void setBirthday(String month, String day, String year) {
        birthday = month + "/" + day + "/" + year;
    }

    public boolean setPassword(String p) {
        if (password.equals(p)) {
            return false;
        }
        password = p;
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
