package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kijana on 10/11/2016.
 */
public class ReportDatabase{
    private static ReportDatabase instance = new ReportDatabase();
    private ArrayList<SourceReport> sourceReports = new ArrayList<>();
    private ArrayList<QualityReport> qualityReports = new ArrayList<>();

    /**
     * Constructor to create a list of all source reports.
     */
    private ReportDatabase() { }


    public static ReportDatabase getInstance() {return instance; }
    /**
     * Method to add a new report to the database
     * @param r source report to be added to the list
     */
    public void add(Object r) { //report class in future?
        if (r instanceof SourceReport) {
            sourceReports.add((SourceReport) r);
        } else if (r instanceof QualityReport) {
            qualityReports.add((QualityReport) r);
        }
    }
    public void saveSource()  {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("source.bin"))) {
                out.writeObject(sourceReports);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    public void saveQuality()  {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("quality.bin"))) {
                out.writeObject(qualityReports);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    public void loadSource()  {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("source.bin"))) {
                ArrayList<SourceReport> srl = (ArrayList<SourceReport>) in.readObject();
                sourceReports = srl;
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.getStackTrace();
        }
    }
    public void loadQuality()  {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("quality.bin"))) {
                ArrayList<QualityReport> qrl = (ArrayList<QualityReport>) in.readObject();
                qualityReports = qrl;
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.getStackTrace();
        }
    }
    /**
     * Returns the source report specified by number
     * @param number the source report number to be retrieved
     * @return source report requested
     */
    public SourceReport getSourceReport(int number) {
        return sourceReports.get(number - 1);
    }

    /**
     * Returns the purity report specified by number
     * @param number the purity report number to be retrieved
     * @return purity report requested
     */
    public QualityReport getPurityReport(int number) {
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
    public int numSource() {
        return sourceReports.size();
    }

    /**
     * Returns the size of the list of source reports
     * @return size of source report
     */
    public int numQuality() {
        return qualityReports.size();
    }

    /**
     * Gets the list of quality reports
     * @return
     */
    public List<QualityReport> getQualityReports() {
        return qualityReports;
    }
}
