<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>

<a href="${pageContext.request.contextPath}/book">Add Book</a>

<table class="listing">
    <tr>
        <th>Title
        <span class="sortLink"> [ <a href="${pageContext.request.contextPath}/book/?order=asc&field=title">asc</a>|
        <a href="${pageContext.request.contextPath}/book/?order=desc&field=title">desc</a> ]
        </span>
        </th>
        <th>Description</th>
        <th>Price
        <span class="sortLink"> [ <a href="${pageContext.request.contextPath}/book/?order=asc&field=price">asc</a>|
        <a href="${pageContext.request.contextPath}/book/?order=desc&field=price">desc</a> ]
        </span></th>
        <th>Publication Date
        <span class="sortLink"> [ <a href="${pageContext.request.contextPath}/book/?order=asc&field=pubDate">asc</a>|
        <a href="${pageContext.request.contextPath}/book/?order=desc&field=pubDate">desc</a> ]
        </span></th>
        <th>Action</th>
    </tr>

    <c:forEach var="book" items="${books}" varStatus="status">
        <tr class="${status.index%2==0 ? 'alt' : ''}">
            <td><a href="${pageContext.request.contextPath}/book?id=${book.id}">${book.title}</a></td>
            <td>${book.description}</td>
            <td>
                <fmt:formatNumber value="${book.price}" type="currency"/>
            </td>
            <td>
                <fmt:formatDate value="${book.pubDate}" type="both" dateStyle="short" timeStyle="short"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/book/remove?id=${book.id}">remove</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
