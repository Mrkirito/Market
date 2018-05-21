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
	
	.panel {
		margin: 0px;
	}

	.panel-body {
		background-size: cover;
	}
	
</style>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
$(function(){
	var trArr = $("#tab_demand tr");
	var needSum=0;
	for(var i = 1; i < trArr.length; i++){
		tdArr = trArr.eq(i).children("td");
		tdArr.eq(4).html(parseInt(tdArr.eq(1).html()) + parseInt(tdArr.eq(2).html()) + parseInt(tdArr.eq(3).html()));
		needSum+=parseInt(tdArr.eq(1).html()) + parseInt(tdArr.eq(2).html()) + parseInt(tdArr.eq(3).html());
	}
	trArr = $("#tab_share tr");
	for(var i = 1; i < trArr.length; i++){
		tdArr = trArr.eq(i).children("td");
		var f1 = parseFloat(tdArr.eq(1).html()) * 100;
		var f2 = parseFloat(tdArr.eq(2).html()) * 100;
		var f3 = parseFloat(tdArr.eq(3).html()) * 100;
		var f4 = f1 + f2 + f3;
		tdArr.eq(1).html(f1.toFixed(1) + "%");
		tdArr.eq(2).html(f2.toFixed(1) + "%");
		tdArr.eq(3).html(f3.toFixed(1) + "%");
		tdArr.eq(4).html(f4.toFixed(1) + "%");
	}
	var trArr = $("#tab_demand tr");
	for(var i = 1; i < trArr.length; i++){
		tdArr = trArr.eq(i).children("td");
		var f5 = parseFloat(tdArr.eq(4).html()*100/needSum);
		tdArr2=$("#tab_share tr").eq(i).children("td");
		tdArr2.eq(4).html(f5.toFixed(1) + "%");
		
	}
	
});
</script>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">市场份额</div>
		<div class="panel-body">
			<div class="container">
				<div class="row" id="row2">
					<div class="col-md-9">
						<div class="panel panel-default">
							<div class="panel-heading">
								<label>市场份额</label>
							</div>
							<div class="panel-body">
								<table class="table table-bordered" id="tab_demand">
									<thead>
										<tr>
											<td>公司</td>
											<td>实用型</td>
											<td>极致型</td>
											<td>商务型</td>
											<td>总份额</td>
										</tr>
									</thead>
									<c:forEach var = "item" items="${companyMarketShare }">
											<tr>
												<td>${item.company.name }</td>
												<td>${item.companyMarketShare.practicalNeed }</td>
												<td>${item.companyMarketShare.perfectNeed }</td>
												<td>${item.companyMarketShare.businessNeed }</td>
												<td></td>								
											</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="panel panel-default">
							<div class="panel-heading">
								<label>市场占有率</label>
							</div>
							<div class="panel-body">
								<table class="table table-bordered" id="tab_share">
									<thead>
										<tr>
											<td>公司</td>
											<td>实用型</td>
											<td>极致型</td>
											<td>商务型</td>
											<td>占有率</td>
										</tr>
									</thead>
									<c:forEach var = "item" items="${companyMarketShare }">
										<tr>
											<td>${item.company.name }</td>
											<td>${item.companyMarketShare.practicalShare }</td>
											<td>${item.companyMarketShare.perfectShare }</td>
											<td>${item.companyMarketShare.businessShare }</td>
											<td></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
		</div>
	</div>
</body>
</html>