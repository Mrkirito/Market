<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		var quarter =$("#quarter").html();
		$("#currentQuarter").html(parseInt(quarter)+1);
	});
</script>
</head>
<body>
	<div id="content">
		<div class="row" id="row2">
			<div class="col-md-9">
				<div class="top">
					<span>平衡记分卡&nbsp;|&nbsp;</span>
					<span>${balanceScoreVo.company.name }&nbsp;|&nbsp;</span>
					当前季度:&nbsp;<span id="currentQuarter"></span>
				</div>
				<hr />
				<div>
					<div class="middle1">
						行业结果季度:&nbsp;<span id="quarter">${balanceScoreVo.balanceScore.quarter } </span>
						<hr />
						<table class="table table-bordered" id="tab">
							<thead>
								<tr>
									<td>----</td>
									<td>最少</td>
									<td>最多</td>
									<td>平均</td>
									<td>${balanceScoreVo.company.name }</td>
								</tr>
							</thead>
								<tr>
									<td>营业总收入</td>
									<td>${balanceScoreVo.balanceScoreMinData.grossRevenue }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.grossRevenue }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.grossRevenue }</td>
									<td id="grossRevenue">${balanceScoreVo.balanceScore.grossRevenue }</td>
								</tr>
								<tr>
									<td>营业总成本</td>
									<td>${balanceScoreVo.balanceScoreMinData.grossCost }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.grossCost }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.grossCost }</td>
									<td id="grossCost">${balanceScoreVo.balanceScore.grossCost }</td>
								</tr>
								<tr>
									<td>营业利润</td>
									<td>${balanceScoreVo.balanceScoreMinData.operatingProfit }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.operatingProfit }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.operatingProfit }</td>
									<td id="operatingProfit">${balanceScoreVo.balanceScore.operatingProfit }</td>
								</tr>
								
								<tr>
									<td>现金等价物余额</td>
									<td>${balanceScoreVo.balanceScoreMinData.cashEquivalent }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.cashEquivalent }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.cashEquivalent }</td>
									<td id="cashEquivalent">${balanceScoreVo.balanceScore.cashEquivalent }</td>
								</tr>
								<tr>
									<td>市场份额</td>
									<td>${balanceScoreVo.balanceScoreMinData.marketShare }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.marketShare }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.marketShare }</td>
									<td id="marketShare">${balanceScoreVo.balanceScore.marketShare }</td>
								</tr>
								<tr>
									<td>单位营销收益</td>
									<td>${balanceScoreVo.balanceScoreMinData.unitMarketingRevenue }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.unitMarketingRevenue }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.unitMarketingRevenue }</td>
									<td id="unitMarketingRevenue">${balanceScoreVo.balanceScore.unitMarketingRevenue }</td>
								</tr>
								<tr>
									<td>销售人员酬金</td>
									<td>${balanceScoreVo.balanceScoreMinData.salesStaffRemuneration }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.salesStaffRemuneration }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.salesStaffRemuneration }</td>
									<td id="salesStaffRemuneration">${balanceScoreVo.balanceScore.salesStaffRemuneration }</td>
								</tr>
								<tr>
									<td>人员培训时间</td>
									<td>${balanceScoreVo.balanceScoreMinData.trainingTime }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.trainingTime }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.trainingTime }</td>
									<td id="trainingTime">${balanceScoreVo.balanceScore.trainingTime }</td>
								</tr>
								<tr>
									<td>资产管理</td>
									<td>${balanceScoreVo.balanceScoreMinData.assetManagement }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.assetManagement }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.assetManagement }</td>
									<td id="assetManagement">${balanceScoreVo.balanceScore.assetManagement }</td>
								</tr>
								<tr>
									<td>生产效率</td>
									<td>${balanceScoreVo.balanceScoreMinData.productionEfficiency }</td>
									<td>${balanceScoreVo.balanceScoreMaxData.productionEfficiency }</td>
									<td>${balanceScoreVo.balanceScoreAvgData.productionEfficiency }</td>
									<td id="productionEfficiency">${balanceScoreVo.balanceScore.productionEfficiency }</td>
								</tr>
						</table>
					</div>
					
					<div class="middle2">
						<jsp:include page="balanceScoreContent.jsp" flush="true"/>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>