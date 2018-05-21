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
<style type="text/css">
body{
	margin:50px auto;
	text-align:center;
}

form input{
	width:250px;
	margin: 5px;
	font-size:15px;
}
.textArea{
	width:250px;
	height:100px;
	margin: 5px;
	font-size:15px;
}

.select{
	width:255px;
	margin: 5px;
	font-size:15px;
}

p{
	font-size:25px;
	color: red;
}

</style>

</head>
<body>
<div>
<p>${message }</p>
<form id="customerServiceForm" action="customerService.do" method="post">
	<input type="text" name="name" required="required" placeholder="姓名"/><br />
	<select name="questionType" class="select">
		<option value="学生端">学生端</option>
		<option value="教师端">教师端</option>
		<option value="管理员端">管理员端</option>
	</select><br />
	<input type="text" name="email" required="required" placeholder="邮件地址" /><br />
	<textarea name="content" required="required" placeholder="内容" class="textArea"></textarea><br />
	<input type="submit" value="提交" style="width:50px;" id="sub"/> 
	<input type="reset" value="重置" style="width:50px"/> 
	<input type="button" value="关闭" style="width:50px" onclick="window.close()"/> 
</form>
</div>

</body>
</html>