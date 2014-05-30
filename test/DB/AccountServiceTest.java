/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Mārcis
 */
public class AccountServiceTest extends TestCase {

    public AccountServiceTest(String testName) {
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
     * Test of Login method, of class AccountService.
     */
    public void testLogin() throws SQLException {
        String ID = "9"; 
        String Password = "password3";
        AccountService.Login(ID, Password);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE ID = '" + ID + "' AND Password = '" + Password + "'");
        if (rs.next())
            assertTrue(true);
    }

    /**
     * Test of addAccount method, of class AccountService.
     */
    public void testAddAccount() throws SQLException {
        String ID = "5"; 
        String Password = "password3";
        int accessLevel = 1;
        AccountService.addAccount(ID, Password, accessLevel);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE ID = '" + ID + "' AND "
                + "PASSWORD = '" + Password + "' AND ACCESSLEVEL = '" + accessLevel + "'");
        if (rs.next())
            assertTrue(true);
    }

    /**
     * Test of deleteAccount method, of class AccountService.
     */
    public void testDeleteAccount() throws SQLException {
        String ID = "5";
        AccountService.deleteAccount(ID);
        Statement st = DBConnection.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE ID = '" + ID + "'");
        if (!rs.next())
            assertTrue(true);
    }

}
