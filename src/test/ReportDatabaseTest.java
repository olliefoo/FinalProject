package test;
import model.QualityReport;
import model.ReportDatabase;
import model.SourceReport;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by miles on 11/15/2016.
 */
public class ReportDatabaseTest {
    @Test
    public void add() throws Exception {
        SourceReport sourceReport = new SourceReport();
        ReportDatabase.getInstance().add(sourceReport);
        assertEquals(sourceReport,ReportDatabase.getInstance()
                .getSourceReport(1));
        QualityReport qualityReport = new QualityReport();
        ReportDatabase.getInstance().add(qualityReport);
        assertEquals(qualityReport,ReportDatabase.getInstance()
                .getQualityReport(1));
    }

}