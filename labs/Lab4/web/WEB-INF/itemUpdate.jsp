<%@ page import="edu.neumont.csc280.lab4.item.UpdateItemModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header1.jsp"%>
<script src="<%=application.getContextPath()%>/js/itemUpdate.js" type="text/javascript"></script>
<%@ include file="header2.jsp"%>
<% UpdateItemModel model = (UpdateItemModel)request.getAttribute("model"); %>


<c:if test="${!model.validationResult.success}">
    ${model.validationResult.toHTML()}
</c:if>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.item.getId()%>/update">
    <h1>Auction Item <%=model.item.getId()%></h1>
    <%--style="display:none;"--%>
    <input name="id" value="<%=model.item.getId()%>" disabled="disabled"/>
    <input name="item_start_time_long" value="<%=model.item.getStartTime()%>" id="item_start_time_long"/>
    <input name="item_end_time_long" value="<%=model.item.getEndTime()%>" id="item_end_time_long" />

    <div>
        <label for="item_title">Title:</label>
        <input type="text" name="item_title" value="<%=model.item.getTitle()%>" id="item_title" />
        <span id="notice_title" class="notice"></span>
        <span id="validate_title" class="validation"></span>
    </div>

    <div>
        <label for="item_description">Description:</label>
        <textarea type="text" name="item_description" id="item_description"><%=model.item.getDescription()%></textarea>
        <span id="notice_description" class="notice"></span>
        <span id="validate_description" class="validation"></span>
    </div>

    <div>
        <label for="item_start_price">Start price:</label>
        <input type="text" name="item_start_price" value="<%=model.item.getStartPrice()%>" id="item_start_price" />
        <span id="notice_start_price" class="notice"></span>
        <span id="validate_start_price" class="validation"></span>
    </div>

    <div>
        <label for="item_start_time">Start time:</label>
        <input type="text" name="item_start_time" id="item_start_time" />
        <span id="notice_start_time" class="notice">If you edit the start time, the end time will automatically be set to 7 days later than the new start time</span>
        <span id="validate_start_time" class="validation"></span>
    </div>

    <div>
        <label for="item_end_time">End time:</label>
        <input type="text" name="item_end_time" id="item_end_time" />
        <span id="notice_end_time" class="notice"></span>
        <span id="validate_end_time" class="validation"></span>
    </div>

    <div>
        <label for="item_image_url">Image url:</label>
        <input type="text" name="item_image_url" value="<%=model.item.getImageUrl()%>" id="item_image_url" />
        <span id="notice_image_url" class="notice"></span>
        <span id="validate_image_url" class="validation"></span>
    </div>

    <div>
        <input type="submit" value="Update the item"/>
    </div>
</form>

<%--<script type="javascript">--%>
    <%--$(document).ready(function() {--%>
        <%--var validateItemTitle = $('#validateItemTitle');--%>
        <%--$('#itemTitle').keyup(function() {--%>
            <%--var t = this;--%>
            <%--if (this.value != this.lastValue) {--%>
                <%--if (this.timer) clearTimeout(this.timer);--%>
                <%--validateItemTitle.removeClass('error').html('<img src="images/ajax-loader.gif" height="16" width="16" /> checking availability...');--%>

                <%--this.timer = setTimeout(function() {--%>
                    <%--$.ajax({--%>
                        <%--url: '/ajax-validation',--%>
                        <%--data: 'action=check_item_title&item_title=' + t.value,--%>
                        <%--dataType: 'json',--%>
                        <%--type: 'post',--%>
                        <%--success: function(j) {--%>
                            <%--validateItemTitle.html(j.msg);--%>
                        <%--}--%>
                    <%--});--%>
                <%--}, 200);--%>
                <%--this.lastValue = this.value;--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

<%--</script>--%>
<%@ include file="footer.jsp"%>
