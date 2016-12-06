package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 10/6/2016.
 */
public class SourceReport extends Entity {

    private static int total = 0;
    private final int number;
    private final Date fullDate;
    private String username;
    private String type;
    private String condition;
    private String location;
    private double latitude;
    private double longitude;
    private static boolean updated;

    /**
     * Constructor for a new source report
     */
    public SourceReport() {
        try {
            List<SourceReport> list = selectAllReports();
            total = list.size() + 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        number = total;
        fullDate = new Date();
    }

    public SourceReport(ResultSet rs) throws SQLException {
        number = rs.getInt(1);
        fullDate = rs.getDate(2);
        username = rs.getString(3);
        type = rs.getString(4);
        condition = rs.getString(5);
        location = rs.getString(6);
        latitude = rs.getDouble(7);
        longitude = rs.getDouble(8);
    }

    public static List<SourceReport> selectAllReports() throws SQLException{
        return select("SELECT * FROM SourceReport;", SourceReport::new);
    }

    public static SourceReport selectReport(int index) throws SQLException {
        List<SourceReport> list = select(String.format("SELECT * FROM SourceReport WHERE Number=%d", index), SourceReport::new);
        return list.get(0);
    }

    public static SourceReport selectNewest() throws SQLException {
        List<SourceReport> list = select(String.format("SELECT * FROM SourceReport WHERE Number=%d", total), SourceReport::new);
        return list.get(0);
    }

    public void insert() throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(fullDate.getTime());
        execute(String.format("INSERT INTO SourceReport VALUES (%d, '%s', '%s'," +
                " '%s', '%s', '%s', %f, %f);", number, sqlDate, username, type,
                condition, location, latitude, longitude));
    }

    /**
     * Returns the string representation of the source report
     * @return the string representing the report
     */
    public String toString() {
        return "<b>Source Report:</b> " + number + "<br />"
                + "<b>Location:</b> " + location + "<br />"
                + "<b>Water Type:</b> " + type + "<br />"
                + "<b>Water Condition:</b> " + condition + "<br />";
    }

    /**
     * List of getters and setters for source report values.
     */
    public static int getTotal() {
        return total;
    }
    public static void setTotal(int num) {
        total = num;
    }
    public void setUsername(String s) {
        username = s;

    }
    public void setType(String type) {
        this.type = type;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public static void setUpdated(boolean b) {
        updated = b;
    }


    public int getNumber() {
        return number;
    }
    public String getUsername() {
        return username;
    }
    public String getType() {
        return type;
    }
    public String getCondition() {
        return condition;
    }
    public String getTime() {
        SimpleDateFormat ft2 = new SimpleDateFormat("h:mm a");
        return ft2.format(fullDate);
    }
    public String getDate() {
        SimpleDateFormat ft1 = new SimpleDateFormat("E MM/dd/yyyy");
        return ft1.format(fullDate);
    }
    public String getLocation() {
        return location;
    }
    public Date getFullDate() {
        return fullDate;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public static boolean getUpdated() {
        return updated;
    }
}
