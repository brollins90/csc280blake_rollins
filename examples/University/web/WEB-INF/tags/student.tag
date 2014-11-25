<%@ tag body-content="scriptless" language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="student" type="edu.neumont.csc280.univ.model.Student" rtexprvalue="true" required="true" %>
<%@ taglib prefix="univ" tagdir="/WEB-INF/tags"%>
<tr>
	<td><univ:link href="app/student/${student.id}">${student.firstName} ${student.lastName}</univ:link></td>
	<td><univ:link href="app/student/${student.id}/update">Edit</univ:link></td>
</tr>