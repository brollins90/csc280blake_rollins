
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript">
        var ws = new WebSocket("ws://localhost:8080/cam/echo");
        ws.onopen = function()
        {
            alert("Web Socket is connected!!");
        };
        ws.onmessage = function (evt)
        {
            var msg = evt.data;
            alert("Message received:" + msg);
        };
        ws.onclose = function()
        {
            alert("Connection is closed...");
        };
    </script>
</head>
<body>
<div>
    <%--<video id="live" width="320" height="240" autoplay style="display: inline;"></video>--%>
    <%--<canvas width="320" id="canvas" height="240" style="display: inline;"></canvas>--%>
</div>

<%--<script type="text/javascript">--%>
    <%--var video = $("#live").get()[0];--%>
    <%--var canvas = $("#canvas");--%>
    <%--var ctx = canvas.get()[0].getContext('2d');--%>

    <%--var ws = new WebSocket("ws://127.0.0.1:9999");--%>
    <%--ws.onopen = function () {--%>
        <%--console.log("Openened connection to websocket");--%>
    <%--}--%>


    <%--navigator.webkitGetUserMedia({video: true},--%>
            <%--function(stream) {--%>
                <%--video.src = webkitURL.createObjectURL(stream);--%>
            <%--},--%>
            <%--function(err) {--%>
                <%--console.log("Unable to get video stream!")--%>
            <%--}--%>
    <%--);--%>

    <%--timer = setInterval(--%>
            <%--function () {--%>
                <%--ctx.drawImage(video, 0, 0, 320, 240);--%>
                <%--var data = canvas.get()[0].toDataURL('image/jpeg', 1.0);--%>
                <%--newblob = dataURItoBlob(data);--%>
                <%--ws.send(newblob);--%>
            <%--}, 250);--%>
    <%--}--%>
<%--</script>--%>
</body>
</html>
