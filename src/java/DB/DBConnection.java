/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import Users.MedicalRecord;
import Users.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Handles connection and methods to interact with database
 *
 * @author
 */
public class DBConnection {

    //Connection to database
    public static Connection con;

    //Inicializing connection in static field
    //@Edgars
    static {
        try {
            String url = "jdbc:mysql://127.0.0.1/e_aprupe";
            String user = "root";
            String pass = "abcd1234";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successfully established! \n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO find medical records by date
    /**
     * Resets database every time we run program TEST PURPOSE
     *
     * @Veronika Pencaka
     */
    public static void resetDatabase() {
        dropDatabases();
        initDatabases();
        
        AccountService.addAccount("D120", "doctor",  2);
        AccountService.addAccount("D121", "doctor1", 2);
        AccountService.addAccount("F220", "facility",  3);
        AccountService.addAccount("F221", "facility1", 3);
        
        DoctorService.addDoctor("D120", "Darts", "Veiders", "Naves Zvaigze", "+666 66666666");
        DoctorService.addDoctor("D121", "Karalis", "Leonids", "Sparta", "+ 371 10000000");
        
        MedicalFacilityService.addMedicalFacility("F220", "Traumpunkts", "Ventspils Inzeneiru iela 15", "+371 23456789");
        MedicalFacilityService.addMedicalFacility("F221", "Dzemdību nodaļa", "Ventspils Inzeneiru iela 13", "+371 11111111");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MedicalRecordService.addMedicalRecords("23", "42","medicalRecordPatientDoctorID2", "medicalRecordFilePath", "tur kaut-kas kustas" ,dateFormat.format(date), "Apsveicu, jums ir meitene!");
        MedicalRecordService.addMedicalRecords("5", "67", "medicalRecordPatientDoctorID", "medicalRecordFilePath", "tur kaut-kas kustas","16.05.2014", "Apsveicu, jums ir meitene!");
        MedicalRecordService.addMedicalRecords("23", "42", "medicalRecordPatientDoctorID", "medicalRecordFilePath", "tur kaut-kas kustas", "16.05.2014", "Apsveicu, jums ir meitene!");
        MedicalRecordService.addMedicalRecords("753", "843", "medicalRecordPatientDoctorID", "medicalRecordFilePath", "tur kaut-kas kustas","16.05.2014", "Apsveicu, jums ir meitene!");
        PatientService.addPatient("2", "Jane", "Doe",  "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        PatientService.addPatient("3", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID3");
        
        System.out.println("Done!");

    }

    /**
     * Drops all databases TEST PURPOSE
     *
     * @Veronika Pencaka
     */
    private static void dropDatabases() {

        try {
            Statement st = con.createStatement();
            st.executeUpdate("DROP TABLE accounts");
            st.executeUpdate("DROP TABLE patients");
            st.executeUpdate("DROP TABLE doctors");
            st.executeUpdate("DROP TABLE medicalFacilities");
            st.executeUpdate("DROP TABLE medicalRecords");
            System.out.println("All tables dropped!");
        } catch (SQLException ex) {
            System.out.println("Couldn't drop tables!");
        }

    }

    /**
     * Creates all databases
     *
     * @Veronika Pencaka
     */
    private static void initDatabases() {
        //accounts table
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  accounts ( "
                    + "ID varchar(20) NOT NULL UNIQUE,"
                    + "Password varchar(32) NOT NULL,"
                    + "AccessLevel INT(1) NOT NULL);");
            System.out.println("Created account table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create accounts table!");
        }
        //patients table
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  patients ( "
                    + "ID varchar(20) NOT NULL UNIQUE,"
                    + "Name varchar(32) NOT NULL,"
                    + "Surname varchar(32) NOT NULL,"
                    + "Address varchar(50) NOT NULL,"
                    + "TelephoneNumber varchar(20) NOT NULL,"
                    + "Email varchar(32) NOT NULL,"
                    + "DoctorID varchar(20) NOT NULL);");
            System.out.println("Created patients table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create patients table!");
        }
        //doctors table
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  doctors ( "
                    + "ID varchar(20) NOT NULL UNIQUE,"
                    + "Name varchar(32) NOT NULL,"
                    + "Surname varchar(32) NOT NULL,"
                    + "Address varchar(50) NOT NULL,"
                    + "TelephoneNumber varchar(20) NOT NULL);");
            System.out.println("Created doctors table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create doctors table!");
        }
        //medical facilities table
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  medicalFacilities ( "
                    + "ID varchar(20) NOT NULL UNIQUE,"
                    + "Name varchar(32) NOT NULL,"
                    + "Address varchar(50) NOT NULL,"
                    + "TelephoneNumber varchar(20) NOT NULL);");
            System.out.println("Created medicalFacilities table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create medicalFacilities table!");
        }
        //medical records table
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  medicalRecords ( "
                    + "ID Int(11) NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,"
                    + "PatientID varchar(32) NOT NULL,"
                    + "AuthorID varchar(32) NOT NULL,"
                    + "PatientDoctorID varchar(50) NOT NULL,"
                    + "FilePath varchar(100) NOT NULL,"
                    + "Description TEXT NOT NULL,"
                    + "Comments TEXT NOT NULL,"
                    + "Date varchar(20) NOT NULL);");
            System.out.println("Created medicalRecords table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create medicalRecords table!");
        }

    }

    public static void main(String[] args) {
        DBConnection.resetDatabase();

    }
}
