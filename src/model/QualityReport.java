package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Owner on 10/20/2016.
 */
public class QualityReport implements Serializable {

    private static int total = 0;
    private final int number;
    private final Date fullDate;

    private User worker;
    private String name;
    private String location;
    private String condition;
    private String date;
    private String time;
    private double latitude;
    private double longitude;
    private double virus;
    private double contaminant;

    /**
     * Constructor for a new source report
     */
    public QualityReport() {
        total++;
        number = total;
        fullDate = new Date();
    }
    /**
     * Returns the string representation of the quality report
     * @return the string representing the report
     */
    public String toString() {
        return("<h1>" + location + "<h2><br>Virus PPM Level: " + virus +
                "<br>Contamination Level: " + contaminant);
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
    public void setReporter(User u) {
        this.worker = u;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDate(String date) {
        this.date = date;
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
    public User getReporter() {
        return worker;
    }
    public String getName() {
        return name;
    }
    public String getCondition() {
        return condition;
    }
    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }
    public String getLocation() {
        return location;
    }
    public int getNumber() {
        return number;
    }
    public Date getFullDate() {
        return fullDate;
    }
    public double getLatitude() {return latitude;}
    public double getLongitude() {
        return longitude;
    }
    public void setVirus(double virus) {this.virus = virus;}
    public void setContaminent(double contaminent) {this.contaminant =
            contaminent; }
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
}
