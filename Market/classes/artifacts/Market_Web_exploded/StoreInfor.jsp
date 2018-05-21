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
	width: 500px;

	background: white;
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
		<div class="panel-heading">各城市竞争对手</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab">
						课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">各公司销售网点</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade " id="notice1">
					<div class="course_content">
						<div class="text1">
							这里显示了你和你的对手的市场布局战略</div>
						<div class="text1">知己知彼，百战不殆。你需要对比你和你的对手的市场战略差异，并优化你的市场战略</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">了解竞争对手的市场战略</div>
					</div>

				</div>
				<div class="tab-pane fade in active" id="notice2">
					<form class="form-horizontal" id="form"
						name="addSalesSalaryForm" action="" >
						<table class="table table-bordered">
							<thead>
								<tr class="success">
									<input type="hidden" value="${len }" id="len"/>
									<input type="hidden" value="${quater }" name="quater"/>
									<input type="hidden" value="${add }" name="add"/>
									<td>公司名称</td>
									<td>开设网店</td>
									<td>印度</td>
									<td>俄罗斯</td>
									<td>中国</td>
									<td>新加坡</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ListSIV }" var="lists">
									<tr>
										<td>${lists.c.name }</td>
										<td>${lists.isphy }</td>
										<td>${lists.mStr[0] }</td>
										<td>${lists.mStr[1] }</td>
										<td>${lists.mStr[2] }</td>
										<td>${lists.mStr[3] }</td>
									</tr>
								</c:forEach>
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