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
<title>高级选项</title>
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
                <span>罚款和其他收入</span>
            </div>
            
            <div class="panel-body">
				<span>在下列表格中，您可以添加当前季度的罚款或其他收入。</span><br/>
				<span>您必须在系统处理当前季度竞赛结果之前输入这些数据。</span>
				<input type="button" value="刷新" onclick="location.reload();"/>
				<hr/>
				
				<p>添加季度6的罚款或其他收入</p>
				<table>
					<tr>
						<td>公司</td>
						<td>罚款</td>
						<td>其他收入</td>
					</tr>
					<tr>
						<td>公司1</td>
						<td><input type="text" value="0" name="fine"/></td>
						<td><input type="text" value="0" name="otherIncome"/></td>
					</tr>
					<tr>
						<td>公司2</td>
						<td><input type="text" value="0" name="fine"/></td>
						<td><input type="text" value="0" name="otherIncome"/></td>
					</tr>
					<tr>
						<td>公司3</td>
						<td><input type="text" value="0" name="fine"/></td>
						<td><input type="text" value="0" name="otherIncome"/></td>
					</tr>
					<tr>
						<td>公司4</td>
						<td><input type="text" value="0" name="fine"/></td>
						<td><input type="text" value="0" name="otherIncome"/></td>
					</tr>
				</table>
				<input type="button" value="修改"/>
            	<input type="button" value="确认"/>
            	<input type="button" value="取消"/>
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>