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
            /*   });
               $("#upload").filebox({
                   reqiured:true
               })*/
        //提交================
        $("#submitBtn").linkbutton({
            plain: true,
            onClick: function () {

                $("#addChapterForm").form("submit", {
                    url: "${pageContext.request.contextPath }/chapter/insert?parentId=" +${param.id},
                    onSubmit: function () {
                        return $("#addChapterForm").form("validate");
                    },
                    success: function () {
                        $("#addChapterDialog").dialog("close");
                        $("#albumTable").treegrid("load");
                        $.messager.show({
                            title: "添加章节",
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
                $("#addChapterForm").form("reset");
            }
        });
        //input框===================
        $("#addTitle").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "名称",
            buttonAlign: "left",
            validType: "length[1,10]"

        });

        $("#addSize").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "大小",
            buttonAlign: "left",
            /*validType:["num","length[1,2]"]*/
            validType: "length[1,10]"

        });
        /*   $("#addUrl").textbox({
               required: true,
               height: 30,
               width: 200,
               type: "text",
               buttonText: "路径",
               buttonAlign: "left",
               validType: "length[1,30]"


           });*/
        $("#addUploadDate").datebox({
            required: true,
            height: 30,
            width: 200,
            buttonText: "时间",
            buttonAlign: "left",

        });

        $("#addDuration").textbox({
            required: true,
            type: "text",
            height: 30,
            width: 200,
            buttonText: "时长",
            buttonAlign: "left"

        });

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    });


</script>
</head>


<h1>
    添加章节:
</h1>
<form id="addChapterForm" method="post" enctype="multipart/form-data">
    <input id="addTitle" name="title"><br>

    <%--<input id="addSize" name="size"><br>--%>

    <%--    <input id="addUrl" name="url"><br>--%>

    <%--<input id="addDuration" name="duration"><br>--%>

    <input id="addUploadDate" name="uploadDate"><br>

    <input id="upload" type="file" name="file"><br>
    <%--<input id="upload" name="file"><br>--%>
    <p>
        <a id="submitBtn">提交</a>
        <a id="resetBtn">重置</a>
    </p>
</form>


