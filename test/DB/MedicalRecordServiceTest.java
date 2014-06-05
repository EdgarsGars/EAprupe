/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import Users.MedicalFacility;
import Users.MedicalRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
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
        String patientID = "123";
        String authorID = "321";
        String patientDoctorID = "medicalRecordPatientDoctorID";
        String filePath = "medicalRecordFilePath";
        String description = "nav neka";
        String date = "16.05.2014";
        String comments = "Apsveicu, jums viss ir kartiba";
        MedicalRecordService.addMedicalRecords(patientID, authorID, patientDoctorID, filePath, description, date, comments);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE PATIENTID = '" + patientID + "' AND "
                + "AUTHORID = '" + authorID + "' AND PATIENTDOCTORID = '" + patientDoctorID + "' AND FILEPATH = '" + filePath
                + "' AND DESCRIPTION = '" + description + "' AND COMMENTS = '" + comments + "' AND DATE = '" + date + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    

    /**
     * Test of findMedicalRecordByID method, of class MedicalRecordService.
     */
    @Test
    public void testFindMedicalRecordByID() throws SQLException {
        System.out.println("findMedicalRecordByID");
        String ID = "1";
        MedicalRecord result = MedicalRecordService.findMedicalRecordByID(ID);
        MedicalRecord expResult = new MedicalRecord(ID, "23", "42", "medicalRecordPatientDoctorID2", "medicalRecordFilePath", "tur kaut-kas kustas", "Apsveicu, jums ir meitene!",result.getDate());
        assertTrue((expResult.getId().equals(result.getId())) &&
                expResult.getPatientId().equals(result.getPatientId()) &&
                expResult.getAuthor().equals(result.getAuthor()) &&
                expResult.getPatientDoctorId().equals(result.getPatientDoctorId()) &&
                expResult.getFilePath().equals(result.getFilePath()) &&
                expResult.getDescription().equals(result.getDescription()) &&
                expResult.getComments().equals(result.getComments()) &&
                expResult.getDate().equals(result.getDate()));
    }

    /**
     * Test of findMedicalRecordsByDoctorID method, of class MedicalRecordService.
     */
    @Test
    public void testFindMedicalRecordsByDoctorID() throws SQLException {
        System.out.println("findMedicalRecordsByDoctorID");
        String doctorID = "medicalRecordPatientDoctorID";
        List<MedicalRecord> result = MedicalRecordService.findMedicalRecordsByDoctorID(doctorID);
        MedicalRecord record1 = new MedicalRecord("2", "5", "67", doctorID, "medicalRecordFilePath", "tur kaut-kas kustas", "Apsveicu, jums ir meitene!", "16.05.2014");
        MedicalRecord record2 = new MedicalRecord("3", "23", "42", doctorID, "medicalRecordFilePath", "tur kaut-kas kustas", "Apsveicu, jums ir meitene!", "16.05.2014");
        MedicalRecord record3 = new MedicalRecord("4", "753", "843", doctorID, "medicalRecordFilePath", "tur kaut-kas kustas", "Apsveicu, jums ir meitene!", "16.05.2014");
        List<MedicalRecord> expResult = new ArrayList<>();
        expResult.add(record1);
        expResult.add(record2);
        expResult.add(record3);
        for(int i =0; i<result.size(); i++){
            MedicalRecord r1, r2;
            r1 = result.get(i);
            r2 = expResult.get(i);
            assertTrue((r1.getId().equals(r2.getId())) &&
                r1.getPatientId().equals(r2.getPatientId()) &&
                r1.getAuthor().equals(r2.getAuthor()) &&
                r1.getPatientDoctorId().equals(r2.getPatientDoctorId()) &&
                r1.getFilePath().equals(r2.getFilePath()) &&
                r1.getDescription().equals(r2.getDescription()) &&
                r1.getComments().equals(r2.getComments()) &&
                r1.getDate().equals(r2.getDate()));
        }
        
    }

    /**
     * Test of findRecordByPatientID method, of class MedicalRecordService.
     */
    @Test
    public void testFindRecordByPatientID() throws SQLException {
        System.out.println("findRecordByPatientID");
        String patientID = "753";
        List<MedicalRecord> result = MedicalRecordService.findRecordByPatientID(patientID);
        MedicalRecord record = new MedicalRecord("4", patientID, "843", "medicalRecordPatientDoctorID", "medicalRecordFilePath", "tur kaut-kas kustas", "Apsveicu, jums ir meitene!", "16.05.2014");
        List<MedicalRecord> expResult = new ArrayList<>();
        expResult.add(record);
        for(int i =0; i<result.size(); i++){
            MedicalRecord r1, r2;
            r1 = result.get(i);
            r2 = expResult.get(i);
            //assertEquals(r1, r2);
            assertTrue((r1.getId().equals(r2.getId())) &&
                r1.getPatientId().equals(r2.getPatientId()) &&
                r1.getAuthor().equals(r2.getAuthor()) &&
                r1.getPatientDoctorId().equals(r2.getPatientDoctorId()) &&
                r1.getFilePath().equals(r2.getFilePath()) &&
                r1.getDescription().equals(r2.getDescription()) &&
                r1.getComments().equals(r2.getComments()) &&
                r1.getDate().equals(r2.getDate()));
        }
    }
    
    /**
     * Test of deleteMedicalRecord method, of class MedicalRecordService.
     */
    @Test
    public void testDeleteMedicalRecord() throws SQLException {
        System.out.println("deleteMedicalRecord");
        String ID = "5";
        MedicalRecordService.deleteMedicalRecord(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalrecords WHERE ID = '" + ID + "'");
        if (!rs.next()) {
            assertTrue(true);
        }
    }
}
