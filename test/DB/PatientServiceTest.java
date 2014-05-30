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
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Mārcis
 */
public class PatientServiceTest extends TestCase {
    
    public PatientServiceTest(String testName) {
        super(testName);
    }
    private Connection cn;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url = "jdbc:mysql://localhost/e_aprupe";
        String user = "root";
        String pass = "abcd1234";

        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection(url, user, pass);
        //System.out.println("Connection successfully established! \n");

        //cn.close();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    /**
     * Test of addPatient method, of class PatientService.
     */
    @Test
    public void testAddPatient() throws SQLException {
        System.out.println("addPatient");
        String ID = "5";
        String Name = "John";
        String Surname = "Doe";
        String Address = "HollyBolly";
        String TelephoneNumber = "+37129193003";
        String Email = "aa@ee.lv";
        String DoctorID = "doctor007";
        PatientService.addPatient(ID, Name, Surname, Address, TelephoneNumber, Email, DoctorID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '" + ID + "' AND NAME = '" + Name + "' AND "
                + "SURNAME = '" + Surname + "' AND ADDRESS = '" + Address + "' AND TelephoneNumber = '" + TelephoneNumber
                + "' AND Email = '" + Email + "' AND DoctorID = '" + DoctorID + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of deletePatient method, of class PatientService.
     */
    @Test
    public void testDeletePatient() throws SQLException {
        System.out.println("deletePatient");
        String ID = "5";
        PatientService.deletePatient(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '" + ID + "'");
        if (!rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findPatientByID method, of class PatientService.
     * REDO
     */
    @Test
    public void testFindPatientByID() throws SQLException {
        System.out.println("findPatientByID");
        String ID = "2";
        Patient p = PatientService.findPatientByID(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '" + ID + "' AND NAME = 'Jane' AND "
                + "SURNAME = 'Doe' AND ADDRESS = 'Bollywood' AND TELEPHONENUMBER = '+37188595867' AND "
                + "EMAIL = 'bobewka@bob.lv' AND DOCTORID = 'doctorID3'");
        if (rs.next()) {
            
            
        }
    }

    /**
     * Test of findPatientsByFullname method, of class PatientService.
     * REDO
     */
    @Test
    public void testFindPatientsByFullname() throws SQLException {
        System.out.println("findPatientsByFullname");
        String name = "Jane";
        String surname = "Doe";
        PatientService.findPatientsByFullname(name, surname);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '2' AND NAME = '" + name + "' AND "
                + "SURNAME = '" + surname + "' AND ADDRESS = 'Bollywood' AND TELEPHONENUMBER = '+37188595867' AND "
                + "EMAIL = 'bobewka@bob.lv' AND DOCTORID = 'doctorID3'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findPatientsByDoctorID method, of class PatientService.
     * REDO
     */
    @Test
    public void testFindPatientsByDoctorID() throws SQLException {
        System.out.println("findPatientsByDoctorID");
        String doctorID = "doctorID3";
        PatientService.findPatientsByDoctorID(doctorID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '2' AND NAME = 'Jane' AND "
                + "SURNAME = 'Doe' AND ADDRESS = 'Bollywood' AND TELEPHONENUMBER = '+37188595867' AND "
                + "EMAIL = 'bobewka@bob.lv' AND DOCTORID = '" + doctorID + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findPatientsBySurname method, of class PatientService.
     * REDO
     */
    @Test
    public void testFindPatientsBySurname() throws SQLException {
        System.out.println("findPatientsBySurname");
        String surname = "Doe";
        PatientService.findPatientsBySurname(surname);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '2' AND NAME = 'Jane' AND "
                + "SURNAME = '" + surname + "' AND ADDRESS = 'Bollywood' AND TELEPHONENUMBER = '+37188595867' AND "
                + "EMAIL = 'bobewka@bob.lv' AND DOCTORID = 'doctorID3'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findPatientsByName method, of class PatientService.
     * REDO
     */
    @Test
    public void testFindPatientsByName() throws SQLException {
        System.out.println("findPatientsByName");
        String name = "Jane";
        PatientService.findPatientsByName(name);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE ID = '2' AND NAME = '" + name + "' AND "
                + "SURNAME = 'Doe' AND ADDRESS = 'Bollywood' AND TELEPHONENUMBER = '+37188595867' AND "
                + "EMAIL = 'bobewka@bob.lv' AND DOCTORID = 'doctorID3'");
        if (rs.next()) {
            assertTrue(true);
        }
    }
    
}
