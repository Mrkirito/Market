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
<title>资产负债表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>资产负债表</span>
				<input type="button" value="返回" onclick="javascript:history.back();">
			</div>

			<div class="panel-body">
				
				<table class="table table-bordered">
					<thead>
						<tr bgcolor="#D9EDF7">
							<th>公司名称</th>
							<c:forEach items="${companyList }" var="item">
								<th>${item.name }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="assetTd"><strong>流动资产</strong></td>
						</tr>
						<tr>
							<td>现金货币</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.huobi }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>三个月定期存款</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.cunkuan }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>存货</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.cunhuo }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>利息</td>
							<c:forEach items="${companyFinanceVoList }" var="item">
								<td>${item.balanceSheet.lixiCollection }</td>
							</c:forEach>
						</tr>
						
						<tr bgcolor="#D9EDF7">
							<td><strong>流动资产合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.liuDongZiChan }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td id="unLiquidAssetsTd"><strong>非流动资产</strong></td>
						</tr>
						<tr>
							<td>固定资产</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.zichan }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>非流动资产合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.zichan }</strong></td>
							</c:forEach>
						</tr>
						<tr id="assetSum" bgcolor="#D9EDF7">
							<td><strong>资产合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.totalZiChan }</strong></td>
							</c:forEach>
						</tr>
						
						<tr>
							<td id="liquidDebtTd"><strong>流动负债</strong></td>
						</tr>
						
						<tr>
							<td>紧急贷款</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.daikuanEmergency }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>应付利息</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.lixiPay }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>流动负债合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.liuDongFuzhai }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td id="unLiquidDebtTd"><strong>非流动负债</strong></td>
						</tr>
						<tr>
							<td>常规银行贷款</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.daikuanNormal }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>非流动负债合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.daikuanNormal }</strong></td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>负债合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.totalFuZhai }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td id="ownerEquityTd"><strong>所有者权益</strong></td>
						</tr>
						<tr>
							<td>股本</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.guben }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>留存收益</td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td>${item.liucun }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>所有者权益合计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.owner }</strong></td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>资产负债总计</strong></td>
							<c:forEach items="${balanceSheetList }" var="item">
								<td><strong>${item.ownerAndFuZhai }</strong></td>
							</c:forEach>
						</tr>
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
		var col=$("table").find("tr").children("th").length;
		$("#assetTd").attr("colspan",col);
		$("#debtShareTd").attr("colspan",col);
		$("#unLiquidAssetsTd").attr("colspan",col);
		$("#liquidDebtTd").attr("colspan",col);
		$("#unLiquidDebtTd").attr("colspan",col);
		$("#ownerEquityTd").attr("colspan",col);
		/* for(var i=2;i<=col;i++){
			var sum=0;
			var node=$("#asset tr td:nth-child(i)");
			if($("#asset tr td:nth-child(i)").length>0){
				$("#asset tr td:nth-child(i)").each(function(){
					alert($(this).text());
					sum+=parseInt($(this).html());
					$("#assetSum").append("<td><strong>").append(sum).append("</strong></td>");
				});
			}else{
				sum+=0;
			}
			
		} */
	});
</script>
</body>
</html>