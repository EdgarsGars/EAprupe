<%-- 
    Document   : f_addRecord
    Created on : May 30, 2014, 1:25:32 AM
    Author     : Edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Add Record</title>
        <link href="css/styles.css" rel="stylesheet" >
        <link href="css/tableStyle.css" rel="stylesheet">
    </head>
    <body>
        <!--TOP MENU -->
         <!--TOP MENU -->
        <div id='cssmenu'>
            <ul>
                <li><a href='/EAprupe/home'><span>Home</span></a></li>
                <li><a href='/EAprupe/medicalRecords'><span>Medical Records</span></a></li>
                <li class='active'><a href='/EAprupe/addRecord'><span>Add Medical Record</span></a></li>
                <li><a href='/EAprupe/settings'><span>Settings</span></a></li>
                <li class='last'><a href='/EAprupe/logout'><span>Logout</span></a></li>
            </ul>
        </div>
        <br>

        <form name="patientCreate" action="/EAprupe/addRecord" method="post">
            Person ID     : <input type="text" name="userID" placeholder="123456-12345" autocomplete="off"><br>
            Upload file: <input type="text" name="file" autocomplete="off"><br>
            Description :<br>
            <p><textarea rows="10" cols="45" name="desc"></textarea></p>
            <input type="submit" value="Add">
        </form>




    </body>
</html>
