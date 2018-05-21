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
		<div class="panel-heading">竞争力</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab">
						课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade " id="notice1">
					<div class="course_content">
						<div class="text1">相应的决策界面显示了竞争对手的固定产能及运行产能。
							当调整生产决策和商业策略的时候，请参看该信息。</div>
					</div>
				</div>
				<div class="tab-pane fade in active" id="notice2">
					<form class="form-horizontal" id="form" method="post" action="">
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="4">上季度固定及运行产能</td>
							</tr>
							<tr>
								<td><B>公司</B></td>
								<td><B>固定产能</B></td>
								<td><B>运行产能</B></td>
								<td><B>有效运行产能</B></td>
							</tr>
							<c:forEach items="${CPVoList}" var="items">
								<tr>
									<td>${items.company.name}</td>
									<td>${items.capacityNow+items.capacityAdd}</td>
									<td>${items.operateCapacity}</td>
									<td><fmt:formatNumber type="number"
											value="${items.operateCapacity*items.workerEfficiency}"
											pattern="0" maxFractionDigits="0" /></td>
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