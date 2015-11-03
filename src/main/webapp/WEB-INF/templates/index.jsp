<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 21.10.15
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
    <p>SingIn</p>

    <form name="loginForm" method="post" action="/auth_validate">
        <input type="text" name="username" placeholder="username" />
        <input type="password" name="password" placeholder="password" />
        <input name="submit" type="submit" value="Login" />
    </form>

</body>
</html>
