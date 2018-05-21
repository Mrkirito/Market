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
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function(){
			//用隐藏域记录信息
			var info=$("#myHidden1").val();
			var name=$("#myHidden2").val(); 
			var former_name=$("#myHidden3").val();
			var obj=document.getElementById("company_name");
			
			if(info==""){
				//第一次进入页面
				obj.value=former_name;
			}
			//点击提交后进入页面
			else if(info=="公司名称必须在4到12个字符！"){
				alert(info);
				obj.value=former_name;
				
			} 
			else {
				alert(info);
				obj.value=name;
			}
			
			
	})
	
</script>

    <style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .panel{
            margin: 0px;
        }
        .panel-body{
            /*border:2px solid red;*/
            padding: 0px;
            min-height:480px;

        }
        #content{
            background: url(images/office.jpg) ;
            background-size: cover;
            opacity:0.9;
            height: 500px;
            width: 100%;
            position: relative;
        }
        #content #office{
            /*border:2px solid red;*/
            width: 600px;
            position: absolute;
            top:130px;
            left:80px;
        }
        #content #office label{
            color: rgba(220,220,220,.8);
            font-size: 15px;
        }
        #content #changeName{
            margin-left: -40px;
        }
        #content form{
            
        }
        .tab-content{
        	margin-top:10px;
        }
        .img{
        	text-align:center;
        }
        .text_left{
        	
        	width:500px;
        	heith:200px;
        	float:left;
        	margin-left:50px;
        	text-indent:30px;
        }
        .text_right{
        	
        	width:400px;
        	height:200px;
        	float:left;
        	background:#eee;
        	margin-left:50px;
        	padding:10px;
        }
        .text_task{
        	text-indent:30px;
        }
    </style>
</head>
<body>
    <div class="panel panel-info">
        <div class="panel-heading">创建公司</div>
        <div class="panel-body">
        
        	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in " id="notice1">
                	<div class="img">
                		<img src="images/quarter1_bg_3.jpg">
                	</div>
                	<div class="text_left">
                		好的名称能够树立一个良好的第一印象。 这可以影响那些不熟悉您的人对您的看法和期望。 然而，公司形象和公司名称的含义最终将由您的竞争行为及商业风格来决定。 毕竟，SONY这 四个字母原本没有多大的意义，但是如今这四个字母代表了一个高专业度、精益求精的集体。
		        	</div>
		        	<div class="text_right">
		        		<span><i class="fa fa-tasks"></i></span>&nbsp;<label>您的任务</label>
		        		<div class="text_task">
		        			您想为公司树立什么样的形象？ 在相应的决策界面，为公司选定一个能够体现其竞争形象的名称。 需要谨记的是，您的公司名称对终端用户、竞争对手、以及潜在投资者而言意味着什么。
		        		</div>
		        	</div>
		        	
                </div>
                <div class="tab-pane fade in active" id="notice2">
                	<div id="content">
		                <div id="office">
		                    <form class="form-horizontal" action="UpdateCompanyName.do" method="post" role="form">
		                        <div class="form-group">
		                            <label for="text" class="col-xs-2 control-label">公司名称：</label>
		                            <div class="col-xs-5">
		                                <input type="text" class="form-control" id="company_name" name="name" placeholder="Enter Name" >
		                            </div>
		                            <div id="submitName" class="col-xs-2"><button class="btn btn-primary">提交</button></div>
		                           <input id="myHidden1" type="hidden" name="myHidden" value="${info }"/>
		                           <input id="myHidden2" type="hidden" name="myHidden" value="${name }"/>
		                           <input id="myHidden3" type="hidden" name="myHidden" value="${former_name }"/>
		                        </div>
		                    </form>
		                </div>
		            </div>
                </div>
          	</div> 
        </div>
        <div class="panel-footer"></div>
    </div>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/classIntroduction.js"></script>
   

</body>
</html>