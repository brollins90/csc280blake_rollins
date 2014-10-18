<%@ page import="edu.neumont.csc280.lab4.auction.AuctionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<% AuctionItem model = (AuctionItem)request.getAttribute("model"); %>

<form method="post" action="<%=application.getContextPath()%>/item/<%=model.getId()%>/update">
    <h1>Auction Item #<%=model.getId()%></h1>
    <input name="id" style="display:none;" value="<%=model.getId()%>"/>
    <%--<div>--%>
        <%--<div>--%>
            <%--<label for="itemTitle">Title:</label>--%>
            <%--<input type="text" name="itemTitle" value="<%=model.getTitle()%>" id="itemTitle" />--%>
            <%--<span id="validateItemTitle"><% if (titleError) { out.print(errorMessage); } %></span>--%>
        <%--</div>--%>
    <%--</div>--%>

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

    <div>
        <label for="start_time">Start Time:</label>
        <input type="text" name="start_time" value="<%=model.getStartTime()%>" id="start_time" />
        <span id="notice_start_time" class="notice">If you edit the start time, the end time will automatically be set to 7 days later than the new start time</span>
        <%--<span id="validate_start_time"><% if (start_time_error) { out.print(start_time_error_message); } %></span>--%>

    </div>
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
