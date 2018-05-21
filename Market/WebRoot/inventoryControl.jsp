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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<style type="text/css">
body {
	width: 99%;
	margin: 5px;
}

.panel {
	margin: 0px;
}

.panel-body {
	background-size: cover;
}

.table {
	width: 500px;
	height: 100px;
	background: white;
}

td, th {
	text-align: center;
	white-space: nowrap;
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

.text1 {
	text-indent: 30px;
	margin-top: 10px;
}

.text1_ul {
	margin-left: 40px;
	margin-top: 10px;
}

.form-control {
	vertical-align: middle;
	float: left;
	size: 40px;
	width: 200px;
}

#fuli {
	vertical-align: middle;
	float: left;
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

#notice2 {
	min-height: 450px;
}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">库存控制</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">
					<div class="course_content">
						<div class="text1">生产经理需要知道各个季度所要生产的品牌，以及季末的库存量。
							是否要生产某款品牌取决于营销部门的决定。 如果营销部门决定销售某款品牌，那生产部门就必须安排其生产。
							季末的库存产品将延续到下个季度，因此库存量将由您是否能在下季度销售掉这批存货而定，您同时还需要考虑库存对资金的占用。
							在通常情况下，您并不需要持有大量的季末库存。 与其将库房堆满产品，还不如在适当时候停止生产，让工人带薪停工。
							这是因为库存成本太高，而且库存产品还可能在不久成为过时品。
							从另一方面来说，如果公司资金充裕，而且您很有信心当前品牌将在下季度畅销，您也可以让工人不间断地生产。</div>
						<div class="text1">在公司的早期阶段（季度2 和 季度 3），品牌设计具有相当大的风险。
							如果某款新品牌对市场缺乏吸引力，则大量持有该品牌的库存就不是明智之举。</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">请在相应的决策区域选择当前季度需要生产的品牌。
							如果要停产某品牌，请务必反选该品牌。 接下来，您需要决定季末的最大库存量。</div>
					</div>
				</div>
				<div class="tab-pane fade in active" id="notice2">
					<input type="hidden" id="quarter" value="${quarter }"> 
					<input type="hidden" id="currentQuarter" value="${currentQuarter }">
					<input type="hidden" id="isSubmit" value="${isSubmit }">
					<form class="form-horizontal" method="post" id="form" name=""
						action="updateInventoryControl.do?quarter=${quarter }">
						<table class="table table-bordered">
							<thead>
								<tr class="success">
									<th>品牌</th>

									<th>季末最大库存量</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${inventoryControl}" var="item"
									varStatus="status">
									<input type="hidden"
										name="companyProductVo2List[${status.index }].id"
										value="${item.id }">
									<tr>
										<td>${item.name}</td>

										<td><input class="form-control" type="text"
											name="companyProductVo2List[${status.index }].inventory"
											value="${item.inventory}" maxlength="4"></td>
									</tr>
									<input type="hidden" id="guding"
										value="${(companyCapacityList1[0].capacityNow+companyCapacityList1[0].capacityAdd)*65}">
								</c:forEach>
							</tbody>
						</table>
						<button class="btn btn-info" type="button" onclick="init()">提交</button>
					</form>
				</div>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>

</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
<script type="text/javascript">
	$(function() {
		var currentQuarter = $("#currentQuarter").val();
		var quarter = $("#quarter").val();
		var isSubmit = $("#isSubmit").val();
		if (currentQuarter > quarter) {
			$("button").attr("disabled", true);
		} else {
			if (isSubmit == 1) {
				$("button").attr("disabled", true);
			}
		}
	});
	function init() {
		//判断整数
		var text = $(".form-control")
		
		for(var i=0;i<text.length;i++){
			if((text[i].value%1)!=0){
				alert("请输入整数！");
				text[i].value=1;
				return;
			}
		}
		
		for (var i = 0; i < text.length; i++) {
			if (text[i].value == "" || text[i].value == 0) {
				alert("不可为空或0")
				return;
			}
		}

		var guding = $("#guding").val()
		for (var i = 0; i < text.length; i++) {
			if (parseInt(guding-text[i].value)<0) { 
				alert("季末最大库存量   不能超过   固定产能")
				return; 

			 } 
		}

		alert("提交成功");
		document.getElementById("form").submit();
	}
</script>
</html>