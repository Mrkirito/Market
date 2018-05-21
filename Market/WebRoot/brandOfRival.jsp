<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/15
  Time: 13:13
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

        #notice2 .left hr {
            margin: 0px;
            border-top: 1px solid #acbad4;
        }

        .top {
            background-color: #acbad4;
        }

        .notice2-left {
            display: inline-block;
            background: #eee;
        }

        .notice2-left ul li {
            list-style: none;
        }

        .notice2-left hr {
            margin: 0px;
        }

        .resize th {
            text-align: center;
        }
        .tab-content{
        	padding-top:10px;
        }

    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">竞争对手的品牌</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策信息</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">
                    <div class="text1">
						作为商业竞争的一员，评估竞争对手的品牌设计是明智之举。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>请务必查看在您的目标市场上评价最高的品牌。 将其与自己的品牌进行比较，以判断市场更青睐哪些功能特性。 如果分数最高的品牌特别出色，您甚至可以直接利用该品牌的设计。</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="resize success">
                        <th>产品名称</th>
                        <th>必备</th>
                        <th>运营商</th>
                        <th>蓝牙</th>
                        <th>屏幕尺寸</th>
                        <th>触控方式</th>
                        <th>cpu速度</th>
                        <th>机身容量</th>
                        <th>拍照像素</th>
                        <th>机身特性</th>
                        <th>电池容量</th>
                        <th>其他功能</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${brandDetail}" var="brandDetail">
                        <tr>
                            <td colspan="12" style="text-align: left"><label>${brandDetail.key}</label></td>
                        </tr>
                        <c:forEach items="${brandDetail.value}" var="value">
                            <tr>
                                <td>${value.productName}</td>
                                <td>${value.bibei}</td>
                                <td>${value.yunying}</td>
                                <td>${value.lanya}</td>
                                <td>${value.size}</td>
                                <td>${value.chukong}</td>
                                <td>${value.cpu}</td>
                                <td>${value.jishen}</td>
                                <td>${value.xiangsu}</td>
                                <td>${value.special}</td>
                                <td>${value.dianchi}</td>
                                <td><c:forEach items="${value.others}" var="other">${other}</c:forEach></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="main" style="width: 600px;height:400px;"></div>
        <div class="panel-footer"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</html>