package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Owner on 10/2/2016.
 */
public class Database implements Serializable {
    private static ArrayList<User> users;
    private static ArrayList<Worker> workers;
    private static ArrayList<Manager> managers;
    private static ArrayList<Admin> admins;
    private static ArrayList<String> usernameList;
    private static ArrayList<String> emailList;

    public Database() {
        users = new ArrayList<>(5);
        workers = new ArrayList<>(5);
        managers = new ArrayList<>(5);
        admins = new ArrayList<>(5);
        usernameList = new ArrayList<>(10);
        emailList = new ArrayList<>(10);
    }

    /**
     * Adds the user to the corresponding list in the database depending on
     * the user type.
     *
     * @param u the user to add
     */
    public static void add(User u) {
        if (u instanceof Admin) {
            admins.add((Admin) u);
        } else if (u instanceof Manager) {
            managers.add((Manager) u);
        } else if (u instanceof Worker) {
            workers.add((Worker) u);
        } else {
            users.add(u);
        }
    }

    /**
     * saves all of the users that have registered an account
     */
    public static void saveAll()  {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("all.bin"))) {
                out.writeObject(users);
                out.writeObject(workers);
                out.writeObject(managers);
                out.writeObject(admins);
                out.writeObject(usernameList);
                out.writeObject(emailList);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    /**
     * loads all of the users that have registered an account
     */
    public static void loadAll()  {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("all.bin"))) {
                ArrayList<User> uL = (ArrayList<User>) in.readObject();
                ArrayList<Worker> wL = (ArrayList<Worker>) in.readObject();
                ArrayList<Manager> mL = (ArrayList<Manager>) in.readObject();
                ArrayList<Admin> aL = (ArrayList<Admin>) in.readObject();
                ArrayList<String> usL = (ArrayList<String>) in.readObject();
                ArrayList<String> eL = (ArrayList<String>) in.readObject();
                users = uL;
                workers = wL;
                managers = mL;
                admins = aL;
                usernameList = usL;
                emailList = eL;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.getStackTrace();
        }
    }
    /**
     * Adds the username to the username list
     *
     * @param name the username to be added
     */
    public static void addUsername(String name) {
        usernameList.add(name);
    }

    /**
     * Adds the email to the email list
     *
     * @param email the email to be added
     */
    public static void addEmail(String email) {
        emailList.add(email);
    }

    /**
     * Checks whether the database contains the specified username
     *
     * @param name username of a user
     * @return whether or no the database contains the specified user
     */
    public static boolean containsUsername(String name) {
        return usernameList.contains(name);
    }

    /**
     * Checks whether the database contains the specified email
     *
     * @param email an email to check
     * @return whether the database contains the specified email
     */
    public static boolean containsEmail(String email) {
        return emailList.contains(email);
    }

    /**
     * Gets the user with the specified username
     *
     * @param name the username of a user
     * @return the user with the specified username. NULL if user is not in the database
     */
    public static User getUser(String name) {
        for (User u : users) {
            if (name.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Gets the worker with the specified username
     *
     * @param name the username of a worker
     * @return the worker with the specified username. NULL if worker is not in the database
     */
    public static User getWorker(String name) {
        for (Worker w : workers) {
            if (name.equals(w.getUsername())) {
                return w;
            }
        }
        return null;
    }

    /**
     * Gets the manager with the specified username
     *
     * @param name the username of a manager
     * @return the manager with the specified username. NULL if manager is not in the database
     */
    public static User getManager(String name) {
        for (Manager m : managers) {
            if (name.equals(m.getUsername())) {
                return m;
            }
        }
        return null;
    }

    /**
     * Gets the admin with the specified username
     *
     * @param name the username of a admin
     * @return the admin with the specified username. NULL if admin is not in the database
     */
    public static User getAdmin(String name) {
        for (Admin a : admins) {
            if (name.equals(a.getUsername())) {
                return a;
            }
        }
        return null;
    }

}
