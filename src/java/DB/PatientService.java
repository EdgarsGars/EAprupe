/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.Patient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Visi
 */
public class PatientService {

    private static final Connection con = DBConnection.con;

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
    public static boolean addPatient(String ID, String Name, String Surname,
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
     * Deletes a patient from patients table
     *
     * @param ID
     *
     * @return true if patient deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean deletePatient(String ID) {
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
     * Finds a patient in patients table by id and returns a patient
     *
     * @return patient if it was found
     * @throws java.sql.SQLExceptionient
     *
     * @param ID
     *
     * @Veronika Pencaka
     */
    public static Patient findPatientByID(String ID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '" + ID + "'");
            if (rs.next()) {
                System.out.println("Patient found: " + rs.getString("Name") + " " + rs.getString("Surname"));
                return new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"),
<<<<<<< HEAD
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("Address"), rs.getString("DoctorID"));
=======
                        rs.getString("Address"), rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
>>>>>>> bf8d97f523c59e8ba5b9c8158baf25ab673fedd3
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Couldn't find patient: " + ID);
        return null;
    }

    /**
     * Finds a patients in patients table by name and surname and returns a
     * patient list
     *
     * @param Name
     * @param Surname
     * @return list of patients
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatientsByFullname(String Name, String Surname) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
<<<<<<< HEAD
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Name LIKE '" + Name + "' AND Surname LIKE '" + Surname + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + "Patient: " + rs.getString("ID") + " " + Name + " " + Surname);
                Patient foundPatient = new Patient(rs.getString("ID"), Name, Surname, rs.getString("Address"),
=======
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Name LIKE '" + Name + "' OR Surname LIKE '" + Surname + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + "Patient: " + rs.getString("ID") + " " + Name + " " + Surname);
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
>>>>>>> bf8d97f523c59e8ba5b9c8158baf25ab673fedd3
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with name " + Name + " and surname " + Surname);
            return null;
        }
    }

    /**
     * Finds a patients in patients table by patients doctor id and returns a
     * list of patients patient list
     *
     * @param DoctorID
     * @return list of patients
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatientsByDoctorID(String DoctorID) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE DoctorID LIKE '" + DoctorID + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + rs.getString("ID") + " " + rs.getString("Name") + " " + rs.getString("Surname"));
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
<<<<<<< HEAD
                        rs.getString("TelephoneNumber"), rs.getString("Email"), DoctorID);
=======
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
>>>>>>> bf8d97f523c59e8ba5b9c8158baf25ab673fedd3
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with doctorID = " + DoctorID);
            return null;
        }
    }

    /**
     * Finds patients in table by surname and retirns list of patients
     *
     * @param Surname
     * @return list of patients
     *
     * @Veronika Pencaka
     */
    public static List<Patient> findPatientsBySurname(String Surname) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Surname LIKE '" + Surname + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + rs.getString("ID") + " " + rs.getString("Name") + " " + Surname);
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), Surname, rs.getString("Address"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with surname = " + Surname);
            return null;
        }
    }

}
