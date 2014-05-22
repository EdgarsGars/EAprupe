/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Visi
 */
public class AccountService {

    private static final Connection con = DBConnection.con;

    public static boolean Login(String ID, String Password) {
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE ID LIKE '" + ID + "' AND Password LIKE '" + Password + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
    public static boolean addAccount(String ID, String Password, int AccessLevel) {
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
     * Deletes an account from accounts table
     *
     * @param ID
     *
     * @return true if account deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean deleteAccount(String ID) {
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

}
