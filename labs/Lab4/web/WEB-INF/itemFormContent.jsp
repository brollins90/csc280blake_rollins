<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!model.validationResult.success}">
    <c:forEach var="m" items="${model.validationResult.messages}">
        <div class="validationError">${m}</div>
    </c:forEach>
</c:if>

<c:choose>
    <c:when test="${not empty model.item.id}">
        <form name="myForm" method="post" action="${pageContext.request.contextPath}/item/${model.item.id}/update">
    </c:when>
    <c:otherwise>
        <form name="myForm" method="post" action="${pageContext.request.contextPath}/item/0/create">
    </c:otherwise>
</c:choose>
<fieldset>
    <legend>
        <c:choose>
            <c:when test="${not empty model.item.id}">
                Updating Item
            </c:when>
            <c:otherwise>
                Adding Item
            </c:otherwise>
        </c:choose>
    </legend>

        <div>
            <label for="title">Title</label>
            <input type="text" name="title" id="title" value="${model.item.title}"/>
            <span id="title_notice" class="notice"></span>
            <span id="title_validation" class="validation"></span>
        </div>

        <div>
            <label for="description">Description</label>
            <textarea type="text" name="description" id="description" rows="2"
                      cols="60">${model.item.description}</textarea>
            <span id="description_notice" class="notice"></span>
            <span id="description_validation" class="validation"></span>
        </div>

        <div>
            <label for="start_price">Start price</label>
            <c:choose>
            <c:when test="${model.item.numBids > 0}">
                <input type="text" name="start_price" id="start_price" value="${model.item.startPrice}" disabled="disabled">
            </c:when>
            <c:otherwise>
                <input type="text" name="start_price" id="start_price" value="${model.item.startPrice}"/>
            </c:otherwise>
            </c:choose>
            <span id="start_price_notice" class="notice"></span>
            <span id="start_price_validation" class="validation"></span>
        </div>

        <div>
            <label for="start_time">Start time:</label>
            <input type="text" name="start_time" id="start_time"/>
            <span id="start_time_notice" class="notice">If you edit the start time, the end time will automatically be set to 7 days later than the new start time</span>
            <span id="start_time_validation" class="validation"></span>
        </div>

        <div>
            <label for="end_time">End time:</label>
            <input type="text" name="end_time" id="end_time"/>
            <span id="end_time_notice" class="notice"></span>
            <span id="end_time_validation" class="validation"></span>
        </div>

        <div>
            <label for="image_url">Image url:</label>
            <input type="text" name="image_url" value="${model.item.imageUrl}" id="image_url"/>
            <span id="image_url_notice" class="notice"></span>
            <span id="image_url_validation" class="validation"></span>
        </div>

        <input type="hidden" name="id" value="${model.item.id}"/>
        <input type="hidden" name="start_time_long" id="start_time_long" value="${model.item.startTime}"/>
        <input type="hidden" name="end_time_long" id="end_time_long" value="${model.item.endTime}"/>
    </fieldset>

    <div class="button-row">
        <a href="${pageContext.request.contextPath}/item/${model.item.id}">Cancel</a> or <input type="submit"
                                                                                                value="Submit"/>
    </div>
</form>

<%@ include file="footer.jsp" %>
