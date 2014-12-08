<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="header">
    <a href="${pageContext.request.contextPath}/item/">
        Blake's NuBay Auction Site!</a>
</div>
<div class="login">
    <c:choose>
    <c:when test="${sessionScope.user}">
        Welcome ${sessionScope.user.username}.  <a href="${pageContext.servletContext.contextPath}/user/logout.jsp">Log out</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.servletContext.contextPath}/user/login.jsp">Log in</a>
    </c:otherwise>
    </c:choose>
</div>
<div class="search">
    <form name="searchForm" id="searchForm" action="${pageContext.request.contextPath}/search/" method="get">
        <input type="text" name="s" id="s" value=""/>
        <input type="hidden" name="c" id="c" value="10"/>
        <input type="hidden" name="o" id="o" value="0"/>
        <input type="submit" value="Search"/></form>
</div>
<div class="container">
