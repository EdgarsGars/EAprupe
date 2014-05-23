/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.MedicalFacility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Visi
 */
public class MedicalFacilityService {

    private static final Connection con = DBConnection.con;

    /**
     * Adds a new medical Facility to medicalFacilities table
     *
     * @param ID
     * @param Name
     * @param Address
     * @param TelephoneNumber
     *
     * @return true if medical facility added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean addMedicalFacility(String ID, String name,
            String address, String telephoneNumber) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO medicalFacilities(ID, Name, Address, TelephoneNumber)"
                    + "VALUES ('" + ID + "','" + name + "','"
                    + address + "','" + telephoneNumber + "')");
            System.out.println("New medicalFacility added to medicalFacilities table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add medicalFacility!");
            return false;
        }
        return true;
    }

    /**
     * Deletes medicalFacility from table MedicalFacilities
     *
     * @param ID
     *
     * @return true if medical facility deleted from table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean deleteMedicalFacility(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM medicalFacilities WHERE ID = '" + ID + "'");
            System.out.println("From table dmedicalFacilities deleted medical Facility " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete medical Facility: " + ID);
            return false;
        }
        return true;
    }

    /**
     * Finds MedicalFacility in medicalFacilities table and return
     * MedicalFacility
     *
     * @param ID
     * @return MedicalFacility if it was found
     *
     * @Veronika Pencaka
     */
    public static MedicalFacility findMedicalFacilityByID(String ID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicalFacilities WHERE ID = '" + ID + "'");
            if (rs.next()) {
                System.out.println("Medical facility found: " + rs.getString("Name"));
                return new MedicalFacility(rs.getString("ID"), rs.getString("Name"), rs.getString("Address"),
                        rs.getString("TelephoneNumber"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Couldn't find medical facility: " + ID);
        return null;
    }

}
