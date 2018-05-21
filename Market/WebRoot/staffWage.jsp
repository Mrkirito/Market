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
    <style type="text/css">
        body{
            width: 99%;
            margin:5px;
        }
        .panel{
            margin: 0px;
        }
        .panel-body{
            /*background:#2c343c;*/
        }
        .table{
            /*color:#999;*/
            margin-top:20px;
        }
        .panel-heading{
            /*font-weight: bold;
            letter-spacing: 2px;*/
        }
        #main,#main2{
            background: #2c343c;
            width: 400px;
            height: 300px;
        }
        #notice1{
        	min-height:420px;
        }
    #notice2,#notice3{
    	margin-top:30px;
    	
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
        	width:500px;
        	padding:20px;
        	float:left;
        	background:#abcdef;
        	margin:10px 0 0 20px;
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
        <div class="panel-heading">员工薪酬</div>
        <div class="panel-body">
            <ul class="nav nav-tabs">
                <li  class="active"><a href="#notice1" data-toggle="tab">课程介绍</a></li>
                <li><a href="#notice2" data-toggle="tab"> 销售人员薪酬</a></li>
                <li><a href="#notice3" data-toggle="tab"> 工厂工人薪酬</a></li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane fade in active " id="notice1">
                	<div class="course_content">
						<div class="text1">
							在争夺高素质销售人员和工厂工人方面的竞争相当激烈。 您提供的薪酬水平，相对于竞争对手和整个电子行业而言，将决定公司能雇佣并留住高素质员工的能力。 相对于行业整体水平的薪酬越高，员工的积极性就越高，因此生产效率也会更高。
						</div>
						
						<div class="text1">
							您将提供的薪酬组合包括了年薪、医疗福利、假期、以及退休金。 从第二季度进行市场开始，您将能够调整销售人员的薪酬组合。 之后您可以每个季度自由调整员工的薪酬组合。
						</div>
                   	</div>
                   	<div class="left">
                   		<div class="left_title">
                   			<span><i class="fa fa-tasks"></i></span><label>你的任务</label>
                   		</div>
                   		<div class="left_content">
                   			<ul>
                   				<li>请在相应决策界面查看电子行业销售人员薪酬组合的平均水平。 电子行业包括了电脑公司、影印公司、电话公司、公司等。</li>
                   				<li>注意薪酬组合哪些方面的改善对员工来说相对更重要。 薪酬组合各方面的相对重要值显示了销售人员对其的重视程度。 这些数值可能会随着时间的增长而改变。</li>
                   			</ul>
                   		</div>
                   	</div>
                   	<div class="right">
                   		<div class="right_title">
                   			<span><i class="fa fa-lightbulb-o"></i></span><label>决策小提示</label>
                   		</div>
                   		<div class="right_content">
                   			您及其它公司的员工都在不断寻求更高的薪酬。 每个季度，调研公司都会对行业内的所有员工进行问卷调查，以了解其最希望获得改善的薪酬组合方面。 在最初的几个季度内，他们最希望公司能提高其底薪。 随着工资的提高，他们的注意力将转移到其它方面，比如医疗福利、假期、及退休金。 然而，问卷调查结果可能与员工的实际反应有所不同。 所以，既要研究调查数据，也要观察竞争对手的工厂工人对其薪酬组合的反应。
                   		</div>
                   	</div>
                </div>
                <div class="tab-pane fade" id="notice2">
                    <table class="table table-bordered">
                        <thead>
                            <tr class="success">
                                <th>区域</th>
                                <th>工资（年薪）</th>
                                <th>福利</th>
                                <th>休假</th>
                                <th>公积金</th>
                                <th>企业年金</th>
                                <th>社会保险</th>
                                <th>年度成本</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${sales.getRegion()}</td>
                                <td>${sales.getSalary()}元</td>
                                <td>${sales.getWelfare()*sales.getSalary()/100}元</td>
                                <td>${sales.getHoliday()}天</td>
                                <td>${sales.getPublicFund()*sales.getSalary()/100}元</td>
                                <td>${sales.getCompanyPension()*sales.getSalary()/100}元</td>
                                <td>${sales.getRetiredPay()*sales.getSalary()/100}元</td>
                                <td>${sales.getCost()}元</td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="main"></div>
                </div>
                <div class="tab-pane fade" id="notice3">
                    <table class="table table-bordered">
                        <thead>
                            <tr class="success">
                                 <th>区域</th>
                                <th>工资(年薪)</th>
                                <th>福利</th>
                                <th>休假</th>
                                <th>公积金</th>
                                <th>企业年金</th>
                                <th>社会保险</th>
                                <th>年度成本</th>
                            </tr>
                        </thead>
                        <tbody>
                             <tr>
                                <td>${workers.getRegion()}</td>
                                <td>${workers.getSalary()}元</td>
                                <td>${workers.getWelfare()*sales.getSalary()/100}元</td>
                                <td>${workers.getHoliday()}天</td>
                                <td>${workers.getPublicFund()*sales.getSalary()/100}元</td>
                                <td>${workers.getCompanyPension()*sales.getSalary()/100}元</td>
                                <td>${workers.getRetiredPay()*sales.getSalary()/100}元</td>
                                <td>${workers.getCost()}元</td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="main2" style="width: 400px;height:300px;"></div>
                </div>
            </div>
        </div>
        <div class="panel-footer"></div>
    </div>
</body>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var myChart2 = echarts.init(document.getElementById('main2'));

        // 指定图表的配置项和数据
        option = {
    backgroundColor: '#2c343c',

    title: {
        text: 'Customized Pie',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:335, name:'福利'},
                {value:310, name:'休假'},
                {value:274, name:'五险一金'},
                {value:235, name:'退休金'},
                {value:400, name:'基本工资'}
            ].sort(function (a, b) { return a.value - b.value}),
            roseType: 'angle',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};


option2 = {
    backgroundColor: '#2c343c',

    title: {
        text: 'Customized Pie',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:335, name:'福利'},
                {value:310, name:'休假'},
                {value:274, name:'五险一金'},
                {value:235, name:'退休金'},
                {value:400, name:'基本工资'}
            ].sort(function (a, b) { return a.value - b.value}),
            roseType: 'angle',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart2.setOption(option2);
    </script>
    <script type="text/javascript" src="js/classIntroduction.js"></script>
</html>