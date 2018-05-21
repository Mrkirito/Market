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
<title>帮助文档</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/score.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/helpDocument.js"></script>

<style type="text/css">
.yincang {
	display: none;
}

.list-group div {
	
}

ul {
	list-style: none;
}

.accordion {
	/* margin-left:100px; */
	width: 260px;
	max-width: 360px;
	/* margin: 30px auto 20px; */
	background: #FFF;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

.accordion .link {
	cursor: pointer;
	margin-left: -30px;
	display: block;
	padding: 15px 2px 15px 2px;
	text-indent: 10px;
	color: #4D4D4D;
	font-size: 14px;
	font-weight: 700;
	border-bottom: 1px solid #CCC;
	position: relative;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li:last-child .link {
	border-bottom: 0;
}

.accordion li i {
	position: absolute;
	top: 16px;
	left: 12px;
	font-size: 18px;
	color: #595959;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li i.fa-caret-right {
	right: 12px;
	left: auto;
	font-size: 16px;
}

.accordion li.open .link {
	color: #b63b4d;
}

.accordion li.open i {
	color: #b63b4d;
}

.accordion li.open i.fa-caret-right {
	-webkit-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-o-transform: rotate(90deg);
	transform: rotate(90deg);
}

/**
 * Submenu
 -----------------------------*/
.submenu {
	display: none;
	background: #444359;
	font-size: 14px;
	margin-left: -30px;
	text-indent: 30px;
}

.submenu li {
	border-bottom: 1px solid #4b4a5e;
}

.submenu a {
	display: block;
	text-decoration: none;
	color: #d9d9d9;
	padding: 12px;
	margin-left: -38px;
	padding-left: 10px;
	-webkit-transition: all 0.25s ease;
	-o-transition: all 0.25s ease;
	transition: all 0.25s ease;
}

.submenu a:hover {
	background: #b63b4d;
	color: #FFF;
}

.documentYangshi{
	text-indent:2em;
	font-size: 16px;
	letter-spacing:1.5pt;
	/* line-height: 1.5pt; */
}
</style>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					&nbsp;&nbsp;<span>帮助文档</span>&nbsp;&nbsp;
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<input type="hidden" name="id" value="${competition.id}"> <label
					for="">${competition.name }</label>
			</div>
		</div>

		<div class="row" id="row2">
			<div class="col-md-3"
				style="width: 300px; padding-left: 15px; margin-left: 0px;">
				<ul id="accordion" class="accordion">
					
				</ul>
			</div>

			<div class="col-md-9">
				<div id="nav44">
					<div class="panel panel-info">
						<div class="panel-heading">
							<span id="title"></span>
						</div>
			
						<div class="panel-body">
							<div id = "documentContent" style="height:600px; overflow-y:auto" class="documentYangshi"></div>
						</div>
			
						<div class="panel-footer"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>