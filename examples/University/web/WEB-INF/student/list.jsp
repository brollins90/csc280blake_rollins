<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="univ" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setBundle basename="edu.neumont.csc280.i18n.message" var="message"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="student" items="${model}">
			<univ:student student="${student}"/>
<%--  			<tr>
				<td><univ:link href="app/student/${student.id}">${student.firstName} ${student.lastName}</univ:link>
				<td>
					<univ:link href="app/student/${student.id}/update">
						<fmt:message key="student.edit" bundle="${message}"/>
					</univ:link>
				</td>
			</tr> --%>												
<%--<a href="${pageContext.request.contextPath}/app/student/${student.id}">${student.firstName} ${student.lastName}</a></td>--%>
<%--<a href="${pageContext.request.contextPath}/app/student/${student.id}/update">Edit</a>"</td> --%>
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/app/student">Create</a>
</body>
</html>