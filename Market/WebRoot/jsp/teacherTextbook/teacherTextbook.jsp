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
<title>教材</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/score.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

<style type="text/css">
.yincang {
	display: none;
}

.xianshi {
	display: inline;
}
</style>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					&nbsp;&nbsp;<span>教师材料</span>&nbsp;&nbsp;
					
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<input type="hidden" value="${competition.id }" /> <label for="">${competition.name }</label>
			</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-3" style="width: 300px">
				<div id="left" style="width: 260px">
					<div class="list-group" style="width: 220px">
						<a class="list-group-item" href="javascript:void(0)"><i
							class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 教材<i
							class="fa fa-caret-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<iframe id="myiframe"
					src="jumpTeacherTextbookList.do?id=${competition.id}" name="main"
					frameBorder=0 width=850 marginheight="15" scrolling="no"></iframe>
			</div>
		</div>


	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript">
	    $("#myiframe").load(function () {
	        var mainheight = $(this).contents().find("body").height() + 20;
	        $(this).height(mainheight);
	    });
    </script>
</body>
</html>