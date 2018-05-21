<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
 $(function(){
	 $("#format").hide();
 });
 function openCloseFormat(){
	 $("#format").toggle("normal");
 }
</script>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">财务比率</div>
		<div class="panel-body">
			<div class="container">
		<div class="row" id="row2">
			<div class="col-md-9">
			<div>
				<button class="btn btn-success" onclick = "openCloseFormat()">比率公式</button>
			</div><br />
			<div class="panel panel-default" id = "format">
					<div class="panel-heading">
						<label>比率公式</label>
					</div>
					<div class="pabel-body">
						<table class="table table-bordered" id="liquidity">
							<thead>
								<tr>
									<td>比率</td>
									<td>公式</td>
								</tr>
							</thead>
							<!-- <tr>
								<td>速动比率</td>
								<td>速动比率 =（现金 + 三个月定期存款）/ 常规银行贷款</td>
							</tr> -->
							<tr>
								<td>库存周转率</td>
								<td>库存周转率 = 销货成本 / 成品库存</td>
							</tr>
							<tr>
								<td>固定资产周转率</td>
								<td>固定资产周转率 = 销售收入 / 净固定资产</td>
							</tr>
							<tr>
								<td>总资产周转率</td>
								<td>总资产周转率 = 销售收入 / 总资产</td>
							</tr>
							<tr>
								<td>负债比率</td>
								<td>负债比率 = 贷款 / 总资产</td>
							</tr>
							<tr>
								<td>毛利率</td>
								<td>毛利率 = 销售毛利 / 销售收入</td>
							</tr>
							<tr>
								<td>净利率</td>
								<td>净利率 = 净收入 / 销售收入</td>
							</tr>
							<tr>
								<td>资产回报率</td>
								<td>资产回报率 = 净收入 / 总资产</td>
							</tr>
						</table>
					</div>
				</div>
				<%-- <div class="panel panel-default">
					<div class="panel-heading">
						<label>流动性比率</label>
					</div>
					<div class="pabel-body">
						<table class="table table-bordered" id="liquidity">
							<thead>
								<tr>
									<td>比率</td>
									<td>最高</td>
									<td>最低</td>
									<td>平均</td>
									<td>${financialRatioVo.company.name }</td>
								</tr>
							</thead>
							<tr>
								<td>速动比率</td>
								<td>${financialRatioVo.financialRatioMax.quickRatio }</td>
								<td>${financialRatioVo.financialRatioMin.quickRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.quickRatio }</td>
								<td>${financialRatioVo.financialRatio.quickRatio }</td>
							</tr>
						</table>
					</div>
				</div> --%>
				
				<div class="panel panel-default">
					<div class="panel-heading">
					<label>活动比率</label>
					</div>
					<div class="pabel-body">
						<table class="table table-bordered" id="activity">
							<thead>
								<tr>
									<td>比率</td>
									<td>最高</td>
									<td>最低</td>
									<td>平均</td>
									<td>${financialRatioVo.company.name }</td>
								</tr>
							</thead>
							<tr>
								<td>库存周转率</td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioMax.inventoryoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioMin.inventoryoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioAvg.inventoryoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatio.inventoryoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
							</tr>
							<tr>
								<td>固定资产周转率</td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioMax.fixedassetoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioMin.fixedassetoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatioAvg.fixedassetoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${financialRatioVo.financialRatio.fixedassetoverRatio }" pattern="#,#00.0"></fmt:formatNumber></td>
							</tr>
							<tr>
								<td>总资产周转率</td>
								<td>${financialRatioVo.financialRatioMax.totalassetRatio }</td>
								<td>${financialRatioVo.financialRatioMin.totalassetRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.totalassetRatio }</td>
								<td>${financialRatioVo.financialRatio.totalassetRatio }</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
					<label>资产负债率比率</label>
					</div>
					<div class="pabel-body">
						<table class="table table-bordered" id="assetsliabilities">
							<thead>
								<tr>
									<td>比率</td>
									<td>最高</td>
									<td>最低</td>
									<td>平均</td>
									<td>${financialRatioVo.company.name }</td>
								</tr>
							</thead>
							<tr>
								<td>负债比率</td>
								<td>${financialRatioVo.financialRatioMax.debtRatio }</td>
								<td>${financialRatioVo.financialRatioMin.debtRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.debtRatio }</td>
								<td>${financialRatioVo.financialRatio.debtRatio }</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
					<label>盈利能力比率</label>
					</div>
					<div class="pabel-body">
						<table class="table table-bordered" id="profit">
							<thead>
								<tr>
									<td>比率</td>
									<td>最高</td>
									<td>最低</td>
									<td>平均</td>
									<td>${financialRatioVo.company.name }</td>
								</tr>
							</thead>
							<tr>
								<td>毛利率</td>
								<td>${financialRatioVo.financialRatioMax.grossprofitRatio }</td>
								<td>${financialRatioVo.financialRatioMin.grossprofitRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.grossprofitRatio }</td>
								<td>${financialRatioVo.financialRatio.grossprofitRatio }</td>
							</tr>
							<tr>
								<td>净利率</td>
								<td>${financialRatioVo.financialRatioMax.netprofitRatio }</td>
								<td>${financialRatioVo.financialRatioMin.netprofitRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.netprofitRatio }</td>
								<td>${financialRatioVo.financialRatio.netprofitRatio }</td>
							</tr>
							<tr>
								<td>回报率</td>
								<td>${financialRatioVo.financialRatioMax.returnRatio }</td>
								<td>${financialRatioVo.financialRatioMin.returnRatio }</td>
								<td>${financialRatioVo.financialRatioAvg.returnRatio }</td>
								<td>${financialRatioVo.financialRatio.returnRatio }</td>
							</tr>
						</table>
						
					</div>
				</div>
			</div>
			
		</div>
	</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>