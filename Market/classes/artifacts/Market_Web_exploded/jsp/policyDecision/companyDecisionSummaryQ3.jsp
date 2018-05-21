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
	.panel-body{
		padding:10px;
	}
	.row{
		margin-top:20px;
		
	}
	.createCompany .row{
		margin-left:20px;
	}
	.strategy .row{
		margin-left:20px;
	}
	.position .row{
		/* margin-left:43px;
		margin-top:20px; */
		margin-left:20px;
	}
	.position table{
		width:500px;
	}
	.teamRule .row{
		margin-left:20px;
	}
	.personalGoal .row{
		margin-left:20px;
	}
	.sale .row,.produce .row,.finace .row{
		margin-left:20px;
	}
	
	.row ul{
		padding:0px;
	}
	
	.createCompany{
		border:1px dashed #666;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
	.strategy{
		border:1px dashed #666;
		margin-top:20px;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
	.humanResource{
		border:1px dashed #666;
		margin-top:20px;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
	.sale{
		border:1px dashed #666;
		margin-top:20px;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
	.produce{
		border:1px dashed #666;
		margin-top:20px;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
	.finace{

		/* background:#eee; */
		border:1px dashed #666;
		margin-top:20px;
		border-radius:5px;
		padding:10px 5px 10px 5px;
	}
</style>

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
$(function(){
	var col=$("#productInfoTb").find("thead").find("tr").children("th").length;
	/* $("#productInfoTr").attr("colspan",col); */
	/* var i=1; */
	/* var maxTdLength=col;
	$("#productInfoTb").find("tbody").find("tr").each(function(index){
		var num=$(this).children("td").length;
		if(maxTdLength<num){
			maxTdLength=num;
		}
		$(this).children("td:eq(col)").attr("colspan",num); 
		$(this).find("td").eq(col-1).attr("colspan",num+1);
		alert($(this).find("td").eq(col-1).text());
	});  */
	
});
</script>
</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>决策汇总</span>
			</div>
			<div class="panel-body">
				<div class="createCompany">
					<label>创建公司</label><br/>
					<div class="row">
						<div class="col-md-2"><label>公司名称:</label></div>
						<div class="col-md-2">${company.name }</div>
					</div>
				</div>
				<div class="strategy">
					<label>目标策略</label><br/>
					<div class="row">
						<div class="col-md-2"><label>产品类型:</label></div>
						<div class="col-md-2">
							<c:choose>
								<c:when test="${companyStrategy.mainPro!=null }">
									1-${companyStrategy.mainPro }
								</c:when>
								<c:otherwise>
									1-本季度未设置！
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-md-2">
							<c:choose>
								<c:when test="${companyStrategy.minorPro!=null }">
									2-${companyStrategy.minorPro }
								</c:when>
								<c:otherwise>
									2-本季度未设置！
								</c:otherwise>
							</c:choose>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-2"><label>服务宗旨:</label></div>
						<div class="col-md-2">
							<c:choose>
								<c:when test="${companyStrategy.service!=null }">
									${companyStrategy.service }
								</c:when>
								<c:otherwise>
									本季度未设置！
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"><label>策略方向:</label></div>
						<div class="col-md-4">
							<ul>
								<c:choose>
									<c:when test="${strategyList!=null&&strategyList.size()>0 }">
										<c:forEach items="${strategyList }" var="item2">
											<li>${item2.detail }</li>
										</c:forEach>
									
									</c:when>
									<c:otherwise>
										本季度未设置！
									</c:otherwise>
								</c:choose>
							</ul>
							
						</div>
					</div>
				</div>
				<div class="humanResource">
					<label>人力资源</label><br/>
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>职位分配</label></div>
							<div class="col-md-6">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>管理者姓名</th>
											<th>主要职责</th>
											<th>次要职责</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${memberDutyList }" var="item">
											<tr>
												<td>${item.name }</td>
												<td>${item.mainDuty }</td>
												<td>${item.minorDuty }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="teamRule">
						<div class="row">
							<div class="col-md-2"><label>团队规则</label></div>
							<div class="col-md-5">
								<ul>
									<c:choose>
										<c:when test="${ruleInfoList!=null&&ruleInfoList.size()>0 }">
											<c:forEach items="${ruleInfoList }" var="item3">
												<li>${item3.detail }</li>
											</c:forEach>
										
										</c:when>
										<c:otherwise>
											本季度未设置！
										</c:otherwise>
									</c:choose>
									
								</ul>
							</div>
						</div>
					</div>
					<%-- <div class="personalGoal">
						<div class="row">
							<div class="col-md-2"><label>个人目标</label></div>
							<div class="col-md-5">
								<c:forEach items="${allPersonalGoalList }" var="item4">
									<label>${item4.member.name }</label>
									<ul>
										<c:when test="${item4.personalGoal!=null }">
											<c:forEach items="${item4.personalGoal }" var="item5">
												<li>${item5.detail }</li>
											</c:forEach>
										
										</c:when>
										<c:otherwise>
											本季度未设置
										</c:otherwise>
									</ul>
								</c:forEach>
							</div>
						</div>
					</div> --%>
				</div>
				<!-- 市场营销 -->
				<div class="humanResource">
					<label>市场营销</label>
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>公司产品</label></div>
							<div class="col-md-4">
								<ul>
									<c:choose>
									<c:when test="${companyProductList!=null && companyProductList.size()>0 }">
										<c:forEach items="${companyProductList }" var="item">
											<li>${item.name }</li>
										</c:forEach>
									
									</c:when>
									<c:otherwise>
										公司未设计产品！
									</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</div>
					<hr/>
					<!-- 设计广告 -->
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>广告</label></div>
							<div class="col-md-4">
								<c:choose>
								<c:when test="${advertisesList!=null && advertisesList.size()>0 }">
									<c:forEach items="${advertisesList }" var="advertise">
										<label>产品名称：${advertise.key.productName}</label><br/>
										<label>广告名称：${advertise.key.advertiseName}</label>
										<ul>
										<c:forEach items="${advertise.value }" var="advertise2">
										<li>
											${advertise2.detail}
										</li>
										</c:forEach>
										</ul>
									</c:forEach>
								
								</c:when>
								<c:otherwise>
									公司未设计广告！
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<hr/>
					<!-- 区域媒体 -->
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>媒体投放（数量）</label></div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>媒体</th>
										<c:forEach items="${mediaInfoList }" var="item">
										<th>${item.detail }</th>
										</c:forEach>			
									</tr>	
								</thead>
								<tbody>
									<tr>
										<td><strong>成本</strong></td>
										<c:forEach items="${mediaInfoList }" var="item">
										<td>${item.cost }</td>
										</c:forEach>			
									</tr>
									<c:choose>
									<c:when test="${productMediaNumList!=null}">
										<c:forEach items="${productMediaNumList }" var="item">
											<tr>
												<td>${item.key }</td>
												<c:forEach items="${item.value }" var="media">
												<td>${media.num }</td>
												</c:forEach>			
											</tr>
										</c:forEach>
									
									</c:when>
									<c:otherwise>
										公司未进行媒体投放！
									</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<div class="col-md-2" style="width: 80%"><label>媒体投放价值总计：${mediaCostSum } 元</label></div>
						</div>
					</div>
					<hr/>
					<!-- 各个产品定价 -->
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>产品定价</label></div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
									<th>品牌</th>
									<th>零售价格</th>
									<th>邮寄返款</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${productPriceList }" var="item">
										<tr>
											<td>${item.key }</td>
											<td>${item.value.price }</td>
											<td>${item.value.youji }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 购买市场调研报告，这部分暂时不要 -->
					<%-- <div class="position">
						<div class="row">
							<div class="col-md-2"><label>市场调研报告</label></div>
							<label>${isBuyReport }</label>
						</div>
					</div> --%>
					
				</div>
				
				<!-- 人力资源 -->
				<div class="humanResource">
					<label>人力资源</label>
					<div class="position">
						<div class="row">
							<div class="col-md-2"><label>员工薪酬：空值表示未设置薪酬！</label></div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
									<th>员工</th>
									<th>工资</th>
									<th>福利</th>
									<th>休假</th>
									<th>公积金</th>
									<th>企业年金</th>
									<th>退休金</th>
									<th>薪酬总额</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>销售人员</td>
										<td>
											${salesSalary.salary }
										</td>
										<td>${salesSalary.welfare }</td>
										<td>${salesSalary.holiday }</td>
										<td>${salesSalary.publicFund }</td>
										<td>${salesSalary.companyPension }</td>
										<td>${salesSalary.retiredPay }</td>
										<td>${salesSalary.salaryTotal }</td>
									</tr>
									<tr>
										<td>工厂工人</td>
										<td>${workersSalary.salary }</td>
										<td>${workersSalary.welfare }</td>
										<td>${workersSalary.holiday }</td>
										<td>${workersSalary.publicFund }</td>
										<td>${workersSalary.companyPension }</td>
										<td>${workersSalary.retiredPay }</td>
										<td>${workersSalary.salaryTotal }</td>
									</tr>
								</tbody>
							</table>
							
							<div class="col-md-2"><label>实体销售人员人数</label></div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>市场名称</th>
										<th>销售人数</th>
										<th>售后人数</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${hirePeopleList }" var="item">
										<tr>
											<td>${item.key }</td>
											<td>${item.value.saleman }</td>
											<td>${item.value.afterSale }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="col-md-2"><label>网络销售人员人数</label></div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>市场名称</th>
										<th>销售人数</th>
										<th>售后人数</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${hirePeopleOnlineList }" var="item">
										<tr>
											<td>${item.key }</td>
											<td>${item.value.saleman }</td>
											<td>${item.value.afterSale }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
						</div>
					</div>
					
				</div>
				
				<div class="sale">
					<label>销售渠道</label><br/>
						<div class="physical">
							<div class="row">
								<div class="col-md-2"><label>开设实体销售中心</label></div>
								<div class="col-md-3">
									<c:forEach items="${phyMarketList }" var="item">
										城市：${item.city }<br/>
									</c:forEach>
								</div>
								<div class="col-md-4">
									实体销售中心总成本：${phyTotalPrice }元<br/>
								</div>
							</div>
						</div>
						
						<div class="net">
							<div class="row">
								<div class="col-md-2"><label>开设网络销售中心</label></div>
								<div class="col-md-3">
									<%-- <c:forEach items="${netMarketList }" var="item">
										&nbsp;${item.city }<br/>
									</c:forEach> --%>
									${netMarket }<br/>
								</div>
								<div class="col-md-4">
									网络销售中心总成本：${netTotalPrice }元
								</div>
							</div>
						</div>
				</div>
				<div class="produce">
					<label>生产制造</label>
					<div class="row">
						<div class="col-md-2"><label>品牌管理</label>
						</div>
					</div>
					<table id="productInfoTb" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>产品名称</th>
								<th>必备</th>
								<th>运营商</th>
								<th>蓝牙</th>
								<th>屏幕尺寸</th>
								<th>触控方式</th>
								<th>处理器速度</th>
								<th>机身容量</th>
								<th>拍照像素</th>
								<th>机身特性</th>
								<th>电池容量</th>
								<th>其他功能</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productsList }" var="product">
								<tr>
									<td>${product.key}</td>
									<c:forEach items="${product.value }" var="product2" varStatus="status">
										<c:if test="${status.index<=9 }">
										<td>
											<div>${product2.detail}</div>
										</td>
										</c:if>
									</c:forEach>
									<td>
										<c:forEach items="${product.value }" var="product2" varStatus="status">
											<c:if test="${status.index>9 }">
												${product2.detail};
											</c:if>
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="col-md-2"><label>固定产能</label></div>
						<div class="col-md-10">
							<ul>
								<li>当前季度固定产能：${companyCapacity.capacityNow }</li>
								<li>计划增加产能：${companyCapacity.capacityAdd }</li>
								<li>下季度固定产能：${nextTotalCapacity }</li>
								<li>当前季度运行产能：
									<c:choose>
									<c:when test="${operationCapacity.operateCapacity!=null}">
										${operationCapacity.operateCapacity }
									</c:when>
									<c:otherwise>
										未设置！
									</c:otherwise>
									</c:choose>
								</li>
							</ul>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2"><label>品牌预测需求量</label></div>
						<div class="col-md-10">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>品牌名称</th>
										<th>预测需求量</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${forecastDemandList }" var="item">
									<tr>
										<td>${item.key}</td>
										<td>${item.value}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-2"><label>品牌库存控制</label></div>
						<div class="col-md-10">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>品牌名称</th>
										<th>库存量</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${inventoryList }" var="item">
									<tr>
										<td>${item.key}</td>
										<td>${item.value}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					
					
				</div>
				<div class="finace">
					<div><label>财务</label></div>
					<div class="row">
						<div class="col-md-2">
							<label>公司持股</label>
						</div>
						<div class="col-md-10">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>股票类型</th>
										<th>所有者名字</th>
										<th>股数</th>
										<th>每股价格</th>
										<th>价值总计</th>
										<th>季度</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${companyStockList }" var="item">
									<tr>
										<td>${item.stockType }</td>
										<td>${item.owner }</td>
										<td>${item.stockNumber }</td>
										<td>${item.stockPrice }</td>
										<td>${item.totalPrice }</td>
										<td>${item.quarter }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<label>定期存款</label>
						</div>
						<div class="col-md-10">
							<ul>
								<li>当前季度存款：${fixedDeposit.cunru }</li>
								<li>季度利率：${fixedDeposit.lilv }</li>
								<li>预计利息收入：${fixedDeposit.cunru*fixedDeposit.lilv/100 }</li>
							</ul>
						</div>
					</div>
				</div>	
			</div>
			<div class="panel-footer"></div>
		</div>
	</div>
</body>
</html>