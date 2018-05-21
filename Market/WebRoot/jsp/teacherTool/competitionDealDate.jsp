<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>竞赛处理日程</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>

<style type="text/css">
	.panel-body{
		min-height:400px;
		
	}
</style>
</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>竞赛处理日程</span>
            </div>
            <div class="panel-body">
            	<label>决策提交到期</label><br/>
            	<label>注意：到期后用户将无法访问本次竞赛！</label><br/>
            
            	<input type="hidden" value="${competition.id }"/>
            	<table class="table table-bordered">
            		<tr>
            			<td>竞赛名称</td>
            			<td>许可证号</td>
            			<td>开始时间</td>
            			<td>截止时间</td>
            			<!-- <td>操作</td> -->
            		</tr>
            		<tr>
            			<td>${competition.name }</td>
            			<td>${competition.license }</td>
            			<td><fmt:formatDate value="${competition.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            			<td><fmt:formatDate value="${competition.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            			<!-- <td><input class="btn btn-sm btn-default" type="button" value="锁定" onclick=""/></td> -->
            		</tr>
            	</table>
            	
            
            </div>
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>