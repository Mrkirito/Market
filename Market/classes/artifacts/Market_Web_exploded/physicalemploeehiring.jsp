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
		document.getElementById("form").action = "pysicalsalersavedata.do";
		document.getElementById("form").submit();
	}
	
	function init(){
		var salary=parseInt($("#hidden_aftersale").val());
		var welfare=parseInt($("#hidden_saller").val());
		var len=$("#len").val();
		if(len!=0){
			$("#aftersale").attr("value",salary);
			$("#saller").attr("value",welfare);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare);
			document.getElementById("toutalpopu").innerText=totalCost+"人";
			document.getElementById("lastseasontoutalpopu").innerText=totalCost+"人";

			//更新员工工资
			sal=Math.ceil(totalCost*40000);
			document.getElementById("salary").innerText=sal+"元";
			//更新支出总计
			document.getElementById("costsum").innerText=sal+"元";
		}
		/*---1---*/
		var salary=parseInt($("#hidden_aftersale1").val());
		var welfare=parseInt($("#hidden_saller1").val());
		if(len!=0){
			$("#aftersale1").attr("value",salary);
			$("#saller1").attr("value",welfare);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare);
			document.getElementById("toutalpopu1").innerText=totalCost+"人";
			document.getElementById("lastseasontoutalpopu1").innerText=totalCost+"人";

			//更新员工工资
			sal=Math.ceil(totalCost*40000);
			document.getElementById("salary1").innerText=sal+"元";
			//更新支出总计
			document.getElementById("costsum1").innerText=sal+"元";
		}
		/*---2---*/
		var salary=parseInt($("#hidden_aftersale2").val());
		var welfare=parseInt($("#hidden_saller2").val());
		if(len!=0){
			$("#aftersale2").attr("value",salary);
			$("#saller2").attr("value",welfare);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare);
			document.getElementById("toutalpopu2").innerText=totalCost+"人";
			document.getElementById("lastseasontoutalpopu2").innerText=totalCost+"人";

			//更新员工工资
			sal=Math.ceil(totalCost*40000);
			document.getElementById("salary2").innerText=sal+"元";
			//更新支出总计
			document.getElementById("costsum2").innerText=sal+"元";
		}
		/*---3---*/
		var salary=parseInt($("#hidden_aftersale3").val());
		var welfare=parseInt($("#hidden_saller3").val());
		if(len!=0){
			$("#aftersale3").attr("value",salary);
			$("#saller3").attr("value",welfare);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare);
			document.getElementById("toutalpopu3").innerText=totalCost+"人";
			document.getElementById("lastseasontoutalpopu3").innerText=totalCost+"人";

			//更新员工工资
			sal=Math.ceil(totalCost*40000);
			document.getElementById("salary3").innerText=sal+"元";
			//更新支出总计
			document.getElementById("costsum3").innerText=sal+"元";
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
						<div class="text1">本页面假设：<p><p>招聘成本100元/人<p>离职补偿150元/人<p>员工工资固定40K</div>
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
									<td>
										<!-- 这里的hidden标签只是保留标签，实质并未使用 -->
										<th >印度事业部</th>
										<th >俄罗斯事业部</th>
										<th >大中华事业部</th>
										<th >新加坡事业部</th>
									</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>售后</td>
									<td><input id="aftersale" class="form-control" type="text" name="aftersale" value="0" onchange="aa()">
									<input type="hidden" value="${salesSalaryList[0].aftersale}" id="hidden_aftersale"/>人</td>
								
									<td><input id="aftersale1" class="form-control" type="text" name="aftersale" value="0" onchange="aa1()">
									<input type="hidden" value="${salesSalaryList[1].aftersale}" id="hidden_aftersale1"/>人</td>
								
									<td><input id="aftersale2" class="form-control" type="text" name="aftersale" value="0" onchange="aa2()">
									<input type="hidden" value="${salesSalaryList[2].aftersale}" id="hidden_aftersale2"/>人</td>
								
									<td><input id="aftersale3" class="form-control" type="text" name="aftersale" value="0" onchange="aa3()">
									<input type="hidden" value="${salesSalaryList[3].aftersale}" id="hidden_aftersale3"/>人</td>
								</tr>
								<tr>
									<td>销售</td>
									<td><input id="saller" class="form-control" type="text"
										name="saller" value="0" onchange="a1()">
										<input type="hidden" value="${salesSalaryList[0].saller}" id="hidden_saller"/>人</td>
								
									<td><input id="saller1" class="form-control" type="text"
										name="saller" value="0" onchange="a11()">
										<input type="hidden" value="${salesSalaryList[1].saller}" id="hidden_saller1"/>人</td>
								
									<td><input id="saller2" class="form-control" type="text"
										name="saller" value="0" onchange="a12()">
										<input type="hidden" value="${salesSalaryList[2].saller}" id="hidden_saller2"/>人</td>
								
									<td><input id="saller3" class="form-control" type="text"
										name="saller" value="0" onchange="a13()">
										<input type="hidden" value="${salesSalaryList[3].saller}" id="hidden_saller3"/>人</td>
								</tr>
								<tr >
									<td>当前销售人员总数</td>
									<td><label name="toutalpopu" id="toutalpopu">0人</label></td>
								
									<td><label name="toutalpopu" id="toutalpopu1">0人</label></td>
								
									<td><label name="toutalpopu" id="toutalpopu2">0人</label></td>
								
									<td><label name="toutalpopu" id="toutalpopu3">0人</label></td>
								</tr>
								<tr>
									<td>新增销售人员数</td>
									<td><label name="freshpopu" id="freshpopu">0人</label></td>
								
									<td><label name="freshpopu" id="freshpopu1">0人</label></td>
								
									<td><label name="freshpopu" id="freshpopu2">0人</label></td>
								
									<td><label name="freshpopu" id="freshpopu3">0人</label></td>
								</tr>
								<tr>
									<td>上季度销售人员总数</td>
									<td><label name="lastseasontoutalpopu" id="lastseasontoutalpopu">0人</label></td>
								
									<td><label name="lastseasontoutalpopu" id="lastseasontoutalpopu1">0人</label></td>
								
									<td><label name="lastseasontoutalpopu" id="lastseasontoutalpopu2">0人</label></td>
								
									<td><label name="lastseasontoutalpopu" id="lastseasontoutalpopu3">0人</label></td>
								</tr>
								<tr>
									<td>招聘成本</td>
									<td><label name="hiringcost" id="hiringcost">0元</label></td>
								
									<td><label name="hiringcost" id="hiringcost1">0元</label></td>
								
									<td><label name="hiringcost" id="hiringcost2">0元</label></td>
								
									<td><label name="hiringcost" id="hiringcost3">0元</label></td>
								</tr>
								<tr>
									<td>离职补偿</td>
									<td><label name="dismisscost" id="dismisscost">0元</label></td>
								
									<td><label name="dismisscost" id="dismisscost1">0元</label></td>
								
									<td><label name="dismisscost" id="dismisscost2">0元</label></td>
								
									<td><label name="dismisscost" id="dismisscost3">0元</label></td>
								</tr>
								<tr>
									<td>员工工资</td>
									<td><label name="salary" id="salary">0元</label></td>
								
									<td><label name="salary" id="salary1">0元</label></td>
								
									<td><label name="salary" id="salary2">0元</label></td>
								
									<td><label name="salary" id="salary3">0元</label></td>
								</tr>
								<tr>
									<td>支出总计</td>
									<td><label name="costsum" id="costsum">0元</label></td>
								
									<td><label name="costsum" id="costsum1">0元</label></td>
								
									<td><label name="costsum" id="costsum2">0元</label></td>
								
									<td><label name="costsum" id="costsum3">0元</label></td>
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
	var text=$("#aftersale").val();
	check(text);
}
function aa1(){
	//判断整数
	var text=$("#aftersale1").val();
	check1(text);
}
function aa2(){
	//判断整数
	var text=$("#aftersale2").val();
	check2(text);
}
function aa3(){
	//判断整数
	var text=$("#aftersale3").val();
	check3(text);
}

