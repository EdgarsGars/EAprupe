/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Users;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class MedicalRecord implements Serializable{
    private final String id;
    private final String patientID;
    private final String author;
    private final String patientDoctorID;
    private final String filePath;
    private final String description;
    private final String date;
    private final String comments;
    
    public MedicalRecord(String id,String patientID,String author,String patientDoctorID,
            String filePath,String description,String date,String comments){
        this.id = id;
        this.patientID = patientID;
        this.author = author;
        this.patientDoctorID = patientDoctorID;
        this.filePath = filePath;
        this.description = description;
        this.date = date;
        this.comments = comments;
    }
    
    public String getId() {
        return id;
    }
    
    public String getPatientId() {
        return patientID;
    }
    
    public String getPatientDoctorId() {
        return patientDoctorID;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getComments() {
        return comments;
    }
}
