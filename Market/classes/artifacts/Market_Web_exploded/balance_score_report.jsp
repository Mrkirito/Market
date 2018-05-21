<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
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

</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">平衡记分卡</div>
		<div class="panel-body">
			<div class="container">
				<div class="top">
				<input type="hidden" id="competitionId" value=<%=request.getParameter("id") %>>
				
				</div>
				<hr>
				<div class="middle1">
					<div class="panel panel-default">
				        <div class="panel-heading">
				        <c:if test="${balanceScoreVo == null }">
							<h1>当前没有平衡积分卡的数据</h1>
						</c:if>
				        	<span>${balanceScoreVo.company.name }&nbsp;|&nbsp;</span>
								当前季度:&nbsp;<span id="currentQuarter"></span>
				        		&nbsp;|&nbsp;&nbsp;平衡记分卡结果季度:&nbsp;<span id="quarter">${balanceScoreVo.balanceScore.quarter } </span>
				        </div>
				        <div class="pabel-body">
				        	<table class="table table-bordered table-hover table-striped" id="tab">
								<thead>
									<tr>
										<td>平衡记分卡项目</td>
										<td>最少</td>
										<td>最多</td>
										<td>平均</td>
										<td>${balanceScoreVo.company.name }</td>
										<td>等级</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>营业总收入</td>
										<td>${balanceScoreVo.balanceScoreMinData.grossRevenue }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.grossRevenue }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.grossRevenue }</td>
										<td id="grossRevenue">${balanceScoreVo.balanceScore.grossRevenue }</td>
										<td></td>
									</tr>
									<tr>
										<td>营业总成本</td>
										<td>${balanceScoreVo.balanceScoreMinData.grossCost }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.grossCost }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.grossCost }</td>
										<td id="grossCost">${balanceScoreVo.balanceScore.grossCost }</td>
										<td></td>
									</tr>
									<tr>
										<td>营业利润</td>
										<td>${balanceScoreVo.balanceScoreMinData.operatingProfit }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.operatingProfit }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.operatingProfit }</td>
										<td id="operatingProfit">${balanceScoreVo.balanceScore.operatingProfit }</td>
										<td></td>
									</tr>
									
									<tr>
										<td>现金等价物余额</td>
										<td>${balanceScoreVo.balanceScoreMinData.cashEquivalent }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.cashEquivalent }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.cashEquivalent }</td>
										<td id="cashEquivalent">${balanceScoreVo.balanceScore.cashEquivalent }</td>
										<td></td>
									</tr>
									<tr>
										<td>市场份额</td>
										<td>${balanceScoreVo.balanceScoreMinData.marketShare }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.marketShare }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.marketShare }</td>
										<td id="marketShare">${balanceScoreVo.balanceScore.marketShare }</td>
										<td></td>
									</tr>
									<tr>
										<td>单位营销收益</td>
										<td>${balanceScoreVo.balanceScoreMinData.unitMarketingRevenue }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.unitMarketingRevenue }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.unitMarketingRevenue }</td>
										<td id="unitMarketingRevenue">${balanceScoreVo.balanceScore.unitMarketingRevenue }</td>
										<td></td>
									</tr>
									<tr>
										<td>销售人员酬金</td>
										<td>${balanceScoreVo.balanceScoreMinData.salesStaffRemuneration }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.salesStaffRemuneration }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.salesStaffRemuneration }</td>
										<td id="salesStaffRemuneration">${balanceScoreVo.balanceScore.salesStaffRemuneration }</td>
										<td></td>
									</tr>
									<tr>
										<td>人员培训时间</td>
										<td>${balanceScoreVo.balanceScoreMinData.trainingTime }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.trainingTime }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.trainingTime }</td>
										<td id="trainingTime">${balanceScoreVo.balanceScore.trainingTime }</td>
										<td></td>
									</tr>
									<tr>
										<td>资产管理</td>
										<td>${balanceScoreVo.balanceScoreMinData.assetManagement }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.assetManagement }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.assetManagement }</td>
										<td id="assetManagement">${balanceScoreVo.balanceScore.assetManagement }</td>
										<td></td>
									</tr>
									<tr>
										<td>生产效率</td>
										<td>${balanceScoreVo.balanceScoreMinData.productionEfficiency }</td>
										<td>${balanceScoreVo.balanceScoreMaxData.productionEfficiency }</td>
										<td>${balanceScoreVo.balanceScoreAvgData.productionEfficiency }</td>
										<td id="productionEfficiency">${balanceScoreVo.balanceScore.productionEfficiency }</td>
										<td></td>
									</tr>
								</tbody>
							</table>
				        </div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		var quarter =$("#quarter").html();
		$("#currentQuarter").html(parseInt(quarter)+1);
	});
	$(function(){
		var trArr = $("#tab tr");
		for(var i = 1; i < trArr.length; i++){
			var tdArr = trArr.eq(i).children("td");
			if(parseFloat(tdArr.eq(1).html()) == 0){
				tdArr.eq(5).html("低");
			}else{
				var level = 1;
				var min = parseFloat(tdArr.eq(1).html());
				for(var j = 1; j < 4; j++){
					/* var f = parseFloat(tdArr.eq(j).html());
					tdArr.eq(j).html(f); */
					var v1 = parseFloat(tdArr.eq(j).html());
					var v2 = parseFloat(tdArr.eq(4).html());
					var temp = Math.abs(v1/v2 - 1);
					if(temp < min){
						min = temp;
						level = j;
					}
				}
				if(level == 1){
					tdArr.eq(5).html("低");
				}else if(level == 2){
					tdArr.eq(5).html("高");
				}else{
					tdArr.eq(5).html("中");
				}
			}
		}
		processThousands();
	});
	
	function processThousands(){
		var trArr = $("#tab tr");
		for(var i = 1; i < trArr.length; i++){
			var tdArr = trArr.eq(i).children("td");
			for(var j = 1; j < 5; j++){
				var f = parseFloat(tdArr.eq(j).html());
				tdArr.eq(j).html(fmoney(f, 3));
			}
		}
	}
	
	function fmoney(s, n)   
	{   
	   n = n > 0 && n <= 20 ? n : 2;   
	   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	   var l = s.split(".")[0].split("").reverse(),   
	   r = s.split(".")[1];   
	   t = "";   
	   for(i = 0; i < l.length; i ++ )   
	   {   
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? " ," : "");   
	   }   
	   return t.split("").reverse().join("") + "." + r;   
	}
</script>
</html>