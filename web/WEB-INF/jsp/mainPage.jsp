<%-- 
    Document   : mainPage
    Created on : May 20, 2014, 11:19:04 AM
    Author     : Edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%  
            Object user = session.getAttribute("user");
            if(user == null){
                String redirectURL = "/EAprupe/login?=error";
                response.sendRedirect(redirectURL);
            }
        %>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            
            //Object user = new Users.Patient();
            session.setAttribute("user",user);
        
        %>
        
        <h1>${userID}</h1>
<a href="/logout">Logout</a>
    </body>
</html>