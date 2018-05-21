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
<title>股票设定</title>
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
                <span>股票设定</span>
            </div>			
            
            <div class="panel-body">
				<p>在下列表格中，您可以选择各个团队在当前季度可以发行的股票量</p>
            	<span>为每位股东输入每股价格、股票总数、及其名字，点击计算后系统将自动计算每位股东的股票数。</span>
            	<br/>
            	<span>按照默认设定，在最初的几个季度内，各个公司将向管理团队发行同筹数额的股票</span>
            	<hr/>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>