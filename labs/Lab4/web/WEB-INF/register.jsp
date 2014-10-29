<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${model.errorMessage}">
        <h1>${model.errorMessage}</h1>
    </c:if>
    <form method="post">
        Username: <input name="username"/>
        Password: <input name="password" type="password"/>
        Confirm: <input name="confirmPassword" type="password"/>
        <input type="submit" value="register"/>
    </form>
</body>
</html>
