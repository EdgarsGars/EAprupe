/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBConnection.con;
import Users.Doctor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Mārcis
 */
public class DoctorServiceTest extends TestCase {

    public DoctorServiceTest(String testName) {
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
     * Test of addDoctor method, of class DoctorService.
     */
    @Test
    public void testAddDoctor() throws SQLException {
        System.out.println("addDoctor");
        String ID = "5";
        String name = "Walter";
        String surname = "White";
        String address = "Albaquerque";
        String telephoneNumber = "+37129193006";
        DoctorService.addDoctor(ID, name, surname, address, telephoneNumber);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM doctors WHERE ID = '" + ID + 
                "' AND NAME = '" + name + "' AND SURNAME = '" + surname + "' AND ADDRESS = '" + 
                address + "' AND TELEPHONENUMBER = '" + telephoneNumber + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of deleteDoctor method, of class DoctorService.
     */
    @Test
    public void testDeleteDoctor() throws SQLException {
        System.out.println("deleteDoctor");
        String ID = "5";
        DoctorService.deleteDoctor(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM doctors WHERE ID = '" + ID + "'");   
        if (!rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findDoctorByID method, of class DoctorService.
     */
    @Test
    public void testFindDoctorByID() throws SQLException {
        System.out.println("findDoctorByID");
        String ID = "2";
        Doctor result = DoctorService.findDoctorByID(ID);
        Doctor expResult = new Doctor("2", "John", "Doe", "Hollywood", "+37199595867");
        assertTrue((expResult.getId().equals(result.getId())) &&
                expResult.getName().equals(result.getName()) &&
                expResult.getSurname().equals(result.getSurname()) &&
                expResult.getTelephoneNumber().equals(result.getTelephoneNumber()));
    }

}
