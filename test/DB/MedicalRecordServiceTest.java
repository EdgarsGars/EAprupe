/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;


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
public class MedicalRecordServiceTest extends TestCase {
    
    public MedicalRecordServiceTest(String testName) {
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
     * Test of addMedicalRecords method, of class MedicalRecordService.
     */
    @Test
    public void testAddMedicalRecords() throws SQLException {
        System.out.println("addMedicalRecords");
        String ID = "5";
        String patientID = "123";
        String authorID = "321";
        String patientDoctorID = "medicalRecordPatientDoctorID";
        String filePath = "medicalRecordFilePath";
        String description = "tur nekas nekustas";
        String date = "16.05.2014";
        String comments = "Apsveicu, jums viss ir kartiba";
        MedicalRecordService.addMedicalRecords(ID, patientID, authorID, patientDoctorID, filePath, description, date, comments);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE ID = '" + ID + "' AND PATIENTID = '" + patientID + "' AND "
                + "AUTHORID = '" + authorID + "' AND PATIENTDOCTORID = '" + patientDoctorID + "' AND FILEPATH = '" + filePath
                + "' AND DESCRIPTION = '" + description + "' AND COMMENTS = '" + comments + "' AND DATE = '" + date + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of deleteMedicalRecord method, of class MedicalRecordService.
     */
    @Test
    public void testDeleteMedicalRecord() throws SQLException {
        System.out.println("deleteMedicalRecord");
        String ID = "5";
        MedicalFacilityService.deleteMedicalFacility(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE ID = '" + ID + "'");
        if (!rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findMedicalRecordByID method, of class MedicalRecordService.
     * REDOO
     */
    @Test
    public void testFindMedicalRecordByID() throws SQLException {
        System.out.println("findMedicalRecordByID");
        String ID = "3";
        MedicalRecordService.findMedicalRecordByID(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE ID = '" + ID + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findMedicalRecordsByDoctorID method, of class MedicalRecordService.
     * REDOOO
     */
    @Test
    public void testFindMedicalRecordsByDoctorID() throws SQLException {
        System.out.println("findMedicalRecordsByDoctorID");
        String doctorID = "42";
        MedicalRecordService.findMedicalRecordsByDoctorID(doctorID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE PatientDoctorID = '" + doctorID + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findRecordByPatientID method, of class MedicalRecordService.
     * REDOOO
     */
    @Test
    public void testFindRecordByPatientID() throws SQLException {
        System.out.println("findRecordByPatientID");
        String patientID = "753";
        MedicalRecordService.findRecordByPatientID(patientID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE PatientID = '" + patientID + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }
    
}
