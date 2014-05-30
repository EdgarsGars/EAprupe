<%-- 
    Document   : f_medicalRecords
    Created on : May 30, 2014, 12:42:19 AM
    Author     : Edgar
--%>

<%@page import="Users.Patient"%>
<%@page import="DB.PatientService"%>
<%@page import="java.util.List"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.MedicalRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.MedicalFacilityService"%>
<%@page import="Users.MedicalFacility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Records</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
        <script type="text/javascript" >
            var monthtext = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
            function date_populate(dayfield, monthfield, yearfield) {
                var today = new Date();
                var dayfield = document.getElementById(dayfield);
                var monthfield = document.getElementById(monthfield);
                var yearfield = document.getElementById(yearfield);
                for (var i = 0; i < 31; i++)
                    dayfield.options[i] = new Option(i + 1, i + 1);

                dayfield.options[today.getDate() - 1] = new Option(today.getDate(), today.getDate(), true, true) //select today's day
                for (var m = 0; m < 12; m++)
                    monthfield.options[m] = new Option(monthtext[m], monthtext[m]);

                monthfield.options[today.getMonth()] = new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true) //select today's month
                var thisyear = today.getFullYear();
                for (var y = 0; y < 100; y++) {
                    yearfield.options[y] = new Option(thisyear, thisyear);
                    thisyear -= 1;
                }
                yearfield.options[0] = new Option(today.getFullYear(), today.getFullYear(), true, true); //select today's year
            }
        </script>
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li class='active'><a href='/EAprupe/medicalRecords'><span>Medical Records</span></a></li>
                <li><a href='/EAprupe/addRecord'><span>Add Medical Record</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->

        <br>
        <form name="input" action="/EAprupe/medicalRecords" method="post">
            Patient ID     : <input type="text" name="patientID" placeholder="123456-12345" autocomplete="off">
            <SELECT id ="date" name = "dd"></SELECT>
            <SELECT id ="month" name = "mm"></SELECT>
            <SELECT id ="year" name = "yyyy"></SELECT>
            <input type="submit" value="Find">
        </form><br>
        <script type="text/javascript">date_populate("date", "month", "year");</script>



        <%
            MedicalFacility user = MedicalFacilityService.findMedicalFacilityByID((String) request.getAttribute("facilityID"));
            String patientID = (String) request.getAttribute("patientID");
            out.print("<table style = \"width:900px\""
                    + "<tr><th>Record ID</th>"
                    + "<th>Patient ID</th>"
                    + "<th>Patient name</th>"
                    + "<th>Patient surname</th>"
                    + "<th>File</th>"
                    + "<th>Description</th>"
                    + "<th>Date added</th>"
                    + "</tr>");
            //MAYBE SEARCH ONLY BY PERSON ID
            List<MedicalRecord> records = null;
            if (patientID != null && patientID.length() > 0) {
                records = MedicalRecordService.findRecordByPatientID(patientID);
                if (records != null) {
                    for (MedicalRecord r : records) {
                        if (r.getAuthor().equals(user.getId())) {
                            Patient p = PatientService.findPatientByID(r.getPatientId());
                            out.print("<tr><td>" + r.getId() + "</td>");
                            out.print("<td>" + r.getPatientId() + "</td>");
                            out.print("<td>" + p.getName() + "</td>");
                            out.print("<td>" + p.getSurname() + "</td>");
                            out.print("<td>" + r.getFilePath() + "</td>");
                            out.print("<td><a href=\"/EAprupe/medicalRecord?ID="+ r.getId() +"\">VIEW</a></td>");
                            out.print("<td>" + r.getDate() + "</td></tr>");
                        }
                    }
                }
            } else if (request.getAttribute("dd") != null && request.getAttribute("mm") != null && request.getAttribute("yyyy") != null) {
                records = MedicalRecordService.findByDate((String) request.getAttribute("dd"), (String) request.getAttribute("mm"), (String) request.getAttribute("yyyy"));
                if (records != null) {
                    for (MedicalRecord r : records) {
                        if (r.getAuthor().equals(user.getId())) {
                            Patient p = PatientService.findPatientByID(r.getPatientId());
                            out.print("<tr><td>" + r.getId() + "</td>");
                            out.print("<td>" + r.getPatientId() + "</td>");
                            out.print("<td>" + p.getName() + "</td>");
                            out.print("<td>" + p.getSurname() + "</td>");
                            out.print("<td>" + r.getFilePath() + "</td>");
                            out.print("<td><a href=\"/EAprupe/medicalRecord?ID="+ r.getId() +"\">VIEW</a></td>");
                            out.print("<td>" + r.getDate() + "</td></tr>");
                        }
                    }
                }
            }
        %>










    </body>
</html>
