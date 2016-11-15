package test;

import model.Database;
import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bryan on 11/15/2016.
 */
public class BryanTest {
    @Test
    public void checkProfile() {
        String username = "test";
        String password = "testP";
        String email = "testE";
        User user = new User(username, password, email);
        user.getProfile().setName("Bryan", "Kim");
        user.getProfile().setPhone("6784808848");
        user.getProfile().setMonth("April");
        user.getProfile().setYear("1996");
        Database db = new Database();
        db.add(user);
        assertEquals(user.getProfile().getFirstname(), db.getUser(username).getProfile().getFirstname());
        assertEquals(user.getProfile().getLastname(), db.getUser(username).getProfile().getLastname());
        assertEquals(user.getProfile().getPhone(), db.getUser(username).getProfile().getPhone());
        assertEquals(user.getProfile().getMonth(), db.getUser(username).getProfile().getMonth());
        assertEquals(user.getProfile().getYear(), db.getUser(username).getProfile().getYear());
    }
}
