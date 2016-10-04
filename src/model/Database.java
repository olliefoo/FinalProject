package model;

import java.util.ArrayList;

/**
 * Created by Owner on 10/2/2016.
 */
public class Database {
    private static ArrayList<User> list;

    private static String loggedIn = "";

    public Database() {
        list = new ArrayList<>(5);
    }

    /**
     * Adds the user to the database
     *
     * @param u the user to add
     */
    public void add(User u) {
        list.add(u);
    }

    /**
     * Checks the database to see if it has the specified user from the username
     *
     * @param s username of a user
     * @return whether or no the database contains the specified user
     */
    public boolean containsUsername(String s) {
        for (User u : list) {
            if (s.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the database contains the specified email
     *
     * @param s an email to check
     * @return whether the database contains the specified email
     */
    public boolean containsEmail(String s) {
        for (User u : list) {
            if (s.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the user with the specified username
     *
     * @param username the username of a user
     * @return the user with the specified username. NULL if user is not in the database
     */
    public User getUser(String username) {
        for (User u : list) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Updates the information of the user
     *
     * @param user the user that is being updated
     */
    public void updateUser(User user) {
        for(User u : list) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
            }
        }
    }

    public String getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(String in) {
        loggedIn = in;
    }

}
