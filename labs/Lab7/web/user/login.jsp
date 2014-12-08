
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Log In</title>
</head>
<body>
<form method="post" action="j_security_check">
  <p>Username</p>
  <input type="text" name="j_username" />
  <p>Password</p>
  <input type="password" name="j_password" />
  <p></p>
  <input type="submit" value="Submit" />
  <input type="reset" value="reset" />
</form>
</body>
</html>
