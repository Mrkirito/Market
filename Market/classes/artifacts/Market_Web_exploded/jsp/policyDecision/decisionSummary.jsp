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
<title>决策汇总</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

$(function(){
	$("a").click(function(){
		var quarter=$("#currentQuarter").val();
		var companyId=$(this).prev("input").val();
		window.parent.main.location.href="policyDecision/companyDecisionSummary.do?companyId="+companyId+"&currentQuarter="+quarter;

	});
	
});
</script>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
			<div class="panel-heading">
				<span>决策汇总</span>
			</div>

			<div class="panel-body">
				<input type="hidden" id="currentQuarter" value="${currentQuarter }"/>
				<label><h4>点击公司名称来查看其决策汇总的完整版本</h4></label>
				<ul>
					<c:forEach items="${companyList }" var="item">
						<li>
							<input type="hidden" value="${item.id }"/>
							<a href="javascript:void(0)">${item.name }</a>
						</li>
					</c:forEach>
				</ul>
				
			</div>

			<div class="panel-footer"></div>
		</div>
	</div>
</body>
</html>