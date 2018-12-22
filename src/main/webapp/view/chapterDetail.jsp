<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
        <link rel="stylesheet" type="text/css" href="../themes/icon.css">
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
        <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
        <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>--%>

<script type="text/javascript">
    $(function () {

        $("#table").edatagrid({
            url: "${pageContext.request.contextPath}/album/showAll",
            fitColumns: true,
            rownumbers: true,
            title: "专辑详情",
            singleSelect: false,
            iconCls: "icon-man",
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:1"><img src="${pageContext.request.contextPath}/'
                    + rowData.coverImg + '" style="height:130px;"></td>' +
                    '<td style="border:0">' +
                    '<p>简介: ' + rowData.brief + '</p>' +
                    '<p>评分: ' + rowData.score + '</p>' +
                    '<p>作者: ' + rowData.pubDate + '</p>' +
                    '<p>播音: ' + rowData.broadcast + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        })

    });


</script>

<%-- </head>
 <body>--%>
<table class="table" id="table">
    <thead>
    <tr class="table_header">
        <%-- <th data-options="field:'xx',checkbox:true"></th>--%>
        <th data-options="field:'title',width:1">名称</th>
        <%--<th data-options="field:'status',width:1,editor:{type:'text',options:{required:true}}">状态</th>--%>
        <th data-options="field:'pubDate',width:1">上传时间</th>
        <th data-options="field:'count',width:1">集数</th>
        <th data-options="field:'coverImg',width:1">图片路径</th>
        <%--<th data-options="field:'opertion',formatter:Opertion">播放</th>--%>
    </tr>
    </thead>

</table>

<%--   </body>

</html>--%>
