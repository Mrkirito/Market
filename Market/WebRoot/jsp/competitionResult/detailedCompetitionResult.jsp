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
<title>详细竞赛结果</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>结果汇总</span>
			</div>

			<div class="panel-body" >
				<input type="hidden" id="competitionId" value="${competition.id }"/>
				<input type="hidden" id="currentQuarter" value="${competition.currentQuarter }"/>
				<input type="hidden" id="quarter" value="${quarter }"/>
				<div id="noReleaseResult" style="display:none">
					<label>当前季度还没有发布竞赛结果，请先去发布结果中发布竞赛结果！</label>
				</div>
				<div id="yesReleaseResult" style="display:none">
					<label>平衡计分卡报告</label>
					<ul>
						<li><a href="javascript:void(0)" onclick="companyPolicyDecisionTime()">决策时间</a></li>
						<li><a href="javascript:void(0)" onclick="balanceScore()">平衡计分卡</a></li>
						<!-- <li><a href="javascript:void(0)" onclick="">累计平衡计分卡</a></li> -->
					</ul>
					<label>财务报告</label>
					<ul>
						<li><a href="javascript:void(0)" onclick="incomeStatement()">损益表</a></li>
						<li><a href="javascript:void(0)" onclick="cashFlow()">现金流表</a></li>
						<li><a href="javascript:void(0)" onclick="balanceSheet()">资产负债表</a></li>
					</ul>
					<label>市场调研报告（终端用户）</label>
					<ul>
						<li><a href="competitionResult/showDemandInfo.do">客户需求</a></li>
						<li><a href="competitionResult/showUsageInfo.do">产品用途</a></li>
						<li><a href="javascript:void(0)" onclick="showMarketInfo2()"">市场规模</a></li>
						<li><a href="competitionResult/showPriceInfo.do">愿意支付的价格</a></li>
						<li><a href="competitionResult/showMediaInfo.do">媒体偏好</a></li>
					</ul>
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
//平衡计分卡
function balanceScore(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/everyCompanyBalanceScore.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}


function everyCompanyPolicyDecisionTime(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/everyCompanyPolicyDecisionTime.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

function balanceSheet(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/balanceSheet.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

function cashFlow(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/cashFlow.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

function incomeStatement(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/incomeStatement.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//竞赛市场调研报告
function showMarketInfo2(){
	var competitionId=$("#competitionId").val();
	window.parent.main.location.href="competitionResult/showMarketInfo2.do?competitionId="+competitionId;
}
</script>
</body>
</html>