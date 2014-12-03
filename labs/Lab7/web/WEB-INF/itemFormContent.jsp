<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!model.validationResult.success}">
    <c:forEach var="m" items="${model.validationResult.messages}">
        <div class="validationError">${m}</div>
    </c:forEach>
</c:if>

<form name="myForm" method="post" action="${pageContext.request.contextPath}/item/${model.item.id}/update">
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
        </div>

        <div>
            <label for="description">Description</label>
            <textarea type="text" name="description" id="description" rows="2"
                      cols="60">${model.item.description}</textarea>
        </div>

        <c:choose>
            <c:when test="${model.item.numBids > 0}">
                <input type="hidden" name="price" id="price" value="${model.item.price}"/>
                <input type="hidden" name="start_time" id="start_time" value="${model.item.getPrettyStart()}"/>
                <input type="hidden" name="end_time" id="end_time" value="${model.item.getPrettyEnd()}"/>
            </c:when>
            <c:otherwise>
                <div>
                    <label for="price">Start price</label>
                    <input type="text" name="price" id="price" value="${model.item.price}"/>
                </div>

                <div>
                    <label for="start_time">Start time:</label>
                    <input type="text" name="start_time" id="start_time" value="${model.item.getPrettyStart()}"/> (MM/dd/yyyy HH:mm)
                    <span id="start_time_notice" class="notice">If you edit the start time, the end time will automatically be set to 7 days later than the new start time</span>
                </div>

                <div>
                    <label for="end_time">End time:</label>
                    <input type="text" name="end_time" id="end_time" value="${model.item.getPrettyEnd()}"/> (MM/dd/yyyy HH:mm)
                </div>

                <script type="text/javascript">


                    function loadPage2() {
                        var startDateTextBox = $('#start_time');
                        var endDateTextBox = $('#end_time');

                        startDateTextBox.datetimepicker({
                            minDate: new Date(),
                            showOn: 'button',
                            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                            buttonImageOnly: true,
                            onClose: function (dateText, inst) {

                            },
                            onSelect: function (selectedDateTime) {
                                endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
                            }
                        }).next('button').text('').button({ icons: { primary: 'ui-icon-calendar' } });

                        endDateTextBox.datetimepicker({
                            showOn: "button",
                            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                            buttonImageOnly: true,
                            onClose: function (dateText, inst) {

                            },
                            onSelect: function (selectedDateTime) {
                            }
                        }).next('button').text('').button({ icons: { primary: 'ui-icon-calendar' } });



                    }
                </script>
            </c:otherwise>
        </c:choose>

        <div>
            <label for="image_url">Image url:</label>
            <input type="text" name="image_url" value="${model.item.imageUrl}" id="image_url"/>
        </div>

        <input type="hidden" name="id" value="${model.item.id}"/>
    </fieldset>

    <div class="button-row">
        <a href="${pageContext.request.contextPath}/item/${model.item.id}">Cancel</a> or <input type="submit"
                                                                                                value="Submit"/>
    </div>
</form>

<%@ include file="footer.jsp" %>
