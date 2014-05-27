<%-- 
    Document   : settingPage
    Created on : May 27, 2014, 3:59:59 PM
    Author     : Edgar
--%>

<%@page import="DB.DoctorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Settings</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet" >
    </head>
    <body>
       <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li><a href='/EAprupe/patientSearch'><span>Patients</span></a></li>
                <li class='active'><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        <br>
        <h3>Your user information</h3>
         <form name="input" action="/EAprupe/updateDoctor" method="post">
            <b>Doctor ID     :</b> <%= DoctorService.findDoctorByID((String)request.getAttribute("doctorID")).getId()%><br>
            <b>Doctor Name   :</b> <%= DoctorService.findDoctorByID((String)request.getAttribute("doctorID")).getName()%><br>
            <b>Doctor Surname:</b> <%= DoctorService.findDoctorByID((String)request.getAttribute("doctorID")).getSurname()%><br>
            <input type="submit" value="Change">
        </form><br>
        
        
        
        
    </body>
</html>
