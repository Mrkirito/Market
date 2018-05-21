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
<title>dicision locks</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<style type="text/css">
td:hover { 
	background-color:#D9EDF7;
	
}
</style>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>关键决策</span>
				<input id="currentQuarter" type="hidden" value="${currentQuarter }"/>
			</div>

			<div class="panel-body">
				<div>
					<table class="table table-bordered">
						<thead>
							<tr id="companyNameTr">
								<th>公司名称</th>
								<c:forEach items="${keyDecisionList }" var="item">
									<th>${item.company.name }</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<tr id="companyId" style="display:none;">
								<td></td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.company.id }</td>
								</c:forEach>
							</tr>
							<tr>
								<td class="headTitle"><strong>市场营销</strong></td>
							</tr>
							<tr id="strategy">
								<td>目标细分市场</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<!-- <td onclick="strategy(this)" style="cursor:pointer;"> -->
									<td>
										${item.companyStrategy.mainPro }<br/>
										${item.companyStrategy.minorPro }
									</td>
								</c:forEach>
							</tr>
							<tr id="newBrand">
								<td>新品牌</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										<c:forEach items="${item.companyProductList }" var="item2">
											${item2.name }<br/>
										</c:forEach>
									</td>
								</c:forEach>
							</tr>
							<tr id="productNum">
								<td>总品牌数量</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										${item.productNumber }
									</td>
								</c:forEach>
							</tr>
							
							<tr id="minPrice">
								<td>最低价</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										${item.minPrice }
									</td>
								</c:forEach>
							</tr>
							<tr id="maxPrice">
								<td>最高价</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										${item.maxPrice }
									</td>
								</c:forEach>
							</tr>
							<tr id="avgPrice">
								<td>平均价</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										${item.avgPrice }
									</td>
								</c:forEach>
							</tr>
							
							<tr id="advertise">
								<td>广告</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										<ul>
										<c:forEach items="${item.companyAdvertiseList }" var="item2">
											<li>
											${item2.advertiseName }<br/>
											</li>
										</c:forEach>
										</ul>
									</td>
								</c:forEach>
							</tr>
							<tr id="mediaNum">
								<td>媒体投放数量</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										${item.mediaNumber }
									</td>
								</c:forEach>
							</tr>
							
							<tr>
								<td class="headTitle"><strong>销售渠道</strong></td>
							</tr>
							<tr id="entitySalesCenter">
								<td>实体销售中心</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										<c:if test="${item.phyMarketList==null }">
						                	<div>未开放</div>
						                </c:if>
										
										<c:forEach items="${item.phyMarketList }" var="item2">
											${item2.city }<br/>
										</c:forEach>
									</td>
								</c:forEach>
							</tr>
							<tr id="netSalesCenter">
								<td>网络销售中心</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>
										<%-- <c:forEach items="${item.netMarketList }" var="item2">
											${item2.city }<br/>
										</c:forEach> --%>
										<c:if test="${item.netMarket.marketId!='全球市场' }">
						                	<div>未开放</div>
						                </c:if>
										${item.netMarket.marketId }
									</td>
								</c:forEach>
							</tr>
							<tr>
								<td class="headTitle"><strong>人力资源</strong></td>
								
							</tr>
							<tr id="teamPeopleNumber">
								<td>管理团队人数</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.memberCount }</td>
								</c:forEach>
							</tr>
							<tr id="phySalerNumber">
								<td>实体销售人数</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.phySalerNum }</td>
								</c:forEach>
							</tr>
							<tr id="netSalerNumber">
								<td>网络销售人数</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.netSalerNum }</td>
								</c:forEach>
							</tr>
							
							
							
							<tr id="salesSalary">
								<td>销售人员薪酬</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.salesSalary.salaryTotal }</td>
								</c:forEach>
							</tr>
							<tr id="workersSalary">
								<td>工厂工人薪酬</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.workersSalary.salaryTotal }</td>
								</c:forEach>
							</tr>
							
							<tr>
								<td class="headTitle"><strong>生产制造</strong></td>
							</tr>
							<tr id="fixedCapacity">
								<td>当前季度固定产能</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.companyCapacity.capacityNow }</td>
								</c:forEach>
							</tr>
							<tr id="addCapacity">
								<td>下季度将增加的固定产能</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.companyCapacity.capacityAdd }</td>
								</c:forEach>
							</tr>
							<tr id="operationCapacity">
								<td>当前季度运行产能</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.companyCapacity.capacityNow }</td>
								</c:forEach>
							</tr>

							<tr>
								<td class="headTitle"><strong>财务</strong></td>
							</tr>
							<tr id="stock">
								<td>股票发行</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.companyStockSum }</td>
								</c:forEach>
							</tr>
							<tr id="stock">
								<td>季度存款</td>
								<c:forEach items="${keyDecisionList }" var="item">
									<td>${item.fixedDeposit }</td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
				<strong>注意：上表中空值表示公司未进行设置！</strong>
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
	
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		var colNum=$("#companyNameTr").children("th").length;
		$(".headTitle").attr("colspan",colNum);
	});

	/* 以下函数将不在用，可以直接删除，现在将上面的点击事件取消 */
	function strategy(node){
		//var id=$(this).parents("tr").find("td").index($(this));
		var id=$(node).parents("tr").find("td").index($(node));
		var companyId=$("#companyId").children('td').eq(id).text();
		var currentQuarter=$("#currentQuarter").val();
		var url="policyDecision/strategyInfo.do?currentQuarter="+currentQuarter+"&companyId="+companyId;
		window.location.href=url;
	}
	
	function newProduct(node){
		var id=$(node).parents("tr").find("td").index($(node));
		var companyId=$("#companyId").children('td').eq(id).text();
		var currentQuarter=$("#currentQuarter").val();
		var url="policyDecision/newProduct.do?currentQuarter="+currentQuarter+"&companyId="+companyId;
		window.location.href=url;
	}
	
	function productNum(node){
		
	}
	
	function entitySailCenter(node){
		var id=$(node).parents("tr").find("td").index($(node));
		var companyId=$("#companyId").children('td').eq(id).text();
		var currentQuarter=$("#currentQuarter").val();
		var url="policyDecision/entitySailCenter.do?currentQuarter="+currentQuarter+"&companyId="+companyId;
		window.location.href=url;
	}
	
	function webSailCenter(node){
		var id=$(node).parents("tr").find("td").index($(node));
		var companyId=$("#companyId").children('td').eq(id).text();
		var currentQuarter=$("#currentQuarter").val();
		var url="policyDecision/webSailCenter.do?currentQuarter="+currentQuarter+"&companyId="+companyId;
		window.location.href=url;
	}
	
	function companyNumber(node){
		var id=$(node).parents("tr").find("td").index($(node));
		var companyId=$("#companyId").children('td').eq(id).text();
		var currentQuarter=$("#currentQuarter").val();
		var url="policyDecision/companyNumberInfo.do?currentQuarter="+currentQuarter+"&companyId="+companyId;
		window.location.href=url;
	}
	
</script>
</body>
</html>