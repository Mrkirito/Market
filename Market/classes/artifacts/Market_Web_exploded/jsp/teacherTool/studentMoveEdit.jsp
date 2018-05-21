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

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	function moveMember(){
		var memberId=$("#memberId").val();
		var companyId=$(":checked").val();
		var competitionId=$("#competitoinId").val();
		//alert(competitionId);
		var maxNum=parseInt($("#maxNum").text());
		//alert(maxNum);
		var num=parseInt($(":checked").parent("td").nextAll("[title='presentNum']").text());
		if(maxNum<num+1){
			alert("移动后队员将超出最大数目，请合理分配！");
			return;
		}else{			
			location.href = "teacherTool/studentMoveSubmit.do?memberId="+memberId+"&companyId="+companyId+"&competitionId="+competitionId;
		}
	}
	
	$(function(){
		var companyId=$("#companyId").val();
		$(":radio").each(function(){
			//arr[key]=$(value).val();
			if($(this).val()==companyId){
				var companyName=$(this).parent("td").next().text();
				$("#companyName").text(companyName);
				
			}
		});
	});
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
                <span>移动参赛队员</span>
                <input type="button" value="刷新" onclick="location.reload();"/>
            </div>
            
            <div class="panel-body">
            	<input id="competitoinId" type="hidden" value="${competition.id }"/>
            	<input id="companyId" type="hidden" value="${member.companyId }"/>
            	<input id="memberId" type="hidden" value="${member.id }"/>
            	<label>竞赛名称：${competition.name }</label><br />
            	<label>团队最大人数：</label><label id="maxNum">${competition.member }</label><br />
				<label>当前队员姓名：${member.name }</label><br/>
				<label>所在团队：</label><label id="companyName"></label><br />
				<label>请选择你要移动到哪个队伍：</label>
				<table class="table table-bordered">
					<tr>
						<th></th>
						<th>公司名称</th>
						<th>公司序号</th>
						<th>公司当前人数</th>
					</tr>
					<c:forEach items="${companyList }" var="item">
					<tr>
						<td><input type="radio" name="companyId" value="${item.company.id }"/></td>
						<td>${item.company.name }</td>
						<td>${item.company.serialNumber }</td>
						<td title="presentNum">${item.memberCount }</td>
					</tr>
					</c:forEach>
				</table>
				<input type="button" class="btn btn-sm btn-default" value="确认" onclick="moveMember()"/>
				<input type="button" class="btn btn-sm btn-default" value="取消" onclick="javascript :history.back(-1)"/>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>