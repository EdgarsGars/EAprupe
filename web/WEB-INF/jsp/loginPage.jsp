<!DOCTYPE html>
<head>
    <Title>Login</Title>
    <link href="css/loginStyle.css" rel="stylesheet" >
</head>
<body>
    <div id="container">       
        <form method = "post" action="/EAprupe/authorize"> 
            <label for="username">User ID</label><input type="text" id="username" name="username" autocomplete="off">
            <label for="password">Password</label><input type="password" id="password" name="password" autocomplete="off">
            <div id="lower">
                
                <input type="submit" value="Login">
            </div>
        </form>
    </div>
</body>
</html>
