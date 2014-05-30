<%-- 
    Document   : f_medicalRecordPage
    Created on : May 30, 2014, 3:49:02 PM
    Author     : Edgar
--%>

<%@page import="DB.PatientService"%>
<%@page import="Users.Patient"%>
<%@page import="Users.Patient"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Medical Record</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li class='active'><a href='/EAprupe/medicalRecords'><span>Medical Records</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->

        <%
            String recordID = (String) request.getAttribute("medID");
            MedicalRecord record = MedicalRecordService.findMedicalRecordByID(recordID);
            Patient p = PatientService.findPatientByID(record.getPatientId());
            if (record != null && p != null) {
                out.print("<h3>Medical Record</h3>");

                out.print("<br><b>Patient name    : </b> " + p.getName());
                out.print("<br><b>Patient surname : </b> " + p.getSurname());
                out.print("<br><b>Patient ID      : </b> " + p.getId());
                out.print("<br><b>Attached file   : </b> " + record.getFilePath());
                out.print("<br><b>Descripton      : </b><br>");
            }
        %>
        <form action="/EAprupe/updateMed?ID=<%=recordID%>" method="post">
            <p><textarea rows="10" cols="45" name="comment"><%=record.getDescription()%></textarea></p>
            <p><input type="submit" value="Add Description"></p>
        </form>




    </body>
</html>
