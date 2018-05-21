<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:'post',
			url:"showAllUser",
			success: function(data){
				/* alert(data); */
				/* alert(data[o].username); */
				/* for(var o in data){ 	
				} */
				var userDataHtml="";
				$.each(data , function(index,obj){
		  			userDataHtml+="<tr>";
		  			userDataHtml+="<td style='text-align:center'><input name='selectUser' type='checkbox' value='"+obj.id+"'/></td>";
		  			userDataHtml+="<td>"+obj.id+"</td>";
		  			userDataHtml+="<td>"+obj.username+"</td>";
		  			userDataHtml+="<td>"+obj.password+"</td>";
		  			userDataHtml+="<td>"+obj.gender+"</td>";
		  			userDataHtml+="<td>"+obj.address+"</td>";
		  			userDataHtml+="<td>"+obj.email+"</td>";
		  			userDataHtml+="<td>"+obj.birthday+"</td>";
		  			userDataHtml+="</tr>";
		  		});
		  		$("#userBody").append(userDataHtml);
			}
		});
	});
</script>
</head>
<body>
<h1>显示所有用户</h1>
<table class="table table-striped">
	<tr>
		<td></td>
		<td>id</td>
		<td>用户名</td>
		<td>密码</td>
		<td>性别</td>
		<td>住址</td>
		<td>邮箱</td>
		<td>生日</td>
	</tr>
	<tbody id="userBody">
	
	</tbody>
</table>
</body>
</html>