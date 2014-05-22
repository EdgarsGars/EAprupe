/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.Doctor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Visi
 */
public class DoctorService {

    private static final Connection con = DBConnection.con;

    /**
     * Adds new doctor to doctors table
     *
     * @param ID
     * @param Name
     * @param Surname
     * @param Address
     * @param TelephoneNumber
     *
     * @return true if doctor added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean addDoctor(String ID, String Name, String Surname,
            String Address, String TelephoneNumber) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO doctors(ID, Name, Surname, Address, TelephoneNumber)"
                    + "VALUES ('" + ID + "','" + Name + "','" + Surname + "','" + Address + "','"
                    + TelephoneNumber + "')");
            System.out.println("New doctor added to doctors table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add doctor!");
            return false;
        }
        return true;
    }

    /**
     * Deletes doctor from doctors table
     *
     * @param ID
     *
     * @return true if doctor deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean deleteDoctor(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM doctors WHERE ID = '" + ID + "'");
            System.out.println("From table doctors deleted doctor " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete doctor: " + ID);
            return false;
        }
        return true;
    }

    /**
     * Finds a doctor in doctors table and returns a doctor
     *
     * @param ID
     * @return Doctor if it was found
     *
     * @Veronika Pencaka
     */
    public static Doctor findDoctorByID(String ID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM doctors WHERE ID = '" + ID + "'");
            if (rs.next()) {
                System.out.println("Doctor found: " + rs.getString("Name") + " " + rs.getString("Surname"));
                return new Doctor(rs.getString("ID"), rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("Address"), rs.getString("TelephoneNumber"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Couldn't find doctor: " + ID);
        return null;
    }
}
