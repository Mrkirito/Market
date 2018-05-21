<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/teacherquery.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript" src="js/chatAndMail.js"></script>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					<a href=""><i class=" fa fa-caret-left"></i></a>
					&nbsp;&nbsp;<span>季度6</span>&nbsp;&nbsp;
					<a href=""><i class="fa fa-caret-right"></i></a>
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<label for="">RChe-TTT-SH-20161215</label>
			</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-3">
				<div id="left">
					<div class="list-group">
					  <a class="list-group-item" href="javascript:void(0)" id="chatOnline" onclick="openChatWin()"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 在线聊天<i class="fa fa-caret-right"></i></a>
					  <ul id="u1">
					  </ul>
					  <a class="list-group-item" href="javascript:void(0)" id="charRecord" onclick="openChatRecord()"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 聊天记录<i class="fa fa-caret-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="right" style="height:430px;width:850px; background:white;" >
					<div id="rightContainer">
						<div class="righttop">正在与“
							<span id="receiver" name="receiver"></span>
						”进行聊天</div>
						<div class="rightmiddle">
						<iframe width="0" height="0" name="actionframe" hidden="true"></iframe>
						<form>
						<textarea rows="10" cols="61" name="msg" id="msg" disabled="disabled"></textarea>
						</form>
						</div>
						<div class="rightbottom">
						
							<form action="" id="form1" method="post">
								<textarea rows="5" cols="61" name="content" id="chat_content" placeholder="请输入内容" required="required"></textarea>
								<input type="button" value="发送" onclick="check()"/>
								<input type="reset" value="重置"/>
							</form>
						</div>
					</div>
					<div id="rightContainer2">
						<table class="table table-bordered" id="tab">
							<thead>
								<tr>
									<td width="160px">发送者</td>
									<td width="160px">接受者</td>
									<td width="200px">日期</td>
									<td width="400px">内容</td>
								</tr>
							</thead>
						</table>
						<input type="hidden" id="pageNowChat" >
						<input type="hidden" id="totalPageChat" >
						<input type="hidden" id="competitionId" value=<%=request.getParameter("id") %> />
						<div id="pageChat" style="text-align:center"></div>
					</div>
					
					<div id="rightContainer3">
						<div>正在给“
							<span id="receiver2" name="receiver"></span>
						”发邮件</div>
						<form id="form2">
							<label>主题：</label>	
							<input type="text" name="title" required="required"placeholder="请输入邮件标题"/><br />
							<label>收件人：</label>	
							<input type="text" name="receiveAddress"  required="required" placeholder="请输入收件人"/><br />
							<label>内容：</label><br/>	
							<textarea rows="5" cols="61" name="content" id="mail_content" placeholder="请输入内容" required="required"></textarea><br />
							<input type="button" value="提交" onclick="sendMail()"/>
							<input type="reset" value="重置" />
						</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>