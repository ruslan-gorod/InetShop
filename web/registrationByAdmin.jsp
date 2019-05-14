<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="style.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration form by admin</title>
</head>
<body>
<form action="regist" method="post" class="login-form">
    <input hidden type="text" name="id"  value="${id}">
    Login <input name="login" type="text" class="my-input" required><br>
    Password <input name="password" type="password" class="my-input" required><br>
    Confirm Password <input name="confirmPassword" type="password" class="my-input" required><br>
    Name <input name="nameUser" type="text" class="my-input" required><br>
    E-mail <input name="emailUser" type="text" class="my-input" required><br>
    Role Id <input name="idRoleUser" type="text" class="my-input" required><br>
    <input type="checkbox" style="font-size: medium;"> Agree<br>
    <input type="submit" class="button">
</form>
</body>
</html>