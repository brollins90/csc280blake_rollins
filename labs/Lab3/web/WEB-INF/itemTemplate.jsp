<%@ page import="edu.neumont.csc280.lab3.nubay.AuctionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<% AuctionItem model = (AuctionItem)request.getAttribute("item");%>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/bid">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input name="id" style="display:none;" value="<%=model.getId()%>"/>
    <img width="200" src="<%=application.getContextPath()%>/item/<%=model.getId()%>/image"/>
    <dl>
        <dt>Current Bid:</dt>
        <dd><%=model.getCurrentPrice()%></dd>

        <dt>Time Left</dt>
        <dd><%=model.getTimeLeft()%></dd>
        <dt>
            <input name="incrementBid"/>
        </dt>
        <dd>
            <input type="submit" value="Place a bid"/>
        </dd>
    </dl>
    </form>
<%@ include file="footer.jsp"%>

