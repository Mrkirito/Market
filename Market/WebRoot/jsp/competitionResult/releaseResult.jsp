<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布结果</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function(){
		var quarter=$("#quarter").val();
		var currentQuarter=$("#currentQuarter").val();
		var maxQuarter=$("#maxQuarter").val();
		if(currentQuarter<=maxQuarter){
			if(quarter<currentQuarter){
				$("#resultButton").val("已经可以查看本季度竞赛结果！").attr("disabled", true);
				$("#deleteResultButton").val("不能删除上季度结果！").attr("disabled",true);
			}else{
				$("#resultButton").val("发布当前季度竞赛结果！");
				$("#deleteResultButton").val("取消上季度竞赛结果！");
			}
		}else{
			$("#resultButton").val("已经是本次竞赛最后一个季度！").attr("disabled", true);
			$("#deleteResultButton").val("取消上季度竞赛结果！");
		}
		
	});
	
	function releaseResult(){
		var competitionId=$("#competitionId").val();
		var quarter=$("#quarter").val();
		var isAllCompanySubmit=$("#isAllCompanySubmit").val();
		if(isAllCompanySubmit=="yes"){
			alert("发布成功！");
			window.parent.location.href="competitionResult/releaseResult.do?competitionId="+competitionId+"&currentQuarter="+quarter;
		}else{
			alert("有公司没有提交，请先去通知公司提交！");
			return false;
		}
	}
	
	function deleteReleaseResult(){
		var competitionId=$("#competitionId").val();
		var quarter=$("#quarter").val();
		window.parent.main.location.href="competitionResult/deleteReleaseResult.do?competitionId="+competitionId+"&currentQuarter="+quarter;
	}
</script>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>发布结果</span>
			</div>

			<div class="panel-body">
				<input type="hidden" id="competitionId" value="${competition.id }"/>
				<input type="hidden" id="currentQuarter" value="${competition.currentQuarter }"/>
				<input type="hidden" id="maxQuarter" value="${competition.quarter }"/>
				<input type="hidden" id="quarter" value="${quarter }"/>
				<input type="hidden" id="isAllCompanySubmit" value="${isAllCompanySubmit }"/>
				<div>
					<label>点击发布竞赛结果后，可以查看本季度竞赛结果，并进入下一季度。</label><br/>
					<label>${message }</label><br/>
					
					<input type="button" id="resultButton" class="btn btn-default" value="" onclick="releaseResult()"/><br/>
					<input type="button" id="deleteResultButton" class="btn btn-default" value="" onclick="deleteReleaseResult()"/><br/>
					<!-- 注意：发布竞赛结果前，先判断各个公司是否已经提交，如果没有，则提示错误信息，否则提交。 -->
				</div>
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
</body>
</html>