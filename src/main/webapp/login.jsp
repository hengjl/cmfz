<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyUI/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyUI/themes/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function () {
            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                alert("自己做");
            });


            $("#submitButton").linkbutton({
                //plain:true,
                //iconCls:"icon-add",
                width: 50,
                height: 30,
                text: "登录",
                onClick: function () {
                    //  form 表单提交
                    $("#loginForm").form("submit", {
                        url: "${pageContext.request.contextPath}/admin/login",
                        onSubmit: function () {
                            return $("#loginForm").form("validate");
                        },
                        success: function (result) {
                            if (result == "ok") {
                                $.messager.show({
                                    title: "登陆提示框",
                                    msg: "恭喜你登陆成功"
                                })
                                location.href = "${pageContext.request.contextPath}/main/main.jsp"
                            } else {
                                $.messager.show({
                                    title: "登陆提示框",
                                    msg: "很遗憾登陆失败，请检查您账号密码是否正确"
                                })
                                $("#loginForm").form("clear");
                            }
                        }

                    });
                }
            })
            $("#homeButton").linkbutton({
                plain: true,
                onClick: function () {
                    location.href = "${pageContext.request.contextPath}/login.jsp";
                }
            })
        });
    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input type="text" name="name" class="text" value="" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input type="password" name="password" class="text" value=""
                           maxlength="20"<%-- autocomplete="off"--%>/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="img/captcha.jpg" title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <%--<input type="button" class="homeButton" value="" onclick="location.href='/'">--%>
                    <%--<input type="submit" class="loginButton" value="登录">--%>
                    <%--<a id="homeButton" class="homeButton" ></a>--%>
                    <a id="submitButton"></a>

                </td>
            </tr>
            </tbody>
        </table>

        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>