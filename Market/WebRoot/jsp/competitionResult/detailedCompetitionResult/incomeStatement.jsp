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
<title>利润表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>利润表</span>
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
						<tr bgcolor="#D9EDF7">
							<td><strong>营业总收入</strong></td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yingYeTotalIncome }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;营业收入</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yingyeIncome }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;利息收入</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.lixiIncome }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>营业总成本</strong></td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yingYeTotalCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;营业成本</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yingyeCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;邮寄返款</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.fankuan }</td>
							</c:forEach>
						</tr>
						
						<tr>
							<td>&nbsp;&nbsp;研发投入</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yanfa }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;广告费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.guanggao }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;销售人员费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.salerCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;实体及网络销售中心费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.salescenterCost+item.salescenterWebCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;市场调研报告</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.baogao }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;货运</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.huoyun }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;库存持有成本</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.kucun }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;过剩产能成本</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.excessCapacity }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;折旧</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.zhejiu }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;网络营销费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.netmarketCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;利息费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.lixiCost }</td>
							</c:forEach>
						</tr>
						
						<tr bgcolor="#D9EDF7">
							<td><strong>营业利润（收入-成本）</strong></td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.yingYeTotalIncome-item.yingYeTotalCost }</td>
							</c:forEach>
						</tr>
						
						<tr>
							<td>&nbsp;&nbsp;+：技术授权收入</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.techIncome }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;+：其他收入</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.qitaIncome }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;-：技术授权费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.techCost }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;-：其他费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.qitaCost }</td>
							</c:forEach>
						</tr>
						
						<tr bgcolor="#D9EDF7">
							<td><strong>利润总额</strong></td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.totalLiRun }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;-：所得税费用</td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td>${item.taxCost }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>=净利润</strong></td>
							<c:forEach items="${incomeStatementList }" var="item">
								<td><strong>${item.jingLiRun }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td><strong>每股收益</strong></td>
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
		/* $("#assetTd").attr("colspan",col);
		$("#payId").attr("colspan",col);
		$("#debtShareTd").attr("colspan",col); */
		
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