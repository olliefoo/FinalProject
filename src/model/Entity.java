package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/2340db?autoReconnect=true&useSSL=false";
    private static final String USER = "2340user";
    private static final String PASS = "password";
    
    private static Connection conn;
    
    public static void initializeSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void endSQL() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    protected interface CreateEntity {
        Entity createEntity(ResultSet rs) throws SQLException;
    }
    
    protected static List select(String sql, CreateEntity ce) throws SQLException {
        List<Entity> ret = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            System.out.println("executing sql select:");
            System.out.println(sql);
            System.out.println();
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    ret.add(ce.createEntity(rs));
                }
            }
        }
        return ret;
    }

    public static void execute(String sql) throws SQLException {
        try (Statement s = conn.createStatement()) {
            System.out.println("executing sql:");
            System.out.println(sql);
            System.out.println();
            s.execute(sql);
        }
    }
    
    protected static String stringOrNull(String str) {
        return str == null || str.isEmpty() ? "NULL" : ("'" + str + "'");
    }
}
