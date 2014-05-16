/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        
        addAccount("1", "password", 1);
        addAccount("2", "password", 1);
        addAccount("3", "password", 1);
        addAccount("4", "password", 1);
        deleteAccount("2");

        addPatient("1", "John", "Doe", "Hollywood", "+37199595867", "bob@bob.lv", "doctorID");
        addPatient("2", "Jane", "Doe", "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID");
        addPatient("3", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID");
        addPatient("4", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", "doctorID");
        deletePatient("2");
        findPatient("3");

        addDoctor("1", "John", "Doe", "Hollywood", "+37199595867");
        addDoctor("2", "John", "Doe", "Hollywood", "+37199595867");
        addDoctor("3", "John", "Doe", "Hollywood", "+37199595867");
        addDoctor("4", "John", "Doe", "Hollywood", "+37199595867");
        deleteDoctor("2");

        addMedicalFacility("1", "Stradina", "Riga", "+37123456789");
        addMedicalFacility("2", "Stradina", "Riga", "+37123456789");
        addMedicalFacility("3", "Stradina", "Riga", "+37123456789");
        addMedicalFacility("4", "Stradina", "Riga", "+37123456789");
        deleteMedicalFacility("2");
        
        addMedicalRecords("1", "7", "434", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "medicalRecordDescription", "medicalRecordDate", "medicalRecordComments");
        addMedicalRecords("2", "5", "67", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustinas", "16.05.2014", "Apsveicu, jums ir meitene!");
        addMedicalRecords("3", "23", "42", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustinas", "16.05.2014", "Apsveicu, jums ir meitene!");
        addMedicalRecords("4", "753", "843", "medicalRecordPatientDoctorID",
                "medicalRecordFilePath", "tur kaut-kas kustinas", "16.05.2014", "Apsveicu, jums ir meitene!");
        deleteMedicalRecord("2");
        
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

        //TODO find(if not found = null)
        //TODO add from account to doctors or patients or facilities or record
    }

    /*****************************************************Add methods***********************************************************/
 
    /**
     * Adds new account to accounts table
     *
     * @param ID
     * @param Password
     * @param AccessLevel
     * 
     * @return true if account added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean addAccount(String ID, String Password, int AccessLevel) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO accounts(ID, Password, AccessLevel)"
                    + " VALUES ('" + ID + "','" + Password + "','" + AccessLevel + "')");
            System.out.println("New account added to account table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add account!");
            return false;
        }
        return true;
    }

    /**
     * Adds new patient to patients table
     *
     * @param ID
     * @param Name
     * @param Surname
     * @param Address
     * @param TelephonNumber
     * @param Email
     * @param DoctorID
     * 
     * @return true if patient added to table
     * @return false if exception was trown
     * 
     * @Veronika Pencaka
     */
    private static boolean addPatient(String ID, String Name, String Surname,
            String Address, String TelephoneNumber, String Email, String DoctorID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO patients(ID, Name, Surname, Address, "
                    + "TelephoneNumber, Email, DoctorID) "
                    + "VALUES ('" + ID + "','" + Name + "','" + Surname + "','" + Address + "','"
                    + TelephoneNumber + "','" + Email + "','" + DoctorID + "')");
            System.out.println("New patient added to patients table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add patient!");
            return false;
        }
        return true;
    }

    /**
     * Adds new doctor to doctors table
     *
     * @param ID
     * @param Name
     * @param Surname
     * @param Address
     * @param TelephoneNumber
     *
     * @return true if doctor added to table
     * @return false if exception was trown
     * 
     * @Veronika Pencaka
     */
    private static boolean addDoctor(String ID, String Name, String Surname,
            String Address, String TelephoneNumber) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO doctors(ID, Name, Surname, Address, TelephoneNumber)"
                    + "VALUES ('" + ID + "','" + Name + "','" + Surname + "','" + Address + "','"
                    + TelephoneNumber + "')");
            System.out.println("New doctor added to doctors table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add doctor!");
            return false;
        }
        return true;
    }

    /**
     * Adds a new medical Facility to medicalFacilities table
     *
     * @param ID
     * @param Name
     * @param Address
     * @param TelephoneNumber
     * 
     * @return true if medical facility added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean addMedicalFacility(String ID, String Name,
            String Address, String TelephoneNumber) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO medicalFacilities(ID, Name, Address, TelephoneNumber)"
                    + "VALUES ('" + ID + "','" + Name + "','"
                    + Address + "','" + TelephoneNumber + "')");
            System.out.println("New medicalFacility added to medicalFacilities table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add medicalFacility!");
            return false;
        }
        return true;
    }

    /**
     * Adds new medicalRecord to medicalRecords table
     *
     * @param ID
     * @param PatientID
     * @param AuthorID
     * @param PatientDoctorID
     * @param FilePath
     * @param Description
     * @param Date
     * @param Comments
     * 
     * @return true if medical record added to table
     * @return false if exception was trown
     * 
     * @Veronika Pencaka
     */
    private static boolean addMedicalRecords(String ID, String PatientID,
            String AuthorID, String PatientDoctorID, String FilePath,
            String Description, String Date, String Comments) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO medicalRecords(ID, PatientID, AuthorID, PatientDoctorID, FilePath, "
                    + "Description, Date, Comments )" + "VALUES ('" + ID + "','" + PatientID + "','"
                    + AuthorID + "','" + PatientDoctorID + "','" + FilePath + "','"
                    + Description + "','" + Date + "','" + Comments + "')");
            System.out.println("New medicalRecord added to medicalRecords table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add medicalRecord!");
            return false;
        }
        return true;
    }

    /******************************************************Delete methods*********************************************************/
    
    /**
     * Deletes an account from accounts table
     *
     * @param ID
     * 
     * @return true if account deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean deleteAccount(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM accounts WHERE ID = '" + ID + "'");
            System.out.println("From table account deleted account: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete account: " + ID);
            return false;
        }
        return true;
    }

    /**
     * Deletes a patient from patients table
     *
     * @param ID
     * 
     * @return true if patient deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean deletePatient(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM patients WHERE ID = '" + ID + "'");
            System.out.println("From table patients deleted patient " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete patient: " + ID);
            return false;
        }
        return true;
    }

    /**
     * Deletes doctor from doctors table
     *
     * @param ID
     * 
     * @return true if doctor deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    private static boolean deleteDoctor(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM doctors WHERE ID = '" + ID + "'");
            System.out.println("From table doctors deleted doctor " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete doctor: " + ID);
            return false;
        }
        return true;
    }

    /**
     * Deletes medicalFacility from table MedicalFacilities
     *
     * @param ID
     * 
     * @return true if medical facility deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */

    private static boolean deleteMedicalFacility(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM medicalFacilities WHERE ID = '" + ID + "'");
            System.out.println("From table dmedicalFacilities deleted medical Facility " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete medical Facility: " + ID);
            return false;
        }
        return true;
    }
    
    /**
     * Deletes medical record from medicalRecords table
     * 
     * @param ID
     * 
     * @return true if medicAL record deleted from table
     * @return false if exception was trown
     * 
     */
    
    private static boolean deleteMedicalRecord(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM medicalRecords WHERE ID = '" + ID + "'");
            System.out.println("From table medicalRecords deleted medicalRecord " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete medicalRecord: " + ID);
            return false;
        }
        return true;

    }
    

    /****************************************************************Find methods************************************************/
    
    /**
     * Finds a patient in patients table by id and returns a pat
     *
     * @return patient if it was found
     * @throws java.sql.SQLExceptionient
     *
     * @param ID
     *
     * @Veronika Pencaka
     */
    public static Patient findPatient(String ID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '" + ID + "'");
            if (rs.next()) {
                System.out.println("Patient found: " + rs.getString("Name") + " " + rs.getString("Surname"));
                return new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("Address"), rs.getString("DoctorID"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in finding patient!");
        }
        System.out.println("Couldn't find patient: " + ID);
        return null;
    }

    //TODO
    /**
     * Finds a patients in patients table by name and surname and returns a
     * patient list
     *
     * @param Name
     * @param Surname
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatients(String Name, String Surname) {

        return null;
    }

    //TODO
    /**
     * Finds a patients in patients table by patients doctor id and returns a
     * patient list
     *
     * @param DoctorID
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatients(String DoctorID) {

        return null;
    }

    public static void main(String[] args) {
        DBConnection.resetDatabase();

    }
}
