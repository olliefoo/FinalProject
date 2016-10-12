package model;

import java.util.Date;

/**
 * Created by Owner on 10/6/2016.
 */
public class SourceReport {

    private static int total = 0;
    private int number;
    private Date fullDate;
    private User reporter;
    private String name;
    private String type;
    private String condition;
    private String date;
    private String time;
    private String location;

    public SourceReport() {
        total++;
        number = total;
        fullDate = new Date();
    }

    public static int getTotal() {
        return total;
    }
    public static void setTotal(int num) {
        total = num;
    }
    public void setReporter(User u) {
        this.reporter = u;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
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
    public User getReporter() {
        return reporter;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
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
}
