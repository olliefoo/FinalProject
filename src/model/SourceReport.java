package model;

import java.util.Date;

/**
 * Created by Owner on 10/6/2016.
 */
public class SourceReport {

    private static int total = 0;
    private int number;
    private Date date;
    private User reporter;

    public SourceReport() {
        total++;
        number = total;
        date = new Date();
    }
}
