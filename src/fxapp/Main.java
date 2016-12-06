package fxapp;

import model.Entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Entity.initializeSQL();
        executeDrop();
        executeCreate();
        Entity.execute("INSERT INTO USER VALUES('admin', 'p', " +
                "'admin@example.com', 'Ollie', 'Foo', 0, 0, 1, NULL," +
                " NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);");
    }

    private static void executeCreate() throws SQLException {
        //Entity.execute(readFile("../FinalProject/create.sql"));
        Entity.execute("CREATE TABLE USER (\n" +
                " Username VARCHAR(100) NOT NULL,\n" +
                " Password VARCHAR(100) NOT NULL,\n" +
                " Email VARCHAR(100) NOT NULL UNIQUE,\n" +
                " FirstName VARCHAR(100) NOT NULL,\n" +
                " LastName VARCHAR(100) NOT NULL,\n" +
                " IsWorker BOOLEAN NOT NULL,\n" +
                " isManager BOOLEAN NOT NULL,\n" +
                " isAdmin BOOLEAN NOT NULL,\n" +
                " Address VARCHAR(100),\n" +
                " City VARCHAR(100),\n" +
                " State VARCHAR(100),\n" +
                " Zip VARCHAR(100),\n" +
                " PhoneNumber VARCHAR(100),\n" +
                " Month VARCHAR(100),\n" +
                " Day VARCHAR(100),\n" +
                " Year VARCHAR(100),\n" +
                " Gender BOOLEAN,\n" +
                " Locked BOOLEAN NOT NULL,\n" +
                " PRIMARY KEY (Username) ) ENGINE=InnoDB;");
        Entity.execute("CREATE TABLE SourceReport (\n" +
                " Number INT NOT NULL AUTO_INCREMENT,\n" +
                " FullDate DATE NOT NULL,\n" +
                " Reporter VARCHAR(100) NOT NULL,\n" +
                " WaterType VARCHAR(100) NOT NULL,\n" +
                " WaterCondition VARCHAR(100) NOT NULL,\n" +
                " Location VARCHAR(250) NOT NULL,\n" +
                " Latitude DOUBLE NOT NULL,\n" +
                " Longitude DOUBLE NOT NULL,\n" +
                " PRIMARY KEY (Number) ) ENGINE=InnoDB;");
        Entity.execute("CREATE TABLE QualityReport (\n" +
                "Number INT NOT NULL AUTO_INCREMENT,\n" +
                "FullDate DATE NOT NULL,\n" +
                "Reporter VARCHAR(100) NOT NULL,\n" +
                "WaterCondition VARCHAR(100) NOT NULL,\n" +
                "VirusPPM DOUBLE NOT NULL,\n" +
                "ContaminantPPM DOUBLE NOT NULL,\n" +
                "Location VARCHAR(250) NOT NULL,\n" +
                "Latitude DOUBLE NOT NULL,\n" +
                "Longitude DOUBLE NOT NULL,\n" +
                "PRIMARY KEY (Number) ) ENGINE=InnoDB;");
    }

    private static void executeDrop() throws SQLException {
        //Entity.execute(readFile("../FinalProject/drop.sql"));
        Entity.execute("DROP TABLE IF EXISTS USER CASCADE;");
        Entity.execute("DROP TABLE IF EXISTS SourceReport CASCADE;");
        Entity.execute("DROP TABLE IF EXISTS QualityReport CASCADE;");
    }

    private static String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
