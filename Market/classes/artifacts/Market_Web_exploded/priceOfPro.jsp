<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/19
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <style type="text/css">
        body {
            width: 99%;
            margin: 5px;
        }

        .panel {
            margin: 0px;
        }

        #notice1 {
            min-height: 420px;
        }

        .course_content {
            border: 1px solid #0ff;
            margin-top: 10px;
            padding: 20px;
            min-height: 220px;
            font-size: 14px;
            letter-spacing: 1px;
        }

        .left {
            /* border:1px solid blue; */
            width: 450px;
            padding: 20px;
            float: left;
            background: #eee;
            margin: 10px 0 0 2px;
            min-height: 220px;
        }

        .left_title span i {
            color: #009;
            font-size: 18px;
            margin-right: 6px;
        }

        .right {
            /* border:1px solid blue; */
            width: 400px;
            padding: 20px;
            float: left;
            background: #abcdef;
            margin: 10px 0 0 100px;
            min-height: 120px;
        }

        .right_title span i {
            color: red;
            font-size: 22px;
            margin-right: 6px;
        }

        .text0 {
            margin-left: 30px;
            margin-top: 10px;
        }

        .text1 {
            text-indent: 30px;
            margin-top: 10px;
        }

        .text2 {
            margin-left: 30px;
            margin-top: 25px;
        }

        .left_content li {
            margin-top: 5px;
        }
    </style>
</head>
<script>
    function init() {
        var table = document.getElementById("table1");
        var rows = table.rows.length;//行数

        //计算销货成本
        for (var i = 1; i < rows; i++) {
            var hidden = table.rows[i].getElementsByTagName("input");
            var cost = hidden[0].value;
            //赋值
            document.getElementById(table.rows[i].id + "n100").innerText = Math.ceil(cost*330/100/Math.log(5));
            document.getElementById(table.rows[i].id + "n250").innerText = Math.ceil(2*cost*330/250/Math.log(5));
            document.getElementById(table.rows[i].id + "n500").innerText = Math.ceil(2.25 * cost / Math.log(500 / 65));
            document.getElementById(table.rows[i].id + "n1000").innerText = Math.ceil(2.4 * cost / Math.log(1000 / 65));
            document.getElementById(table.rows[i].id + "n5000").innerText = Math.ceil(3 * cost / Math.log(5000 / 65));
        }
    }
</script>
<body onLoad="init()">
<div class="panel panel-info">
    <div class="panel-heading">生产成本</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">

                    <div class="text1">
                        生产成本也会影响销售价格的设定。 然而，您的生产量在本季度仍然会比较低。 直到第四季度之前，您可能无法取得生产的规模经济效应。 尽管如此，您还是需要估算生产成本。
                    </div>

                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>为了估算生产成本，您需要先预测销量。 假如您已经改进了市场营销计划，则您可以假设销售人员当前季度的人均销量在 50 到 100 之间。 如果雇佣了 10
                                个销售人员，则您的销售量大概在 500 到 1000 之间。
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <label class="chanpin">销货成本(COGS)</label>

                <table class="table table-bordered table-hover" id="table1">
                    <thead>
                    <tr class="success">
                        <td>产品信息</td>
                        <td>100件数</td>
                        <td>250件数</td>
                        <td>500件数</td>
                        <td>1000件数</td>
                        <td>5000件数</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty companyProducts}">
                            <c:forEach items="${companyProducts}" var="product">
                                <tr id="${product.id }">
                                    <input id="${product.id }cost" type="hidden" value="${ product.cost}"/>
                                    <td>${product.name}</td>
                                    <td><label id="${product.id}n100"></label></td>
                                    <td><label id="${product.id}n250"></label></td>
                                    <td><label id="${product.id}n500"></label></td>
                                    <td><label id="${product.id}n1000"></label></td>
                                    <td><label id="${product.id}n5000"></label></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6">抱歉,未找到产品信息！</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
                <div id="main" style="width: 600px;height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="panel-footer"></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>

</html>