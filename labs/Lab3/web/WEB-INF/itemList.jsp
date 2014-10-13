<%@ page import="edu.neumont.csc280.lab3.nubay.AuctionItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="5" >
    <title>Item List</title>
</head>
<body>
    <h1>Items:</h1>
    <%
        List<AuctionItem> items = (List)request.getAttribute("items");
        Iterator it = items.iterator();
        while(it.hasNext()) {
            AuctionItem current = (AuctionItem)it.next();
            out.print("<br><a href=\"" + current.getId() + "\">"+ current.getName() +"</a>\n");
        }
    %>
</body>
</html>

