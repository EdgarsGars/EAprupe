<%-- 
    Document   : patientSearchPage
    Created on : May 22, 2014, 10:20:05 PM
    Author     : Edgar
--%>

<%@page import="Users.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="DB.PatientService"%>
<%@page import="Users.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Search Page</title>
        <style>
            table,th,td{border:1px solid black; border-collapse:collapse; }
            th,td{ padding:5px; }
            th{ text-align:left; }
        </style>
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
                    + "</tr>");

            if (ID != null) {
                Patient patient = PatientService.findPatientByID(ID);
                if (patient != null) {
                    if (patient.getFamilyDoctor().equals(((Doctor) session.getAttribute("user")).getId())) {
                        out.print("<tr><td>" + patient.getId() + "</td><td><a href=\"/EAprupe/patient?patientID=" + patient.getId()
                                + "\">" + patient.getName() + "</td><td>" + patient.getSurname() + "</td><td>" + patient.getAddress() + "</td></tr>");

                    }
                }
            }
            List<Patient> patients = null;
            if (name != null && surname != null) {
                patients = PatientService.findPatientsByFullname(name, surname);
            }else if(surname != null) {
                patients = PatientService.findPatientsBySurname(surname);
            }else if(name != null){
                patients = PatientService.findPatientsByName(name);
            }
            
            if (patients != null) {
                for (Patient patient : patients) {
                    if (patient.getFamilyDoctor().equals(((Doctor) session.getAttribute("user")).getId())) {
                        out.print("<tr><td>" + patient.getId() + "</td><td><a href=\"/EAprupe/patient?patientID=" + patient.getId()
                                + "\">" + patient.getName() + "</td><td>" + patient.getSurname() + "</td><td>" + patient.getAddress()+ "</td></tr>");
                    }
                }
            }

            out.print("</table>");
        %>        

    </body>
</html>
