<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Server Error...</h1>

<c:if test="${model.errors}">
    <c:forEach var="m" items="${model.errors}">
        <div class="row">
            <div class="validationError">${m}</div>
        </div>
    </c:forEach>
</c:if>
</div>
