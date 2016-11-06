package model;

import java.io.Serializable;

/**
 * Created by Owner on 10/2/2016.
 */
public class Admin extends User implements Serializable {
    public Admin(String username, String password, String email) {
        super(username, password, email);
    }
}

