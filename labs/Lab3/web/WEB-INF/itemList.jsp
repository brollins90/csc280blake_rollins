<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="5" >
    <title>Item #<%=request.getAttribute("id")%></title>
</head>
<body>
    <form method="post" action="/lab3/item/<%=request.getAttribute("id")%>/bid"/>
    <h1>Auction Item #<%=request.getAttribute("id")%></h1>
    <input name="id" style="display:none;" value="<%=request.getAttribute("id")%>"/>
    <img width="200" src="/lab3/item/<%=request.getAttribute("id")%>/image"/>
    <dl>
        <dt>Current Bid:</dt>
        <dd><%=request.getAttribute("currentBid")%></dd>

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

