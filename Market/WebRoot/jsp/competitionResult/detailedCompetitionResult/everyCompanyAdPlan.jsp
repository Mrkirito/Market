<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司广告媒体投放</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>公司广告媒体投放</span>
				<input type="button" value="返回" onclick="javascript:history.back();">
			</div>

			<div class="panel-body">
				<label>公司广告媒体投放</label>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>品牌名称</th>
							<th>广告名称</th>
							<th>媒体投放数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mediaNumList }" var="item">
							<tr>
								<td>${item.companyName }</td>
								<td>${item.productName }</td>
								<td>${item.adName }</td>
								<td>${item.mediaNum }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
</script>
</body>
</html>