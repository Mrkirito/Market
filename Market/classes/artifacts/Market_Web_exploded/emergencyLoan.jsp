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
	function init(){
		var extra=$("#extra_get").text().replace(/,/g, "");//额外借入
		var totalDebt=$("#totalDebt").text().replace(/,/g, "");//债务能力
		var get=$("#get").val().replace(/,/g, "");//借入
		var payBack=$("#return").val().replace(/,/g, "");//偿还
		var resMoney=$("#resMoney").val().replace(/,/g, "");//可用资金
		if(get==""){
			get=0
			$("#get").val("0"); 
		}
		if(payBack==""){
			payBack=0
			$("#return").val("0"); 
		} 
	    if((get%1)==0 && (payBack%1)==0 && get>=0 && payBack>=0){
	    	if((extra-get)<0&&get!=0){
	    		 alert("借入额度不够，请及时修改决策！");
	 	        $("#get").val("0"); 
	 	        return;
	    	}else{
	    		if((resMoney-payBack)<0&&payBack!=0){
	    			alert("请输入有效偿还资金！");
	    			$("#return").val("0"); 
		 	        return;
	    		}else{
    				alert("提交成功");
		    		$("#form").submit();
	    			
	    		}
	    	}
	    }else{  
	        alert("请输入有效数字！");  
	        return;
	    }  
	}
</script>
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">紧急银行贷款</div>
		<div class="panel-body">
			<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li  class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade" id="notice1">
                	<div class="course_content">
						<div class="text1">
							您的公司已经营运三个季度了，银行现在愿意为您提供短期贷款。
							如果有积极的扩张计划，您可能需求从银行贷取 2 百万资金。 这将保证，在市场需求量低于预期的时候，您不会出现资金短缺并被迫借入紧急贷款。
							您可以随时向银行借取低于一定信用额度的贷款。 信用额度等于公司股本的 0.8 倍。
						</div>
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			信用额度 = 0.8 X 公司股本 
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade  in active" id="notice2">
					<input type="hidden" id="quarter" value="${quarter }">
					<input type="hidden" id="currentQuarter" value="${currentQuarter }">
					<input type="hidden" id="isSubmit" value="${isSubmit }">
                	<form id="form" class="form-horizontal" action="showEmergencyLoan1.do">
						<table class="table table-bordered">
							
							<tbody>
								<tr>
									<td>上季度债务总额</td>
									<c:choose>
										<c:when test="${companyLoan!=null}">
											<td id="totalDebtLast"><fmt:formatNumber type="number" value="${companyLoan.loanLast}" /></td>
										</c:when>
										<c:otherwise>
											<td id="totalDebtLast">0</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<input type="hidden" name="quarter" value="${quarter }"/>
									<input type="hidden" id="resMoney" name="resMoney" value="${resMoney }"/>
									<td>债务能力（0.8 X 普通股股本）</td>
									<td><fmt:formatNumber type="number" value="${totalDebt}" /></td>
								</tr>
								<tr>
									<td>可用资金</td>
									<td><fmt:formatNumber type="number" value="${resMoney}" /></td>
								</tr>
								
								
								<tr>
									<td>迄今债务总额</td>
									<c:choose>
										<c:when test="${companyLoan!=null}">
											<td id="totalDebt"><fmt:formatNumber type="number" value="${companyLoan.loanLast+companyLoan.getNum-companyLoan.returnNum}" /></td>
										</c:when>
										<c:otherwise>
											<td id="totalDebt">0</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>您总计还可以额外借入</td>
									<c:choose>
										<c:when test="${companyLoan!=null}">
											<td id="extra_get"><fmt:formatNumber type="number" value="${totalDebt-(companyLoan.loanLast+companyLoan.getNum-companyLoan.returnNum)}"/></td>
										</c:when>
										<c:otherwise>
											<td id="extra_get">${totalDebt}</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td>您希望借入多少资金？</td>
									<td>
										<%-- <c:choose>
											<c:when test="${companyLoan.getNum!=null}">
												<input type="text" id="get" name="get" value="${companyLoan.getNum }"/>
											</c:when>
											<c:otherwise> --%>
												<input type="text" id="get" name="get" value="0"/>
											<%-- </c:otherwise>
										</c:choose> --%>
									</td>
								</tr>
								<tr>
									<td>您希望偿还多少资金？</td>
									<td>
										<%-- <c:choose>
											<c:when test="${companyLoan.returnNum!=null}">
												<input type="text" id="return" name="return" value="${companyLoan.returnNum }"/>
											</c:when>
											<c:otherwise> --%>
												<input type="text" id="return" name="return" value="0"/>
											<%-- </c:otherwise>
										</c:choose> --%>
									</td>
								</tr>
								<tr>
									<td>季度利率</td>
									<td>4%</td>
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
<script>

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