<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/9
  Time: 12:03
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

        .tab-content {
            padding-top: 10px;
        }
    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">品牌评价</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策信息</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">

                    <div class="text1">
                        调研公司为您收集的市场信息当中，最重要的一条便是消费者如何评价上季度市场上所有的品牌。
                    </div>

                    <div class="text1">
                        您将会收到品牌在各个细分市场上的评价分数。
                    </div>

                    <div class="text1">
                        如需了解该评价分数的计算方式，请参见帮助文档的<a href="#">终端用户反馈： 快速测试</a>一节。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>分析消费者对您的品牌设计的满意程度是进行市场分析的良好着手点。
                            </li>
                            <li>品牌评价衡量了目标细分市场对您的品牌设计的青睐度。 它由产品的功能特性所决定。 潜在用户会根据其期望的收益来评价产品所包含的功能特性。</li>
                            <li>该评价的分值在 0 到 100 之间，100 分说明市场对您的品牌设计完全满意。 在第二季度，一个好品牌的评分应该在 70 以上。 如果您的品牌评分低于
                                65，则您应该停产该品牌并重新设计品牌。
                            </li>
                            <li>在您的目标细分市场上，竞争对手的评分是否在 70 以上？ 如果有，记下这款品牌的名称。 您需要查看该品牌并将其设计融入到自己的品牌当中。
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="success">
                        <th>品牌</th>
                        <th>预估需求量</th>
                        <th>预估需求量/销售量</th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${hashMap}" var="hashMap">
                        <tr>
                            <td colspan="3"><label>${hashMap.key}</label></td>
                        </tr>

                        <c:forEach items="${hashMap.value}" var="list">
                            <tr>
                                <td>${list.productName}</td>
                                <td>${list.totalNeed}</td>
                                <td>${list.eva}</td>
                            </tr>

                        </c:forEach>
                    </c:forEach>


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