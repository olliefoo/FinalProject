package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kijana on 10/11/2016.
 */
public class ReportDatabase implements Serializable {
    private static ArrayList<SourceReport> sourceReports;
    private static ArrayList<QualityReport> qualityReports;

    /**
     * Constructor to create a list of all source reports.
     */
    public ReportDatabase() {
        sourceReports = new ArrayList<>(10);
        qualityReports = new ArrayList<>(10);
    }

    /**
     * Method to add a new report to the database
     * @param r source report to be added to the list
     */
    public static void add(Object r) { //report class in future?
        if (r instanceof SourceReport) {
            sourceReports.add((SourceReport) r);
        } else if (r instanceof QualityReport) {
            qualityReports.add((QualityReport) r);
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
    public static QualityReport getPurityReport(int number) {
        return qualityReports.get(number - 1);
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
    public static int numQuality() {
        return qualityReports.size();
    }

}
