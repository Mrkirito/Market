<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<style type="text/css">
body {
	width: 99%;
	margin: 4px;
	height: 1500px;
}

#renGong1, #renGong2, #guDing1, #guDing2, #chengBen1, #chengBen2,
	#chengBen31, #chengBen41, #chengBen32, #chengBen42, #chengBen33,
	#chengBen43 {
	border: none;
	text-align: center;
	background: none;
}

.panel {
	margin: 0px;
}

.panel-body {
	background-size: cover;
}

.table {
	width: 800px;
	height: 100px;
	background: white;
}

#three {
	width: 430px;
	height: 100px;
}

td, th {
	text-align: center;
	height: 20px;
}

#notice2 {
	min-height: 420px;
}

.course_content {
	border: 1px solid #0ff;
	margin-top: 10px;
	padding: 20px;
	min-height: 220px;
	font-size: 14px;
	letter-spacing: 1px;
}

.left {
	/* border:1px solid blue; */
	width: 450px;
	padding: 20px;
	float: left;
	background: #eee;
	margin: 10px 0 0 2px;
	min-height: 220px;
}

.left_title span i {
	color: #009;
	font-size: 18px;
	margin-right: 6px;
}

.right {
	/* border:1px solid blue; */
	width: 400px;
	padding: 20px;
	float: left;
	background: #abcdef;
	margin: 10px 0 0 60px;
	min-height: 120px;
}

.right_title span i {
	color: red;
	font-size: 22px;
	margin-right: 6px;
}

.text0 {
	margin-left: 30px;
	margin-top: 10px;
}

.kaozuo {
	text-align: left;
}

.text1 {
	text-indent: 30px;
	margin-top: 10px;
}

.text1_ul {
	margin-left: 40px;
	margin-top: 10px;
}

.text2 {
	margin-left: 30px;
	margin-top: 25px;
}

.left_content li {
	margin-top: 5px;
}

