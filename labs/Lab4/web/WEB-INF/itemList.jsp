<%@ page import="edu.neumont.csc280.lab4.item.AuctionItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header1.jsp"%>
<script src="<%=application.getContextPath()%>/js/itemList.js" type="text/javascript"></script>
<%@ include file="header2.jsp"%>
<% List<AuctionItem> model = (List<AuctionItem>)request.getAttribute("model"); %>
    <h1>Items:</h1>
    <%
        Iterator it = model.iterator();
        while(it.hasNext()) {
            AuctionItem current = (AuctionItem)it.next();
            out.print("<div class=\"itemdiv\">\n" +
                    "<a href=\"" +application.getContextPath() + "/item/" + current.getId() + "\">"+ current.getTitle() +"</a>\n" +
                    "</div>\n");
        }
    %>
<%@ include file="footer.jsp"%>
