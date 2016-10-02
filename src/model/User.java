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
    private String address;
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
        if (!firstname.equals(fn)) {
            firstname = fn;
        }
        if (!(lastname.equals(ln))) {
            lastname = fn;
        }
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setAddress(String street, String state, String city, String zip) {
        address = street + ", " + state + ", " + city + ", " + zip;
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
}
