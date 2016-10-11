package model;

import javax.xml.transform.Source;
import java.util.ArrayList;

/**
 * Created by Kijana on 10/11/2016.
 */
public class ReportDatabase {
    private static ArrayList<SourceReport> sourceReports;

    public ReportDatabase() {
        sourceReports = new ArrayList<>(10);
    }

    public static void add(SourceReport r) { //report class in future?
        if (r instanceof SourceReport) {
            sourceReports.add((SourceReport) r);
        }
    }

    public static SourceReport getSourceReport(int number) {
        return sourceReports.get(number - 1);
    }
}
