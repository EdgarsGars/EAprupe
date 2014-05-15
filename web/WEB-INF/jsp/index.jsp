<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP page</title>
    </head>

    <body>
        <form name="login" action="/EAprupe/login" method="post">
            Account ID: <input type="text" name="accountID"><br>
            Password  : <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
    </body>
</html>
