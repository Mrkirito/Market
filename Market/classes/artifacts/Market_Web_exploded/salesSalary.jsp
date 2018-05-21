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
</style>

</head>
<body onLoad="init()">
	<div class="panel panel-info">
		<div class="panel-heading">销售人员薪酬</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">
					<div class="course_content">
						<div class="text1">
							每个季度，调研公司都会对行业内的所有员工进行问卷调查，以了解其最希望获得改善的薪酬组合方面。</div>
						<div class="text1">在最初的几个季度内，他们最希望公司能提高其底薪。
							随着工资的提高，他们的注意力将转移到其它方面，比如福利、休假、公积金、企业年金及退休金。</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">您必须决定销售人员的工资及福利。</div>
					</div>

				</div>
				<div class="tab-pane fade in active" id="notice2">
					<input type="hidden" id="quarter" value="${quarter }"> <input
						type="hidden" id="currentQuarter" value="${currentQuarter }">
					<input type="hidden" id="isSubmit" value="${isSubmit }">
					<form class="form-horizontal" method="post" id="form"
						name="addSalesSalaryForm" action="">
						<table class="table table-bordered">
							<thead>
								<tr class="success">
									<input type="hidden" value="${len }" id="len" />
									<th colspan="2">年度薪酬组合计划</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>工资（年薪）</td>
									<td><input id="salary" class="form-control" type="text"
										name="salary" value="0" onchange="myfunction()"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="8" /> <input type="hidden"
										value="${salesSalaryList[0].salary}" id="hidden_salary" />元</td>
								</tr>
								<tr>
									<td>福利</td>
									<td><input id="welfare" class="form-control" type="text"
										name="welfare" value="0" onchange="myfunction()" maxlength="2"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="2" /> <input type="hidden"
										value="${salesSalaryList[0].welfare}" id="hidden_welfare" />%</td>
								</tr>
								<tr>
									<td>休假</td>
									<td><input id="holiday" class="form-control" type="text"
										name="holiday" value="0" onchange="myfunction()" maxlength="2"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="2" /> <input type="hidden"
										value="${salesSalaryList[0].holiday}" id="hidden_holiday" />天</td>
								</tr>
								<tr>
									<td>公积金</td>
									<td><input id="publicFund" class="form-control"
										type="text" name="publicFund" value="0"
										onchange="myfunction()" maxlength="2"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="2" /> <input type="hidden"
										value="${salesSalaryList[0].publicFund}"
										id="hidden_publicFund" />%</td>
								</tr>
								<tr>
									<td>企业年金</td>
									<td><input id="companyPension" class="form-control"
										type="text" name="companyPension" value="0"
										onchange="myfunction()" maxlength="2"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="2" /> <input type="hidden"
										value="${salesSalaryList[0].companyPension}"
										id="hidden_companyPension" />%</td>
								</tr>
								<tr>
									<td>退休金</td>
									<td><input id="retiredPay" class="form-control"
										type="text" name="retiredPay" value="0"
										onchange="myfunction()" maxlength="2"
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										maxlength="2" /> <input type="hidden"
										value="${salesSalaryList[0].retiredPay}"
										id="hidden_retiredPay" />%</td>
								</tr>
								<tr>
									<td>年度薪酬组合总额</td>
									<td><label name="TotalCost" id="TotalCost">0元</label></td>
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
function myfunction(){
	var salary=parseInt($("#salary").val());
	var welfare=parseInt($("#welfare").val());
	var holiday=parseInt($("#holiday").val());
	var publicFund=parseInt($("#publicFund").val());
	var companyPension=parseInt($("#companyPension").val());
	var retiredPay=parseInt($("#retiredPay").val());
	

	
	totalCost=Math.ceil(salary+welfare/100*salary+publicFund/100*salary+companyPension/100*salary+retiredPay/100*salary+holiday/365*salary);

	
	
	
	
	

var $total=$("label[name='TotalCost']");
$total[0].innerText = totalCost;
	
	
}

















	/* function aa(){
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
	 */
	//function check(text){
	//	if(text==""){
	//		alert("不可为空值");
	//		return;
		//}
	//	if((text%1)!=0){
	//		alert("请输入整数！");
	//		return;
	//	}else{
	//		if(text>10||text<0){
	//			alert("请输入1到10的整数！")
	//			return;
	//		}
	//	}
		cal();
	
	
	function cal(){
		var salary=parseInt($("#salary").val());
		var welfare=parseInt($("#welfare").val());
		var holiday=parseInt($("#holiday").val());
		var publicFund=parseInt($("#publicFund").val());
		var companyPension=parseInt($("#companyPension").val());
		var retiredPay=parseInt($("#retiredPay").val());
		
		var totalCost=0;
		
		totalCost=Math.ceil(salary+welfare/100*salary+publicFund/100*salary+companyPension/100*salary+retiredPay/100*salary+holiday/365*salary);
		document.getElementById("TotalCost").innerText=totalCost+"元";
	}
</script>
<script type="text/javascript">
	var quarter=$("#quarter").val();
	function sign() {
		//判断整数
		var text=$(".form-control")
		var flag=0
		for(var i=0;i<text.length;i++){
			if((text[i].value%1)!=0){
				alert("请输入整数！");
				text[i].value=1;
				return;
			}else if(text[i].value==""){
				alert("不可为空！");
				
				text[i].value=10;
				return;
			}
		}
		alert("提交成功");
		document.getElementById("form").action = "updateSalesSalary2.do?quarter="+quarter;
		document.getElementById("form").submit();
	}
	
	function init(){
		var salary=parseInt($("#hidden_salary").val());
		var welfare=parseInt($("#hidden_welfare").val());
		var holiday=parseInt($("#hidden_holiday").val());
		var publicFund=parseInt($("#hidden_publicFund").val());
		var companyPension=parseInt($("#hidden_companyPension").val());
		var retiredPay=parseInt($("#hidden_retiredPay").val());
		var len=$("#len").val();
		if(len!=0){
			$("#salary").attr("value",salary);
			$("#welfare").attr("value",welfare);
			$("#holiday").attr("value",holiday);
			$("#publicFund").attr("value",publicFund);
			$("#companyPension").attr("value",companyPension);
			$("#retiredPay").attr("value",retiredPay);
			
			var totalCost=0;
			totalCost=Math.ceil(salary+welfare/100*salary+publicFund/100*salary+companyPension/100*salary+retiredPay/100*salary+holiday/365*salary);
			document.getElementById("TotalCost").innerText=totalCost+"元";
		}

	}
	$(function(){
		var currentQuarter=$("#currentQuarter").val();
		var quarter=$("#quarter").val();
        var isSubmit=$("#isSubmit").val();
		if(currentQuarter>quarter){
			$("button").attr("disabled",true);
		}else{
            if(isSubmit==1){
                $("button").attr("disabled",true);
            }
		}
	});
</script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
</html>