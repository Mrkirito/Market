<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">各公司销量</div>
		<div class="panel-body">
			<div class="container">
				<div class="middle1">
					<div class="panel panel-default">
						<div class="panel-heading">
							<label>销量合计</label>
						</div>
						<div class="panel-body">
							<table class="table table-bordered" id="tab">
								<thead>
									<tr>
										<td>公司</td>
										<td>实用型销量</td>
										<td>实用型脱销</td>
										<td>实用型库存</td>
										<td>极致型销量</td>
										<td>极致型脱销</td>
										<td>极致型库存</td>
										<td>商务型销量</td>
										<td>商务型脱销</td>
										<td>商务型库存</td>
									</tr>
								</thead>
								<c:forEach var = "item" items="${companyMarketShare }">
										<tr>
											<td>${item.company.name }</td>
											<td>${item.companyMarketShare.practicalSale }</td>
											<td>${item.companyMarketShare.practicalStockoun }</td>
											<td>${item.companyMarketShare.practicalStock }</td>
											<td>${item.companyMarketShare.perfectSale }</td>
											<td>${item.companyMarketShare.perfectStockoun }</td>
											<td>${item.companyMarketShare.perfectStock }</td>
											<td>${item.companyMarketShare.businessSale }</td>
											<td>${item.companyMarketShare.businessStockoun }</td>
											<td>${item.companyMarketShare.businessStock }</td>								
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