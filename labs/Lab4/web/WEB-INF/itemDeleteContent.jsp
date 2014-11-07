<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="${pageContext.request.contextPath}/item/${model.id}/delete">
  <h1>Auction Item #${model.id}</h1>
  <h2>Are you sure you want to remove the item?</h2>

  <div class="button-row">
    <a href="${pageContext.request.contextPath}/item">Cancel</a> or <input type="submit" value="Submit"/>
  </div>
</form>

<%@ include file="footer.jsp" %>
