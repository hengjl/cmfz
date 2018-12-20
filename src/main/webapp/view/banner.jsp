<%--
  Created by IntelliJ IDEA.
  User: 冰冰
  Date: 2018/12/20
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
       --%>
<script type="text/javascript">
    $(function () {
        $("#addBanner").linkbutton({
            iconCls: "icon-add",
            text: "添加",
            onClick: function () {
                $("#addBannerDialog").dialog({
                    title: "添加轮播图",
                    width: 300,
                    height: 300,
                    modal: true,
                    resizable: true,
                    href: "${pageContext.request.contextPath}/view/addBanner.jsp"
                });
            }
        })
        $("#updateBanner").linkbutton({
            iconCls: 'icon-edit',
            text: "修改",
            onClick: function () {
                var row = $("#table").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#table").edatagrid("getRowIndex", row);
                    $("#table").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }
            }
        })


        $("#saveBanner").linkbutton({
            iconCls: "icon-save",
            text: "保存",
            onClick: function () {

                $("#table").edatagrid("saveRow")
            }

        })
        $("#deleteBanner").linkbutton({
            iconCls: "icon-remove",
            text: "删除",
            onClick: function () {
                $("#table").edatagrid("destroyRow");


            }

        })
        $("#table").edatagrid({
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            destroyUrl: "${pageContext.request.contextPath}/banner/delete",
            saveUrl: "${pageContext.request.contextPath}/banner/insert",
            fitColumns: true,
            //rownumbers:true,
            title: "所有图片展示",
            singleSelect: false,
            //iconCls:"icon-man",
            toolbar: "#bannerTool",
            view: detailview,
            url: "${pageContext.request.contextPath}/banner/showAllImg",

            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        })

    });

    function Opertion(value, row, index) {
        return "<a class='delete' href='javascript:' onclick='deleteTeam(" + row.id + ")'>删除</a></td>";
    }
</script>

<%-- </head>
 <body>--%>
<table class="table" id="table">
    <thead>
    <tr class="table_header">
        <%-- <th data-options="field:'xx',checkbox:true"></th>--%>
        <th data-options="field:'title',width:1">名称</th>
        <th data-options="field:'status',width:1,editor:{type:'text',options:{required:true}}">状态</th>
        <th data-options="field:'pubDate',width:1">上传时间</th>
        <th data-options="field:'imgPath',width:1">图片路径</th>
        <%-- <th data-options="field:'opertion',formatter:Opertion">操作</th>--%>
    </tr>
    </thead>

</table>
<div id="bannerTool">
    <a id="addBanner"></a>
    <a id="deleteBanner"></a>
    <a id="updateBanner"></a>
    <a id="saveBanner"></a>
</div>
<div id="addBannerDialog"></div>
<%--   </body>

</html>
--%>