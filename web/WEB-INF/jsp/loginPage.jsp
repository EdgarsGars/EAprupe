<html>
    <head> <title> Autorizacija </title> </head>
<body>
   <h1>Login</h1>
   <form action="/EAprupe/authorize" method='post'>
      <table>
         <tr>
             <% if(request.getAttribute("error") != null) out.print("Failed to login!"); %>
            <td>ID:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>
</body>
</html>