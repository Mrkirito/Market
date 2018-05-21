<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/score.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					<a href=""><i class=" fa fa-caret-left"></i></a>
					&nbsp;&nbsp;<span>季度6</span>&nbsp;&nbsp;
					<a href=""><i class="fa fa-caret-right"></i></a>
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<label for="">RChe-TTT-SH-20161215</label>
			</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-3">
				<div id="left">
					<div class="list-group">
					  <a class="list-group-item" href="score1.html" target="main" target="blank"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; Home<i class="fa fa-caret-right"></i></a>
					  <a class="list-group-item" href="score2.html" target="main"><i class="fa fa-book fa-fw" aria-hidden="true"></i>&nbsp; Library<i class="fa fa-caret-right"></i></a>
					  <a class="list-group-item" href="score3.html" target="main"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i>&nbsp; Applications<i class="fa fa-caret-right"></i></a>
					  <a class="list-group-item" href="score4.html" target="main"><i class="fa fa-cog fa-fw" aria-hidden="true"></i>&nbsp; Settings<i class="fa fa-caret-right"></i>
					  </a>
					  <a class="list-group-item" href="score5.html" target="main"><i class="fa fa-cog fa-fw fa-spin" aria-hidden="true"></i>&nbsp; Settings<i class="fa fa-caret-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<iframe src="score1.html" name="main" frameBorder=0 width=850 height=430 marginheight="15" scrolling="no"></iframe>	
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>