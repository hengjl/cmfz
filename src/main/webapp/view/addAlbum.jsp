<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {

        $.extend($.fn.validatebox.defaults.rules, {
            Length: {
                validator: function (value, param) {
                    return value.length == param[0];
                },
                message: "输入的必须是{0}位数"
            },
            num: {
                validator: function (value, param) {
                    return !isNaN(value);
                },
                message: "输入的必须是数字"
            },
        });
        //提交================
        $("#submitBtn").linkbutton({
            plain: true,
            onClick: function () {
                $("#addAlbumForm").form("submit", {
                    url: "${pageContext.request.contextPath }/album/insertAlbum",
                    onSubmit: function () {
                        return $("#addAlbumForm").form("validate");
                    },
                    success: function () {
                        //alert("fffffffffff");
                        $("#addAlbumDialog").dialog("close");
                        $("#albumTable").datagrid("load");
                        $.messager.show({
                            title: "添加专辑",
                            msg: "添加成功"
                        });
                    }
                });
            }
        });
        //重置============================
        $("#resetBtn").linkbutton({
            plain: true,
            onClick: function () {
                $("#addAlbumForm").form("reset");
            }
        });
        //input框===================
        $("#addName").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "名称",
            buttonAlign: "left",
            validType: "length[1,10]"

        });

        $("#addCount").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "评分",
            buttonAlign: "left",
            validType: ["num", "length[1,2]"]

        });
        /*     $("#addCoverImg").textbox({
                 required: true,
                 height: 30,
                 width: 200,
                 type: "text",
                 buttonText: "路径",
                 buttonAlign: "left",
                 validType: "length[1,30]"


             });*/
        $("#addPubDate").datebox({
            required: true,
            height: 30,
            width: 200,
            buttonText: "时间",
            buttonAlign: "left",

        });
        $("#addBrief").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "简介",
            buttonAlign: "left"

        });
        $("#addAuthor").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "作者",
            buttonAlign: "left"

        });
        $("#addBroadcast").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "声优",
            buttonAlign: "left"

        });
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    });


</script>
</head>


<h1>
    添加专辑:
</h1>
<form id="addAlbumForm" method="post" enctype="multipart/form-data"
      action="${pageContext.request.contextPath}/album/insertAlbum">
    <input id="addName" name="title"><br>

    <input id="addCount" name="score"><br>

    <%--<input id="addCoverImg" name="coverImg" ><br>--%>
    <input id="addAuthor" name="author"><br>

    <input id="addBroadcast" name="broadcast"><br>
    <input id="addBrief" name="brief"><br>
    <input id="addPubDate" name="pubDate"><br>

    <input id="addCoverImg" name="file" type="file"><br>
    <p>
        <a id="submitBtn">提交</a>
        <a id="resetBtn">重置</a>
    </p>
</form>


