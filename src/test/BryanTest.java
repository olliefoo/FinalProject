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
        user.getProfile().setAddress("7895 Royal Melbourne Way", "Georgia", "Duluth", "30097");
        user.getProfile().setYear("1996");
        Database db = new Database();
        db.add(user);
        assertEquals(user.getProfile().getFirstname(), db.getUser(username).getProfile().getFirstname());
        assertEquals(user.getProfile().getLastname(), db.getUser(username).getProfile().getLastname());
        assertEquals(user.getProfile().getPhone(), db.getUser(username).getProfile().getPhone());
        assertEquals(user.getProfile().getAddress(), db.getUser(username).getProfile().getAddress());
        assertEquals("7895 Royal Melbourne Way, Duluth, Georgia, 30097", db.getUser(username).getProfile().getAddress());
    }
}
