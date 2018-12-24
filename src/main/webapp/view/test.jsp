<%--
  Created by IntelliJ IDEA.
  User: 冰冰
  Date: 2018/12/22
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $('#tt').treegrid({
            url: 'get_data.php',
            idField: 'id',
            treeField: 'name',
            columns: [[
                {title: '名称', field: 'title', width: 180},
                {field: 'opertion', title: '音频', formatter: Opertion, width: 60, align: 'right'},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'url', title: '章节路径', width: 80}
                {field: 'duration', title: '音频时长', width: 80}
            ]]
        });

    </script>
</head>
<body>
<%--<audio controls="smallconsole"
width="22"
       src="${pageContext.request.contextPath}/view/sad.mp3">

</audio>--%>
<%--<bgsound src="${pageContext.request.contextPath}/view/sad.mp3"/>
<dynsrc src="${pageContext.request.contextPath}/view/sad.mp3">--%>

<audio controls="smallconsole" loop="loop"
       src="${pageContext.request.contextPath}/'+row.url+'">
</audio>


</body>
</html>
