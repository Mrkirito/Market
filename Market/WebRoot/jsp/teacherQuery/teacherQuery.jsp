<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查找</title>
	<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/teacher_index1.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/score.css">
</head>
<style type="text/css">
.tableDiv{
	border:2px solid #a1a1a1;
	padding:0px;
	margin-left:200px;
	background:#ffffff;
	width:800px;
	border-radius:10px;
}
</style>
<body>
    <div id="container">
        <div class="row" id="row2">
			<div class="col-md-3">
				<div id="left">
					<div class="list-group">
					  <a class="list-group-item" href="jsp/teacherQuery/queryCompetition.jsp" target="main" target="blank"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 查找竞赛<i class="fa fa-caret-right"></i></a>
					  <a class="list-group-item" href="jsp/teacherQuery/queryCompany.jsp" target="main"><i class="fa fa-book fa-fw" aria-hidden="true"></i>&nbsp; 查找团队<i class="fa fa-caret-right"></i></a>
					  <a class="list-group-item" href="jsp/teacherQuery/queryMember.jsp" target="main"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i>&nbsp; 查找队员<i class="fa fa-caret-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
			<!-- <div class="tableDiv"> -->
				<iframe src="jsp/teacherQuery/queryCompetition.jsp" name="main" frameBorder=0 width="100%" height=530 marginheight="15" scrolling="no">
				</iframe>	
			</div>
		</div>
	</div>   
</body>
</html>