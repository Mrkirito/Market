<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成员信息</title>

</head>
<body> 
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br/>
</c:forEach>
</c:if>


<!-- enctype="multipart/form-data" form表单中添加这个，适用于文件上传，值传不到controller中 -->
<form id="memberForm" action="${pageContext.request.contextPath }/teacher/editMemberSubmit.do" method="post">
<input type="hidden" name="id" value="${member.id }"/>
<input type="hidden" name="email" value="${member.email }"/>
<input type="hidden" name="password" value="${member.password }"/>
<input type="hidden" name="img" value="${member.img }"/>
修改成员信息：
<table width="100%" border=1>
<tr>
	<td>成员id</td>
	<td>${member.id }</td>
</tr>
<tr>
	<td>成员email</td>
	<td>${member.email }</td>
</tr>
<tr>
	<td>成员姓名</td>
	<td><input type="text" name="name" value="${member.name }"/></td>
</tr>
<tr>
	<td>所属公司</td>
	<td>${company.name }</td>
</tr>
<tr>
	<td>移动哪个到公司？</td>
	<td>
		<table>
		<c:forEach items="${companyList }" var="item2">
		<tr>
			<td><input type="radio" name="companyId" value="${item2.id}"></td>
			<td>${item2.order }</td>
			<td>${item2.name }</td>			
		</tr>
		</c:forEach>
		</table>
	</td>
</tr>


<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>

</html>