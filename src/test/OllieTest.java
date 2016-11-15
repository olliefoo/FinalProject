package test;

import model.Database;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Owner on 11/14/2016.
 */
public class OllieTest {
    @Test
    public void testGetUser() {
        String username = "Ollie";
        String password = "password";
        String email = "ofoo3@gatech.edu";
        User user = new User(username, password, email);

        Database database = new Database();
        Database.add(user);
        assertEquals(user, Database.getUser(username));
    }
}
