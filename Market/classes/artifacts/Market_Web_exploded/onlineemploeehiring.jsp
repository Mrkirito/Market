<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	height: 300px;
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
<script type="text/javascript">
	function sign() {
		alert("提交成功");
		document.getElementById("form").action = "OnlineEmploeeSingin.do";
		document.getElementById("form").submit();
	}
	
	function init(){
		var salary=parseInt($("#hidden_aftersale").val());
		var welfare=parseInt($("#hidden_ecnomical").val());
		var len=$("#len").val();
		if(len!=0){
			$("#aftersale").attr("value",salary);
			$("#ecnomical").attr("value",welfare);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare+publicFund+companyPension+holiday);
			document.getElementById("toutalpopu").innerText=totalCost+"人";
		}
	}
</script>
</head>
<body onLoad="init()">
	<div class="panel panel-info">
		<div class="panel-heading">销售人员</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#notice1" data-toggle="tab">
						课程介绍</a></li>
				<li><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade in active" id="notice1">
					<div class="course_content">
						<div class="text1">
							(在这里输入实体店店员雇佣的介绍字段)</div>
						<div class="text1">本页面假设：<p><p>招聘成本100元/人<p>离职补偿150元/人</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">雇佣适当数量的销售人员</div>
					</div>

				</div>
				<div class="tab-pane fade" id="notice2">
					<form class="form-horizontal" id="form"
						name="addSalesSalaryForm" action="" >
						<table class="table table-bordered">
							<thead>
								<tr>
									<input type="hidden" value="${len }" id="len"/>
									<input type="hidden" value="${quater }" name="quater"/>
									<input type="hidden" value="${add }" name="add"/>
									<th colspan="2">销售人员雇佣</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>售后</td>
									<td><input id="aftersale" class="form-control" type="text" name="aftersale" value="0" onchange="aa()">
									<input type="hidden" value="${salesSalaryList.aftersale}" id="hidden_aftersale"/>人</td>
								</tr>
								<tr>
									<td>销售</td>
									<td><input id="ecnomical" class="form-control" type="text"
										name="ecnomical" value="0" onchange="a1()">
										<input type="hidden" value="${salesSalaryList.saler}" id="hidden_ecnomical"/>人</td>
								</tr>
								<tr>
									<td>当前销售人员总数</td>
									<td><label name="toutalpopu" id="toutalpopu">0人</label></td>
								</tr>
								<tr>
									<td>新增销售人员数</td>
									<td><label name="freshpopu" id="freshpopu">0人</label></td>
								</tr>
								<tr>
									<td>上季度销售人员总数</td>
									<td><label name="lastseasontoutalpopu" id="lastseasontoutalpopu">0人</label></td>
								</tr>
								<tr>
									<td>招聘成本</td>
									<td><label name="hiringcost" id="hiringcost">0元</label></td>
								</tr>
								<tr>
									<td>离职补偿</td>
									<td><label name="dismisscost" id="dismisscost">0元</label></td>
								</tr>
								<tr>
									<td>员工工资</td>
									<td><label name="salary" id="salary">0元</label></td>
								</tr>
								<tr>
									<td>支出总计</td>
									<td><label name="costsum" id="costsum">0元</label></td>
								</tr>
								<tr>
									<td colspan="5"><button class="btn btn-info"
											onclick="sign()">提交</button></td>
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
<script>
	function aa(){
		//判断整数
		var text=$(":input[name=salary]");
		for(var i=0;i<text.length;i++){
			if((text[i].value%1)!=0){
				alert("请输入整数！");
				return;
			}
		}
	}
	
	function a1(){
		var text=$("#welfare").val();
		check(text);
	}
	
	function a2(){
		var text=$("#holiday").val();
		if((text%1)!=0){
			alert("请输入整数！");
			return;
		}
	}
	
	function a3(){
		var text=$("#publicFund").val();
		check(text);
	}
	
	function a4(){
		var text=$("#companyPension").val();
		check(text);
	}
	
	function a5(){
		var text=$("#retiredPay").val();
		check(text);
	}
	
	function check(text){
		if(text==""){
			alert("不可为空值");
			return;
		}
		if((text%1)!=0){
			alert("请输入整数！");
			return;
		}
		/* 输入检查成功之后才应当提交 */
		cal();/*老版本代码中的公式，现在暂时不与使用 */
	}
	
	function cal(){
		/*
			这里的任务包括：
			1，更新新增员工数字
			2，更新员工总人数数字
			3，更新招聘成本数字，
			4，更新离职成本数字
			5，更新总成本数字
			
			这里假设：
			不同岗位之间的销售可以直接转岗，而无需重新雇佣
			这意味着招聘成本和离职成本两项中必有一项为0
		*/
		var salary=parseInt($("#hidden_aftersale").val());
		var welfare=parseInt($("#hidden_ecnomical").val());
		
		var totalCost=0;
		
		totalCost=Math.ceil(salary+welfare+publicFund+companyPension+holiday);
		document.getElementById("toutalpopu").innerText=totalCost+"人";
	}
	
</script>
</html>