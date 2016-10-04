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
    private boolean gender;
    private String month;
    private String day;
    private String year;

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

    public void setGender(boolean g) {
        gender = g;
    }

    public void setMonth(String m) {
        month = m;
    }

    public void setYear(String y) {
        year = y;
    }

    public void setDay(String d) {
        day = d;
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

    public String getMonth() { return month; }

    public String getYear() { return year; }

    public String getDay() { return day; }

    public boolean getGender() { return gender; }
}
