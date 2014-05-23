/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import Users.MedicalRecord;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Visi
 */
public class MedicalRecordService {

    private static final Connection con = DBConnection.con;

    /**
     * Adds new medicalRecord to medicalRecords table
     *
     * @param ID
     * @param PatientID
     * @param AuthorID
     * @param PatientDoctorID
     * @param FilePath
     * @param Description
     * @param Date
     * @param Comments
     *
     * @return true if medical record added to table
     * @return false if exception was trown
     *
     * @Veronika Pencaka
     */
    public static boolean addMedicalRecords(String ID, String patientID,
            String authorID, String patientDoctorID, String filePath,
            String description, String date, String comments) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO medicalRecords(ID, PatientID, AuthorID, PatientDoctorID, FilePath, "
                    + "Description, Date, Comments )" + "VALUES ('" + ID + "','" + patientID + "','"
                    + authorID + "','" + patientDoctorID + "','" + filePath + "','"
                    + description + "','" + date + "','" + comments + "')");
            System.out.println("New medicalRecord added to medicalRecords table: " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't add medicalRecord!");
            return false;
        }
        return true;
    }

    /**
     * Deletes medical record from medicalRecords table
     *
     * @param ID
     *
     * @return true if medicAL record deleted from table
     * @return false if exception was trown
     *
     */
    public static boolean deleteMedicalRecord(String ID) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM medicalRecords WHERE ID = '" + ID + "'");
            System.out.println("From table medicalRecords deleted medicalRecord " + ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Couldn't delete medicalRecord: " + ID);
            return false;
        }
        return true;

    }

    /**
     * Finds MedicalRecord in medicalRecords table and return MedicalRecord
     *
     * @param ID
     * @return MedicalRecord if it was found
     *
     * @Veronika Pencaka
     */
    public static MedicalRecord findMedicalRecordByID(String ID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicalRecords WHERE ID = '" + ID + "'");
            if (rs.next()) {
                System.out.println("Medical record found: " + rs.getString("ID"));
                return new MedicalRecord(rs.getString("ID"), rs.getString("PatientID"), rs.getString("AuthorID"),
                        rs.getString("PatientDoctorID"), rs.getString("FilePath"), rs.getString("Description"),
                        rs.getString("Comments"), rs.getString("Date"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Couldn't find medical record: " + ID);
        return null;
    }

    /**
     * Finds medical record in medicalRecords table by patientID
     *
     * @param PatientID
     * @return list of medical records
     *
     * @Veronika Pencaka
     */
    public static List<MedicalRecord> findRecordByPatientID(String patientID) {
        ArrayList<MedicalRecord> records = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM medicalRecords WHERE ID = '" + patientID + "'");
            while (rs.next()) {
                System.out.println("Medical Record found: " + rs.getString("ID"));
                MedicalRecord foundRecord = new MedicalRecord(rs.getString("ID"), rs.getString("PatientID"), rs.getString("AuthorID"),
                        rs.getString("PatientDoctorID"), rs.getString("FilePath"), rs.getString("Description"), rs.getString("Comments"),
                        rs.getString("Date"));
                records.add(foundRecord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!records.isEmpty()) {
            return records;
        } else {
            System.out.println("Couldn't find medical records with patient id = " + patientID);
            return null;
        }
    }

}
