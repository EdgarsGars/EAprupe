<%-- 
    Document   : addPatient
    Created on : May 27, 2014, 1:31:48 AM
    Author     : Edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Patient</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
        <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li class='active'><a href='/EAprupe/patientSearch'><span>Patients</span></a></li>
                <li><a href='#'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <!--             -->
        <br>

        <form name="patientCreate" action="/EAprupe/docAddPatient" method="post">
            Person ID     : <input type="text" name="userID" placeholder="123456-12345" autocomplete="off"><br>
            Person Name   : <input type="text" name="name" autocomplete="off"><br>
            Person Surname: <input type="text" name="surname" autocomplete="off"><br>
            Person Address: <input type="text" name="address" autocomplete="off"><br>
            Person Telephone: <input type="text" name="number" autocomplete="off"><br>
            Person email: <input type="text" name="email" autocomplete="off"><br>
            Person password : <input type="password" name="password1" autocomplete="off"><br>
            Retype password : <input type="password" name="password2" autocomplete="off"><br>
            <input type="submit" value="Add">
        </form>




    </body>
</html>
