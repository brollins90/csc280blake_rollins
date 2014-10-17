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
            <input name="item_title" value="<%=model.getTitle()%>"/>
        </dd>

        <dt>Description:</dt>
        <dd>
            <textarea name="item_description"><%=model.getDescription()%></textarea>
        </dd>

        <dt>Start Bid:</dt>
        <dd>
            <input name="item_start_bid" value="0.01"/>
        </dd>

        <dt>Start Time:</dt>
        <dd>
            <input name="item_start_time" value="<%=model.getStartTime()%>"/>
        </dd>

        <dt>End Time:</dt>
        <dd>
            <input name="item_end_time" value="<%=model.getEndTime()%>"/>
        </dd>

        <dt>img URL:</dt>
        <dd>
            <input name="item_image_url" value="<%=model.getImageUrl()%>"/>
        </dd>

        <dd>
            <input type="submit" value="Update the item"/>
        </dd>
    </dl>
</form>
<%@ include file="footer.jsp"%>
