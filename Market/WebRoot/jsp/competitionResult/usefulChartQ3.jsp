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
						<li><a href="javascript:void(0)" onclick="sumDebtChart()">总负债</a></li>
						<li><a href="javascript:void(0)" onclick="liucunChart()">留存收益</a></li>
						<!-- <li><a href="javascript:void(0)" onclick="cashChart()">每股收益</a></li>
						<li><a href="javascript:void(0)" onclick="cashChart()">每股收益增长率</a></li> -->
						<li><a href="javascript:void(0)" onclick="incomeChart()">收入</a></li>
						<li><a href="javascript:void(0)" onclick="sellingCostChart()">销货成本</a></li>
						<li><a href="javascript:void(0)" onclick="netMarketCostChart()">网络营销费用</a></li>
						<li><a href="javascript:void(0)" onclick="netIncomeChart()">净收入</a></li>
					</ul>
					
					<label>市场</label>
					<ul>
						<!-- <li><a href="javascript:void(0)" onclick="marketNeedChart()">市场规模</a></li>
						<li><a>细分市场规模</a></li> -->
						<li><a href="javascript:void(0)" onclick="companyMarketShareChart()">各个公司市场份额</a></li>
						<li><a href="javascript:void(0)" onclick="practicalMarketShareChart()">实用型市场份额</a></li>
						<li><a href="javascript:void(0)" onclick="perfectMarketShareChart()">极致型市场份额</a></li>
						<li><a href="javascript:void(0)" onclick="businessMarketShareChart()">商务型市场份额</a></li>
						<li><a href="javascript:void(0)" onclick="companyNeedNumChart()">各个公司总需求量</a></li>
						<li><a href="javascript:void(0)" onclick="companySaleNumChart()">各个公司销售量</a></li>
						<li><a href="javascript:void(0)" onclick="companyProductNumChart()">产品数量</a></li>
						<li><a href="javascript:void(0)" onclick="companyAvgPriceChart()">平均价格</a></li>
						<li><a href="javascript:void(0)" onclick="companyAdCostChart()">广告费用</a></li>
						<li><a href="javascript:void(0)" onclick="companyAdNumChart()">媒体投放总数</a></li>
						<li><a href="javascript:void(0)" onclick="companyMarketNumChart()">实体及网络销售中心数量</a></li>
						<li><a href="javascript:void(0)" onclick="companySaleCenterCostChart()">实体及网络销售中心费用</a></li>
						<li><a href="javascript:void(0)" onclick="companySalesNumChart()">销售人员总数</a></li>
						<li><a href="javascript:void(0)" onclick="companySalesCostChart()">销售人员费用</a></li>
						<li><a href="javascript:void(0)" onclick="companyWorkerEffiChart()">工厂工人效率</a></li>
						<li><a href="javascript:void(0)" onclick="companySalesEffiChart()">销售人员效率</a></li>
					</ul>
					<label>生产制造</label>
					<ul>
						<li><a href="javascript:void(0)" onclick="companyFixedCapacityChart()">固定产能</a></li>
						<li><a href="javascript:void(0)" onclick="companyOperationCapacityChart()">运行产能</a></li>
						<li><a href="javascript:void(0)" onclick="productionEfficiencyChart()">产能利用率</a></li>
						<!-- <li><a>生产成本</a></li>
						<li><a>平均销售成本</a></li> -->
						<li><a href="javascript:void(0)" onclick="companyStockCostChart()">库存持有成本</a></li>
						<li><a href="javascript:void(0)" onclick="companyStockNumChart()">季末库存</a></li>
						<li><a href="javascript:void(0)" onclick="companyStockounNumChart()">产品脱销</a></li>
					</ul>
					
					<label>平衡记分卡</label>
					<ul>
						<li><a href="javascript:void(0)" onclick="grossRevenueChart()">营业总收入</a></li>
						<li><a href="javascript:void(0)" onclick="grossCostChart()">营业总成本</a></li>
						<li><a href="javascript:void(0)" onclick="operatingProfitChart()">营业利润</a></li>
						<li><a href="javascript:void(0)" onclick="cashEquivalentChart()">现金等价物余额</a></li>
						<!-- <li><a href="javascript:void(0)" onclick="marketShareChart()">市场份额</a></li> -->
						<li><a href="javascript:void(0)" onclick="unitMarketingRevenueChart()">单位营销收益</a></li>
						<li><a href="javascript:void(0)" onclick="salesRemunerationChart()">销售人员酬金</a></li>
						<li><a href="javascript:void(0)" onclick="trainingTimeChart()">人员学习时间</a></li>
						<li><a href="javascript:void(0)" onclick="assetManagementChart()">资产管理</a></li>
						<li><a href="javascript:void(0)" onclick="productionEfficiencyChart()">生产效率</a></li>
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
//总负债表
function sumDebtChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySumDebtChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
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

//整个市场规模===========================
function marketNeedChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/marketNeedChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//各个公司市场份额
function companyMarketShareChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyMarketShareChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//实用型市场份额
function practicalMarketShareChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/practicalMarketShareChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//极致型市场份额
function perfectMarketShareChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/perfectMarketShareChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//商务型市场份额
function businessMarketShareChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/businessMarketShareChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//各个公司需求量
function companyNeedNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyNeedNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//各个公司销售量
function companySaleNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySaleNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//公司的品牌数量
function companyProductNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyProductNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//平均价格
function companyAvgPriceChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyAvgPriceChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//媒体总投资
function companyAdCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyAdCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//媒体投放数量
function companyAdNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyAdNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//实体及网络销售中心费用
function companySaleCenterCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySaleCenterCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//实体及网络市场数量
function companyMarketNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyMarketNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//销售人员总数
function companySalesNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySalesNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}


//销售人员费用
function companySalesCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySalesCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//工厂工人效率
function companyWorkerEffiChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyWorkerEffiChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//销售工人效率
function companySalesEffiChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companySalesEffiChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//固定产能
function companyFixedCapacityChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyFixedCapacityChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//运行产能
function companyOperationCapacityChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyOperationCapacityChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//产能利用率
function companyCapacityUtilizationChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyCapacityUtilizationChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//库存持有成本
function companyStockCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyStockCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
function companyStockNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyStockNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
function companyStockounNumChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/companyStockounNumChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}







//营业总收入表
function grossRevenueChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/grossRevenueChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//营业总成本
function grossCostChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/grossCostChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//营业总利润
function operatingProfitChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/operatingProfitChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//现金等价物余额表
function cashEquivalentChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/cashEquivalentChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

//单位营销收益表
function unitMarketingRevenueChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/unitMarketingRevenueChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//销售人员薪酬表
function salesRemunerationChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/salesRemunerationChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//学习时间表
function trainingTimeChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/trainingTimeChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//资产管理表
function assetManagementChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/assetManagementChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}
//生产效率表
function productionEfficiencyChart(){
	var competitionId=$("#competitionId").val();
	var quarter=$("#quarter").val();
	window.parent.main.location.href="competitionResult/productionEfficiencyChart.do?competitionId="+competitionId+"&currentQuarter="+quarter;
}

</script>
</body>
</html>