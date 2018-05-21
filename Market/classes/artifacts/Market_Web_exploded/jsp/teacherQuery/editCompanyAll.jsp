<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量修改公司列表</title>
<script type="text/javascript">
function updateCompanyListSubmit(){
	//提交form
	document.companyForm.action="${pageContext.request.contextPath }/teacher/updateCompanyListSubmit.do";
	document.companyForm.submit();
}
function queryCompany(){
	//提交form
	document.companyForm.action="${pageContext.request.contextPath }/teacher/updateCompanyList.do";
	document.companyForm.submit();
}
</script>
</head>

<body> 
<form name="companyForm" action="${pageContext.request.contextPath }/teacher/updateCompanyAll.do" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>
<!-- 如果有多个嵌套的包装pojo，则要在最内层传参，要用... -->
竞赛名称：<input name="competition.name" value="${competition.name }"/>
竞赛许可证：<input name="competition.license" value="${competition.license }"/>
公司名称：<input name="company.name" value="${company.name }"/>
</td>
<td><input type="button" value="查询" onclick="queryCompany()"/>
<input type="button" value="批量修改提交" onclick="updateCompanyListSubmit()"/>
</td>
</tr>
</table>
公司列表：
<table width="100%" border=1>
<tr>
	<td></td>
	<td>公司名称</td>
	<td>公司序号</td>
	<td>公司人数</td>
	<td>竞赛名称</td>
	<td>竞赛许可证</td>
</tr>
<c:forEach items="${companyList }" var="item" varStatus="status">
<tr>
	<td><input type="hidden" name="companyList[${status.index }].id" value="${item.company.id }"/></td>
	<td><input name="companyList[${status.index }].name" value="${item.company.name }"/></td>
	<td>${item.company.order }</td>
	<td>${item.memberCount }</td>
	<td>${item.competition.name }</td>
	<td>${item.competition.license }</td>
	
</tr>
</c:forEach>

</table>
</form>
</body>

</html>