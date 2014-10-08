<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="post" action="/item/<%request.getAttribute("id");%>/bid"/>
<h1>Auction Item #1234</h1>
<img width="200" src="http://localhost:8080/lab2/item/<%request.getAttribute("id");%>/image"/>
<dl>
    <dt>Current Bid:</dt>
    <dd>$1.00</dd>
    <dt>Time Left</dt>
    <dd>2 Days</dd>
    <dt>
        <input/>
    </dt>
    <dd>
        <input type="submit" value="Place a bid"/>
    </dd>
</dl>
</form>
</body>
</html>

