<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
html {
	height: 100%;
}

body {
	scrollbar-face-color: #f6f6f6;
	scrollbar-highlight-color: #fff;
	scrollbar-shadow-color: #eeeeee;
	scrollbar-3dlight-color: #eeeeee;
	scrollbar-arrow-color: #000;
	scrollbar-track-color: #fff;
	scrollbar-darkshadow-color: #fff;
}

body {
	padding: 0px;
	margin: 0px;
	height: 500px;
	font-family: "Microsoft YaHei"
}

a {
	color: white;
}

.nav {
	width: 200px;
	padding: 40px 28px 25px 0;
	height: 100%;
	background: #1e485d;
	color: white;
	box-sizing: border-box; -
	-moz-box-sizing: border-box;
}

ul.nav {
	padding: 0;
	margin: 0;
	font-size: 1em;
	line-height: 0.5em;
	list-style: none;
}

ul.nav li {
	margin-top: 10px;
}

ul.nav li a {
	line-height: 10px;
	font-size: 16px;
	padding: 10px 5px;
	color: #fff;
	text-indent: 20px;
	display: block;
	text-decoration: none;
}

ul.nav>li:nth-of-type(1) {
	margin-top: 0px;
}

ul.nav li a:hover {
	background-color: #675C7C;
	color: white;
}

ul.nav ul {
	margin: 0;
	padding: 0;
	display: none;
	background: #333;
}

ul.nav ul li {
	margin: 0;
	padding: 0;
	clear: both;
}

ul.nav ul li a {
	padding-left: 20px;
	font-size: 14px;
	font-weight: normal;
}

ul.nav ul li a:hover {
	background-color: #D3C99C;
	color: #675C7C;
}

ul.nav ul li a:active {
	background-color: #D3C99C;
	color: #675C7C;
}

ul.nav ul li a:current {
	background-color: #f00;
	color: #f00;
}

ul.nav ul ul li a {
	color: silver;
	padding-left: 40px;
}

ul.nav ul ul li a:hover {
	background-color: #D3CEB8;
	color: #675C7C;
}

