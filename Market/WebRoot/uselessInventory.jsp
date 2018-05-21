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
	height: 1000px;
}

.panel {
	margin: 0px;
}

.panel-body {
	background-size: cover;
}

.table {
	width: 700px;
	height: 100px;
	background: white;
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
		<div class="panel-heading">无用库存</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li ><a href="#notice1" data-toggle="tab">
						课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">

					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">如果您决定停产某品牌，但该品牌还有一定的存货，则您可能需要在二级市场上处理这批存货。
							处理价格通常都会低于产品的生产成本，您不可能从中赢利。

							如果您选择对无用库存进行清仓处理，则从其出库到资金回笼支票账户将耗费一个季度的时间。</div>
					</div>
				</div>
				<div class="tab-pane fade in active" id="notice2">
					<form class="form-horizontal" id="form" method="post" action="">
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="5"><B>销售无用库存</B></td>
							</tr>
							<tr>
								<td><B>品牌</B></td>
								<td><B>库存产品数</B></td>
								<td><B>清仓价格</B></td>
								
								<td><B>清仓收入</B></td>
							</tr>
							<c:forEach items="${SLVoList}" var="items">
								<tr>
									<td><B>${items.companyProducts.name}</B></td>
									<td>${items.stock}</td>
									<td>${items.companyProducts.cost}</td>
									
									<td>${items.stock*items.companyProducts.cost}</td>
								</tr>
							</c:forEach>
						</table>
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
</html>