<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/9
  Time: 9:50
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

        .right_title span i {
            color: red;
            font-size: 22px;
            margin-right: 6px;
        }

        .text1 {
            text-indent: 30px;
            margin-top: 10px;
        }

        .left_content li {
            margin-top: 5px;
        }

        .strong {
            font-weight: bold;
        }

        .tab-content {
            padding-top: 10px;
        }

    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">品牌盈利能力</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
            <li class="active"><a href="#notice2" data-toggle="tab">决策信息</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in " id="notice1">
                <div class="course_content">
                    <div class="text1">
                        除了监控整个公司的盈利能力，您还需要管理各个品牌的利润率。
                    </div>
                </div>
                <div class="left">
                    <div class="left_title">
                        <span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                    </div>
                    <div class="left_content">
                        <ul>
                            <li>在相应决策界面，您会看到分摊到各个品牌之上的销售收入及支出（如果您拥有的品牌数大于 1）。 请注意利润率最高的品牌。 您可能需要重点关注该品牌。
                                例如，您可以提高其销售优先级别，或者为其投入更多的广告资金。
                            </li>
                            <li>对于盈利能力最低的品牌，您可以考虑对其进行升级，或者降价，或者为其投入更多的广告资金，从而提供该品牌的需求量和利润率。</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade in active" id="notice2">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="success">
                        <th>产品名称</th>
                        <c:forEach items="${brandList}" var="brand">
                            <th>${brand.productName}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>销售收入</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.salesproceeds}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>-邮寄返款</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.postoffice}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>-销货成本</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.costofselling}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="strong">=毛利</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.grossmargin}</td>
                        </c:forEach>
                    </tr>
                    <%--  <tr>
                         <td>品牌设计</td>
                         <c:forEach items="${brandList}" var="brand">
                             <td>0</td>
                         </c:forEach>
                     </tr> --%>
                    <tr>
                        <td>+广告设计及投放</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.adofbrand}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="strong">=品牌费用</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.costofbrand}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="strong">品牌利润</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>${brand.profit}</td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>占销售收入的比重%</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>
                                <c:if test="${'0' != brand.salesproceeds}">${brand.profitMargin}%</c:if>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td class="strong">单位利润</td>
                        <c:forEach items="${brandList}" var="brand">
                            <td>
                                <c:if test="${'0' != brand.totalSale}">${brand.unitProfit}</c:if>
                            </td>
                        </c:forEach>
                    </tr>
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
</html>