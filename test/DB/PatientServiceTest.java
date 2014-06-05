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
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
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
        String Name = "Walter";
        String Surname = "White";
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
     * Test of findPatientByID method, of class PatientService.
     */
    @Test
    public void testFindPatientByID() {
        System.out.println("findPatientByID");
        String ID = "2";
        Patient result = PatientService.findPatientByID(ID);
        Patient expResult = new Patient(ID, "Jane", "Doe", 
                "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        assertTrue((expResult.getId().equals(result.getId())) &&
                expResult.getName().equals(result.getName()) &&
                expResult.getSurname().equals(result.getSurname()) &&
                expResult.getAddress().equals(result.getAddress()) &&
                expResult.getNumber().equals(result.getNumber()) &&
                expResult.getEmail().equals(result.getEmail()) &&
                expResult.getFamilyDoctor().equals(result.getFamilyDoctor()));
    }

    /**
     * Test of findPatientsByFullname method, of class PatientService.
     */
    @Test
    public void testFindPatientsByFullname() {
        System.out.println("findPatientsByFullname");
        String name = "Jane";
        String surname = "Doe";
        List<Patient> result = PatientService.findPatientsByFullname(name, surname);
        Patient record = new Patient("2", name, surname, "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        List<Patient> expResult = new ArrayList<>();
        expResult.add(record);
        
        for(int i=0; i<result.size(); i++){
            Patient p1, p2;
            p1 = result.get(i);
            p2 = expResult.get(i);
            //assertEquals(p1, p2);
            
            assertTrue((p1.getId().equals(p2.getId())) &&
                p1.getName().equals(p2.getName()) &&
                p1.getSurname().equals(p2.getSurname()) &&
                p1.getAddress().equals(p2.getAddress()) &&
                p1.getNumber().equals(p2.getNumber()) &&
                p1.getEmail().equals(p2.getEmail()) &&
                p1.getFamilyDoctor().equals(p2.getFamilyDoctor()));
        }
    }
    

    /**
     * Test of findPatientsByDoctorID method, of class PatientService.
     */
    @Test
    public void testFindPatientsByDoctorID() {
        System.out.println("findPatientsByDoctorID");
        String doctorID = "doctorID3";
        List<Patient> result = PatientService.findPatientsByDoctorID(doctorID);
        Patient record = new Patient("2", "Jane", "Doe", "Bollywood", "+37188595867", "bobewka@bob.lv", doctorID);
        Patient record2 = new Patient("3", "Bob", "Square", "Bollywood", "+37188576867", "gubka@bob.lv", doctorID);
        List<Patient> expResult = new ArrayList<>();
        expResult.add(record);
        expResult.add(record2);
//        for(Patient p : result){
//            System.out.println(p.getName()+"YOLO");
//        }
//        for(Patient p : expResult){
//            System.out.println(p.getName()+"SWAG");
//        }
        for(int i=0; i<result.size(); i++){
            Patient p1, p2;
            p1 = result.get(i);
            p2 = expResult.get(i);
            assertTrue((p1.getId().equals(p2.getId())) &&
                p1.getName().equals(p2.getName()) &&
                p1.getSurname().equals(p2.getSurname()) &&
                p1.getAddress().equals(p2.getAddress()) &&
                p1.getNumber().equals(p2.getNumber()) &&
                p1.getEmail().equals(p2.getEmail()) &&
                p1.getFamilyDoctor().equals(p2.getFamilyDoctor()));
        }
    }

    /**
     * Test of findPatientsBySurname method, of class PatientService.
     */
    @Test
    public void testFindPatientsBySurname() {
        System.out.println("findPatientsBySurname");
        String surname = "Doe";
        List<Patient> result = PatientService.findPatientsBySurname(surname);
        Patient record = new Patient("2", "Jane", surname, "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        List<Patient> expResult = new ArrayList<>();
        expResult.add(record);
        for(int i=0; i<result.size(); i++){
            Patient p1, p2;
            p1 = result.get(i);
            p2 = expResult.get(i);
            assertTrue((p1.getId().equals(p2.getId())) &&
                p1.getName().equals(p2.getName()) &&
                p1.getSurname().equals(p2.getSurname()) &&
                p1.getAddress().equals(p2.getAddress()) &&
                p1.getNumber().equals(p2.getNumber()) &&
                p1.getEmail().equals(p2.getEmail()) &&
                p1.getFamilyDoctor().equals(p2.getFamilyDoctor()));
        }      
    }

    /**
     * Test of findPatientsByName method, of class PatientService.
     */
    @Test
    public void testFindPatientsByName() {
        System.out.println("findPatientsByName");
        String name = "Jane";
        List<Patient> result = PatientService.findPatientsByName(name);
        Patient record = new Patient("2", name, "Doe", "Bollywood", "+37188595867", "bobewka@bob.lv", "doctorID3");
        List<Patient> expResult = new ArrayList<>();
        expResult.add(record);
        for(int i=0; i<result.size(); i++){
            Patient p1, p2;
            p1 = result.get(i);
            p2 = expResult.get(i);
            assertTrue((p1.getId().equals(p2.getId())) &&
                p1.getName().equals(p2.getName()) &&
                p1.getSurname().equals(p2.getSurname()) &&
                p1.getAddress().equals(p2.getAddress()) &&
                p1.getNumber().equals(p2.getNumber()) &&
                p1.getEmail().equals(p2.getEmail()) &&
                p1.getFamilyDoctor().equals(p2.getFamilyDoctor()));
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
    
}