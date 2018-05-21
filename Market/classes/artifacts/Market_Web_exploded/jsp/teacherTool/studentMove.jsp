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
<title>移动参赛队员</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">


</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>移动参赛队员</span>
            </div>
            
            <div class="panel-body">
            	<input type="hidden" value="${competition.id }"/>
            	<label>竞赛名称：${competition.name }</label><br />
            	<label>团队最大人数：${competition.member }</label><br />
				
				<label>参赛队员列表</label><br/>
				
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>队员编号</th>
							<th>队员姓名</th>
							<th>邮箱</th>
							<th>年龄</th>
							<th>性别</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${companyAndMemberList }" var="item">
							<tr>
								<td colspan="7">${item.company.name }</td>
							</tr>
							<c:forEach items="${item.memberList }" var="memberItem">
								<tr>
									<td></td>
									<td>${memberItem.id }</td>
									<td>${memberItem.name }</td>
									<td>${memberItem.email }</td>
									<td>${memberItem.age }</td>
									<td>${memberItem.gender }</td>
									<td>
										<input type="button" class="btn btn-sm btn-primary" onclick="location.href='teacherTool/studentMoveEdit.do?memberEmail=${memberItem.email}&competitionId=${competition.id}'" value="移动"/>
										<input type="button" class="btn btn-sm btn-primary" onclick="if(confirm('确认删除吗')) window.location.href='teacherTool/deleteMember.do?memberId=${memberItem.id }&competitionId=${competition.id}'; else return false;" value="删除"/>	
									</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
            </div>
        	<div class="panel-footer"></div>    
    	</div>
	</div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

</script>
</body>
</html>