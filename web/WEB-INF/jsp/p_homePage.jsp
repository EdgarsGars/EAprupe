<%-- 
    Document   : homePage
    Created on : May 22, 2014, 6:18:40 PM
    Author     : Edgar
--%>

<%@page import="java.util.List"%>
<%@page import="Users.MedicalRecord"%>
<%@page import="DB.MedicalRecordService"%>
<%@page import="Users.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li class='active'><a href='/EAprupe/home'><span>Home</span></a></li>
                <li><a href='/EAprupe/contact'><span>Contact doctor</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        <br><h3>Your medical record history</h3>
        <%
           out.print("<table style = \"width:600px\""
                 + "<tr><th>Facility</th>"
                 + "<th>Description</th>"
                 + "<th>Comment</th>"
                 + "<th>Date</th>");
           
           Patient p = (Patient)session.getAttribute("user");
           String patientID = p.getId();
           List<MedicalRecord> recordList = MedicalRecordService.findRecordByPatientID(patientID);
           if(recordList != null){
               for(int i = 0; i < recordList.size(); i++){
               MedicalRecord r = recordList.get(i);
               if(r.getComments() != null && r.getComments().length() > 0){
                   
                   
                   out.print("<tr><td>"+ r.getAuthor() +"</td><td>"+r.getDescription()+"</td><td><a href=\"/EAprupe/medicalRecord?ID="+r.getId()+"\""+">VIEW</a></td><td>"+ r.getDate()+"</td></tr>");
               }
           }
           }
        %>
        
        
    </body>
</html>
