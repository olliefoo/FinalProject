package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ollie Foo on 9/20/2016.
 */
public class User extends Entity {

    private String username, password, email, firstname, lastname, address,
            city, state, zip, phone, month, day, year;
    private boolean isWorker, isManager, gender;


    public User(String username, String password, String email,
                String firstname, String lastname, boolean isWorker,
                boolean isManager) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isWorker = isWorker;
        this.isManager = isManager;
    }

    public User(ResultSet rs) throws SQLException {
        username = rs.getString(1);
        password = rs.getString(2);
        email = rs.getString(3);
        firstname = rs.getString(4);
        lastname = rs.getString(5);
        isWorker = rs.getBoolean(6);
        isManager = rs.getBoolean(7);
        address = rs.getString(8);
        city = rs.getString(9);
        state = rs.getString(10);
        zip = rs.getString(11);
        phone = rs.getString(12);
        month = rs.getString(13);
        day = rs.getString(14);
        year = rs.getString(15);
        gender = rs.getBoolean(16);
    }

    public static List<User> selectAllUsers() throws SQLException {
        return select("SELECT * FROM USER;", User::new);
    }

    public static User selectUser(String username) throws SQLException {
        List<User> list = select(String.format("SELECT * FROM USER WHERE Username='%s';", username), User::new);
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void insert() throws SQLException {
        execute(String.format("INSERT INTO USER VALUES ('%s', '%s', '%s'," +
                " '%s', '%s', %d, %d, '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', %d);", username, password, email, firstname,
                lastname, isWorker ? 1 : 0, isManager ? 1 : 0, null, null,
                null, null, null, null, null, null, 0));
    }

    public void update() throws SQLException {
        execute((String.format("UPDATE USER SET Email='%s', Address='%s', " +
                "City='%s', State='%s', Zip='%s', PhoneNumber='%s', Month='%s', " +
                "Day='%s', Year='%s', Gender=%d WHERE Username='%s';", email,
                address, city, state, zip, phone, month, day, year,
                gender ? 1 : 0, username)));
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
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public boolean isWorker() {
        return isWorker;
    }
    public boolean isManager() {
        return isManager;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() { return address; }
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

    public void setEmail(String s) {
        email = s;
    }
    public void setPhone(String s) {
        phone = s;
    }
    public void setAddress(String s) {
        address = s;
    }
    public void setCity(String s) {
        city = s;
    }
    public void setState(String s) {
        state = s;
    }
    public void setZip(String s) {
        zip = s;
    }
    public void setMonth(String s) {
        month = s;
    }
    public void setDay(String s) {
        day = s;
    }
    public void setYear(String s) {
        year = s;
    }
    public void setGender(boolean b) {
        gender = b;
    }

}
