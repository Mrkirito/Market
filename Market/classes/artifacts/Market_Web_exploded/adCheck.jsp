<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/11/3
  Time: 16:07
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
    <title>核查广告语</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">
            核查广告语
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>品牌名称</th>
                <th>广告名称</th>
                <th>核查结果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${checkResultList}" var="crl">
                <tr>
                    <td>${crl.productName}</td>
                    <td>${crl.advertiseName}</td>
                    <td>${crl.result}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="main" style="width: 600px;height:400px;"></div>
    </div>
</div>
</body>
</html>