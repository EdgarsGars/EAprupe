<%-- 
    Document   : medicalRecordPage
    Created on : May 27, 2014, 9:38:11 AM
    Author     : Edgar
--%>

<%@page import="Users.Patient"%>
<%@page import="DB.PatientService"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Record</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet" >
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li class='active'><a href='/EAprupe/patientSearch'><span>Patients</span></a></li>
                <li><a href='#'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        <%
            String medID = (String) request.getAttribute("medID");
            MedicalRecord r = MedicalRecordService.findMedicalRecordByID(medID);
            Patient p = PatientService.findPatientByID(r.getPatientId());
            if (r != null && p != null) {
                out.print("<h3>Medical Record</h3>");

                out.print("<br><b>Patient name    : </b> " + p.getName());
                out.print("<br><b>Patient surname : </b> " + p.getSurname());
                out.print("<br><b>Patient ID      : </b> " + p.getId());
                out.print("<br><b>Medical facility: </b> " + r.getAuthor());//TODO show name
                out.print("<br><b>Attached file   : </b> " + r.getFilePath());
                out.print("<br><b>Descripton      : </b><br>" + r.getDescription());
                out.print("<br><b>Add Comment     : </b> ");
            }%>

        <form action="/EAprupe/updateMed?ID=<%=medID%>" method="post">
            <p><textarea rows="10" cols="45" name="comment"><%=r.getComments()%></textarea></p>
            <p><input type="submit" value="Add Comment"></p>
        </form>









    </body>
</html>
