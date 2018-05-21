<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>教学质量评估</title>
	<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/score.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    
<style type="text/css">
	.yincang{
		display: none;
	}
	.xianshi{
		display:inline;
	}
</style>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					&nbsp;&nbsp;<span>教学质量评估</span>&nbsp;&nbsp;
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<input type="hidden" name="id" value="${competition.id}">
				<label for="">${competition.name }</label>
			</div>
		</div>
		
		<div class="row" id="row2">
			<div class="col-md-3" style="width:300px">
				<div id="left" style="width:260px">
					<div class="list-group" style="width:220px">
					  <a class="list-group-item" href="javascript:void(0)" onclick="changeModel(this);" style="background-color: #d5d5d5"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 教学质量评估<i class="fa fa-caret-right"></i></a>
					  <div class="xianshi">
					  	<a class="list-group-item" href="teachingEvaluation/evaluationRule.do??id=${competition.id}" target="main">●&nbsp;评估准则</a>
					  	<a class="list-group-item" href="teachingEvaluation/evaluationRuleCustom.do?id=${competition.id}" target="main">●&nbsp;自定义教学质量评估</a>
					  </div>
					</div>
				</div>
			</div>
			
			<div class="col-md-9">
				<iframe id="myiframe" src="teachingEvaluation/evaluationRule.do?id=${competition.id}" name="main" frameBorder=0 width=850 marginheight="15" scrolling="no"></iframe>	
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
    	function changeModel(aNode){
    		var divNode=aNode.nextSibling.nextSibling;
    		if(divNode.className=="yincang"){
    			divNode.className="xianshi";
    		}else{
    			divNode.className="yincang";
    		}
    	}

        $("#myiframe").load(function () {
            var mainheight = $(this).contents().find("body").height() + 20;
            $(this).height(mainheight);
        });
    	
    </script>
</body>
</html>