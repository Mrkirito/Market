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
<title>授予访问权限</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">
<style>
	ul{
		list-style:none;
	}
	.license label{
		
		width:90px;
	}
	.note-footer li label{
		
		width:90px;
	}
</style>
</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>学员注册说明</span>
            </div>
            
            <div class="panel-body">
            	<div>
	           		该说明已包含竞赛编号和参赛队伍序号，只需要在下方下拉列表中选择相应的参赛队伍为参赛队员打印说明
	           		<br/>
	           		说明中包含竞赛名称、竞赛许可证、团队序号、团队许可证
	           		<br/>
	           		<label>为参赛队伍打印注册说明：</label>
					<input type="button" class="btn btn-info btn-sm" value="打印说明" onclick="preview()"/>
				</div>
				<hr/>
				<div id="note">
					<!--startprint-->
					<p><strong>学员注册说明</strong></p>
					<p><strong>竞赛名称：</strong>${competition.name }</p>
					<label>在创建学员账号以前，您需要以下信息：</label><br/>
					<c:forEach items="${companyList }" var="item">
						<ul class="license">
							<li><label>竞赛许可证：</label>${competition.license }</li>
							<li><label>团队许可证：</label>${item.license }</li>
							<li><label>团队序号：</label>${item.serialNumber }</li>
						</ul>
					</c:forEach>
					<p><strong>技术支持：</strong></p>
					<ul class="note-footer">
						<li><label>电子邮件：</label></strong>support@market.com</li>
						<li><label>电话：</label>18917587969</li>
					</ul>
					<!--endprint-->
				</div>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
function preview() {
    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML = prnhtml;
    window.print();
    //window.download();
}
</script>
</body>
</html>