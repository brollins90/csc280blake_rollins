<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param.lang != null}">
	<fmt:setLocale value="${param.lang}"/>
</c:if>

<fmt:setBundle basename="edu.neumont.csc280.i18n.message" var="message"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit student ${model.firstName} ${model.lastName}</title>
</head>
<body>
	<form method="post">
		<dl>					
			<dt><fmt:message key="student.firstName" bundle="${message}"/></dt>
			<dd><input name="firstName" value="${model.firstName}"/></dd>
			<dt><fmt:message key="student.lastName" bundle="${message}"/></dt>
			<dd><input name="lastName" value="${model.lastName}"/></dd>
		</dl>
		<input type="submit" value="Create/Update"/>
	</form>
</body>
</html>