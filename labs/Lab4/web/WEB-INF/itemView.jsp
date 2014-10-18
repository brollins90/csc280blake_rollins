<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header1.jsp"%>
<script src="<%=application.getContextPath()%>/js/itemView.js" type="text/javascript"></script>
<%@ include file="header2.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<a href="<%=application.getContextPath()%>/item/<%=model.getId()%>/json">View item json</a><br/>
<a href="<%=application.getContextPath()%>/item/<%=model.getId()%>/update">Update this item</a><br/>
<a href="<%=application.getContextPath()%>/item/<%=model.getId()%>/delete">Delete this item</a><br/>
<a href="<%=application.getContextPath()%>/item/0/create">Create a new item</a><br/>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/bid">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input id="item_id" style="display:none;" value="<%=model.getId()%>"/>
    <img width="200" src="<%=application.getContextPath()%><%=model.getImageUrl()%>"/>
    <dl>

        <div>
            <label for="item_title">Title:</label>
            <label id="item_title" ><%=model.getTitle()%></label>
        </div>

        <div>
            <label for="item_description">Description:</label>
            <label id="item_description" ><%=model.getDescription()%></label>
        </div>

        <div>
            <label for="item_current_bid">Current Bid:</label>
            <label id="item_current_bid" ><%=model.getCurrentPrice()%></label>
        </div>

        <div>
            <label for="item_number_bids">Number of bids:</label>
            <label id="item_number_bids" ><%=model.getNumBids()%></label>
        </div>

        <div>
            <label for="item_start_time">Start time:</label>
            <label id="item_start_time" ><%=model.getStartTime()%></label>
        </div>

        <div>
            <label for="item_end_time">End time:</label>
            <label id="item_end_time" ><%=model.getEndTime()%></label>
        </div>

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

