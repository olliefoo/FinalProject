package model;

/**
 * Created by Ollie Foo on 9/20/2016.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private Profile profile;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        profile = new Profile();
        profile.setEmail(email);
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

    public Profile getProfile() { return profile; }

    public void setEmail(String s) {
        email = s;
    }

    public boolean setPassword(String p) {
        if (password.equals(p)) {
            return false;
        }
        password = p;
        return true;
    }

}