function a1(){
	var text=$("#saller").val();
	check(text);
}
function a11(){
	var text=$("#saller1").val();
	check1(text);
}
function a12(){
	var text=$("#saller2").val();
	check2(text);
}
function a13(){
	var text=$("#saller3").val();
	check3(text);
}
	
function check(text){
	if(text==""){
		alert("不可为空值");
		return;
	}
	if((text%1)!=0){
		alert("请输入整数！"+text);
		return;
	}
	/* 输入检查成功之后才应当提交 */
	cal();/*老版本代码中的公式，现在暂时不与使用 */
}

function check1(text){
	if(text==""){
		alert("不可为空值");
		return;
	}
	if((text%1)!=0){
		alert("请输入整数！"+text);
		return;
	}
	/* 输入检查成功之后才应当提交 */
	cal1();/*老版本代码中的公式，现在暂时不与使用 */
}

function check2(text){
	if(text==""){
		alert("不可为空值");
		return;
	}
	if((text%1)!=0){
		alert("请输入整数！"+text);
		return;
	}
	/* 输入检查成功之后才应当提交 */
	cal2();/*老版本代码中的公式，现在暂时不与使用 */
}

function check3(text){
	if(text==""){
		alert("不可为空值");
		return;
	}
	if((text%1)!=0){
		alert("请输入整数！"+text);
		return;
	}
	/* 输入检查成功之后才应当提交 */
	cal3();/*老版本代码中的公式，现在暂时不与使用 */
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
		var salary=parseInt($("#aftersale").val());
		var welfare=parseInt($("#saller").val());
		
		var totalCost=0;
		
		//更新当前销售人员总数
		totalCost=Math.ceil(salary+welfare);
		document.getElementById("toutalpopu").innerText=totalCost+"人";

		//更新新增人数
		var hidden_salary=parseInt($("#hidden_aftersale").val());
		var hidden_welfare=parseInt($("#hidden_saller").val());
		var additional = Math.ceil(totalCost-(hidden_salary+hidden_welfare));
		document.getElementById("freshpopu").innerText=additional+"人";
		 
		//更新招聘成本和离职补偿
		if(additional>0){//人数增加
			additional = Math.ceil(additional*100);
			document.getElementById("hiringcost").innerText=additional+"元";
			document.getElementById("dismisscost").innerText=0+"元";
		}else{
			additional = Math.ceil(-additional*120);
			document.getElementById("hiringcost").innerText=0+"元";
			document.getElementById("dismisscost").innerText=additional+"元";
		}
		
		//更新员工工资
		sal=Math.ceil(totalCost*40000);
		document.getElementById("salary").innerText=sal+"元";
		
		//更新支出总计
		Moneyout = Math.ceil(sal+additional);
		document.getElementById("costsum").innerText=Moneyout+"元";
	}
	
	function cal1(){
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
		var salary=parseInt($("#aftersale1").val());
		var welfare=parseInt($("#saller1").val());
		
		var totalCost=0;
		
		//更新当前销售人员总数
		totalCost=Math.ceil(salary+welfare);
		document.getElementById("toutalpopu1").innerText=totalCost+"人";

		//更新新增人数
		var hidden_salary=parseInt($("#hidden_aftersale1").val());
		var hidden_welfare=parseInt($("#hidden_saller1").val());
		var additional = Math.ceil(totalCost-(hidden_salary+hidden_welfare));
		document.getElementById("freshpopu1").innerText=additional+"人";
		 
		//更新招聘成本和离职补偿
		if(additional>0){//人数增加
			additional = Math.ceil(additional*100);
			document.getElementById("hiringcost1").innerText=additional+"元";
			document.getElementById("dismisscost1").innerText=0+"元";
		}else{
			additional = Math.ceil(-additional*120);
			document.getElementById("hiringcost1").innerText=0+"元";
			document.getElementById("dismisscost1").innerText=additional+"元";
		}
		
		//更新员工工资
		sal=Math.ceil(totalCost*40000);
		document.getElementById("salary1").innerText=sal+"元";
		
		//更新支出总计
		Moneyout = Math.ceil(sal+additional);
		document.getElementById("costsum1").innerText=Moneyout+"元";
	}
	
	function cal2(){
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
		var salary=parseInt($("#aftersale2").val());
		var welfare=parseInt($("#saller2").val());
		
		var totalCost=0;
		
		//更新当前销售人员总数
		totalCost=Math.ceil(salary+welfare);
		document.getElementById("toutalpopu2").innerText=totalCost+"人";

		//更新新增人数
		var hidden_salary=parseInt($("#hidden_aftersale2").val());
		var hidden_welfare=parseInt($("#hidden_saller2").val());
		var additional = Math.ceil(totalCost-(hidden_salary+hidden_welfare));
		document.getElementById("freshpopu2").innerText=additional+"人";

		//更新招聘成本和离职补偿
		if(additional>0){//人数增加
			additional = Math.ceil(additional*100);
			document.getElementById("hiringcost2").innerText=additional+"元";
			document.getElementById("dismisscost2").innerText=0+"元";
		}else{
			additional = Math.ceil(-additional*120);
			document.getElementById("hiringcost2").innerText=0+"元";
			document.getElementById("dismisscost2").innerText=additional+"元";
		}
		
		//更新员工工资
		sal=Math.ceil(totalCost*40000);
		document.getElementById("salary2").innerText=sal+"元";
		
		//更新支出总计
		Moneyout = Math.ceil(sal+additional);
		document.getElementById("costsum2").innerText=Moneyout+"元";
	}
	
	function cal3(){
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
		var salary=parseInt($("#aftersale3").val());
		var welfare=parseInt($("#saller3").val());
		
		var totalCost=0;
		
		//更新当前销售人员总数
		totalCost=Math.ceil(salary+welfare);
		document.getElementById("toutalpopu3").innerText=totalCost+"人";

		//更新新增人数
		var hidden_salary=parseInt($("#hidden_aftersale3").val());
		var hidden_welfare=parseInt($("#hidden_saller3").val());
		var additional = Math.ceil(totalCost-(hidden_salary+hidden_welfare));
		document.getElementById("freshpopu3").innerText=additional+"人";

		//更新招聘成本和离职补偿
		if(additional>0){//人数增加
			additional = Math.ceil(additional*100);
			document.getElementById("hiringcost3").innerText=additional+"元";
			document.getElementById("dismisscost3").innerText=0+"元";
		}else{
			additional = Math.ceil(-additional*120);
			document.getElementById("hiringcost3").innerText=0+"元";
			document.getElementById("dismisscost3").innerText=additional+"元";
		}
		
		//更新员工工资
		sal=Math.ceil(totalCost*40000);
		document.getElementById("salary3").innerText=sal+"元";
		
		//更新支出总计
		Moneyout = Math.ceil(sal+additional);
		document.getElementById("costsum3").innerText=Moneyout+"元";
	}
	
</script>
</html>