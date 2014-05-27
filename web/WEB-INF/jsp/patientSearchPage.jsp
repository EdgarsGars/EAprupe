<%-- 
    Document   : patientSearchPage
    Created on : May 22, 2014, 10:20:05 PM
    Author     : Edgar

    TODO:
        sort patients by name
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Users.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="DB.PatientService"%>
<%@page import="Users.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Patient Search Page</title>
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
        <br>
        <form name="input" action="/EAprupe/patientSearch" method="post">
            Person ID     : <input type="text" name="userID" placeholder="123456-12345" autocomplete="off">
            Person Name   : <input type="text" name="name" autocomplete="off">
            Person Surname: <input type="text" name="surname" autocomplete="off">
            <input type="submit" value="Find">
        </form>
        <br>


        <%
            String ID = (String) request.getAttribute("userID");
            String name = (String) request.getAttribute("name");
            String surname = (String) request.getAttribute("surname");

            out.print("<table style = \"width:600px\""
                    + "<tr> <th> Patient ID </th>"
                    + "<th>Patient Name </th>"
                    + "<th>Patient Surname</th>"
                    + "<th>Patient Address</th>"
                    + "<th>Patient Number</th>"
                    + "</tr>");

            List<Patient> patients = null;
            if (ID != null && ID.length() > 0) {
                Patient patient = PatientService.findPatientByID(ID);
                if (patient != null) {
                    if (patient.getFamilyDoctor().equals(((Doctor) session.getAttribute("user")).getId())) {
                        out.print("<tr><td>" + patient.getId() + "</td><td><a href=\"/EAprupe/patient?patientID=" + patient.getId()
                                + "\">" + patient.getName() + "</td><td>" + patient.getSurname() + "</td><td>" + patient.getAddress() + "</td><td>" + patient.getNumber()+ "</td></tr>");
                    }
                }
            } else if (name != null && surname != null) {
                patients = PatientService.findPatientsByFullname(name, surname);
            } else if (surname != null) {
                patients = PatientService.findPatientsBySurname(surname);
            } else if (name != null) {
                patients = PatientService.findPatientsByName(name);
            } else {
                patients = PatientService.findPatientsByDoctorID(((Doctor) session.getAttribute("user")).getId());
            }

            if (patients != null) {
                for (Patient patient : patients) {
                    if (patient.getFamilyDoctor().equals(((Doctor) session.getAttribute("user")).getId())) {
                        out.print("<tr><td>" + patient.getId() + "</td><td><a href=\"/EAprupe/patient?patientID=" + patient.getId()
                                + "\">" + patient.getName() + "</td><td>" + patient.getSurname() + "</td><td>" + patient.getAddress() + "</td><td>" + patient.getNumber() + "</td></tr>");
                    }
                }
            }

            out.print("</table>");
            request.setAttribute("userID", null);
        %>        

    </body>
</html>
