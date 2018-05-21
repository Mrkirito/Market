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
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .panel{
            margin: 0px;
        }
        #notice1{
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
        	margin:10px 0 0 100px;
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
        <div class="panel-heading">产品用途</div>
        <div class="panel-body">
        	<ul class="nav nav-tabs">
                <li><a href="#notice1" data-toggle="tab"> 课程介绍</a></li>
                <li class="active"><a href="#notice2" data-toggle="tab">决策界面</a></li>
            </ul>
            
            <div class="tab-content">
                <div class="tab-pane fade in " id="notice1">
                	<div class="course_content">
                		<div class="text0"><label>考虑每个部分中客户的使用模式</label></div>

						<div class="text1">
							了解一个部分的应用程序对于选择满足该部分的组件和软件是至关重要的。 
						</div>	
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
							在工作区中，您将找到一些关于客户打算如何使用手机的信息。每个段都表示对应用程序最重要的应用程序。 
                   		</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
							注意任何值超过110的应用程序。价值低于100的应用程序可能会对客户吸引力产生负面影响
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade in active" id="notice2">
                	<table class="table table-bordered">
		                <thead>
		                    <tr class="success">
		                        <th></th>
		                        <th>实用型</th>
		                        <th>极致型</th>
		                        <th>商务型</th>
		                    </tr>
		                </thead>
		                    <tbody>
		                      <c:forEach items="${productUseList}" var="hyy">
		                    	<tr>
		                    	<td>${hyy.detail }</td>
		                    		<td>${hyy.practical }</td>
		                    		<td>${hyy.perfect }</td>
		                    		<td>${hyy.business }</td>
		                    
		                    	</tr>
		                    </c:forEach>
		                    </tbody>
		            </table>
		            <div id="main" style="width: 600px;height:400px;"></div>
                </div>
            </div>
        </div>
        <div class="panel-footer panel-info"></div>
    </div>

</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
           title: {
        text: '按用途划分的细分市场',
        subtext: '数据来自网络'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['实用型','极致型','商务型']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: []
    },
    series: [
        {
            name: '实用型',
            type: 'bar',
            data: [],
            color:['#61a0a8']
        },
        {
            name: '极致型',
            type: 'bar',
            data: [],
            color:['#d48265']
        },
        {
            name: '商务型',
            type: 'bar',
            data: [],
            color:['#91c7ae']
        },
        //['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3']
        
    ]
        };

        myChart.showLoading(); 
        var xa=[];
        var sz1=[];
        var sz2=[];
        var sz3=[];
        
        $.ajax({
			type:'post',
			url:"showUsageInfojson.do",
			success: function(data){
				
			    
				$.each(data , function(index,obj){
					
                    
					xa.push(obj.detail);
					sz1.push(obj.practical);
					sz2.push(obj.perfect);
					sz3.push(obj.business);
					
				
					
		  		});
			
				 myChart.hideLoading();    //隐藏加载动画
                 myChart.setOption({        //加载数据图表
                	 yAxis: {
                	        type: 'category',
                	        data: xa
                	    },
                	    series: [
                	        {
                	            name: '实用型',
                	            type: 'bar',
                	            data: sz1
                	        },
                	        {
                	            name: '极致型',
                	            type: 'bar',
                	            data: sz2
                	        },
                	        {
                	            name: '商务型',
                	            type: 'bar',
                	            data: sz3
                	        }

                	        
                	    ]
                 });
                 
		  	
			}
		});

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript" src="js/classIntroduction.js"></script>
</html>