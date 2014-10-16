<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/bid">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input name="id" style="display:none;" value="<%=model.getId()%>"/>
    <img width="200" src="<%=application.getContextPath()%><%=model.getImageUrl()%>"/>
    <dl>

        <dt>Description:</dt>
        <dd><%=model.getDescription()%></dd>

        <dt>Current Bid:</dt>
        <dd><%=model.getCurrentPrice()%></dd>

        <dt>Start Time:</dt>
        <dd><%=model.getStartTime()%></dd>

        <dt>End Time:</dt>
        <dd><%=model.getEndTime()%></dd>

        <dt>Time Left</dt>
        <dd><%=model.getTimeLeft()%></dd>

        <dt>Bid Amount:</dt>
        <dd><input type="radio" name="bidAmount" value="one" checked>$1</dd>
        <dd><input type="radio" name="bidAmount" value="five">$5</dd>
        <dd><input type="radio" name="bidAmount" value="ten">$10</dd>
        <%--<dd><input type="radio" name="bidAmount" value="min"><%=model.getMinBidAmount()%></dd>--%>
        <dd><input type="radio" name="bidAmount" value="custom">Other:</dd>
        <dt>
            <input name="incrementBid"/>
        </dt>
        <dd>
            <input type="submit" value="Place a bid"/>
        </dd>
    </dl>
    </form>
<%@ include file="footer.jsp"%>

