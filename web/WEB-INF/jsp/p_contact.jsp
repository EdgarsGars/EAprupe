<%-- 
    Document   : p_contact
    Created on : 2014.27.5, 13:46:58
    Author     : Visi
--%>

<%@page import="Users.Doctor"%>
<%@page import="DB.DoctorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Doctor contacts</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
         <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li class='active'><a href='/EAprupe/contact'><span>Contact doctor</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
         <br><h3>Doctor contact information</h3>
        <%
            String doctorID = (String)request.getAttribute("doctorID");
            Doctor doctor = DoctorService.findDoctorByID(doctorID);
            out.print("<b>Doctor name    : </b> " + doctor.getName());
            out.print("<br><b>Doctor surname : </b> " + doctor.getSurname());
            out.print("<br><b>Doctor ID      : </b> " + doctor.getId());
            out.print("<br><b>Doctor address : </b> " + doctor.getAddress());
            out.print("<br><b>Doctor telephone : </b> " + doctor.getTelephoneNumber());
            
        %>
        
        
        
    </body>
</html>
