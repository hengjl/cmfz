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
        $("#addAlbumBtn").linkbutton({
            iconCls: "icon-add",
            text: "添加专辑",
            onClick: function () {
                $("#addAlbumDialog").dialog({
                    title: "添加专辑",
                    width: 300,
                    height: 380,
                    modal: true,
                    resizable: true,
                    href: "${pageContext.request.contextPath}/view/addAlbum.jsp?",
                    //queryParams:{id:id}
                });
            }
        })

        //--------------------------------
        $("#addChapterBtn").linkbutton({
            iconCls: "icon-add",
            text: "添加章节",
            onClick: function () {
                var row = $("#albumTable").treegrid("getSelected");
                if (row != null) {

                    if (row._parentId != null) {
                        $.messager.alert("提示框", "请先选择专辑")
                    } else {
                        $("#addChapterDialog").dialog({
                            title: "添加章节",
                            width: 300,
                            height: 300,
                            modal: true,
                            resizable: true,
                            href: "${pageContext.request.contextPath}/view/addChapter.jsp?id=" + row.id
                        });
                    }
                } else {
                    $.messager.alert("提示框", "请先选择专辑")
                }


            }

        })
        //=====================================================
        $("#loadChapterBtn").linkbutton({
            iconCls: 'icon-undo',
            text: "下载音频",
            onClick: function () {
                var row = $("#albumTable").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#table").edatagrid("getRowIndex", row);
                    $("#table").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }
            }
        })
        //==================================================================================
        $("#albumDetailBtn").linkbutton({
            iconCls: "icon-tip",
            text: "专辑详情",
            onClick: function () {
                $("#chapterDetailDialog").dialog({
                    title: "专辑详情",
                    width: 1000,
                    height: 500,
                    modal: true,
                    resizable: true,
                    href: "${pageContext.request.contextPath}/view/chapterDetail.jsp"
                })
                /*  var row = $("#albumTable").treegrid("getSelected");
                  if(row!=null){
                      if(row._parentId!=null){
                          $.messager.alert("提示框","请先选择专辑");
                      }else {
                          $("#chapterDetailDialog").dialog({
                              title: "专辑详情",
                              width: 300,
                              height: 300,
                              modal: true,
                              resizable: true,
                              href: "x/view/chapterDetail.jsp?id="+row.id
                          })
                      }
                  }else{
                      $.messager.alert("提示框","请先选择专辑");
                  }*/
            }
        })
        //-=-=-=-=-=-=-=-=-=-=-=-----------------------=====================================
        $("#albumTable").treegrid({
            url: "${pageContext.request.contextPath}/album/showAllAlbums",
            //url:"${pageContext.request.contextPath}/chapter/showAllChpater",
            /*columns:[[
                {title:'名称',field:'title',width:180},

                {field:'size',title:'章节大小',width:80},
                {field:'url',title:'章节路径',width:80},
                {field:'duration',title:'音频时长',width:80},
                {field:'opertion',title:'音频',formatter:Opertion,width:60,align:'right'}
            ]],*/
            idField: 'id',
            cascadeCheck: false,
            // animate:true,//动画效果
            //lines:true,
            treeField: 'title',
            fit: true,
            dnd: true,
            fitColumns: true,
            toolbar: "#chapterTool",
            onLoadSuccess: function () {
                $("#albumTable").treegrid("collapseAll")
            }
        })
//------------------------------------------------------------------------------------------------------------------------
    })

    function Opertion(value, row, index) {
        if (row.children == null) {
            return "<audio controls=\"smallconsole\" loop=\"loop\"\n" +
                "       src=\"${pageContext.request.contextPath}/view/sad.mp3\">\n" +
                "</audio>";
        }

    }

</script>

<%-- </head>
 <body>--%>
<table id="albumTable">
    <thead>
    <tr>
        <th data-options="field:'title',width:60,height:20">名字</th>
        <th data-options="field:'url',width:60,height:20">章节路径</th>
        <th data-options="field:'size',width:60,height:20">章节大小</th>
        <th data-options="field:'opertion',width:100,formatter:Opertion">播放</th>
        <th data-options="field:'duration',width:60,height:20">章节时长</th>
    </tr>
    </thead>
    >
</table>

<div id="chapterTool">
    <a id="albumDetailBtn"></a>
    <a id="addAlbumBtn"></a>
    <a id="addChapterBtn"></a>
    <a id="loadChapterBtn"></a>

</div>
<div id="addAlbumDialog"></div><%--添加专辑--%>
<div id="addChapterDialog"></div><%--添加章节--%>
<div id="chapterDetailDialog"></div><%--专辑详情--%>
<%--
   </body>
</html>
--%>

