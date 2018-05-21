<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询成员列表</title>
<style type="text/css">
	.panel-body{
		min-height:400px;
		
	}
</style>

<link rel="stylesheet" href="css/pagination.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<script type="text/javascript">
function deleteMember(){
	//提交form
	document.competitionForm.action="${pageContext.request.contextPath }/teacher/deleteMember.do";
	document.competitionForm.submit();
}
function queryMember(){
	//提交form
	document.competitionForm.action="${pageContext.request.contextPath }/queryMemberSubmit.do";
	document.competitionForm.submit();
}
function queryMember2(){
	//提交form
	document.competitionForm.action="queryMemberByPage.do";
	document.competitionForm.submit();
}
function updateMemberList(){
	//提交form
	document.competitionForm.action="${pageContext.request.contextPath }/teacher/updateMemberList.do";
	document.competitionForm.submit();
}

</script>

</head>
<body> 
	<div class="panel panel-info">
        <div class="panel-heading">竞赛查询</div>
        <div class="panel-body">
        		<div id="queryCondition">
        			<form class="form-horizontal" name="competitionForm" action="${pageContext.request.contextPath }/teacher/queryCompetitionSubmit.do" method="post">
        				<div class="text">
        					<label>队员名字：</label>
        					<input name="member.name" class="form-control" value="${member.name }"/>
        				</div>
        				<div class="text">
							<label>队员邮箱：</label>
							<input name="member.email" class="form-control" value="${member.email }"/>
	        				
	        			</div>
	        			<!-- <div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryMember()"/>
	        			</div> -->
	        			<div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryMember2()"/>
	        			</div>
	        			<div class="split"></div>
				    </form>
        		</div>
        		<div id="queryResult">
        			<table class="table table-bordered">
        				<thead>
        					<tr>
        						<td></td>
								<td>队员姓名</td>
								<td>队员email</td>	
								<td>公司名称</td>

								<td>竞赛名称</td>
								
								<!-- <td>操作</td> -->
        					</tr>
        				</thead>
        				<tbody>
        					<c:forEach items="${memberList }" var="item">
								<tr>
									<td><input type="checkbox" name="member_id" value="${item.member.id}"></td>
									<td>${item.member.name }</td>
									<td>${item.member.email }</td>
									<td>${item.company.name }</td>
									
									<td>${item.competition.name }</td>
									
									<!-- <td><a href="javascript:void(0)">修改</a></td> -->
								
								</tr>
								</c:forEach>
        				</tbody>
        			</table>
        		</div>
        		<%-- <input type="text" id="totalCount" value="${page.totalCount }"> --%>
        		<input type="hidden" id="pageSize" value="${page.pageSize }">
        		<input type="hidden" id="pageNow" value="${page.pageNow }">
        		<div id="div3" style="text-align:center"></div>
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	function handlePaginationClick(new_page_index, pagination_container) {
		 $("#companyForm").attr("action","queryCompanyByPage.do?pageNow="+ (new_page_index+1));
		 $("#companyForm").submit();
	    return false;
	} 
  	$(function() {
  		var a=$("#totalCount").val();
  		var b=$("#pageSize").val();
  		var c=$("#pageNow").val();
     $("#div3").pagination( a,{
        items_per_page: b, // 每页显示多少条记录
        current_page: c-1, // 当前显示第几页数据
        num_display_entries: 4, // 分页显示的条目数
        next_text:"下一页",
        prev_text:"上一页",
        num_edge_entries: 1 ,// 连接分页主体，显示的条目数
        load_first_page: false,
        callback:handlePaginationClick
	});
});
</script>
<%-- <form name="competitionForm" action="${pageContext.request.contextPath }/teacher/queryCompanySubmit.do" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>
<!-- 如果有多个嵌套的包装pojo，则要在最内层传参，要用... -->
竞赛名称：<input name="competition.name" value="${competition.name }"/>
竞赛许可证：<input name="competition.license" value="${competition.license }"/>
公司名称：<input name="company.name" value="${company.name }"/>
队员名字：<input name="member.name" value="${member.name }"/>
队员email：<input name="member.email" value="${member.email }"/>

</td>
<td>
<input type="button" value="查询" onclick="queryMember()"/>
<input type="button" value="批量删除" onclick="deleteMember()"/>

</td>
</tr>
</table>
竞赛列表：
<table width="100%" border=1>
<tr>
	<td></td>
	<td>队员姓名</td>
	<td>队员email</td>	
	<td>公司名称</td>
	<td>竞赛名称</td>
	<td>竞赛许可证</td>
	<td>操作</td>
</tr>
<c:forEach items="${memberList }" var="item">
<tr>
	<td><input type="checkbox" name="member_id" value="${item.member.id}"></td>
	<td>${item.member.name }</td>
	<td>${item.member.email }</td>
	<td>${item.company.name }</td>
	<td><a href="">${item.competition.name }</a></td>
	<td>${item.competition.license }</td>
	<td><a href="${pageContext.request.contextPath }/teacher/editMember.do?id=${item.member.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form> --%>
</body>

</html>