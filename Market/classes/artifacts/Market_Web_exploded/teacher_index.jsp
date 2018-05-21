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
<link rel="stylesheet" type="text/css" href="css/teacher_index1.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<style type="text/css">
	#myCarousel{
		margin-left:100px;
		margin-top:30px;
	}
	#imgdiv{
		height: 270px;
		background: url("images/bgmiphone3.jpg");
		margin:0 30px 0 30px;
		overflow:hidden;
	}
	#textdiv{
		color: white;
		margin-top: 140px;
		margin-left: 150px;
		font-size: 22px;
	}
</style>
</head>
<body>
    <div id="head">
        <div id="head-left">
            <img src="images/logo.png">
            <span id="head-title">Market</span>
        </div>
        <div id="head-right">
            <a href="#"><span><i class="fa fa-user"></i></span></a>
            <div id="user-detail">
                <h3>账户详情</h3>
                <p>用户名:${teacher.name}</p>
                <p>邮箱：${teacher.email}</p>
                <p>单位：${teacher.org}</p>
                <button type="button" class="btn btn-info" id="btn1" data-toggle="modal" data-target="#mymodal-data" data-keyboard=true data-backdrop="static">修改个人信息</button>
            </div>
        </div>
        <div id="head-right2">
            <a href="#" data-toggle="tooltip" data-placement="left" data-original-title="退出" title=""><span><i class=" fa fa-share"></i></span></a>
        </div>
    </div>
    <div id="container">
        <ul class="nav1">
            <!-- <li><a href="javascript:void(0)" onclick="customerService();">售后服务</a></li> -->
            <li><a href="#" onclick="teacherReference()">参考资料</a></li>
            <li><a href="#" onclick="teacherQuery()">查找队员</a></li>
            <li><a href="#" onclick="designCompetition();">设计竞赛</a></li>
            <li><a href="javascript:void(0)" onclick="aboutUs()">关于我们</a></li>
        </ul>
        <div id="imgdiv">
			<div id="textdiv">
				Market创业培训系统
			</div>
        	<%--<img src="images/bgmiphone3.jpg">--%>
        </div>
        
        
        <!-- <!-- 滚动图片 -->
        <!-- <div class="carousel slide" id="myCarousel" data-interval="4000" data-ride="carousel" style="width: 1263px;height:270px;overflow:hiden;margin-top:0px;margin-left: 65px">
			<ol class="carousel-indicators">
	           <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	           <li data-target="#myCarousel" data-slide-to="1"></li>
	           <li data-target="#myCarousel" data-slide-to="2"></li>
	        </ol>
			<div class="carousel-inner">
				<div class="item active">
					<img src="images/bgmiphone3.jpg">
					<div class="carousel-caption" style="z-index: 10">
						<h4>图片一</h4>
						<p>图片一张的内容</p>
					</div>
				</div>
	
				<div class="item ">
					<img src="images/bgmiphone3.jpg">
					<div class="carousel-caption">
						<h4>图片二</h4>
						<p>图片二张的内容</p>
					</div>
	
				</div>
	
				<div class="item ">
					<img src="images/bgmiphone3.jpg">
					<div class="carousel-caption">
						<h4>图片三</h4>
						<p>图片三张的内容</p>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
	        <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
        
        <div id="bgimage">
            <div>说明文字一</div>
            <div>说明文字二</div>
            <div>说明文字三</div>
        </div> -->
        <div>${errorInfo }</div>
        
        <div><a href="checkCompetition.do?competitionId=17">审核竞赛</a></div>
        <div id="main" class="competiton_body">
            <div id="main_title">
                <h3>竞赛列表</h3>
            </div>
            <ul id="competition_title">
                <li>竞赛名称</li>
                <li>序列号</li>
                <li>当前季度</li>
                <li>队伍数量</li>
                <li>团队最大人数</li>
                <li>截止时间</li>
                <li>状态</li>
            </ul>
        </div>
        <br>
        
        <div class="modal fade" id="mymodal-data">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal" action="updateTeacherById.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times</button>
						<h4>修改个人信息</h4>
					</div>
					<div class="modal-body">
						
						<input type="hidden"  name="id" value="${teacher.id}">
						<input type="hidden"  name="email" value="${teacher.email}">
						
						<div class="form-group">
					    	<label class="col-md-4 control-label">用户名：</label>
					    	<div class="col-md-5">
					    		<input type="text" class="form-control" name="name" value="${teacher.name }">
					    	</div>
					    	<div class="col-md-3 teacher_info">4到10个字符</div>
					    </div>
					     <div class="form-group">
					    	<label class="col-md-4 control-label">新密码：</label>
					    	<div class="col-md-5">
					    		<input type="password" class="form-control" name="password" value="${teacher.password }">
					    	</div>
					    	<div class="col-md-3 teacher_info">6到12个字符</div>
					    	
					    </div>
					    
					    <div class="form-group">
					    	<label class="col-md-4 control-label">机构：</label>
					    	<div class="col-md-5">
					    		<input type="text" class="form-control" name="org" value="${teacher.org }">
					    	</div>
					    </div>
					    
					    <div class="form-group">
					    	<label class="col-md-4 control-label">职位：</label>
					    	<div class="col-md-5">
					    		<input type="text" class="form-control" name="position" value="${teacher.position }">
					    	</div>
					    </div>
					    
					    <div class="form-group">
					    	<label class="col-md-4 control-label">联系方式：</label>
					    	<div class="col-md-5">
					    		<input type="text" class="form-control" name="phone" value="${teacher.phone }">
					    	</div>
					    </div>
					    
					    <div class="form-group">
					    	<label class="col-md-4 control-label">地址：</label>
					    	<div class="col-md-5">
					    		<input type="text" class="form-control" name="address" value="${teacher.address }">
					    	</div>
					    </div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default">确定</button>
						<button class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
        
        
    </div>
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
    	$(".carousel").carousel();
    	$(function(){
    		var id=${teacher.id};
    		$.ajax({
    			type:'post',
    			data:{teacherId:id},
    			url:"showCompetitionByTeacher.do",
    			success: function(data){
    			    var dataHtml="";
    				$.each(data , function(index,obj){
    					var oDate = new Date(obj.endTime);
    					var oYear=oDate.getFullYear();
    					var oMonth=oDate.getMonth()+1;
    					var oDay = oDate.getDate();
    					var oTime=oYear+"-"+oMonth+"-"+oDay;
    					var competitionId=obj.id;
    					/* dataHtml+="<input type='hidden' name='id' value='"+obj.id+"'>"; */
    					dataHtml+="<ul class='competition_nav'>";
    					dataHtml+="<li><a href='showCompanyByCompetitionId.do?id="+competitionId+"'>"+obj.name+"</a></li>"
    					dataHtml+="<li>"+obj.license+"</li>";
    					dataHtml+="<li>"+obj.currentQuarter+"</li>";
    					dataHtml+="<li>"+obj.team+"</li>";
    					dataHtml+="<li>"+obj.member+"</li>";
    					dataHtml+="<li>"+oTime+"</li>";
	    				dataHtml+="<li>"+obj.status+"</li>";
    					/* if(obj.status=="已通过"){
    					}else{
    						dataHtml+="<li><a href='checkCompetition.do?competitionId="+competitionId+"'>审核竞赛</a></li>";
    					} */
    					
    					dataHtml+="</ul>";
    		  		});
    		  		$(".competiton_body").append(dataHtml);
    			}
    		});
    	})
        $(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
        function designCompetition(){
            window.open('teacher_design_competition.jsp?id=${teacher.id}', 'newwindow', 'height=520, width=800, top=60, left=240, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
        function teacherQuery(){
    		window.open("jsp/teacherQuery/teacherQuery.jsp",'newwindow', 'height=520, width=1100, top=60, left=90, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
    	}
        
        function teacherReference(){
            window.open('teacherReferenceBook.do', 'newwindow', 'height=520, width=800, top=60, left=160, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function aboutUs(){
            window.open('profile.jsp', 'newwindow', 'height=520, width=800, top=60, left=160, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        function customerService(){
            window.open('customerService.jsp', 'newwindow', 'height=520, width=800, top=60, left=240, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
    </script>
</body>
</html>