<%@ page import="edu.neumont.csc280.lab3.nubay.AuctionItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<%=application.getContextPath()%>/css/main.css" rel="stylesheet"/>
    <meta http-equiv="refresh" content="5" >
    <title>Item List</title>
</head>
<body>
<a href="<%=application.getContextPath()%>/item/">
<div class="header">
    Blake's NuBay Auction Site!
</div></a>
<div class="container">
    <h1>Items:</h1>
    <%
        List<AuctionItem> items = (List)request.getAttribute("items");
        Iterator it = items.iterator();
        while(it.hasNext()) {
            AuctionItem current = (AuctionItem)it.next();
            out.print("<div class=\"itemdiv\">\n" +
                    "<a href=\"" +application.getContextPath() + "/item/" + current.getId() + "\">"+ current.getName() +"</a>\n" +
                    "</div>\n");
        }
    %>
</div>
</body>
</html>

