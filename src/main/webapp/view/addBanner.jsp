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
                $("#addBannerForm").form("submit", {
                    url: "${pageContext.request.contextPath }/banner/insert",
                    onSubmit: function () {
                        return $("#addBannerForm").form("validate");
                    },
                    success: function () {
                        $("#addBannerDialog").dialog("close");
                        $("#table").datagrid("load");
                        $.messager.show({
                            title: "添加用户",
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
                $("#addBannerForm").form("reset");
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

        $("#addStatus").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "状态",
            buttonAlign: "left",
            validType: "length[1,3]"

        });
        $("#addImgPath").textbox({
            required: true,
            height: 30,
            width: 200,
            type: "text",
            buttonText: "路径",
            buttonAlign: "left",
            validType: "length[1,30]"


        });
        $("#addPubDate").datebox({
            required: true,
            height: 30,
            width: 200,
            buttonText: "时间",
            buttonAlign: "left",

        });
        $("#addDescription").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "简介",
            buttonAlign: "left"

        });
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    });


</script>
</head>


<h1>
    添加轮播图:
</h1>
<form id="addBannerForm" method="post">
    <input id="addName" name="title"><br>

    <input id="addImgPath" name="imgPath" value="images/shouye/3.jpg"><br>

    <input id="addStatus" name="status" value="Y/N"><br>

    <input id="addPubDate" name="pubDate"><br>

    <input id="addDescription" name="description"><br>
    <p>
        <a id="submitBtn">提交</a>
        <a id="resetBtn">重置</a>
    </p>
</form>


