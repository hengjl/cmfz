<%--
  Created by IntelliJ IDEA.
  User: 冰冰
  Date: 2018/12/25
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>
<head>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/echarts.min.js"></script>

    <script src="../js/china.js"></script>
</head>
<body>--%>
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));

    option = {
        title: {
            text: '用户分布',
            subtext: '猜出来的',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['用户数量']
        },
        visualMap: {
            min: 0,
            max: 5000,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        /* series : [
             {
                 name: '用户数量',
                 type: 'map',
                 mapType: 'china',
                 roam: false,
                 label: {
                     normal: {
                         show: false
                     },
                     emphasis: {
                         show: true
                     }
                 },
               data:[]
             }

         ]*/
    };
    myChart.setOption(option);
    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/admin/showAllByProvince",
        dataType: "JSON",
        success: function (data) {
            console.log(data);
            myChart.setOption({
                series: [
                    {
                        name: '用户数量',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data
                    }

                ]
            })
        }
    })
</script>

x