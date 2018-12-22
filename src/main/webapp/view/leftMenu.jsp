<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/menu/showAllFirstSort",
            dataType: "JSON",
            success: function (data) {
                $.each(data, function (index, first) {

                    var id = first.id;
                    $("#aa").accordion("add", {
                        title: first.title,
                        iconCls: first.iconCls,
                        selected: false,
                        content: "<ul id=secondTree" + id + "></ul>"

                    })
                    //通过获取对应的id来发异步请求将二级类别查出来
                    $.ajax({
                        type: 'POST',
                        async: false,
                        dataType: "json",
                        url: '${pageContext.request.contextPath}/menu/showAllSecondSort',
                        data: {"parentId": id},
                        success: function (data) {
                            $("#secondTree" + id).tree({
                                data: data,
                                animate: true,
                                //iconCls:data[i].iconcls,
                                //在树节点加图片
                                formatter: function (node) {
                                    return node.title;
                                },
                                //lines: true, //显示虚线效果
                                onClick: function (node) { // 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs
                                    //if(node.url){//判断url是否存在，存在则创建tabs
                                    if (node) {
                                        addTab(node);
                                    }

                                }

                            });
                        }

                    });
                })

            }
        })
    });

    function addTab(node) {
        var tabExitOrNot = $('#tabs').tabs('exists', node.title);//判断此选项卡是否已存在
        if (tabExitOrNot) {
            $('#tabs').tabs('select', node.title);
        } else {
            //添加选项卡
            $('#tabs').tabs('add', {
                title: node.title,
                iconCls: "icon-search",
                closable: true,
                selected: true,
                href: "${pageContext.request.contextPath}/" + node.url
            });
        }
    }
</script>
<%--<ul id="tree"></ul>--%>
<div id="aa" class="easyui-accordion" data-options="fit:true">

</div>