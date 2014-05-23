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
                        rs.getString("Address"), rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));

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
    public static List<Patient> findPatientsByFullname(String name, String surname) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Name LIKE '" + name + "' OR Surname LIKE '" + surname + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + "Patient: " + rs.getString("ID") + " " + name + " " + surname);
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with name " + name + " and surname " + surname);
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
    public static List<Patient> findPatientsByDoctorID(String doctorID) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE DoctorID LIKE '" + doctorID + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + rs.getString("ID") + " " + rs.getString("Name") + " " + rs.getString("Surname"));
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with doctorID = " + doctorID);
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
    public static List<Patient> findPatientsBySurname(String surname) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Surname LIKE '" + surname + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + rs.getString("ID") + " " + rs.getString("Name") + " " + surname);
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with surname = " + surname);
            return null;
        }
    }

    public static List<Patient> findPatientsByName(String name) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE Surname LIKE '" + name + "'");
            while (rs.next()) {
                System.out.println("Patient found: " + rs.getString("ID") + " " + rs.getString("Name") + " " + name);
                Patient foundPatient = new Patient(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
                        rs.getString("TelephoneNumber"), rs.getString("Email"), rs.getString("DoctorID"));
                patients.add(foundPatient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!patients.isEmpty()) {
            return patients;
        } else {
            System.out.println("Couldn't find patients with surname = " + name);
            return null;
        }
    }
    
}
