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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" /> 
<link rel="stylesheet" type="text/css" href="css/style2.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<style type="text/css">
	body{
		background:url(images/indexbg.jpg) no-repeat;
	}
	.split{
		clear:left;
	}
	a{
		text-decoration:none;
		cursor:pointer;
	}
	#steptitle .title1{
		float:left;
		width:100px;
		
	}
	#button_left{
		margin-top:8px;
		background:#ddd;
	}
	#button_right{
		margin-top:8px;
		margin-left:10px;
	}
	#step1{
		display:block;
		margin-top:-20px;
	}
	#step2{
		display:none;
		margin-top:-20px;
	}
	#errorInfo{
		color:red;

	}
	
</style>
</head>
<body>
	<div class="container">
		<div id="title">
			<div><img src="images/logo.png"></div>
			<div><label>Market</label></div>
		</div>
		<div id="text">
			<div id="text1"><span>“</span>给的再多</div>
			<div id="text2">不如懂我<span>”</span></div>
		</div>
	
        <section>				
	        <div id="container_demo" >
	            <a class="hiddenanchor" id="toregister"></a>
	            <a class="hiddenanchor" id="toregister2"></a>
	            <a class="hiddenanchor" id="tologin"></a>
	            <div id="wrapper">
	                <div id="login" class="animate form">
	                    <form action="Login.do" method="post"> 
	                        <h1> 登录</h1> 
	                        <p>
	                        	<select name="role" class="selectpicker form-control">
	                         		<option value="member">普通用户</option>
	                         		<option value="teacher">教师用户</option>
	                        	</select>
	                        </p>
							<p> 
							    <label for="username" class="uname" data-icon="u" ></label>
							    <input id="username" name="email" required="required" type="text" placeholder="邮箱"/>
							</p>
	                        <p> 
	                            <label for="password" class="youpasswd" data-icon="p"> </label>
	                            <input id="password" name="password" required="required" type="password" placeholder="密码" /> 
	                        </p>
	                        <p class="keeplogin"> 
								<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
								<label for="loginkeeping">记住我</label><br>
								<div id="errorInfo">${errorInfo}</div>
							</p>
							<p>
								<input type="submit" id="loginbtn" class="btn btn-danger" value="确认">
							</p>
		                    <p class="change_link">
								<a href="#toregister2"> 教师注册</a>
								<a href="#toregister" class="to_register"> 学生注册</a>
							</p>
	                    </form>
	                </div>
	                <div id="register2" class="animate form">
	                	<form action="teacherRegister.do" method="post"> 
	                        <h3>教师注册</h3>
	                        <p> 
							    <label for="username" class="uname" data-icon="u" ></label>
							    <input id="username" name="name" required="required" type="text" placeholder="姓名"/>
							</p>
							<p> 
							    <label for="username" class="uname" data-icon="u" ></label>
							    <input id="email" name="email" required="required" type="text" placeholder="邮箱"/>
							</p>
							
							<p> 
	                            <label for="password" class="youpasswd" data-icon="p"> </label>
	                            <input id="password" name="password" required="required" type="password" placeholder="密码" /> 
	                        </p>
	                        <p>
								<input type="submit" id="teacherbtn" class="btn btn-danger" value="确认">
							</p>
		                	<p class="change_link">
								<a href="#tologin" class="to_register">去登录</a>
							</p>
						</form>
		               
	                </div>
	                <div id="register" class="animate form">
	                    <form  action="memberRegister.do" method="post"> 
	                        <div class="navbar navbar-default">
								<div class="navbar-header">
									<span class="navbar-brand">注册</span>
								</div>
								<ul class="nav navbar-nav">
									<li><button class="btn button_left" id="button_left">团队信息</button></li>
									<li><button class="btn button_right" id="button_right">个人信息</button></li>
								</ul>
							</div>
	                        <div id="step1">
	                        	<p> 
		                            <label for="competition_number" class="" data-icon="p"></label>
		                            <input id="competition_number" name="team_order" onkeyup='this.value=this.value.replace(/\D/gi,"")'
		                             required="required" type="text" placeholder="竞赛编号" />
		                        </p>
		                        <p> 
		                            <label for="competition_number" class="" data-icon="p"></label>
		                            <input id="license1_number" name="license1" required="required" type="text" placeholder="竞赛许可证号" />
		                        </p>
		                        <p> 
		                            <label for="license_number" class="" data-icon="p" ></label>
		                            <input id="license2_number" name="license2" required="required" type="text" placeholder="团队许可证号"/> 
		                        </p>
		                       
		                        <p>
									<button class="btn button_right">下一步</button>
								</p>
	                        </div>
	                        <div id="step2">
	                        	<p> 
		                            <label for="usernamesignup" class="uname" data-icon="u"></label>
		                            <input id="usernamesignup" name="name" required="required" type="text" placeholder="用户名" />
		                        </p>
		                        <p> 
		                            <label for="emailsignup" class="youmail" data-icon="e" ></label>
		                            <input id="emailsignup" name="email" required="required" type="email" placeholder="邮箱"/> 
		                        </p>
		                        <p> 
		                            <label for="passwordsignup" class="youpasswd" data-icon="p"></label>
		                            <input id="passwordsignup" name="password" required="required" type="password" placeholder="密码"/>
		                        </p>
		                       <!--  <p> 
		                            <label for="passwordsignup_confirm" class="youpasswd" data-icon="p"></label>
		                            <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="再次输入密码"/>
		                        </p> -->
		                         <p>
									<button class="button_left">返回</button>
								</p>
		                     	<p>
									<input type="submit" class="btn btn-danger" value="提交">
								</p>
	                        </div>
	                        <p class="change_link">  
								<span>已经是会员</span>
								<a href="#tologin" class="to_register"> 去登录</a>
							</p>
	                    </form>
	                </div>
	                
	            </div>
	        </div>  
   		</section>
 	</div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
	function check_button(){
		var str1=$("#competition_number").val();
		var str2=$("#license1_number").val();
		var str3=$("#license2_number").val();
		if(str1==""||str2==""||str3==""){
			$(".button_right").attr("disabled","disabled");
		}else{
			$(".button_right").attr("disabled",false);
		}
	}
	$(function(){
		$(".button_left").on("click",function(){
			$("#step2").css("display","none");
			$("#step1").css("display","block");
			$("#button_left").css("background","#ddd");
			$("#button_right").css("background","transparent");
		})
		$(".button_right").on("click",function(){
			$("#step2").css("display","block");
			$("#step1").css("display","none");
			$("#button_left").css("background","transparent");
			$("#button_right").css("background","#ddd");
		})
		setInterval('check_button();', 500);
	})
</script>
</body>
</html>