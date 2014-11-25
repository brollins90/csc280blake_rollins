<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="href" required="true" rtexprvalue="true" %>
<a href="${pageContext.request.contextPath}/${href}"><jsp:doBody/></a>
