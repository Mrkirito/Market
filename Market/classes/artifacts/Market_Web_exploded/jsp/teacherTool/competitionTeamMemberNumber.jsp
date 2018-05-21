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
<title>每支参赛队伍的学员数</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
function modify(){
	$("#teamMember").removeAttr("disabled");
	$("[value='确认']").css("display","inline");
	$("[value='取消']").css("display","inline");
	$("[value='修改']").css("display","none");
}

function modifyCompetitionNameSubmit(){
	var params={};
	var numRule=/^[0-9]*[1-9][0-9]*$/;
	var teamNumber=$("#teamMember").val();
	
	/* boolean flag = numRule.test(teamNumber); */
	
	params.id=$("#competitionId").val();
	params.teamNumber=$("#teamMember").val();
	if(numRule.test(teamNumber)){
		//取当前公司人数最大值
		var num=0;
		var arr=new Array();
		//alert($("[title='teamNumber']"));
		$("[title='teamNumber']").each(function(key,value) {
			//alert(key+":"+$(value).text());
			if(num<parseInt($(value).text())){
				num=parseInt($(value).text());
			}
	        //alert(num);
	    });
		
		//当前人数与最大人数比较
		if(parseInt($("#teamMember").val())<num){
			alert("数量小于团队当前人数，请在学员管理中删除成员后再进行修改！");
			return;
		}
		if(parseInt($("#teamMember").val())>99){
			alert("设置成员数量太大！请重新进行设置");
			return;
		}
		$.ajax({
			type:'post',
			url:"teacherTool/modifyTeamNumber.do",
			data:params,
			success: function(data){
					
			}
		});
		$("#teamMember").attr("disabled","false");
		alert("修改成功！");
		$("[value='确认']").css("display","none");
		$("[value='取消']").css("display","none");
		$("[value='修改']").css("display","inline");
	}else{
		alert("输入不合规范！");
	}
}

function cancel(){
	var orgMember = $("#orgTeamMember").val();
	$("#teamMember").attr("disabled","false");
	$("[value='确认']").css("display","none");
	$("[value='取消']").css("display","none");
	$("[value='修改']").css("display","inline");
	$("#teamMember").val(orgMember);
}	
</script>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>竞赛每支参赛队伍的学员数</span>
            </div>
            <div class="panel-body">
            	<div>

            		<input id="competitionId" type="hidden" value="${competition.id }" name="id"/>
	            	<label>每支参赛队伍的最大学员数：</label>
	            	<input id="orgTeamMember" value="${competition.member }" style="display: none"/>
	            	<input id="teamMember" class="form-control" style="width: 200px" name="teamMember" value="${competition.member }" disabled="disabled"/><br/>
	            	<input type="button" class="btn btn-sm btn-default" onclick="modify()" value="修改" />
	            	<input type="button" class="btn btn-sm btn-default" onclick="modifyCompetitionNameSubmit()" value="确认" style="display: none"/>
	            	<input type="button" class="btn btn-sm btn-default" onclick="cancel()" value="取消" style="display: none"/>
            	</div>
                <hr/>
                <div>
                	<label>当前每支参赛队伍人数</label>
                	<table class="table table-bordered">
        				<thead>
        					<tr>
								<td>公司名称</td>
								<td>公司序号</td>
								<td>公司当前人数</td>
        					</tr>
        				</thead>
         				
        				<tbody>
        					<c:forEach items="${companyList }" var="item">
								<tr>
									<td>${item.company.name }</td>
									<td>${item.company.serialNumber }</td>
									<td title="teamNumber">${item.memberCount }</td>
								</tr>
							</c:forEach>
        				</tbody>
        			</table>
                </div>
                
            </div>
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>