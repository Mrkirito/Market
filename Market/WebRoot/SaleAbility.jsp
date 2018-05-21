<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
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
	width: 800px;
}

td, th {
	text-align: center;
	 white-space:nowrap;
	 
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
.form-control{
vertical-align:middle;
float:left;
size:40px;
width:200px;
}
#fuli{
vertical-align:middle;
float:left;
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
<body onLoad="init()">
	<div class="panel panel-info">
		<div class="panel-heading">渠道盈利能力</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab">
						课程介绍</a></li>
				<li  class="active"><a href="#notice2" data-toggle="tab">上季度数据统计</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">
					<div class="course_content">
						<div class="text1">
							这个页面显示了上季度你的成本利润比重</div>
						<div class="text1">你需要根据这些信息，判断你当前的成本结构和利润率是否合理</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">了解上季度的成本结构并思考下季度的价格战略</div>
					</div>

				</div>
				<div class="tab-pane fade in active" id="notice2">
					<form class="form-horizontal" id="form"
						name="addSalesSalaryForm" action="" >
						<table class="table table-bordered">
							<thead>
								<tr class="success" style="font-weight:bold;color:#333;">
									<input type="hidden" value="${len }" id="len"/>
									<input type="hidden" value="${quater }" name="quater"/>
									<input type="hidden" value="${add }" name="add"/>
									<td>销售收入(元)</td>
									<td>邮寄返款(元)</td>
									<td>销货成本(元)</td>
									<td>毛利(元)</td>
									<td>销售人员费用(元)</td>
									<td>销售利润(元)</td>
									<td>利润占收入比重</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${vo.saleIncomSum }</td>
									<td>${vo.youjiSum }</td>
									<td>${vo.saleCostSum }</td>
									<td>${fuzzyincom }</td>
									<td>${salary_sum }</td>
									<td>${income }</td>
									<td>${rate }</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		
		</div>
		<div class="panel-footer"></div>
	</div>
	<br>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</html>