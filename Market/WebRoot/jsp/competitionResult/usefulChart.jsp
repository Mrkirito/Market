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
<title>有用图表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<style type="text/css">
	
</style>
</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>有用图表</span>
			</div>

			<div class="panel-body">
				<input type="hidden" id="competitionId" value="${competition.id }"/>
				<input type="hidden" id="currentQuarter" value="${competition.currentQuarter }"/>
				<input type="hidden" id="quarter" value="${quarter }"/>
				<div id="noReleaseResult" style="display:none">
					<label>当前季度还没有发布竞赛结果，请先去发布结果中发布竞赛结果！</label>
				</div>
				<div id="yesReleaseResult" style="display:none">
					<table class="table table-bordered" style="display: none">
						<tr>
							<td>财务会计</td>
						</tr>
						<tr>
							<td><a>资产负债表总量</a></td>
							<td><a>现金</a></td>
							<td><a>营运现金流</a></td>
							<td><a>净股本</a></td>
							<td><a>总负债</a></td>
						</tr>
						<tr>
							<td><a>留存收益</a></td>
							<td><a>每股收益</a></td>
							<td><a>每股收益增长率</a></td>
							<td><a>收入</a></td>
							<td><a>实体销售中心收入</a></td>
						</tr>
						<tr>
							<td><a>网络销售中心收入</a></td>
							<td><a>销货成本</a></td>
							<td><a>网络营销费用</a></td>
							<td><a>净收入</a></td>
							<td><a>毛利</a></td>
						</tr>
						<tr>
							<td><a>营运成本</a></td>
							<td><a>研发投资</a></td>
							<td><a>库存周转率</a></td>
							<td><a>固定资产周转率</a></td>
							<td><a>总资产周转率</a></td>
						</tr>
						<tr>
							<td><a>负债比率</a></td>
							<td><a>毛利率</a></td>
							<td><a>净利率</a></td>
							<td><a>资产回报率</a></td>
							<td><a>股本回报率</a></td>
						</tr>
					</table>
					<label>财务会计</label>
					<ul>
						<li><a href="javascript:void(0)" onclick="balanceSheetChart()">资产负债表总量</a></li>
						<li><a href="javascript:void(0)" onclick="cashChart()">现金</a></li>
						<li><a href="javascript:void(0)" onclick="operatingCashChart()">营运现金流</a></li>
						<li><a href="javascript:void(0)" onclick="netStockChart()">净股本</a></li>
						<li><a href="javascript:void(0)" onclick="liucunChart()">留存收益</a></li>
						<!-- <li><a>每股收益</a></li>
						<li><a>每股收益增长率</a></li> -->
						<li><a href="javascript:void(0)" onclick="incomeChart()">收入</a></li>
						<li><a href="javascript:void(0)" onclick="sellingCostChart()">销货成本</a></li>
						<li><a href="javascript:void(0)" onclick="netMarketCostChart()">网络营销费用</a></li>
						<li><a href="javascript:void(0)" onclick="netIncomeChart()">净收入</a></li>
						<!-- <li><a>毛利</a></li>
						<li><a>营运成本</a></li>
						<li><a>研发投资</a></li>
						<li><a>库存周转率</a></li>
						<li><a>固定资产周转率</a></li>
						<li><a>总资产周转率</a></li>
						<li><a>负债比率</a></li>
						<li><a>毛利率</a></li>
						<li><a>净利率</a></li>
						<li><a>资产回报率</a></li>
						<li><a>股本回报率</a></li> -->
					</ul>
					
					
					<!-- <label>其他</label>
					<ul>
						<li><a>总决策时间</a></li>
					</ul> -->
				</div>
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
	
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
$(function(){
	var quarter=$("#quarter").val();
	var comCurrentQuarter=$("#currentQuarter").val();
	if(quarter<comCurrentQuarter){
		$("#noReleaseResult").hide();
		$("#yesReleaseResult").show();
	}else if(quarter==comCurrentQuarter){
		$("#yesReleaseResult").hide();
		$("#noReleaseResult").show();
	}
});

//资产负债表总量
function balanceSheetChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/balanceSheetChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//现金
function cashChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/cashChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//营运现金流
function operatingCashChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/operatingCashChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//净股本
function netStockChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyNetStockChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//留存收益
function liucunChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyLiucunChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//收入
function incomeChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyIncomeChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//销货成本
function sellingCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySellingCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//网络营销费用
netMarketCostChart
function netMarketCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyNetMarketCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//净收入
function netIncomeChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/netIncomeChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
</script>
</body>
</html>