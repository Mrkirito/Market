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
		<div class="panel-heading">业内工厂工人薪酬</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#notice1" data-toggle="tab">课程介绍</a>
				</li>
				<li><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade in active" id="notice1">
					<div class="course_content">
						<div class="text1">每个季度，您都必须设定工厂工人的薪酬组合。
							该组合中包含了年薪、医疗福利、休假、及退休金。 您可以每个季度自由调整员工的薪酬组合。 在争夺高素质工厂工人方面的竞争相当激烈。
							您提供的薪酬水平，相对于竞争对手和整个电子行业而言，将决定公司能雇佣并留住高素质员工的能力。
							相对于行业整体水平的薪酬越高，工厂员工的积极性就越高，因此其生产效率也会越高。</div>
						<div class="text1">您及其它公司的员工都在不断寻求更高的薪酬。
							每个季度，调研公司都会对行业内的所有员工进行问卷调查，以了解其最希望获得改善的薪酬组合方面。
							在最初的几个季度内，他们最希望公司能提高其底薪。 随着工资的提高，他们的注意力将转移到其它方面，比如医疗福利、假期、及退休金。
							然而，问卷调查结果可能与员工的实际反应有所不同。 所以，既要研究调查数据，也要观察竞争对手的销售人员对其薪酬组合的反应。</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">请在相应决策界面查看电子行业工厂工人薪酬组合的平均水平。
							电子行业包括了电脑公司、影印公司、及电话公司等。 同时也请注意薪酬组合哪些方面的改善对员工来说相对更重要。
							薪酬组合各方面的相对重要值显示了工厂工人对其的重视程度。 这些数值可能会随着时间的增长而改变。
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="notice2">
					<form class="form-horizontal" id="form" method="post" action="">
						<table class="table table-bordered">
							<tr class="success">
								<td colspan="9">竞争对手提供的年度工厂工人薪酬组合</td>
							</tr>
							<tr>
								<td><B>公司</B></td>
								<td><B>生产率</B></td>
								<td><B>工资（年薪）</B></td>
								<td><B>福利</B></td>
								<td><B>休假</B></td>
								<td><B>公积金</B></td>
								<td><B>企业年金</B></td>
								<td><B>退休金</B></td>
								<td><B>年度成本</B></td>
							</tr>
							<c:forEach items="${allVoList}" var="items">
								<tr>
									<td>${items.company.name}</td>
									<td>${items.workerEfficiency}</td>
									<td>${items.workersSalary.salary}元</td>
									<td>${items.workersSalary.welfare}%</td>
									<td>${items.workersSalary.holiday}天</td>
									<td>${items.workersSalary.publicFund}%</td>
									<td>${items.workersSalary.companyPension}%</td>
									<td>${items.workersSalary.retiredPay}%</td>
									<td>${items.workersSalary.salaryTotal}</td>
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
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>