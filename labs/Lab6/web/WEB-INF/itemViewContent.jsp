<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="${pageContext.request.contextPath}/item/${model.id}/json">View item json</a><br/>
<a href="${pageContext.request.contextPath}/item/${model.id}/update">Update this item</a><br/>
<a href="${pageContext.request.contextPath}/item/${model.id}/delete">Delete this item</a><br/>
<%--<a href="${pageContext.request.contextPath}/item/0/create">Create a new item</a><br/>--%>

<form method="post" action="${pageContext.request.contextPath}/item/${model.id}/bid">
    <h1>Auction Item #${model.id}</h1>
    <input id="item_id" style="display:none;" value="${model.id}"/>
    <img width="200" src="${model.imageUrl}"/>
    <dl>

        <div>
            <label for="item_title">Title:</label>
            <label id="item_title">${model.title}</label>
        </div>

        <div>
            <label for="item_description">Description:</label>
            <label id="item_description">${model.description}</label>
        </div>

        <div>
            <label for="item_current_bid">Current Bid:</label>
            <label id="item_current_bid"><fmt:formatNumber value="${model.price}" type="currency"/></label>
        </div>

        <div>
            <label for="item_number_bids">Number of bids:</label>
            <label id="item_number_bids">${model.numBids}</label>
        </div>

        <div>
            <label for="item_start_time">Start time:</label>
            <label id="item_start_time">${model.startTime}</label>
        </div>

        <div>
            <label for="item_end_time">End time:</label>
            <label id="item_end_time">${model.endTime}</label>
        </div>

        <dt>Bid Amount:</dt>
        <dd><input type="radio" name="bidAmount" value="one" checked>$1</dd>
        <dd><input type="radio" name="bidAmount" value="five">$5</dd>
        <dd><input type="radio" name="bidAmount" value="ten">$10</dd>
        <dd><input type="radio" name="bidAmount" value="custom">Other:</dd>
        <dt> <input name="incrementBid"/></dt>
        <dd>
            <input type="submit" value="Place a bid"/>
        </dd>
    </dl>
</form>
