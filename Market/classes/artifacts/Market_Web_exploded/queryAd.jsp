<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/10/19
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">广告详情</div>
    <div class="panel-body">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="brandName" class="col-sm-2 control-label">品牌名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="brandName"
                           value="${adInfo.productName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="adName" class="col-sm-2 control-label">广告名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="adName"
                           value="${adInfo.advertiseName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:forEach items="${detailList}" var="detail">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" checked="checked" disabled="disabled"> ${detail}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
    </div>
</div>
</form>
</body>
</html>