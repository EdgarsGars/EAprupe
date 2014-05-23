<%-- 
    Document   : doctorPage
    Created on : May 16, 2014, 10:11:57 AM
    Author     : Edgar
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient list by id 'doctorID3'</title>
        
        
        
        
        
    </head>
    <body>
        <h1>This is doctor page</h1>
        <%
            List<Users.Patient> patientList = DB.PatientService.findPatientsByDoctorID("doctorID3");
            for (Users.Patient elem : patientList) {
                    out.print("<BR>"+ elem.getId()+ " " + elem.getName() + " " + elem.getSurname());
                }
            
            
        %>    
        <a href="/logout">Logout</a>
        
        
        
    </body>
</html>
