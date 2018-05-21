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
	width: 600px;
	height: 250px;
	background: white;
}

td, th {
	text-align: center;
}

.kaozuo {
	text-align: left;
}

#two {
	width: 500px;
	height: 5px;
}

#three {
	width: 500px;
	height: 80px;
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

#button {
	float: right;
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

.form-control {
	vertical-align: middle;
	float: left;
	size: 40px;
	width: 200px;
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

#renGong2 {
	border: none;
	text-align: center;
	background: none;
}

#renGong1 {
	border: none;
	text-align: center;
	background: none;
}
#guDing1 {
	border: none;
	text-align: center;
	background: none;
}
#guDing2 {
	border: none;
	text-align: center;
	background: none;
}
.tab-content {
	margin-top: 10px;
}
</style>

</head>
<script type="text/javascript">

</script>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">运行产能</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
				<li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
				<li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade" id="notice1">
					<div class="course_content">
						<div class="text1">您才刚开始经营公司。 所以，需求量不可能非常高，而您也不需要运行全部的工厂产能。
							您要决定需要使用多少产能。 这称之为工厂的运行产能。 运行产能决定了每天生产的产品数量。 您可以每天生产 8 小时，每周生产 5
							天，每季度生产 13 周（65个工作日）。</div>
						<div class="text1">实践准则 日运行产能 = 预计总需求量/ 65天 X（1 + 安全库存比例）
							X（1 + 由于工人生产率而损失的产能比率）</div>
					</div>
					<div class="left">
						<div class="left_title">
							<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
						</div>
						<div class="left_content">预测工人的生产率。 您必须决定工厂的日产量。 工厂日产量乘以 65
							就得出了当前季度的总产量。 注意，运行产能不能超过固定产能。
							如果您在当前季度进行了产能投资，新产能将在一个季度以后到位，因此您只能在下个季度提高运行产能。
							要决定日运行产能，将预测总需求量除以 65 天。 在您预测的基础上增加 10%
							以作为安全库存，再加上由于工厂工人生产率而损失的产能比率。 得出的数字即为您应当设定的当前季度日运行产能。
							如需了解更多详情，请参见帮助文档的运行产能一节。</div>
					</div>
				</div>
				<div class="tab-pane fade in active" id="notice2">
					<input type="hidden" id="quarter" value="${quarter }"> <input
						type="hidden" id="currentQuarter" value="${currentQuarter }">
					<input type="hidden" id="isSubmit" value="${isSubmit }">
					<form class="form-horizontal" id="form" name="form" action=""
						method="post">
						<table class="table table-bordered">
							<thead>
								<tr class="success">
									<th colspan="">运行产能</th>
									<th colspan="">件/天</th>
									<th colspan="">件/季度</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="kaozuo">上季度固定产能</td>
									<td>${lastCompanyCapacityList[0].capacityNow+lastCompanyCapacityList[0].capacityAdd}</td>
									<td>${lastCompanyCapacityList[0].capacityNow*65+lastCompanyCapacityList[0].capacityAdd*65}</td>
								</tr>
								<tr>
									<td class="kaozuo">上季度运行产能</td>
									<c:if test="${not empty lastOperationCapacityList }">
										<td>${lastOperationCapacityList[0].operateCapacity}</td>
										<td>${lastOperationCapacityList[0].operateCapacity*65}</td>
									</c:if>
									<c:if test="${empty lastOperationCapacityList }">
										<td>0</td>
										<td>0</td>
									</c:if>
								</tr>
								<tr>
									<td class="kaozuo">固定产能</td>
									<td>${companyCapacityList1[0].capacityNow+companyCapacityList1[0].capacityAdd}</td>
									<input type="hidden" id="oneValue" name="oneValue"
										value="${companyCapacityList1[0].capacityNow+companyCapacityList1[0].capacityAdd}"
										/>
									<td>${companyCapacityList1[0].capacityNow*65+companyCapacityList1[0].capacityAdd*65}</td>
								</tr>
								<tr>
									<td class="kaozuo">运行产能</td>
									<td><input
										onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
										class="form-control" type="text" name="operateCapacity"
										maxlength="4" id="twoValue" onblur="onblurs()" onchange="myfunction()"
										value="${operationCapacityList[0].operateCapacity}"></td>
									<td><span name="jidu" id="quarterNum">${operationCapacityList[0].operateCapacity*65}</span></td>
								</tr>
								<tr>
									<td class="kaozuo">预测有效运行产能</td>
									<td><span name ="day"></span><fmt:formatNumber type="number"
											value="${operationCapacityList[0].operateCapacity*operationCapacityList[0].workerProductivity/100}"
											pattern="0" maxFractionDigits="0" /></span></td>
									<td><fmt:formatNumber type="number"
											value="${operationCapacityList[0].operateCapacity*operationCapacityList[0].workerProductivity/100*65}"
											pattern="0" maxFractionDigits="0" /></td>
								</tr>
							</tbody>
						</table>
						<table class="table table-bordered" id="two">
							<tr>
								<td class="kaozuo" colspan="">预测工人生产率</td>
								<td colspan=""><input
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									class="form-control" type="text" name="workerProductivity"
									maxlength="2" onchange="myfunction1()" id ="productivity"
									value="${operationCapacityList[0].workerProductivity}">%</td>
							</tr>
						</table>

						<table class="table table-bordered" id="three">
							<tr>
								<td class="kaozuo">人工成本(单位运行产能)</td>
								<c:if test="${operationCapacityList[0].operateCapacity==null}">
									<td><span id="rG">*</span></td>
								</c:if>
								<c:if test="${operationCapacityList[0].operateCapacity<=5}">
									<td><input disabled="disabled" id="renGong1" name="renGong1" value="" /></td>
								</c:if>
								<c:if test="${operationCapacityList[0].operateCapacity>5}">
									<td><input disabled="disabled" id="renGong2" name="renGong2" value="" /></td>
								</c:if>
							</tr>
							<tr>
								<td class="kaozuo">固定成本(单位运行产能)</td>
								<c:if test="${operationCapacityList[0].operateCapacity==null}">
									<td>*</td>
								</c:if>
								<c:if
									test="${operationCapacityList[0].operateCapacity!=null&&operationCapacityList[0].operateCapacity<=5}">
									<td>
									<input disabled="disabled" id="guDing1" value="" /></td>
								</c:if>
								<c:if
									test="${operationCapacityList[0].operateCapacity!=null&&operationCapacityList[0].operateCapacity>5}">
									<td><input disabled="disabled" id="guDing2" value="" /></td>
								</c:if>
							</tr>
							<tr>
								<td class="kaozuo">调整成本(总运行产能)</td>
								<c:if test="${operationCapacityList[0].operateCapacity==null}">
									<td>*</td>
								</c:if>
								<c:if test="${operationCapacityList[0].operateCapacity!=null}">
									<td><fmt:formatNumber type="number"
											value="${operationCapacityList[0].operateCapacity*workersSalaryList[0].salaryTotal/(companyCapacityList1[0].capacityNow+companyCapacityList1[0].capacityAdd+1)}"
											pattern="0" maxFractionDigits="0" /></td>
								</c:if>
							</tr>
						</table>
						<button class="btn btn-info" type="button" id="button"
							onclick="sign()">提交</button>
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
<script type="text/javascript">
function   myfunction(){
	   
	   var twoValue = $("#twoValue").val();
	   var productivity = $("#productivity").val();
	   if(twoValue==''){
		  var  jidu=0;
	   }else{
	   var jidu = parseInt(twoValue)*65;
	   }
	   var $total=$("span[name='jidu']");
	   $total[0].innerText = jidu;
	   if(twoValue==''||productivity==''){
			  var  day=0;
		   }else{
		   var day = parseInt(twoValue)*parseInt(productivity);
		   }
		   var $total=$("span[name='day']");
		   $total[1].innerText = day;
		   
		  
}	   


   function init(){
	   //var text=$(".form-control")
	   var twoValue=$("#twoValue").val();
	
	   var Value1 = Math.round(1500/Math.log(1+twoValue));
	   var Value2 = Math.round(1500/Math.log(7)-twoValue);
	   var Value3 = Math.round(1200/Math.log(1+twoValue));
	   var Value4 = Math.round(1200/Math.log(7)-twoValue);
		$("#renGong1").val(Value1);
		$("#renGong2").val(Value2);
		$("#guDing1").val(Value3);
		$("#guDing2").val(Value4);
   }
   init();
  
	function sign() {
		
		//判断整数
		var text=$(".form-control")
		var oneValue=$("#oneValue").val()
		var twoValue=$("#twoValue").val()
		
		
		
	var flag=0
	for(var i=0;i<text.length;i++){
		if((text[i].value%1)!=0){
			alert("请输入整数！");
			text[i].value=1;
			return;
		}
		}
		for(var i=0;i<text.length;i++){
			if(text[i].value==""||text[i].value==0){
				alert("不可为空或0！");
				text[i].value="";
				return;
			}
		}
		if(twoValue-oneValue>0){
			alert("运行产能不得大于固定产能")
			$("#twoValue").val("")
			$("#quarterNum").text("")
			return
		}
		   
	     
		alert("提交成功");
		
	 
		var quarter = $("#quarter").val();
		document.getElementById("form").action = "showOperationCapacity1.do?quarter="
				+ quarter;
		document.getElementById("form").submit();

	}

	
	//$(function() {
	
	//	var x = document.getElementById("renGong1").value ;
	//	var renGong1 = Math.round(x);
	//	$("#rG1").html(renGong1);
	//	var x = document.getElementById("renGong2").value ;
	//	var renGong2 = Math.round(x);
	//	$("#rG2").html(renGong2);
	//})
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