.tab-content {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">上季度结果</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">
					<div class="course_content">
						<div class="text1">也许您会惊讶地发现，工厂的实际产量比计划产量要小。
							工厂的生产率在很大程度上由工人的积极性所决定。 而工人积极性，反过来，又由您提供的薪酬组合决定。
							如果您提供的薪酬完全满足了工人的需要，并且高于业内其它公司的薪酬，则工人的产出量将更高。
							如果您的工人对薪酬不满意，生产量就会下跌，而相应的成本则会增加。 （将预计成本与实际成本相比较。）</div>
						<div class="text1">在敲定生产决策之前，您可能需要回顾一下工人薪酬方面的决策，并且增加额外的安全库存，以免实际产量低于计划产量。</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">相应决策界面的表格中记录了上季度的生产活动和生产线运行成本。
							请注意产品脱销及季末库存。 同样，您还需要分析各个品牌的成本构成以及工厂工人的生产效率。</div>
					</div>
				</div>
				<div class="tab-pane fade  in active" id="notice2">
					<form class="form-horizontal" id="form" method="post" action="">
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="8"><B>库存水平--件数</B></td>
							</tr>
							<tr>
								<td><B>品牌</B></td>
								<td><B>季初库存</B></td>
								<td><B>已生产产品件数</B></td>
								<td><B>实际需求量</B></td>
								<td><B>已售出产品件数</B></td>
								<td><B>因脱销而损失的销售</B></td>
								<td><B>季末库存</B></td>
							</tr>
							<c:set var="sum1" value="0"></c:set>
							<c:set var="sum7" value="0"></c:set>
							<c:set var="sum2" value="0"></c:set>
							<c:set var="sum3" value="0"></c:set>
							<c:set var="sum4" value="0"></c:set>
							<c:set var="sum5" value="0"></c:set>
							<c:set var="sum6" value="0"></c:set>
							<c:forEach items="${SLVoList}" var="items">
								<tr>
									<td>${items.companyProducts.name}</td>
									<td>${items.afterStock}</td>
									<td>${items.yiShengChan}</td>
									<input type="hidden" id="yiShengChan" name="yiShengChan"
										value="${(items.singaporeSale+items.hongkongSale+items.moscowSale+items.newdelhiSale+items.onlineSale+items.stock)/65}" />
									<td>${items.singaporeNeed+items.hongkongNeed+items.moscowNeed+items.newdelhiNeed+items.onlineNeed}</td>

									<td>${items.singaporeSale+items.hongkongSale+items.moscowSale+items.newdelhiSale+items.onlineSale}</td>
									<td>${items.stockoun}</td>
									<td>${items.stock}</td>

								</tr>
								<c:set var="sum1"
									value="${sum1+items.singaporeSale+items.hongkongSale+items.moscowSale+items.newdelhiSale+items.onlineSale+items.stock}"></c:set>
								<c:set var="sum2"
									value="${sum2+items.singaporeSale+items.hongkongSale+items.moscowSale+items.newdelhiSale+items.onlineSale+items.stock}"></c:set>
								<c:set var="sum3"
									value="${sum3+items.singaporeNeed+items.hongkongNeed+items.moscowNeed+items.newdelhiNeed+items.onlineNeed}"></c:set>
								<c:set var="sum4"
									value="${sum4+items.singaporeSale+items.hongkongSale+items.moscowSale+items.newdelhiSale+items.onlineSale}"></c:set>
								<c:set var="sum5" value="${sum5+items.stockoun}"></c:set>
								<c:set var="sum6" value="${sum6+items.stock}"></c:set>
								<c:set var="sum7" value="${sum7+items.afterStock}"></c:set>
							</c:forEach>
							<tr>
								<td>合计</td>
								<td>${sum7}</td>
								<td>${sum1}</td>

								<td>${sum3}</td>
								<td>${sum4}</td>
								<td>${sum5}</td>
								<td>${sum6}</td>
							</tr>
						</table>
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="6"><B>单位生产成本</B></td>
							</tr>
							<tr>
								<td><B>品牌</B></td>
								<td><B>已生产产品件数</B></td>
								<td><B>直接材料</B></td>
								<td><B>直接人工</B></td>
								<td><B>总固定费用</B></td>
								<td><B>平均单位生产成本</B></td>

							</tr>
							<c:forEach items="${SLVoList}" var="items">
								<tr>
									<td>${items.companyProducts.name}</td>
									<td><fmt:formatNumber type="number"
											value="${items.yiShengChan/65}" pattern="0"
											maxFractionDigits="0" /></td>
									<td>${items.companyProducts.cost}</td>
									<!-- 直接人工 -->
									<td>${items.rengong}</td>
									<!-- 总固定费用 -->
									<td>${items.guding}</td>
									<!-- 平均单位生产成本 -->
									<td>${items.chengben}</td>
								</tr>
							</c:forEach>
						</table>
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="5"><B>库存水平-成本/件数</B></td>
							</tr>
							<tr>
								<td><B>品牌</B></td>
								<td><B>季初库存：平均成本/件</B></td>
								<td><B>本季平均：生产成本/件</B></td>
								<td><B>本季平均：销货成本/件</B></td>
								<td><B>季末库存：平均成本/件</B></td>
							</tr>
							<c:forEach items="${SLVoList}" var="items">
								<tr>
									<td>${items.companyProducts.name}</td>
									<td>0</td>
									<td>${items.chengben}</td>
									<td>${items.chengben}</td>
									<td>${items.chengben}</td>
								</tr>
							</c:forEach>
						</table>
						<table class="table table-bordered" id="three">
							<tr class="success">
								<td colspan="2"><B>运行产能利用率</B></td>
							</tr>
							<tr>
								<td colspan="2"><B>运行产能</B></td>
							</tr>
							<tr>
								<td class="kaozuo">计划运行产能</td>
								<td>${operationCapacityList[0].operateCapacity*65}</td>
							</tr>
							<tr>
								<td class="kaozuo">工厂工人生产率</td>
								<td><fmt:formatNumber type="number"
										value="${companyInvestmentList[0].workerEfficiency*100}"
										pattern="0" maxFractionDigits="0" />%</td>
							</tr>
							<tr>
								<td class="kaozuo">有效运行产能</td>
								<td><fmt:formatNumber type="number"
										value="${operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency}"
										pattern="0" maxFractionDigits="0" /></td>
							</tr>
							<tr>
								<td class="kaozuo">实际运行产能</td>
								<td>${sum2}</td>
							</tr>
							<!--  <tr>
								<td class="kaozuo">有效运行产能利用率</td>
								<c:if
									test="${sum2/(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency+1)*100<=100}">
									<td><fmt:formatNumber type="number"
											value="${sum2/(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency+1)*100}"
											pattern="0" maxFractionDigits="0" />%</td>
								</c:if>
								<c:if
									test="${sum2/(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency+1)*100>100}">
									<td><fmt:formatNumber type="number" value="${100}"
											pattern="0" maxFractionDigits="0" />%</td>
								</c:if>
							</tr>
							-->
							<tr>
								<td colspan="2"><B>过剩产能</B></td>

							</tr>
							<tr>
								<td class="kaozuo">闲置的运行产能</td>
								<c:if
									test="${(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2>=0}">
									<td><fmt:formatNumber type="number"
											value="${(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2}"
											pattern="0" maxFractionDigits="0" /></td>
								</c:if>
								<c:if
									test="${(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2<0}">
									<td><fmt:formatNumber type="number" value="${0}"
											pattern="0" maxFractionDigits="0" /></td>
								</c:if>
							</tr>
							<!-- 
							<tr>
								<td class="kaozuo">过剩运行产能</td>
								<c:if
									test="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)*100)==0}">
									<td><fmt:formatNumber type="number" value="${0}"
											pattern="0" maxFractionDigits="0" />%</td>
								</c:if>
								<c:if
									test="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)*100)>100}">
									<td><fmt:formatNumber type="number" value="${0}"
											pattern="0" maxFractionDigits="0" />%</td>
								</c:if>
								<c:if
									test="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)*100)!=0&&((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)*100)<100}">
									<td><fmt:formatNumber type="number"
											value="${100-(sum2/(operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)*100)}"
											pattern="0" maxFractionDigits="0" />%</td>
								</c:if>


							</tr>
						<!--  	<tr>
								<td class="kaozuo">运行产能过剩所产生的固定成本及人工</td>
								<c:if test="${sum2!=0}">
									<c:if
										test="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2)*(5000/(sum2*65)+5*(sum2/65)+100)>0}">
										<td><fmt:formatNumber type="number"
												value="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2)*(5000/(sum2*65)+5*(sum2/65)+100)}"
												pattern="0" maxFractionDigits="0" /></td>
									</c:if>
									<c:if
										test="${((operationCapacityList[0].operateCapacity*65*companyInvestmentList[0].workerEfficiency)-
								sum2)*(5000/(sum2*65)+5*(sum2/65)+100)<0}">
										<td><fmt:formatNumber type="number" value="${0}"
												pattern="0" maxFractionDigits="0" /></td>
									</c:if>
								</c:if>
								<c:if test="${sum2==0}">
									<td><fmt:formatNumber type="number" value="${0}"
											pattern="0" maxFractionDigits="0" /></td>
								</c:if>
							</tr>
