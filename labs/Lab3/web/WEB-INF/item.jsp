<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String line = "";
    line = "<form method=\"post\" action=\"/lab3/item/" + request.getAttribute("id") + "/bid\"/>";
    out.println(line);

    line = "<h1>Auction Item #"+request.getAttribute("id")+"</h1>";
    out.println(line);

    line = "<input name=\"id\" style=\"display:none;\" value=\""+request.getAttribute("id")+"\"/>";
    out.println(line);

    line = "<img width=\"200\" src=\"/lab3/item/" + request.getAttribute("id") + "/image\"/>";
    out.println(line);
%>
<dl>
    <dt>Current Bid:</dt>
    <%
        line = "<dd>$" + request.getAttribute("currentBid") + "</dd>";
        out.println(line);
    %>

    <dt>Time Left</dt>
    <dd>2 Days</dd>
    <dt>
        <input name="incrementBid"/>
    </dt>
    <dd>
        <input type="submit" value="Place a bid"/>
    </dd>
</dl>
</form>
</body>
</html>

