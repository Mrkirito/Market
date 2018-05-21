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
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<link rel="stylesheet" href="css/score.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript" src="js/studentOperate.js"></script>
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
				<div id="rightContainer2" style="background:white;">
						<table class="table table-bordered" id="tab">
							<thead>
								<tr>
									<td width="50px">#</td>
									<td width="120px">成员名字</td>
									<td width="200px">公司名字</td>
									<td width="200px">操作日期</td>
									<td width="50px">季度</td>
									<td width="300px">操作内容</td>
								</tr>
							</thead>
						</table>
						<input type="hidden" id="pageNowOperate" >
						<input type="hidden" id="totalPageOperate" >
						<input type="hidden" id="companyId"  value=<%=request.getParameter("companyId") %>>
						<div id="pageOperate" style="text-align:center"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>