<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
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
#notice2{
        	min-height:420px;
        }
        
        .course_content{
        	border:1px solid #0ff;
        	margin-top:10px;
        	padding:20px;
        	min-height:220px;
        	font-size:14px;
        	letter-spacing:1px;
        }
        .left{
        	/* border:1px solid blue; */
        	width:450px;
        	padding:20px;
        	float:left;
        	background:#eee;
        	margin:10px 0 0 2px;
        	min-height:220px;
        }
        .left_title span i{
        	color:#009;
        	font-size:18px;
        	margin-right:6px;
        }
        
        
        .right{
        	/* border:1px solid blue; */
        	width:400px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 60px;
        	min-height:120px;
        }
        
        .right_title span i{
        	color:red;
        	font-size:22px;
        	margin-right:6px;
        }
        .text0{
        	margin-left:30px;
        	margin-top:10px;
        }
        .text1{
		text-indent:30px;
		margin-top:10px;
	}
	.text1_ul{
		margin-left:40px;
		margin-top:10px;
	}
	.text2{
        	margin-left:30px;
        	margin-top:25px;
        }
	.left_content li{
		margin-top:5px;
	}
	.tab-content{
		margin-top:10px;
	}
</style>
<script type="text/javascript">
	function init() {
		var cunkuanLast=$("#cunkuanLast").val();
		var tiqu=$("#tiqu").val();
		var cunru=$("#cunru").val();
		if(tiqu==""){
			$("#tiqu").val("0");
		}
		if(cunru==""){
			$("#cunru").val("0");
		}
		
		
		if(cunkuanLast-tiqu<0){
			alert("提取金额不得大于上季度存款总额!");
			$("#tiqu").val("0");
			return;
		}
		
		var quarter = $("#quarter").val();
       	var resMoney=$("#resMoney").val();
       	if(resMoney-cunru<0){
           	alert("可用资金不足！")
           	document.getElementById("form").action = "showFixedDeposite.do?quarter="
               + quarter
           	document.getElementById("form").submit();
           }else{
           	alert("提交成功")
           	document.getElementById("form").action = "showFixedDeposite1.do"
               document.getElementById("form").submit();
           }
		
		
	}
</script>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">定期存款</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li  class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade" id="notice1">
                	<div class="course_content">
						<div class="text1">
							商业银行及其它金融机构提供三个月的定期存款，季度利率为 1.5%。
						</div>
						<div class="text1">
							您可以在下季度初提取当前季度的存款。
						</div>
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			请在相应决策界面的空白处填入当前季度所需存入的定期存款。 请务必查看您在下季度初所能获得的利息收入。
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade  in active" id="notice2">
                	<form id="form" class="form-horizontal" action="">
						<table class="table table-bordered">
							
							<tbody>
							
								<tr>
									<td>上季度的三个月定期存款总额</td>
									<input type="hidden" id="quarter" name="quarter" value="${quarter }"/>
									<input type="hidden" id="cunkuanLast" value="${fixedDepositeList[0].cunkuanLast }"/>
									<input type="hidden" id="currentQuarter" value="${currentQuarter }">
									<input type="hidden" id="resMoney" value="${resMoney }">
									<input type="hidden" id="isSubmit" value="${isSubmit }">
									<c:choose>
										<c:when test="${fixedDepositeList[0].cunkuanLast !=null }">
											<td>${fixedDepositeList[0].cunkuanLast }</td>
										</c:when>
										<c:otherwise>
											<td>0</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>你需要从该定期存款中提取多少资金？</td>
									<td><input class="form-control" type="text" name="tiQu" id="tiqu" value="${fixedDepositeList[0].tiqu}"
		                			onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8"></td>
								</tr>
								<tr>
									<td>你需要向该定期存款中存入多少资金？</td>
									<td><input class="form-control" type="text" name="cunRu" id="cunru" value="${fixedDepositeList[0].cunru}"
		                			onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="8"></td>
								</tr>
								<tr>
									<td>当前季度的三个月定期存款额</td>
									
									<c:choose>
										<c:when test="${fixedDepositeList[0].cunru!=null}">
											<td><fmt:formatNumber type="number" value="${fixedDepositeList[0].cunkuanLast-fixedDepositeList[0].tiqu+fixedDepositeList[0].cunru}" pattern="#.00"/></td>
										</c:when>
										<c:otherwise>
											<td>0</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>季度利率</td>
									<td>1.5</td>
								</tr>
								<tr>
									<td>预计利息收入</td>
									<td><fmt:formatNumber type="number" value="${(fixedDepositeList[0].cunkuanLast-fixedDepositeList[0].tiqu+fixedDepositeList[0].cunru)*0.015}" pattern="#.00"/></td>
								</tr>
								<tr>
									<td colspan="5"><button onclick="init()" type="button"
											class="btn btn-info">提交</button></td>
								</tr>
							</tbody>
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
<script type="text/javascript">
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
</html>