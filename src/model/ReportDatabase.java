package model;

import java.util.ArrayList;

/**
 * Created by Kijana on 10/11/2016.
 */
public class ReportDatabase {
    private static ArrayList<SourceReport> sourceReports;
    private static ArrayList<PurityReport> purityReports;

    /**
     * Constructor to create a list of all source reports.
     */
    public ReportDatabase() {
        sourceReports = new ArrayList<>(10);
        purityReports = new ArrayList<>(10);
    }

    /**
     * Method to add a new report to the database
     * @param r source report to be added to the list
     */
    public static void add(Object r) { //report class in future?
        if (r instanceof SourceReport) {
            sourceReports.add((SourceReport) r);
        } else if (r instanceof PurityReport) {
            purityReports.add((PurityReport) r);
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
     * Returns the purity report specified by number
     * @param number the purity report number to be retrieved
     * @return purity report requested
     */
    public static PurityReport getPurityReport(int number) {
        return purityReports.get(number - 1);
    }

    /**
     * Returns the first source report in the list
     * @return first source report in list
     */
    /*public static SourceReport getSourceReport() {
        return sourceReports.get(0);
    }*/

    /**
     * Returns the size of the list of source reports
     * @return size of source report
     */
    public static int numSource() {
        return sourceReports.size();
    }

    /**
     * Returns the size of the list of source reports
     * @return size of source report
     */
    public static int numPurity() {
        return purityReports.size();
    }

}
