<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head onload='document.loginForm.j_username.focus();'>
        <title> Custom Login Page</title>
    </head>
    <body>
        <h3>Login</h3>
        <%
            String errorString = (String) request.getAttribute("error");
            if (errorString != null && errorString.trim().equals("true")) {
                out.println("Your login attempt was unsuccessful. Please retry using correct login name and password.");
            }
        %>
        <form name='loginForm' action="<c:url value='EAprupe/autorize' />" method='POST'>
            <table>
                <tr>
                    <td>User ID:</td>
                    <td><input type='text' name='username' value=''>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password' />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input type="submit" value="Login" />
                </tr>

            </table>

        </form>
    </body>
</html>