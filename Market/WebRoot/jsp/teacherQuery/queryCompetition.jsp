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
<title>查询竞赛列表</title>
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<style type="text/css">
	.panel-body{
		min-height:400px;
		
	}
</style>

</head>
<body>
	<div class="panel panel-info">
        <div class="panel-heading">竞赛查询</div>
        <div class="panel-body">
        
        		<div id="queryCondition">
        			<form class="form-horizontal" id="competitionForm" name="competitionForm" action="${pageContext.request.contextPath }/teacher/queryCompetitionSubmit.do" method="post">
        				<div class="text">
        					<label>竞赛名称：</label>
        					<input name="competition.name" class="form-control" value="${competition.name }"/>
        				</div>
        				<div class="text">
							<label>竞赛许可证：</label>
							<input name="competition.license" class="form-control" value="${competition.license }"/>
	        				
	        			</div>
	        			<!-- <div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryCompetition()"/>
	        			</div> -->
	        			<div class="query-btn">
	        				<input type="button" class="btn btn-default" value="查询" onclick="queryCompetition2()"/>
	        			</div>
	        			<div class="split"></div>
				    </form>
        		</div>
        		<div id="queryResult">
        			<table class="table table-bordered">
        				<thead>
        					<tr>
        						<td></td>
        						<td>竞赛名称</td>
        						<td>许可证号</td>
        						<td>当前季度</td>
        						<td>最大季度</td>
        						<td>成员数</td>
        						<td>公司数数</td>
        						<!-- <td>开始时间</td>
        						<td>结束时间</td> -->
        					</tr>
        				</thead>
        				<tbody>
        					<c:forEach items="${competitionList }" var="item">
								<tr>
									<td><input type="checkbox" name="competition_id" value="${item.id}"></td>
									<td>${item.name }</td>
									<td>${item.license }</td>
									<td>${item.currentQuarter }</td>
									<td>${item.quarter }</td>
									<td>${item.member }</td>
									<td>${item.team }</td>
									<%-- <td>${item.startTime }</td>
									<td>${item.endTime}</td> --%>
									<%-- <td><a href="${pageContext.request.contextPath }/teacher/editCompetition.do?id=${item.id}">修改</a></td> --%>
								
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
    
    <%-- <form name="competitionForm" action="${pageContext.request.contextPath }/teacher/queryCompetitionSubmit.do" method="post">
				<p class="newfont">查询条件：</p>
				<table width="100%" border=1 >
				<tr>
				<td>
				<!-- 如果有多个嵌套的包装pojo，则要在最内层传参，要用... -->
				竞赛名称：<input name="competition.name" value="${competition.name }" style="height:15px;"/>
				竞赛许可证：<input name="competition.license" value="${competition.license }" style="height:15px;"/>
				</td>
				<td>
				<input type="button" value="查询" onclick="queryCompetition()"/>
				<input type="button" value="批量删除" onclick="deleteCompetition()"/>
				</td>
				</tr>
				</table>
				<p class="newfont">竞赛列表：</p>
				<table width="100%" border=1 >
				
				
				<c:forEach items="${competitionList }" var="item">
				<tr>
					<td><input type="checkbox" name="competition_id" value="${item.id}"></td>
					<td><a href="">${item.name }</a></td>
					<td>${item.license }</td>
					<td>${item.currentQuarter }</td>
					<td>${item.quarter }</td>
					<td>${item.member }</td>
					<td>${item.team }</td>
					<td>${item.startTime }</td>
					<td>${item.endTime}</td>
					<td><a href="${pageContext.request.contextPath }/teacher/editCompetition.do?id=${item.id}">修改</a></td>
				
				</tr>
				</c:forEach>
				
				</table>
			</form> --%>

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	function handlePaginationClick(new_page_index, pagination_container) {
		 $("#competitionForm").attr("action","selectCompetitionByPage.do?pageNow="+ (new_page_index+1));
		 $("#competitionForm").submit();
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


<script type="text/javascript">

function deleteCompetition(){
	//提交form
	if(!window.confirm("你确定要删除选择的竞赛吗？"))
		return false;
	document.competitionForm.action="${pageContext.request.contextPath }/teacher/deleteCompetition.do";
	document.competitionForm.submit();
}
function queryCompetition(){
	//提交form
	document.competitionForm.action="queryCompetitionSubmit.do";
	document.competitionForm.submit();
}
function queryCompetition2(){
	//提交form
	document.competitionForm.action="selectCompetitionByPage.do";
	document.competitionForm.submit();
}

/* 
$(function(){
	queryCompetition();
}); */

</script>
</body>
</html>