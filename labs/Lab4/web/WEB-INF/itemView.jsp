<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<a href="<%=application.getContextPath()%>/item/<%=model.getId()%>/update">Update this item</a><br/>
<a href="<%=application.getContextPath()%>/item/<%=model.getId()%>/delete">Delete this item</a><br/>
<a href="<%=application.getContextPath()%>/item/0/create">Create a new item</a><br/>

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
        <dd><%=(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(model.getStartTime())) + " - " + model.getStartTime()%></dd>

        <dt>End Time:</dt>
        <dd><%=(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(model.getEndTime())) + " - " + model.getEndTime()%></dd>

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

