<%-- 
    Document   : p_medocalRecord
    Created on : 2014.27.5, 14:34:29
    Author     : Visi
--%>

<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.MedicalRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Medical Record</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet" >
   </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li  class='active'><a href='/EAprupe/home'><span>Home</span></a></li>
                <li><a href='/EAprupe/patientSearch'><span>Contact Doctor</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        
        <%
            MedicalRecord m = MedicalRecordService.findMedicalRecordByID((String)request.getAttribute("medID"));
            if (m != null) {
                out.print("<h3>Medical Record</h3>");
                out.print("<br><b>Date      : </b><br>" + m.getDate());
                out.print("<br><b>Record ID    : </b> " + m.getId());
                out.print("<br><b>Facility   : </b> " + m.getAuthor());
                out.print("<br><b>Description : </b><br> " + m.getDescription());
                out.print("<br><b>Comments      : </b><br>" + m.getComments());
            }
        %>

    </body>
</html>
