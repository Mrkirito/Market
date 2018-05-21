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
<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/competition_content.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
    <div id="head">
        <div id="head-left">
            <img src="images/logo.png">
            <span id="head-title">Market</span>
        </div>
        <div id="head-right">
            <a href=""><span><i class="fa fa-user"></i></span></a>
            <div id="user-detail">
                <h3>账户详情</h3>
                <p>用户名：username</p>
                <p>其他信息一</p>
                <p>其他信息二</p>
            </div>
        </div>
        <div id="head-right2">
            <a href="login.jsp" data-toggle="tooltip" data-placement="left" data-original-title="退出" title=""><span><i class=" fa  fa-share"></i></span></a>
        </div>
    </div>
    <div class="split"></div>
    <div id="container">
        <div id="introduce">
        	<div class="content_1">
		        <div class="content_left">正在查看竞赛：</div>
		        <div class="content_right">${competition.name }</div>
		        <div class="split"></div>
            </div>
            <div class="content">
		        <div class="content_left">竞赛序列号：</div>
		        <div class="content_right">${competition.license }</div>
		        <div class="split"></div>
            </div>
            <div class="content">
		        <div class="content_left">当前季度：</div>
		        <div class="content_right">${competition.currentQuarter }</div>
		        <div class="split"></div>
            </div>
            <div id="to_index">
				<a href="teacher_index.jsp"><span><i class="fa fa-hand-o-right"></i></span>&nbsp;&nbsp;返回竞赛列表</a>          	
            </div>
        </div>
		<div class="split"></div>
		<div id="tab">
			<ul>
				<li>
                    <div>
                        <a href="#" onclick="policyDecision();"><span><i class="fa fa-tags"></i></span>&nbsp;&nbsp;当前季度决策</a>
                    </div>
                </li>
				<li>
                    <div>
                        <a href="" onclick="openGradeEvaluation();"><span><i class="fa fa-search"></i></span>&nbsp;&nbsp;成绩查询及评估</a>
                    </div>
                </li>
				<!-- <li>
                    <div><a href="" onclick="openChat();"><span><i class="fa fa-envelope"></i></span>&nbsp;&nbsp;在线聊天</a>
                    </div>
                </li> -->
				<li>
                    <div><a href="" onclick="openHelpDocument();"><span><i class="fa fa-edit"></i></span>&nbsp;&nbsp;帮助文档</a>
                    </div>
                </li>
				<li>
                    <div><a href="" onclick="competitionResult();"><span><i class="fa fa-trophy"></i></span>&nbsp;&nbsp;竞赛结果</a></div>
                </li>
				<li>
                    <div><a href="" onclick="teachingEvaluation();"><span><i class="fa fa-commenting"></i></span>&nbsp;&nbsp;教学质量评估</a>
                    </div>
                </li>
				<li>
                    <div><a href="" onclick="teacherTool();"><span><i class="fa fa-cog"></i></span>&nbsp;&nbsp;教员工具箱</a>
                    </div>
                </li>
				<li>
                    <div><a href="" onclick="teacherTextbook();"><span><i class="fa fa-file-text-o"></i></span>&nbsp;&nbsp;教材</a>
                    </div>
                </li>
			</ul>
		</div>
        <div>
            <!-- <ul class="nav1">
                <li><a href="">售后服务</a></li>
                <li><a href="">参考资料</a></li>
                <li><a href="">查找队员</a></li>
                <li><a href="" onclick="designCompetition();">设计竞赛</a></li>
                <li><a href="">关于我们</a></li>
            </ul> -->
        </div>
        <div id="main">
            <div id="main_title">
                <h3><span><i class="fa fa-list"></i></span>&nbsp;&nbsp;团队列表</h3>
            </div>
            <ul id="competition_title">
            	<li>#</li>
                <li>公司</li>
               
                <li>平衡计分卡</li>
                <li>提交时间</li>
                <li>本季度已使用时间</li>
                <li>查看团队决策</li>
                <li>查看队员</li>
                <li>提交决策</li>
            </ul>
            
            <%-- <c:forEach items="${companyList }" var="item">
            	<input type="hidden" value="${item.id}"/>
            	<ul class="competition_nav">
            		<li>${item.serialNumber}</li>
            		<li><a href="#">${item.name}</a></li>
            		<li><a href="#">${competition.currentQuarter }</a></li>
            		<li><a href="#" onclick="balanceScore(${item.id})">--</a></li>
            		<li><a href="#">-</a></li>
            		<li><a href="#">-</a></li>
            		<li><a href="#" onclick="openOperate(${item.id})">操作</a></li>
            		<li data-toggle="modal" data-target="#${item.serialNumber }" data-keyboard=true data-backdrop="static">查看</li>
            		<li><a href="#">-</a></li>
            	</ul>
            	<div class="modal fade" id="${item.serialNumber }">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times</button>
								<h4>${item.name }</h4>
							</div>
							<div class="modal-body">
								<div>${item.license}</div>
							</div>
							<div class="modal-footer">
								<!-- <button class="btn btn-default">确定</button>
								<button class="btn btn-default" data-dismiss="modal">取消</button> -->
							</div>
						</div>
					</div>
				</div>
			</c:forEach> --%>
			<c:forEach items="${companyResult }" var="item">
				
            	<input type="hidden" value="${item.company.id}"/>
            	<ul class="competition_nav">
            		<li>${item.company.serialNumber}</li>
            		<li><a href="#">${item.company.name}</a></li>
            		<li><a href="#" onclick="balanceScore(${item.company.id})">-</a></li>
            		<li><a href="#">-</a></li>
            		<li><a href="#">-</a></li>
            		<li><a href="#" onclick="openOperate(${item.company.id})">查看</a></li>
            		<li data-toggle="modal" data-target="#${item.company.serialNumber }" data-keyboard=true data-backdrop="static">查看</li>
            		<c:choose>
            			<c:when test="${item.companyQuarterTime.isSubmit!=1 }">
            				未提交
            				<li><a href="javascript:void(0)" onclick="companySubmit(${item.company.id})" class="">提交</a></li>
            			</c:when>
            			<c:otherwise>
            				已提交
            				<li><a href="javascript:void(0)" onclick="concelCompanySubmit(${item.company.id})" class="">取消提交</a></li>
            			</c:otherwise>
            		</c:choose>
            		
            	</ul>
            	<div class="modal fade" id="${item.company.serialNumber }">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times</button>
								<h4>${item.company.name }</h4>
							</div>
							<div class="modal-body">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<td>成员姓名</td>
											<td>邮箱</td>
										
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${item.memberList}" var="memberItem">
											<tr>
												<td>${memberItem.name}</td>
												<td>${memberItem.email}</td>
											</tr>
								
										</c:forEach>
									</tbody>
								</table>
								
							</div>
							<div class="modal-footer">
								<!-- <button class="btn btn-default">确定</button>
								<button class="btn btn-default" data-dismiss="modal">取消</button> -->
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
            
           <!--  <ul class="competition_nav">
            	<li>1</li>
                <li><a href="">上海致一电脑有限公司</a></li>
                <li>6</li>
                <li>3.008</li>
                <li>2017-02-09 10:29:26</li>
                <li>98</li>
                <li><a href="">操作</a></li>
                <li><a href="">队员</a></li>
                <li><a href="">提交</a></li>
            </ul>
            <ul class="competition_nav">
            	<li>1</li>
                <li><a href="">上海致一电脑有限公司</a></li>
                <li>6</li>
                <li>3.008</li>
                <li>2017-02-09 10:29:26</li>
                <li>98</li>
                <li><a href="">操作</a></li>
                <li><a href="">队员</a></li>
                <li><a href="">提交</a></li>
            </ul> -->
            
            
            <br>
        </div>
        <br>
        <br>
        <br>
        
        
    </div>
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
        function show(){
            window.open('teacher_score.jsp', 'newwindow', 'height=550, width=1120, top=20, left=80, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
        function designCompetition(){
            window.open('teacher_design_competition.jsp', 'newwindow', 'height=520, width=800, top=80, left=240, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
        $(function(){

        });
        function openChat(){
        	window.open('chatAndmail.jsp?id=${competition.id}', 'newwindow', 'height=550, width=1120, top=20, left=80, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
        
        function policyDecision(){
        	window.open("jumpPolicyDecision.do?id=${competition.id}", 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function competitionResult(){
            var message="查看本季度竞赛结果，请先去发布结果！";
        	window.open("jumpCompetitionResult.do?id=${competition.id}&message="+message, 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function openHelpDocument(){
        	window.open('jsp/teacherHelpDocument/HelpDocument.jsp', 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function openOperate(Id){
        	window.open('studentOperate.jsp?companyId='+Id, 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no')
        }
        
        function teacherTool(){
			window.open("jumpTeacherTool.do?id=${competition.id}" , 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no');	
		}
        function teacherTextbook(){
            //window.open('jsp/teacherTool/teacherTool.jsp', 'newwindow', 'height=550, width=1120, top=20, left=80, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        	window.open("jumpTeacherTextbook.do?id=${competition.id}", 'newwindow', 'height=570, width=1210, top=30, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no');
        }
        function balanceScore(Id){
            //window.open('loadBalanceScore.do?companyId='+Id, 'newwindow', 'height=550, width=850, top=60, left=240, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function teachingEvaluation(){
			window.open("jsp/teachingEvaluation/teachingEvaluation.jsp" , 'newwindow', 'height=550, width=1140, top=20, left=80, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no');	
		}
        function openGradeEvaluation(){
            window.open('gradeEvaluation.jsp?id=${competition.id}', 'newwindow', 'height=570, width=1210, top=60, left=120, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no')
        }
        
        function companySubmit(Id){
        	//页面居中
        	var yPosition =(screen.height-550)/2
			var xPosition =(screen.width-800)/2
			/* top =yPosition 
			left = xPosition */
        	
        	window.open("companySubmit.do?companyId="+Id+"&quarter=${competition.currentQuarter}&competitionId=${competition.id}", 'newwindow', 'height=550, width=800, top=60, left=120, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=yes, status=no');
            //window.location.href="companySubmit.do?companyId="+Id+"&quarter=${competition.currentQuarter}&competitionId=${competition.id}";
        	//alert("提交成功！");
        }
        
        function concelCompanySubmit(Id){
            window.location.href='concelCompanySubmit.do?companyId='+Id+'&quarter=${competition.currentQuarter}&competitionId=${competition.id}';
            //alert("已取消!");
        }
        
    </script>
</body>
</html>