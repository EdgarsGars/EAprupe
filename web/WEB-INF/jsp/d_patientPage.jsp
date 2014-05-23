<%-- 
    Document   : patientsPage
    Created on : May 23, 2014, 10:31:18 AM
    Author     : Edgar
--%>

<%@page import="Users.Doctor"%>
<%@page import="Users.MedicalRecord"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="java.util.List"%>
<%@page import="Users.Patient"%>
<%@page import="DB.PatientService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            table,th,td{border:1px solid black; border-collapse:collapse; }
            th,td{ padding:5px; }
            th{ text-align:left; }
        </style>
        <title>Patient ${patientID}</title>
    </head>
    <body>
        <!--TOP MENU -->
        <table>
            <tr>
                <td><a href="/EAprupe/home" STYLE="text-decoration: none">Home</a></td>
                <td><a href="/EAprupe/patientSearch" STYLE="text-decoration: none">Patients</a></td>
                <td><a href="#" STYLE="text-decoration: none">Medical Records</a></td>
                <td><a href="/EAprupe/settings" STYLE="text-decoration: none">Settings</a></td>
                <td><a href="/EAprupe/logout" STYLE="text-decoration: none">Logout</a></td>
            </tr>
        </table>

        <%
            Patient p = PatientService.findPatientByID(request.getParameter("patientID"));
            out.print("<br><b>Patient name    : </b> " + p.getName());
            out.print("<br><b>Patient surname : </b> " + p.getSurname());
            out.print("<br><b>Patient ID      : </b> " + p.getId());
            out.print("<br><b>Patient address : </b> " + p.getAddress());
            out.print("<br><b>Patient telephone : </b> " + p.getNumber());
            out.print("<br><b>Patient email : </b> " + p.getEmail());
            
            out.print("<br><br><table style = \"width:600px\""
                 + "<tr><th>Record ID</th>"
                 + "<th>Author ID</th>"
                 + "<th>File</th>"
                 + "<th>Description</th>"
                 + "<th>Comment</th>"
                 + "<th>Date added</th>"
                 + "</tr>");
            
            List<MedicalRecord> records = MedicalRecordService.findRecordByPatientID(p.getId());
            if(records != null){
                System.out.println(records.size());
                for(MedicalRecord r : records){
                    out.print("<tr><td>"+ r.getId() +"</td><td>"+r.getAuthor()+"</td><td>"+ r.getFilePath()+"</td><td>"+ r.getDescription()+"</td><td>"+ r.getComments()+"</td><td>"+ r.getDate()+"</td></tr>");
                }
            }
            
        %>

    </body>
</html>
