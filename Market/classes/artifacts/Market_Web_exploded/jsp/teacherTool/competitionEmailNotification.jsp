<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件通知</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/strategy.css">

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
	<div id="nav44">
		<div class="panel panel-info">
            <div class="panel-heading">
                <span>电子邮件通知</span>
            </div>
            
            <div class="panel-body">
            	<label>如果您选择了该选项，系统处理中心处理完当前季度竞赛后将以电子邮件方式通知您</label>
            	<hr/>
            	<input type="checkbox" value="" name=""/>在系统处理完竞赛结果后，通过电子邮件通知我。
            	<br/>
            	<input class="btn btn-sm btn-default" type="button" value="修改"/>
            	<input class="btn btn-sm btn-default" type="button" value="确认"/>
            	<input class="btn btn-sm btn-default" type="button" value="取消"/>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>