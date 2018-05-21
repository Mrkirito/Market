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
<title>查询公司列表</title>
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<style type="text/css">
	.panel-body{
		min-height:400px;
		
	}
</style>

<script type="text/javascript">
function deleteCompany(){
	//提交form
	document.competitionForm.action="deleteCompany.do";
	document.competitionForm.submit();
}
function queryCompany(){
	//提交form
	document.competitionForm.action="queryCompanySubmit.do";
	document.competitionForm.submit();
}
function queryCompany2(){
	//提交form
	document.competitionForm.action="queryCompanyByPage.do";
	document.competitionForm.submit();
}

function updateCompanyList(){
	//提交form
	document.competitionForm.action="updateCompanyList.do";
	document.competitionForm.submit();
}

</script>

</head>
<body> 
	<div class="panel panel-info">
        <div class="panel-heading">公司查询</div>
        <div class="panel-body">
        		<div id="queryCondition">
        			<form id="companyForm" class="form-horizontal" name="competitionForm" action="" method="post">
        				<div class="text">
        					<label>公司名称：</label>
        					<input name="company.name" class="form-control" value="${company.name }"/>
        				</div>
	        			<!-- <div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryCompany()"/>
	        			</div> -->
	        			<div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryCompany2()"/>
	        			</div>
	        			
	        			<div class="split"></div>
				    </form>
        		</div>
        		<div id="queryResult">
        			<table class="table table-bordered">
        				<thead>
        					<tr>
        						<td></td>
								<td>公司名称</td>
								<td>公司序号</td>
								<td>公司人数</td>
								<td>所属竞赛名称</td>
								<td>竞赛许可证</td>
        					</tr>
        				</thead>
         				
        				<tbody>
        					<c:forEach items="${companyList }" var="item">
								<tr>
									<td><input type="checkbox" name="company_id" value="${item.company.id}"></td>
									<td>${item.company.name }</td>
									<td>${item.company.serialNumber }</td>
									<td>${item.memberCount }</td>
									<td>${item.competition.name }</td>
									<td>${item.competition.license }</td>
									<%-- <td><a href="${pageContext.request.contextPath }/teacher/editCompany.do?id=${item.company.id}">修改</a></td> --%>
								</tr>
								</c:forEach>
        				</tbody>
        			</table>
        		</div>
        		<input type="hidden" id="totalCount" value="${page.totalCount }">
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
</body>

</html>