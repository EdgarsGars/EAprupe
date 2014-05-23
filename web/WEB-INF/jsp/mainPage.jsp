<%-- 
    Document   : mainPage
    Created on : May 20, 2014, 11:19:04 AM
    Author     : Edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            
           out.print("Welcome " + ((Users.Patient)(session.getAttribute("user"))).getName());
        
        %>
        
        
<a href="/EAprupe/logout">Logout</a>
    </body>
</html>