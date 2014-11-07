<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>

<a href="${pageContext.request.contextPath}/item/0/create">Add Item</a>

<table class="listing">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <%--<th>Start Date</th>--%>
        <th>Action</th>
    </tr>

    <c:forEach var="item" items="${model}" varStatus="status">
        <tr class="${status.index%2==0 ? 'alt' : ''}">
            <td><a href="${pageContext.request.contextPath}/item/${item.id}">${item.title}</a></td>
            <td>${item.description}</td>
            <td>
                <fmt:formatNumber value="${item.currentPrice.amount}" type="currency"/>
            </td>
            <%--<td>--%>
                <%--<fmt:formatDate value="${item.startDate}" type="both" dateStyle="short" timeStyle="short"/>--%>
            <%--</td>--%>
            <td>
                <a href="${pageContext.request.contextPath}/item/${item.id}/delete">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
