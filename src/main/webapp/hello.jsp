<html>
<head>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: "BC-d8c061cbb5c84f6999a30a96a07b44b2"
    });
    goEasy.subscribe({
        channel: "testhjl",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });

</script>
<h2>Hello World!</h2>
</body>
</html>
