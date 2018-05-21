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
<title>公司决策时间</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>公司决策时间</span>
				<input type="button" value="返回" onclick="javascript:history.back();">
			</div>

			<div class="panel-body">
				<label>公司决策时间</label>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>营业总收入</th>
							<th>营业总成本</th>
							<th>营业利润</th>
							<th>现金等价物余额</th>
							<th>单位营销收益</th>
							<th>销售人员酬金</th>
							<th>人员学习时间</th>
							<th>资产管理</th>
							<th>生产效率</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${companyQTList }" var="item">
							<tr>
								<td>${item.key }</td>
								<td>${item.value.grossRevenue }</td>
								<td>${item.value.grossCost }</td>
								<td>${item.value.operatingProfit }</td>
								<td>${item.value.cashEquivalent }</td>
								<td>${item.value.unitMarketingRevenue }</td>
								<td>${item.value.salesStaffRemuneration }</td>
								<td>${item.value.trainingTime }</td>
								<td>${item.value.assetManagement }</td>
								<td>${item.value.productionEfficiency }</td>
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