<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student page for ${model.firstName} ${model.lastName}</title>
</head>
<body>
	<dl>
		<dt>First Name:</dt>
		<dd>${model.firstName}</dd>
		<dt>Last Name:</dt>
		<dd>${model.lastName}</dd>
	</dl>
</body>
</html>