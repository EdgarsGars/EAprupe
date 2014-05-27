/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


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
            String url = "jdbc:mysql://10.2.6.147/e_aprupe";
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

        AccountService.addAccount("1", "password", 1);
        AccountService.addAccount("2", "password", 1);
        AccountService.addAccount("3", "password", 1);
        AccountService.addAccount("4", "password", 1);
        AccountService.deleteAccount("1");

        PatientService.addPatient("1", "Jane", "Doe", "Hollywood", "+37199595867", "bob@bob.lv", "doctorID");
        PatientService.addPatient("2", "Jane", "Doe", "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        PatientService.addPatient("3", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID3");
        PatientService.addPatient("4", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID");
        PatientService.deletePatient("1");
        PatientService.findPatientByID("3");
        PatientService.findPatientsByFullname("Jane", "Doe");
        PatientService.findPatientsByDoctorID("doctorID3");
        PatientService.findPatientsBySurname("Doe");

        DoctorService.addDoctor("1", "John", "Doe", "Hollywood", "+37199595867");
        DoctorService.addDoctor("2", "John", "Doe", "Hollywood", "+37199595867");
        DoctorService.addDoctor("3", "John", "Doe", "Hollywood", "+37199595867");
        DoctorService.addDoctor("4", "John", "Doe", "Hollywood", "+37199595867");
        DoctorService.deleteDoctor("1");
        DoctorService.findDoctorByID("3");

        MedicalFacilityService.addMedicalFacility("1", "Stradina", "Riga", "+37123456789");
        MedicalFacilityService.addMedicalFacility("2", "Stradina", "Riga", "+37123456789");
        MedicalFacilityService.addMedicalFacility("3", "Stradina", "Riga", "+37123456789");
        MedicalFacilityService.addMedicalFacility("4", "Stradina", "Riga", "+37123456789");
        MedicalFacilityService.deleteMedicalFacility("1");
        MedicalFacilityService.findMedicalFacilityByID("3");

        MedicalRecordService.addMedicalRecords("1", "7", "434", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "medicalRecordDescription", "medicalRecordDate", "medicalRecordComments");
        MedicalRecordService.addMedicalRecords("2", "5", "67", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustas", "16.05.2014", "Apsveicu, jums ir meitene!");
        MedicalRecordService.addMedicalRecords("3", "23", "42", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustas", "16.05.2014", "Apsveicu, jums ir meitene!");
        MedicalRecordService.addMedicalRecords("4", "753", "843", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustas", "16.05.2014", "Apsveicu, jums ir meitene!");
        MedicalRecordService.deleteMedicalRecord("1");
        MedicalRecordService.findMedicalRecordByID("3");
        MedicalRecordService.findRecordByPatientID("2");
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
                    + "ID varchar(20) NOT NULL UNIQUE,"
                    + "PatientID varchar(32) NOT NULL,"
                    + "AuthorID varchar(32) NOT NULL,"
                    + "PatientDoctorID varchar(50) NOT NULL,"
                    + "FilePath varchar(100) NOT NULL,"
                    + "Description varchar(100) NOT NULL,"
                    + "Comments varchar(100) NOT NULL,"
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
