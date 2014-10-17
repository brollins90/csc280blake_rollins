<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/update">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input name="id" style="display:none;" value="<%=model.getId()%>"/>
    <dl>

        <dt>Title:</dt>
        <dd>
            <input id="item_title" value="<%=model.getTitle()%>"/>
        </dd>

        <dt>Description:</dt>
        <dd>
            <input id="item_description" value="<%=model.getDescription()%>"/>
        </dd>

        <dt>Start Bid:</dt>
        <dd>
            <input id="item_start_bid" value="0.01"/>
        </dd>

        <dt>Start Time:</dt>
        <dd>
            <input id="item_start_time" value="<%=model.getStartTime()%>"/>
        </dd>

        <dt>End Time:</dt>
        <dd>
            <input id="item_end_time" value="<%=model.getEndTime()%>"/>
        </dd>

        <dt>img URL:</dt>
        <dd>
            <input id="item_image_url" value="<%=model.getImageUrl()%>"/>
        </dd>

        <dd>
            <input type="submit" value="Create the item"/>
        </dd>
    </dl>
</form>
<%@ include file="footer.jsp"%>