ul.nav span {
	float: right;
}
</style>
</head>
<body>






	<ul class="nav">
		<li><a href="#none">业绩报告<i></i></a>
			<ul>
				<!-- balance_score_report -->
				<li><a href="showCompanyMarketShare.do?quarter=5" target="main">市场份额</a></li>
				<li><a href="showTotalSale.do?quarter=5" target="main">各公司销售量</a></li>
				<li><a href="showCashFlowResult.do?quarter1=4" target="main">现金流表</a></li>
				<li><a href="showIncomeStatementResult.do?quarter=4 " target="main">利润表</a></li>
				<li><a href="showBalanceSheetResult.do?quarter=4" target="main">资产负债表</a></li>
				<li><a href="loadBalanceScoreReport.do?quarter=5" target="main">平衡积分卡</a></li>
				<li><a href="showFinancialRatio.do?quarter=5" target="main">财务比率</a></li>
				<li><a href="showProductMarketShare.do?quarter=5" target="main">品牌详细需求报告</a></li>

			</ul></li>
		<li><a href="#none">市场营销<i></i></a>
			<ul>
				<li><a href="showMarketInfo2.do" target="main">市场规模</a></li>
				<li><a href="#none">品牌管理</a>
					<ul>
						<li><a href="brandProfit.do?quarter=5" target="main">品牌盈利能力</a></li>
						<li><a href="brandEva.do?quarter=5" target="main">品牌评价</a></li>
						<li><a href="brandOfRival.do?quarter=5" target="main">竞争对手的品牌</a></li>
						<li><a href="CompanyProduct.do?quarter=5" target="main">设计品牌</a></li>
					</ul></li>
				<li><a href="#none">定价</a>
					<ul>
						<li><a href="priceOfRival.do?quarter=5" target="main">竞争对手的价格</a></li>
						<li><a href="priceOfPro.do?quarter=5" target="main">生产成本</a></li>
						<li><a href="showProductPrice.do?quarter=5" target="main">产品定价</a></li>
						<li><a href="priceAndSale.do?quarter=5" target="main">可销售品牌</a></li>
					</ul></li>
				<li><a href="#none">广告</a>
					<ul>
						<li><a href="adOfRival1.do?quarter=5" target="main">竞争对手的广告</a></li>
						<li><a href="adDesign.do?quarter=5" target="main">设计广告</a></li>
						<li><a href="showMediaInfo.do?quarter=5" target="main">媒体偏好</a></li>
						<li><a href="showOperateMedia.do?quarter=5" target="main">主流媒体投放</a></li>
						<li><a href="adCheck.do?quarter=5" target="main">核查广告语</a></li>
					</ul></li>
				<!-- <li><a href="buyInvesReport.do?quarter=5" target="main">购买市场调研报告</a></li> -->
			</ul></li>
		<li><a href="#none">销售渠道<i></i></a>
			<ul>
				<li><a href="SalePathInfor.do?quarter=5" target="main">各产品销量</a></li>
				<li><a href="SaleAbilityInfor.do?quarter=5" target="main">盈利能力</a></li>
				<li><a href="GlobalPathNeeds.do?quarter=5" target="main">需求预测与实际销售</a></li>
				<li><a href="StoreInfor.do?quarter=5" target="main">各城市的竞争对手</a></li>
				<li><a href="#" target="main">实体销售中心</a>
					<ul>
						<li><a href="showMarketInfo.do?quarter=5" target="main">开设实体销售中心</a></li>
						<li><a href="hireSalePeople.do?quarter=5" target="main">雇佣实体销售人员</a></li>
					</ul></li>
				<li><a href="#" target="main">网络销售中心</a>
					<ul>
						<li><a href="showMarketWebInfo.do?quarter=5" target="main">开设网络销售中心</a></li>
						<li><a href="hireSalePeopleOnline.do?quarter=5" target="main">雇佣网络销售人员</a></li>
					</ul></li>

			</ul></li>
		<li><a href="#none">人力资源<i></i></a>
			<ul>
				<li><a href="showAllSalesSalary.do?quarter=4" target="main">业内销售人员薪酬</a></li>
				<li><a href="showSalesSalary.do?quarter=5" target="main">销售人员薪酬</a></li>
				<li><a href="showAllWorkersSalary.do?quarter=4" target="main">业内工厂工人薪酬</a></li>
				<li><a href="showWorkersSalary.do?quarter=5" target="main">工厂工人薪酬</a></li>
			</ul></li>
		<li><a href="#none">生产制造<i></i></a>
			<ul>
				<li><a href="showQuarterResult.do?quarter=5" target="main">上季度结果</a></li>
				<li><a href="showCompetitivePower.do?quarter=5" target="main">竞争力</a></li>
				<li><a href="showUselessInventory.do?quarter=5" target="main">无用库存</a></li>
				<li><a href="demandForecast.do?quarter=5" target="main">需求量预测</a></li>
				<li><a href="showInventoryControl.do?quarter=5" target="main">库存控制</a></li>
				<li><a href="showOperationCapacity.do?quarter=5" target="main">运行产能</a></li>
				<li><a href="showCapacityInfo.do?quarter=5" target="main">固定产能</a></li>
			</ul></li>

		<li><a href="#none">财务<i></i></a>
			<ul>
				<li><a href="showShareHold.do?quarter=5" target="main">内部持股</a></li>
				<li><a href="showFixedDeposite.do?quarter=5" target="main">定期存款</a></li>
				<li><a href="showEmergencyLoan.do?quarter=5" target="main">紧急贷款</a></li>
			</ul></li>

		<li><a href="#none">财会预算<i></i></a>
			<ul>
				<li><a href="showCashFlow.do?quarter1=5" target="main">现金流表</a></li>
				<li><a href="showIncomeStatement.do?quarter=5" target="main">利润表</a></li>
				<li><a href="showBalanceSheet.do?quarter=5" target="main">资产负债表</a></li>
			</ul></li>

		<li>
        <a href="#none">其他决策<i></i></a>
        <ul>
            <li><a href="companyRuleInfo.do?quarter=5" target="main">团队规则</a></li>
            <!-- <li><a href="showAllMemberByComapnyId.do?quarter=2" target="main">职位分配</a></li> -->
            <li><a href="strategyInfo.do?quarter=5" target="main">目标策略</a></li>
        </ul>
    </li>

    <li>
         <a href="#none">决策汇总<i></i></a>
        <ul>
        	<li><a href="policyDecision/companyDecisionSummary.do?companyId=<%=request.getParameter("companyId") %> &currentQuarter=5" target="main">决策汇总</a></li>
        	
            <li><a href="finalCheck.do?quarter=5" target="main">最终检查</a></li>

        </ul>
    </li>

	</ul>

	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="js/accordion.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".nav").accordion({
				speed : 500,
				closedSign : '+',
				openedSign : '-'
			});
			$(".nav").hover(function() {
				$(this).css("overflow", "auto")
			}, function() {
				$(this).css("overflow", "hidden")
			})
		});
	</script>

</body>
</html>