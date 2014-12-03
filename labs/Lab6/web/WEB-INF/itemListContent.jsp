<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>

<a href="${pageContext.request.contextPath}/item/0/create">Add Item</a>

<table class="listing">
    <tr>
        <th>Title</th>
        <th>Image</th>
        <th>Description</th>
        <th>Time left</th>
        <th>Number of bids</th>
        <th>Price</th>
        <%--<th>Action</th>--%>
    </tr>

    <c:forEach var="item" items="${model.getItems()}" varStatus="status">
        <tr class="${status.index%2==0 ? 'alt' : ''}">
            <td><a href="${pageContext.request.contextPath}/item/${item.id}">${item.title}</a></td>
            <td><a href="${pageContext.request.contextPath}/item/${item.id}"><img src="${item.imageUrl}" width="30" height="30"/></a></td>
            <td>${item.description}</td>
            <td>${item.getTimeLeft()}</td>
            <td>${item.getNumBids()}</td>
            <td>
                <fmt:formatNumber value="${item.price.amount}" type="currency"/>
            </td>
            <%--<td>--%>
                <%--<fmt:formatDate value="${item.startDate}" type="both" dateStyle="short" timeStyle="short"/>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<a href="${pageContext.request.contextPath}/item/${item.id}/delete">delete</a>--%>
            <%--</td>--%>
        </tr>
    </c:forEach>
</table>

<br/>
<c:if test="${model.offset > 0}">
    <a href="${pageContext.request.contextPath}/search/?s=${model.searchTerm}&c=${model.count}&o=${model.offset - model.count}">Previous page</a>
</c:if>
Showing results ${model.offset + 1} - ${model.offset + model.count} out of ${model.itemsCountFound}
<c:if test="${model.itemsCountFound > model.offset + model.count}">
    <a href="${pageContext.request.contextPath}/search/?s=${model.searchTerm}&c=${model.count}&o=${model.offset + model.count}">Next page</a>
</c:if>
