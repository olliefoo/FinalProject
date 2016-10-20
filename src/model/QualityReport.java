package model;

import java.util.Date;

/**
 * Created by Owner on 10/20/2016.
 */
public class QualityReport {

    private static int total = 0;
    private int number;
    private Worker worker;
    private String name;
    private String condition;
    private Date fullDate;
    private String date;
    private String time;
    private double latitude;
    private double longitude;

    /**
     * Constructor for a new source report
     */
    public QualityReport() {
        total++;
        number = total;
        fullDate = new Date();
    }

}
