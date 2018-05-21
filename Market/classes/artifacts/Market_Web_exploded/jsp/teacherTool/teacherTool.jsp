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
	<title>教员工具箱</title>
	<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/score.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    
<style type="text/css">
	.yincang{
		display: none;
	}
	.list-group div{
	
	}
	ul{
		list-style:none;
	}
	.accordion {
	/* margin-left:100px; */
	
 	width: 260px;
 	max-width: 360px;
 	/* margin: 30px auto 20px; */
 	background: #FFF;
 	-webkit-border-radius: 4px;
 	-moz-border-radius: 4px;
 	border-radius: 4px;
 }

	
.accordion .link {
	cursor: pointer;
	margin-left:-30px;
	display: block;
	padding: 15px 2px 15px 2px;
	
	text-indent:10px;
	color: #4D4D4D;
	font-size: 14px;
	font-weight: 700;
	border-bottom: 1px solid #CCC;
	position: relative;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li:last-child .link {
	border-bottom: 0;
}

.accordion li i {
	position: absolute;
	top: 16px;
	left: 12px;
	font-size: 18px;
	color: #595959;
	-webkit-transition: all 0.4s ease;
	-o-transition: all 0.4s ease;
	transition: all 0.4s ease;
}

.accordion li i.fa-caret-right {
	right: 12px;
	left: auto;
	font-size: 16px;
}

.accordion li.open .link {
	color: #b63b4d;
}

.accordion li.open i {
	color: #b63b4d;
}
 .accordion li.open i.fa-caret-right {
	-webkit-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-o-transform: rotate(90deg);
	transform: rotate(90deg);
} 

/**
 * Submenu
 -----------------------------*/
 .submenu {
 	
 	display: none;
 	background: #444359;
 	font-size: 14px;
 	
 	margin-left:-30px;
 	text-indent:30px;
 }

 .submenu li {
 	border-bottom: 1px solid #4b4a5e;
 }

 .submenu a {
 	display: block;
 	text-decoration: none;
 	color: #d9d9d9;
 	padding: 12px;
 	margin-left:-38px;
 	padding-left: 10px;
 	-webkit-transition: all 0.25s ease;
 	-o-transition: all 0.25s ease;
 	transition: all 0.25s ease;
 }

 .submenu a:hover {
 	background: #b63b4d;
 	color: #FFF;
 }
	
</style>
</head>
<body>
	<div id="content">
		<div class="row" id="row1">
			<div class="col-md-3">
				<div id="top_left">
					<i class=" fa fa-caret-left"></i>
					&nbsp;&nbsp;<span>当前季度：${competition.currentQuarter }</span>&nbsp;&nbsp;
					<i class="fa fa-caret-right"></i>
				</div>
			</div>
			<div class="col-md-7">
				<h3></h3>
			</div>
			<div class="col-md-2">
				<input type="hidden" name="id" value="${competition.id}">
				<label for="">${competition.name }</label>
			</div>
		</div>
		<div class="row" id="row2">
			<div class="col-md-3" style="width:300px;padding-left:15px;margin-left:0px;">
				<%-- <div id="left" style="width:260px">
					<div class="list-group" style="width:220px">
					  <a class="list-group-item" href="javascript:void(0)" style="background-color: #ddd"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; 竞赛设置<i class="fa fa-caret-right"></i></a>
					  <div class="xianshi">
					  	<a class="list-group-item" href="teacherTool/competitionName.do?id=${competition.id}" target="main">●&nbsp;竞赛名称</a>
					  	<a class="list-group-item" href="teacherTool/competitionDealDate.do?id=${competition.id}" target="main">●&nbsp;竞赛处理日程</a>
					  	<a class="list-group-item" href="teacherTool/competitionTeamMemberNumber.do?id=${competition.id}" target="main">●&nbsp;每支参赛队伍的学员数</a>
					  	<a class="list-group-item" href="jsp/teacherTool/competitionGrantAccess.jsp" target="main">●&nbsp;授予访问权限</a>
					  	<a class="list-group-item" href="jsp/teacherTool/competitionEmailNotification.jsp" target="main">●&nbsp;电子邮件通知</a>
					  	<a class="list-group-item" href="jsp/teacherTool/competitionAdvancedOption.jsp" target="main">●&nbsp;高级选项</a>
					  </div>
					  <a class="list-group-item" href="javascript:void(0)"  style="background-color: #ddd"><i class="fa fa-book fa-fw" aria-hidden="true"></i>&nbsp; 竞赛选项<i class="fa fa-caret-right"></i></a>
					  <div class="yincang">
					  	<a class="list-group-item" href="jsp/teacherTool/stockSetting.jsp" target="main">●&nbsp;股票设定</a>
					  	<a class="list-group-item" href="jsp/teacherTool/fineAndOtherIncome.jsp" target="main">●&nbsp;罚款和其他收入</a>
					  	<a class="list-group-item" href="jsp/teacherTool/technologyLicensing.jsp" target="main">●&nbsp;技术授权</a>
					  	<a class="list-group-item" href="jsp/teacherTool/dicisionLocks.jsp" target="main">●&nbsp;Decision Locks</a>
					  </div>
					  <a class="list-group-item" href="javascript:void(0)"  style="background-color: #ddd"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i>&nbsp; 学员管理<i class="fa fa-caret-right"></i></a>
						<div class="yincang">
					  	<a class="list-group-item" href="teacherTool/studentRegisterNotes.do?id=${competition.id}" target="main">●&nbsp;学员注册说明</a>
					  	<a class="list-group-item" href="jsp/teacherTool/studentBatchRegister.jsp" target="main">●&nbsp;批次注册</a>
					  	<a class="list-group-item" href="teacherTool/studentMove.do?id=${competition.id}" target="main">●&nbsp;移动参赛队员</a>
					  	<a class="list-group-item" href="teacherTool/studentRegistryRight.do?id=${competition.id}" target="main">●&nbsp;访问权限</a>
					  	<a class="list-group-item" href="jsp/teacherTool/studentSumbitLog.jsp" target="main">●&nbsp;决策提交日志</a>
					  	<a class="list-group-item" href="jsp/teacherTool/studentFeedback.jsp" target="main">●&nbsp;学员反馈</a>
					  	</div>
					</div>
				</div> --%>
				<ul id="accordion" class="accordion">
					<li>
						<div class="link">竞赛设置<i class="fa fa-caret-right"></i></div>
						<ul class="submenu">
							<li><a  href="teacherTool/competitionName.do?id=${competition.id}" target="main">&nbsp;竞赛名称</a></li>
						  	<li><a  href="teacherTool/competitionDealDate.do?id=${competition.id}" target="main">&nbsp;竞赛处理日程</a></li>
						  	<li><a  href="teacherTool/competitionTeamMemberNumber.do?id=${competition.id}" target="main">&nbsp;每支参赛队伍的学员数</a></li>
						  	<!-- <li><a  href="jsp/teacherTool/competitionGrantAccess.jsp" target="main">&nbsp;授予访问权限</a></li>
						  	<li><a  href="jsp/teacherTool/competitionEmailNotification.jsp" target="main">&nbsp;电子邮件通知</a></li>
						  	<li><a  href="jsp/teacherTool/competitionAdvancedOption.jsp" target="main">&nbsp;高级选项</a></li> -->
						</ul>
					</li>
					<!-- <li>
						<div class="link">竞赛选项<i class="fa fa-caret-right"></i></div>
						<ul class="submenu">
							<li><a href="jsp/teacherTool/stockSetting.jsp" target="main">&nbsp;股票设定</a></li>
						  	<li><a href="jsp/teacherTool/fineAndOtherIncome.jsp" target="main">&nbsp;罚款和其他收入</a></li>
						  	<li><a href="jsp/teacherTool/technologyLicensing.jsp" target="main">&nbsp;技术授权</a></li>
						  	<li><a href="jsp/teacherTool/dicisionLocks.jsp" target="main">&nbsp;Decision Locks</a></li>
						</ul>
					</li> -->
					<li>
						<div class="link">学员管理<i class="fa fa-caret-right"></i></div>
						<ul class="submenu">
							<li><a href="teacherTool/studentRegisterNotes.do?id=${competition.id}" target="main">&nbsp;学员注册说明</a></li>
						  	<!-- <li><a href="jsp/teacherTool/studentBatchRegister.jsp" target="main">&nbsp;批次注册</a></li> -->
						  	<li><a href="teacherTool/studentMove.do?id=${competition.id}" target="main">&nbsp;移动参赛队员</a></li>
						  	<%-- <li><a href="teacherTool/studentRegistryRight.do?id=${competition.id}" target="main">&nbsp;访问权限</a></li>
						  	<li><a href="jsp/teacherTool/studentSumbitLog.jsp" target="main">&nbsp;决策提交日志</a></li>
						  	<li><a href="jsp/teacherTool/studentFeedback.jsp" target="main">&nbsp;学员反馈</a></li> --%>
						</ul>
					</li>
				</ul>
			</div>
			
			<div class="col-md-9">
				<iframe id="myiframe" src="teacherTool/competitionName.do?id=${competition.id}" name="main" frameBorder=0 width=850 marginheight="0" scrolling="no"></iframe>	
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
    	/* function changeModel(aNode){

    		var divNode=aNode.nextSibling.nextSibling;
    		
    		if(divNode.className=="yincang"){
    			divNode.className="xianshi";
    		}else{
    			divNode.className="yincang";
    		}
    	} */
    	$(function(){   		
    		$(".list-group").children("a").click(function(){
    			$(".xianshi,.yincang").hide();       		
        		$(this).next("div").show();
    		});
    		
    	});


        $("#myiframe").load(function () {
            var mainheight = $(this).contents().find("body").height() + 20;
            $(this).height(mainheight);
            //注意：这里设置了最小长度
        });
        $(function() {
        	var Accordion = function(el, multiple) {
        		this.el = el || {};
        		this.multiple = multiple || false;

        		// Variables privadas
        		var links = this.el.find('.link');
        		// Evento
        		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
        	}

        	Accordion.prototype.dropdown = function(e) {
        		var $el = e.data.el;
        			$this = $(this),
        			$next = $this.next();

        		$next.slideToggle();
        		$this.parent().toggleClass('open');

        		if (!e.data.multiple) {
        			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        		};
        	}	

        	var accordion = new Accordion($('#accordion'), false);
        });
    	
    </script>
</body>
</html>