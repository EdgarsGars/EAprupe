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
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
=======

>>>>>>> bf8d97f523c59e8ba5b9c8158baf25ab673fedd3

/**
 *
 * @author Visi
 */
public class AccountService {

    private static final Connection con = DBConnection.con;

    public static Object Login(String ID, String Password) {
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE ID = '" + ID + "' AND Password = '" + Password + "'");
            if (rs.next()) {
                int level = rs.getInt("AccessLevel");
                if(level == 1) return PatientService.findPatientByID(ID);
                else if(level == 2) return DoctorService.findDoctorByID(ID);
                else if(level == 3) return MedicalFacilityService.findMedicalFacilityByID(ID);
            }
        } catch (SQLException ex) {
            System.out.println("Cannot find such account! ");
        }
        return null;
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
