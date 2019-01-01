<html>
<head>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
    $(function () {
        var goEasy = new GoEasy({
            appkey: "BC-d8c061cbb5c84f6999a30a96a07b44b2"
        });
        //发送消息
        goEasy.publish({
            channel: "testhjl",
            message: "Hello, GoEasy!"
        });
    })


</script>
<h2>Hello World!</h2>
</body>
</html>
