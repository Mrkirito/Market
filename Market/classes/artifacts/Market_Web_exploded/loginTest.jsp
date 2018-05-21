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
</head>
<body>
	<h1>Login</h1>
	<form action="user/doLogin">
		                                                                                                                                     
		<div class="form-group">
	        <label for="text" class="col-xs-2 control-label">UserName：</label>
	        <div class="col-xs-5">
	            <input type="text" class="form-control" name="username" placeholder="Enter Name">
	        </div>
	    </div>
		<div class="form-group">
	        <label for="text" class="col-xs-2 control-label">Password：</label>
	        <div class="col-xs-5">
	            <input type="text" class="form-control" name="password" placeholder="Enter Password">
	        </div>
	    </div>
       	<div id="submitName" class="col-xs-2"><button type="submit" class="btn btn-primary">提交</button></div>
	</form>
	<div>
		<img alt="背景图片"  src="images/bgm1.jpg"/>
	</div>
</body>
</html>