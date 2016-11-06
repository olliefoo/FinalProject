package model;

import java.io.Serializable;

/**
 * Created by Owner on 10/2/2016.
 */
public class Worker extends User implements Serializable {

    public Worker(String username, String password, String email) {
        super(username, password, email);
    }
}
