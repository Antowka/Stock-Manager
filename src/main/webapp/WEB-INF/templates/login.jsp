<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 31.10.15
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="loginForm" method="post" action="/auth_validate">
        <input type="text" name="username" placeholder="username" />
        <input type="password" name="password" placeholder="password" />
        <input name="submit" type="submit" value="Login" />
    </form>
</body>
</html>
