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
	background: white;
	
}

td, th {
	 text-align: center;
	/*  white-space:nowrap; */
	 
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
		<div class="panel-heading">各产品销量</div>
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
							在这里，我们将统计上季度的销量，产量，销售额等数据</div>
						<div class="text1">这些信息将帮助你做出下个季度的经营决策，并优化你的投资方案。</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">了解上季度销售情况</div>
					</div>

				</div>
				<div class="tab-pane fade in active" id="notice2">
					<form class="form-horizontal" id="form"
						name="addSalesSalaryForm" action="" >
						<table class="table table-bordered rounded-corner">
							<thead>
								<tr class="success" style="font-weight:bold;color:#333;">
									<input type="hidden" value="${len }" id="len"/>
									<input type="hidden" value="${quater }" name="quater"/>
									<input type="hidden" value="${add }" name="add"/>
									<td>产品名称</td>
									<td>预计需求量(件)</td>
									<td>产品销量(件)</td>
									<td>销售额(元)</td>
									<td>产品脱销(件)</td>
									<td>脱销损失(元)</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listssv }" var="lists">
									<tr>
										<td>${lists.PMS.productType }</td>
										<td>${lists.demandsum }</td>
										<td>${lists.salesum }</td>
										<td>${lists.saleIncom }</td>
										<td>${lists.PMS.stockoun }</td>
										<td>${lists.saleLost }</td>
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