<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/update">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input name="id" style="display:none;" value="<%=model.getId()%>"/>
    <dl>

        <dt>Title:</dt>
        <dd><%=model.getTitle()%></dd>

        <dt>Description:</dt>
        <dd><%=model.getDescription()%></dd>

        <dt>Start Bid:</dt>
        <dd><%=model.getCurrentPrice()%></dd>

        <dt>Start Time:</dt>
        <dd><%=model.getStartTime()%></dd>

        <dt>End Time:</dt>
        <dd><%=model.getEndTime()%></dd>

        <dt>img URL:</dt>
        <dd><%=model.getImageUrl()%></dd>

        <dd>
            <input type="submit" value="Update the item"/>
        </dd>
    </dl>
</form>
<%@ include file="footer.jsp"%>

