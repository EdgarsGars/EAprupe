<%-- 
    Document   : doctorsHomePage
    Created on : May 22, 2014, 10:08:56 PM
    Author     : Edgar
--%>

<%@page import="java.util.List"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.MedicalRecord"%>
<%@page import="Users.Patient"%>
<%@page import="DB.PatientService"%>
<%@page import="Users.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctors Page</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li class='active'><a href='/EAprupe/home'><span>Home</span></a></li>
                <li><a href='/EAprupe/patientSearch'><span>Patients</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        <br>
        <h3> Medical records that need your attention! </h3>
        <table style="width: 400px">
            <tr><th>ID</th><th> Patient Name </th><th> Date </th></tr>
                    <%

                        String ID = ((Doctor) request.getAttribute("user")).getId();
                        List<MedicalRecord> records = MedicalRecordService.findMedicalRecordsByDoctorID(ID);
                        if (records != null) {
                            for (MedicalRecord r : records) {
                                if (r.getComments() == null || r.getComments().length() == 0) {
                                    Patient p = PatientService.findPatientByID(r.getPatientId());
                                    out.print("<tr><td>" + r.getId() + "</td><td><a href='/EAprupe/medicalRecord?ID=" + r.getId() + "'> " + p.getName() + " " + p.getSurname() + "</a></td><td>" + r.getDate() + "</td></tr>");
                                }
                            }
                        }
                    %>           
        </table>
    </body>
</html>
