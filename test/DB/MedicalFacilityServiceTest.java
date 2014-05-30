/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import Users.MedicalFacility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Mārcis
 */
public class MedicalFacilityServiceTest extends TestCase {
    
    public MedicalFacilityServiceTest(String testName) {
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
     * Test of addMedicalFacility method, of class MedicalFacilityService.
     */
    @Test
    public void testAddMedicalFacility() throws SQLException {
        System.out.println("addMedicalFacility");
        String ID = "5";
        String name = "Stradina";
        String address = "Riga";
        String telephoneNumber = "+37126222640";
        MedicalFacilityService.addMedicalFacility(ID, name, address, telephoneNumber);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalFacilities WHERE ID = '" + ID + 
                "' AND NAME = '" + name + "' AND ADDRESS = '" + address + "' AND TELEPHONENUMBER = '" + 
                telephoneNumber + "'");
        if (rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of deleteMedicalFacility method, of class MedicalFacilityService.
     */
    @Test
    public void testDeleteMedicalFacility() throws SQLException {
        System.out.println("deleteMedicalFacility");
        String ID = "5";
        MedicalFacilityService.deleteMedicalFacility(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalFacilities WHERE ID = '" + ID + "'");
        if (!rs.next()) {
            assertTrue(true);
        }
    }

    /**
     * Test of findMedicalFacilityByID method, of class MedicalFacilityService.
     * REDOO
     */
    @Test
    public void testFindMedicalFacilityByID() throws SQLException {
        System.out.println("findMedicalFacilityByID");
        String ID = "4";
        MedicalFacilityService.findMedicalFacilityByID(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM medicalFacilities WHERE ID = '" + ID + "' AND NAME = 'Stradina' "
                + "AND ADDRESS = 'Riga' AND TELEPHONENUMBER = '+37123456789'");
        if (rs.next()) {
            assertTrue(true);
        }
    }
    
}
