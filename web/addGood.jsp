<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add good</title>
</head>
<body>
<form action="addGood" method="post" class="login-form">
    <input hidden type="text" name="idGood"  value="${idGood}">
    Name <input name="nameGood" type="text" class="my-input" required><br>
    Description <input name="description" type="text" class="my-input" required><br>
    Price <input name="price" type="text" class="my-input" required><br>
    <input type="submit" class="button">
</form>
</body>
</html>
