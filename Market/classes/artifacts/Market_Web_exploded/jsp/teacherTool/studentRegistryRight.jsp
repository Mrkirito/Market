<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>访问权限</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
	table,tr,td,th{
		border: 1px solid;
	}
</style>
</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>访问权限</span>
            </div>
            
            <div class="panel-body">
            	<input type="hidden" value="${competition.id }"/>
            	<label>竞赛名称：${competition.name }</label><br />
            	<label>注意：锁定后用户将无法访问本次竞赛！</label><br />
            	<hr />
				<label>参赛队员列表</label><br/>
				<table class="table table-bordered">
					<tr>
						<th>公司名称</th>
						<th>编号</th>
						<th>队员姓名</th>
						<th>邮箱</th>
						<th>访问截止时间</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${companyAndMemberList }" var="item">
					<tr>
						<td colspan="6">公司名称：${item.company.name }</td>
					</tr>
						<c:forEach items="${item.memberList }" var="memberItem">
						<tr>
							<td></td>
							<td>${memberItem.id }</td>
							<td>${memberItem.name }</td>
							<td>${memberItem.email }</td>
							<td>${competition.endTime }</td>
							<td>
								<input type="button" class="btn btn-sm btn-default" onclick="" value="锁定"/>
								<input type="button" class="btn btn-sm btn-default" onclick="" value="解锁"/>	
							</td>
						</tr>
						</c:forEach>
					</c:forEach>
				</table>
				
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>