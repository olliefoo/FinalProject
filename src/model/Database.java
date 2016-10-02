package model;

import java.util.ArrayList;

/**
 * Created by Owner on 10/2/2016.
 */
public class Database {
    private static ArrayList<User> list;

    public Database() {
        list = new ArrayList<>(5);
    }

    public static void add(User u) {
        list.add(u);
    }

    public boolean containsUsername(String s) {
        for (User u : list) {
            if (s.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEmail(String s) {
        for (User u : list) {
            if (s.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username) {
        for (User u : list) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

}
