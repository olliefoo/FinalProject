package model;

import javax.xml.transform.Source;
import java.util.ArrayList;

/**
 * Created by Kijana on 10/11/2016.
 */
public class ReportDatabase {
    private static ArrayList<SourceReport> sourceReports;

    /**
     * Constructor to create a list of all source reports.
     */
    public ReportDatabase() {
        sourceReports = new ArrayList<>(10);
    }

    /**
     * Method to add a new repor to the list
     * @param r source report to be added to the list
     */
    public static void add(SourceReport r) { //report class in future?
        if (r instanceof SourceReport) {
            sourceReports.add((SourceReport) r);
        }
    }

    /**
     * Returns the source report specified by number
     * @param number the source report number to be retrieved
     * @return source report requested
     */
    public static SourceReport getSourceReport(int number) {
        return sourceReports.get(number - 1);
    }

    /**
     * Returns the first source report in the list
     * @return first source report in list
     */
    public static SourceReport getSourceReport() {
        return sourceReports.get(0);
    }

    public static int size() {
        return sourceReports.size();
    }
}
