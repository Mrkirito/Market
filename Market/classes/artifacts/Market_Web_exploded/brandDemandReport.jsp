<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<style>
	body{
		width: 99%;
		margin: 5px;
	}
	.container{
		
	}
	.pabel-body{
		padding:5px;
	}
</style>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>

</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">品牌详细需求报告</div>
		<div class="panel-body">
			<div class="container">
				<div class="middle1">
					<div class="panel panel-default">
						<div class="panel-heading">
							<label>全球市场</label>
						</div>
						<div class="pabel-body">
							<table class="table table-bordered" id="tab">
								<thead>
									<tr>
										<td>品牌</td>
										<td>公司</td>
										<td>类型</td>
										<td>新加坡销量</td>
										<td>香港销量</td>
										<td>莫斯科销量</td>
										<td>新德里销量</td>
										<td>网络销量</td>
									</tr>
								</thead>
								<c:forEach var="item" items="${productMarketShare }">
									<tr>
										<td>${item.companyProduct.name }</td>
										<td>${item.company.name }</td>
										<td>${item.productMarketShare.productType }</td>
										<td><fmt:formatNumber value="${item.productMarketShare.singaporeSale }" pattern="#,#00.0" /></td>
										<td><fmt:formatNumber value="${item.productMarketShare.hongkongSale }" pattern="#,#00.0" /></td>
										<td><fmt:formatNumber value="${item.productMarketShare.moscowSale }" pattern="#,#00.0" /></td>
										<td><fmt:formatNumber value="${item.productMarketShare.newdelhiSale }" pattern="#,#00.0" /></td>
										<td><fmt:formatNumber value="${item.productMarketShare.onlineSale }" pattern="#,#00.0" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
	</div>
		</div>
		<div class="panel-footer"></div>
	</div>
	

</body>
</html>