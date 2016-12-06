package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Owner on 10/20/2016.
 */
public class QualityReport extends Entity {

    private static int total = 0;
    private final int number;
    private final Date fullDate;
    private String username;
    private String condition;
    private double virus;
    private double contaminant;
    private String location;
    private double latitude;
    private double longitude;
    private static boolean updated;

    /**
     * Constructor for a new source report
     */
    public QualityReport() {
        try {
            List<QualityReport> list = selectAllReports();
            total = list.size() + 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        number = total;
        fullDate = new Date();
    }

    public QualityReport(ResultSet rs) throws SQLException {
        number = rs.getInt(1);
        fullDate = rs.getDate(2);
        username = rs.getString(3);
        condition = rs.getString(4);
        virus = rs.getDouble(5);
        contaminant = rs.getDouble(6);
        location = rs.getString(7);
        latitude = rs.getDouble(8);
        longitude = rs.getDouble(9);
    }


    public static List<QualityReport> selectAllReports() throws SQLException{
        return select("SELECT * FROM QualityReport;", QualityReport::new);
    }

    public static List<QualityReport> selectHistoryReports(String location, String year) throws SQLException {
        return select(String.format("SELECT * FROM QualityReport WHERE (Location='%s' AND EXTRACT(YEAR from FullDate)='%s');", location, year), QualityReport::new);
    }

    public static QualityReport selectReport(int index) throws SQLException {
        List<QualityReport> list = select(String.format("SELECT * FROM QualityReport WHERE Number=%d", index), QualityReport::new);
        return list.get(0);
    }

    public static QualityReport selectNewest() throws SQLException {
        List<QualityReport> list = select(String.format("SELECT * FROM QualityReport WHERE Number=%d", total), QualityReport::new);
        return list.get(0);
    }

    public void insert() throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(fullDate.getTime());
        execute(String.format("INSERT INTO QualityReport VALUES (%d, '%s', '%s'," +
                        " '%s', %f, %f,'%s', %f, %f);", number, sqlDate, username,
                condition, virus, contaminant, location, latitude, longitude));
    }

    /**
     * Returns the string representation of the quality report
     * @return the string representing the report
     */
    public String toString() {
        return "<b>Quality Report:</b> " + number + "<br />"
                + "<b>Location:</b> " + location + "<br />"
                + "<b>Water Condition:</b> " + condition + "<br />"
                + "<b>Contaminant PPM:</b> " + contaminant + "<br />"
                + "<b>Virus PPM:</b> " + virus + "<br />";
    }

    /**
     * List of getters and setters for source report values.
     */
    public static void setTotal(int num) {
        total = num;
    }
    public void setUserame(String name) {
        username = name;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void setContaminant(double contaminant) {this.contaminant =
            contaminant; }
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


    public static int getTotal() {
        return total;
    }
    public int getNumber() {
        return number;
    }
    public String getUsername() {
        return username;
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
    public double getLatitude() {return latitude;}
    public double getLongitude() {
        return longitude;
    }
    public void setVirus(double virus) {this.virus = virus;}
    public double getVirus() {return virus; }
    public double getContaminant() {return contaminant; }
    public String getYear() {
        SimpleDateFormat yr = new SimpleDateFormat("yyyy");
        return yr.format(fullDate);
    }
    public String getMonth() {
        SimpleDateFormat mn = new SimpleDateFormat("MMM");
        return mn.format(fullDate);
    }
    public static boolean getUpdated() {
        return updated;
    }
}
