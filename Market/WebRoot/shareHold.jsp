<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <style type="text/css">
        body{
            width: 98%;
            margin:5px;
            height: 90%;

        }
        .panel{
            margin: 0px;
        }
        .panel-body{
           
            background-size: cover;
            height: 420px;
        }
        .table{
            width: 500px;
            /*height: 300px;*/
            background:white;
        }
        #notice2{
        	min-height:420px;
        }
        
        .course_content{
        	border:1px solid #0ff;
        	margin-top:10px;
        	padding:20px;
        	min-height:220px;
        	font-size:14px;
        	letter-spacing:1px;
        }
        .left{
        	/* border:1px solid blue; */
        	width:450px;
        	padding:20px;
        	float:left;
        	background:#eee;
        	margin:10px 0 0 2px;
        	min-height:220px;
        }
        .left_title span i{
        	color:#009;
        	font-size:18px;
        	margin-right:6px;
        }
        
        
        .right{
        	/* border:1px solid blue; */
        	width:400px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 60px;
        	min-height:120px;
        }
        
        .right_title span i{
        	color:red;
        	font-size:22px;
        	margin-right:6px;
        }
        .text0{
        	margin-left:30px;
        	margin-top:10px;
        }
        .text1{
		text-indent:30px;
		margin-top:10px;
	}
	.text1_ul{
		margin-left:40px;
		margin-top:10px;
	}
	.text2{
        	margin-left:30px;
        	margin-top:25px;
        }
	.left_content li{
		margin-top:5px;
	}
	.tab-content{
		margin-top:10px;
	}
    </style>
</head>
<body>
    <div class="panel panel-info">
        <div class="panel-heading">内部持股</div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li class="active"><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in active" id="notice1">
                	<div class="course_content">
						<div class="text1">
							作为公司的拥有者，您的管理团队会通过认购公司股份的方式来进行注资。 在第一季度，团队将投资 2 百万。在接下来的两个季度中，团队将分别再追加 1 百万的投资。
						</div>
						<div class="text1">
							当前季度，您的团队将以每股 100 的价格认购 20,000 股。 公司可将这些资金（2 百万）用于当前季度的所有支出。 剩下的现金将延续到下个季度。
						</div>
						
                   	</div>
                   	
                </div>
                <div class="tab-pane fade" id="notice2">
                	<form class="form-horizontal">
		                <table class="table table-bordered">
		                <thead>
		                    <tr>
		                        <th>股票类型</th>
		                        <th>所有者姓名</th>
		                        <th>股数</th>
		                        <th>每股价格</th>
		                        <th>价值总计</th>
		                        <th>季度</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <tr>
		                        <td>${shareHold[0].stockType }</td>
		                        <td>${shareHold[0].owner }</td>
		                        <td>${shareHold[0].stockNumber }</td>
		                        <td>${shareHold[0].stockPrice }</td>
		                        <td>${shareHold[0].totalPrice }</td>
		                        <td>${shareHold[0].quarter }</td>
		                    </tr>
		                    <c:if test="${quarter>1 }">
		                    <tr>
		                        <td>${shareHold[1].stockType }</td>
		                        <td>${shareHold[1].owner }</td>
		                        <td>${shareHold[1].stockNumber }</td>
		                        <td>${shareHold[1].stockPrice }</td>
		                        <td>${shareHold[1].totalPrice }</td>
		                        <td>${shareHold[1].quarter }</td>
	                    	</tr>
		                    </c:if>
		                    <c:if test="${quarter>2 }">
		                    <tr>
		                        <td>${shareHold[2].stockType }</td>
		                        <td>${shareHold[2].owner }</td>
		                        <td>${shareHold[2].stockNumber }</td>
		                        <td>${shareHold[2].stockPrice }</td>
		                        <td>${shareHold[2].totalPrice }</td>
		                        <td>${shareHold[2].quarter }</td>
	                    	</tr>
		                    </c:if>
		                    <c:if test="${quarter>3 }">
		                    <tr>
		                        <td>${shareHold[3].stockType }</td>
		                        <td>${shareHold[3].owner }</td>
		                        <td>${shareHold[3].stockNumber }</td>
		                        <td>${shareHold[3].stockPrice }</td>
		                        <td>${shareHold[3].totalPrice }</td>
		                        <td>${shareHold[3].quarter }</td>
	                    	</tr>
		                    </c:if>
		                    <c:if test="${quarter>4 }">
		                    <tr>
		                        <td>${shareHold[4].stockType }</td>
		                        <td>${shareHold[4].owner }</td>
		                        <td>${shareHold[4].stockNumber }</td>
		                        <td>${shareHold[4].stockPrice }</td>
		                        <td>${shareHold[4].totalPrice }</td>
		                        <td>${shareHold[4].quarter }</td>
	                    	</tr>
		                    </c:if>
		                    
		                </tbody>
		                </table>
		            </form>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
</html>