-->
						</table>
					</form>

				</div>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>

</body>
<script type="text/javascript">
	//function init() {

	// var text=$(".form-control");
	//var yiShengChan = $("#yiShengChan").val();
	//var cost = $("#cost").val();

	//var Value1 = Math.round(1500 / Math.log(1 + parseInt(yiShengChan)));
	//var Value2 = Math.round(1500 / Math.log(7) - parseInt(yiShengChan));
	//var Value3 = Math.round(1200 / Math.log(1 + parseInt(yiShengChan)));
	//var Value4 = Math.round(1200 / Math.log(7) - parseInt(yiShengChan));
	//var Value5 = parseInt(cost) + parseInt(Value1) + parseInt(Value3);
	//var Value6 = parseInt(cost) + parseInt(Value2) + parseInt(Value4);
	//$("#renGong1").val(Value1);
	//$("#renGong2").val(Value2);
	//$("#guDing1").val(Value3);
	//$("#guDing2").val(Value4);
	///$("#chengBen1").val(Value5);
	//$("#chengBen2").val(Value6);
	//	$("#chengBen31").val(Value5);
	//	$("#chengBen32").val(Value5);
	//	$("#chengBen33").val(Value5);
	//	$("#chengBen41").val(Value6);
	//	$("#chengBen42").val(Value6);
	//	$("#chengBen43").val(Value6);
	//}
	//init();
</script>

</html>