<%-- 
    Document   : doctorsHomePage
    Created on : May 22, 2014, 10:08:56 PM
    Author     : Edgar
--%>

<%@page import="Users.Patient"%>
<%@page import="DB.PatientService"%>
<%@page import="Users.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctors Page</title>
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






    </body>
</html>
