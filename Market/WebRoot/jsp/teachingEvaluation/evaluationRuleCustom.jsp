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
<title>自定义教学质量评估</title>
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
                <span>自定义教学质量评估</span>
            </div>			
            
            <div class="panel-body">
				<p>
				可在系统进入第4季度后，学员已完成某季度决策但尚未提交决策之前进行评估测试。
				 请注意，在教学系统使用完毕后将无法进行评估测试。
				</p>
				<p>
					例如，您计划在第4季度进行评估测试。 
					根据教学计划，学员须在周一到周五之间完成第4季度决策并于周五晚上8点提交决策，您可以安排在周五早上或者下午进行评估测试。 
					这样，学生有充分的时间去完成第4季度的决策，以便他们更好地回答测试当中有关第4季度的问题。 
					系统在处理第4季度市场结果的同时也会学员测试成绩打分。 
					每个参加测试的学员都有自己的分数。
				</p>
				<p><strong>
					评估测试应为随堂测试，并设专人监考。 这样才能保证评估的准确性。
				</strong></p>
				<p>学校可使用该评估数据来考核课程质量。</p>
				<!-- <p>如果您希望对学员进行该项评估，请将您的联系方式提供给ILS的代理商，我们将尽快与您联系。</p>
				
				<form action="" method="post">
					<table>
						<tr>
							<td>姓名</td>
							<td><input type="text"/></td>
						</tr>
						<tr>
							<td>电子邮件</td>
							<td><input type="text"/></td>
						</tr>
						<tr>
							<td>电话号码</td>
							<td><input type="text"/></td>
						</tr>
					</table>
					<label>请告诉我们您将如何使用该评估。</label><br/>
					<textarea rows="5" cols="40">
					请输入您的意见！
					</textarea><br/>
					<input type="submit" value="提交"/>
					<input type="reset" value="取消"/>
				</form> -->
				
            </div>
        	
        	<div class="panel-footer"></div>    
    	</div>
	</div>
</body>
</html>