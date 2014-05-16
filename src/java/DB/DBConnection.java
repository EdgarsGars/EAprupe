/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Handles connection and methods to interact with database
 *
 * @author
 */
public class DBConnection {

    //Connection to database
    private static Connection con;

    //Inicializing connection in static field
    //@Edgars
    static {
        try {
            String url = "jdbc:mysql://192.168.0.104/e_aprupe";
            String user = "root";
            String pass = "abcd1234";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successfully established! \n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Resets database every time we run program TEST PURPOSE
     *
     * @Veronika Pencaka
     */
    public static void resetDatabase() {
        dropDatabases();
        initDatabases();
        addAccount("accountID", "password", 1);
        addAccount("accountID1", "password", 1);
        addAccount("accountID2", "password", 1);
        addAccount("accountID3", "password", 1);
        deleteAccount("accountID2");
        addPatient("patientID", "John", "Doe", "Hollywood", "+37199595867", "bob@bob.lv", "doctorID");
        addPatient("patientID3", "Jane", "Doe", "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID");
        addPatient("patientID5", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID");
        deletePatient("patientID3");

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
            System.out.println("Account table dropped!");
        } catch (SQLException ex) {
            System.out.println("Couldn't drop table!");
        }

    }

    /**
     * Creates all databases
     *
     * @Veronika Pencaka
     */
    private static void initDatabases() {

        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  accounts ( "
                    + "accountID varchar(20) NOT NULL UNIQUE,"
                    + "accountPassword varchar(32) NOT NULL,"
                    + "accountAccessLevel INT(1) NOT NULL);");
            System.out.println("Created account table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create accounts table!");
        }

        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  patients ( "
                    + "patientID varchar(20) NOT NULL UNIQUE,"
                    + "patientName varchar(32) NOT NULL,"
                    + "patientSurname varchar(32) NOT NULL,"
                    + "patientAdress varchar(50) NOT NULL,"
                    + "patientTelefonNumber varchar(20) NOT NULL,"
                    + "patientEmail varchar(32) NOT NULL,"
                    + "patientDoctorID varchar(20) NOT NULL);");
            System.out.println("Created patients table!");
        } catch (SQLException ex) {
            System.out.println("Couldn't create patients table!");
        }
        
        
        //@Veronika Pencaka
        
        //TODO create Doctors Table (id, name, surname, adress, telephoneNumber)
        //TODO create MedicalFacilities (id, name, adress, telephoneNumbner)
        //TODO create MedicalRecords (id, patientID, author, patientDoctorID, file, description, date, comments)
        //TODO drop, add, delete, find(if not found = null)
        //TODO boolean delete, add
        //TODO ? login 
        //TODO add from account to doctors or patients or facilities
    }

    /**
     * Adds new account to accounts table
     *
     * @param accountID
     * @param accountPassword
     * @param accountAccessLevel
     * @return true if account added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean addAccount(String accountID, String accountPassword, int accountAccessLevel) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO accounts(accountID, accountPassword, accountAccessLevel)"
                    + " VALUES ('" + accountID + "','" + accountPassword + "','" + accountAccessLevel + "')");
            System.out.println("New account added to account table: " + accountID);
        } catch (SQLException ex) {
            System.out.println("Couldn't add account!");
            return false;
        }
        return true;
    }

    /**
     * Adds new patient to patients table
     *
     * @param patientID
     * @param patientName
     * @param patientSurname
     * @param patientAdress
     * @param patientTelefonNumber
     * @param patientEmail
     * @param patientDoctorID
     *
     * @Veronika Pencaka
     */
    private static void addPatient(String patientID, String patientName, String patientSurname,
            String patientAdress, String patientTelefonNumber, String patientEmail, String patientDoctorID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO patients(patientID, patientName, patientSurname, patientAdress, "
                    + "patientTelefonNumber, patientEmail, patientDoctorID) "
                    + "VALUES ('" + patientID + "','" + patientName + "','" + patientSurname + "','" + patientAdress + "',"
                    + "'" + patientTelefonNumber + "','" + patientEmail + "','" + patientDoctorID + "')");
            System.out.println("New patient added to patients table: " + patientID);
        } catch (SQLException ex) {
            System.out.println("Couldn't add patient!");
        }
    }

    /**
     * Deletes an account from accounts table
     *
     * @param accountID
     *
     * @Veronika Pencaka
     */
    private static void deleteAccount(String accountID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM accounts WHERE accountID = '" + accountID + "'");
            System.out.println("From table account deleted account: " + accountID);
        } catch (SQLException ex) {
            System.out.println("Couldn't delete account: " + accountID);
        }

    }

    /**
     * Deletes a patient from patients table
     *
     * @param patientID
     *
     * @Veronika Pencaka
     */
    private static void deletePatient(String patientID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM patients WHERE patientID = '" + patientID + "'");
            System.out.println("From table patients deleted patient " + patientID);
        } catch (SQLException ex) {
            System.out.println("Couldn't delete patient: " + patientID);
        }

    }

    //TODO
    /**
     * Finds a patient in patients table by id and returns a patient
     *
     * @param patientID
     *
     * @Veronika Pencaka
     */
    public static Patient findPatient(String patientID) {

        return null;
    }

    //TODO
    /**
     * Finds a patients in patients table by name and surname and returns a
     * patient list
     *
     * @param patientName
     * @param patientSurname
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatients(String patientName, String patientSurname) {

        return null;
    }

    //TODO
    /**
     * Finds a patients in patients table by patients doctor id and returns a
     * patient list
     *
     * @param patientDoctorID
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatients(String patientDoctorID) {

        return null;
    }

    public static void main(String[] args) {
        DBConnection.resetDatabase();

    }
}
