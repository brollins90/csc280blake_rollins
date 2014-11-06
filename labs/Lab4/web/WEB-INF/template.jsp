<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>${param.title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/jquery-ui-timepicker-addon.1.5.0.min.css"/>
    <c:if test="${not empty param.css}">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/${param.css}"/>
    </c:if>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-ui-timepicker-addon.1.5.0.min.js"></script>
    <c:if test="${not empty param.js}">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/${param.js}"></script>
    </c:if>
</head>
<body>
<jsp:include page="/WEB-INF/header2.jsp"/>
<h1>${param.title}</h1>
<jsp:include page="/WEB-INF/${param.content}.jsp"/>
<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
