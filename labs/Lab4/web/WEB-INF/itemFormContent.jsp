<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!model.validationResult.success}">
    ${model.validationResult.toHTML()}
</c:if>

<form method="post"
      action="${pageContext.request.contextPath}/item/${model.item.id}/update">
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
            <input type="text" name="start_price" id="start_price" value="${model.item.startPrice}"/>

            <%--<input type="text" name="start_price" id="start_price" value="${model.item.startPrice}" />--%>
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

        <c:if test="${not empty model.item.id}">
            <input type="hidden" name="id" value="${model.item.id}"/>
        </c:if>
        <%--style="display:none;"--%>
        <input name="start_time_long" value="${model.item.startTime}" id="start_time_long"/>
        <input name="end_time_long" value="${model.item.endTime}" id="end_time_long"/>

    </fieldset>

    <div class="button-row">
        <a href="${pageContext.request.contextPath}/item/">Cancel</a> or <input type="submit" value="Submit"/>
    </div>
</form>

<%@ include file="footer.jsp" %>
