<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改竞赛信息</title>

</head>
<body> 
<!-- 显示错误信息 -->
<c:if test="${allErrors!=null }">
	<c:forEach items="${allErrors }" var="error">
	${ error.defaultMessage}<br/>
</c:forEach>
</c:if>


<!-- enctype="multipart/form-data" form表单中添加这个，适用于文件上传，值传不到controller中 -->
<form id="competitionForm" action="${pageContext.request.contextPath }/teacher/editCompetitionSubmit.do" method="post">
<input type="hidden" name="id" value="${competition.id }"/>
修改竞赛信息：
<table width="100%" border=1>
<tr>
	<td>竞赛名称</td>
	<td><input type="text" name="name" value="${competition.name }"/></td>
</tr>
<tr>
	<td>竞赛许可证</td>
	<td><input type="text" name="license" value="${competition.license }"/></td>
</tr>
<tr>
	<td>季度</td>
	<td><input type="text" name="maxQuarter" value="${competition.maxQuarter }"/></td>
</tr>
<tr>
	<td>最大团队成员数</td>
	<td><input type="text" name="maxMember" value="${competition.maxMember }"/></td>
</tr>
<tr>
	<td>最大团队数</td>
	<td><input type="text" name="maxTeam" value="${competition.maxTeam }"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>

</html>