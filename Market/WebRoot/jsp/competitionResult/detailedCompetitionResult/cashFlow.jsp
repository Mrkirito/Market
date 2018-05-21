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
<title>现金流表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>现金流表</span>
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
						<%-- <tr bgcolor="#D9EDF7">
							<td><strong>季初现金余额</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.yuE }</strong></td>
							</c:forEach>
						</tr> --%>
						<tr>
							<td id="operatingActiveTd"><strong>一、经营活动产生的现金流量</strong></td>
						</tr>
						<tr>
							<td>销售商品收到的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.xiaoshouGet }</td>
							</c:forEach>
						</tr>
						
						<tr>
							<td>收取利息的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.lixiGet }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>收取利息授权的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.jishuGet }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>收到其他与经营活动有关的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.qitaGet }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>经营活动现金流入小计</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.xianJinGet }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td>邮寄返款支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.fankuanPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>生产支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.shengchanPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的研发费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.yanfaPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的广告费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.guanggaoPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的销售人员费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.salerPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的实体及网络销售中心费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.salescenterPay+item.salescenterWebPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的市场调研费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.diaoyanPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的货运的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.huoyunPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的库存费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.kucunPay }</td>
							</c:forEach>
						<tr>
							<td>支付的网络营销费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.netmarketPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的收入税</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.taxPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的利息费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.lixiPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付的技术授权费用</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.jishuPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>支付其他与经营活动有关的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.qitaPay }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>经营活动产生的现金流出小计</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.xianJinPay }</strong></td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>经营活动产生的现金流量净额</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.xianJinGet-item.xianJinPay }</strong></td>
							</c:forEach>
						</tr>
						
						<tr>
							<td id="investActivityTd"><strong>二、投资活动产生的现金流量</strong></td>
						</tr>
						<tr>
							<td>构建工厂固定产能支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.gongchangPay }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>投资活动现金流出小计</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.gongchangPay }</strong></td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>投资活动产生的现金流量净额</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.gongchangPay }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td id="chouziActivityTd"><strong>三、筹资活动产生的现金流量</strong></td>
						</tr>
						<tr>
							<td>取得常规贷款收到的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.daikuanNormalGet }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>取得紧急贷款收到的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.daikuanEmergyGet }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>提取三个月定期存款收到的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.cunkuanRegularGet }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>筹资活动现金流入小计</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.chouZiGet }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td>偿还常规贷款支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.daikuanNormalPay }</td>
							</c:forEach>
						</tr>
						
						<tr>
							<td>偿还紧急贷款支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.daikuanEmergyPay }</td>
							</c:forEach>
						</tr>
						<tr>
							<td>三个月定期存款支出的现金</td>
							<c:forEach items="${cashFlowList }" var="item">
								<td>${item.cunkuanRegularPay }</td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>投资活动现金流出小计</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.chouZiPay }</strong></td>
							</c:forEach>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>筹资活动产生的现金流量净额</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.chouZiGet-item.chouZiPay }</strong></td>
							</c:forEach>
						</tr>
						<tr>
							<td id="cashYuETd"><strong>四、现金及现金等级物净增加额</strong></td>
						</tr>
						<tr bgcolor="#D9EDF7">
							<td><strong>季末现金余额</strong></td>
							<c:forEach items="${cashFlowList }" var="item">
								<td><strong>${item.yuE }</strong></td>
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
		$("#operatingActiveTd").attr("colspan",col);
		$("#investActivityTd").attr("colspan",col);
		$("#chouziActivityTd").attr("colspan",col);
		$("#cashYuETd").attr("colspan",col);
		
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