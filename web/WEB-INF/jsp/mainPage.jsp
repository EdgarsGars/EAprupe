<%-- 
    Document   : mainPage
    Created on : May 15, 2014, 7:54:30 PM
    Author     : Edgar
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
 
    <body>
        You have successfully logged in.
        <br/>
         
        <a href="<c:url value="/admin"/>">Admin Page</a>
        <br/>
        <br/>
        <br/>
        <a href="<c:url value="/common"/>">Regular User Page</a>
         
        <br/>
        <br/>
        <br/>
        <a href="<c:url value="/logout"/>">Logout</a>
         
    </body>
</html>
