<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<div class="header">
    <a href="${pageContext.request.contextPath}/item/">
        Blake's NuBay Auction Site!</a>
</div>
<div class="search">
    <form name="searchForm" id="searchForm" action="${pageContext.request.contextPath}/search/" method="get">
        <input type="text" name="s" id="s" value=""/>
        <input type="hidden" name="c" id="c" value="10"/>
        <input type="hidden" name="o" id="o" value="0"/>
        <input type="submit" value="Search"/></form>
</div>
<div class="container">
