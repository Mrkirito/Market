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
<link rel="stylesheet" media="screen" href="css/teacher_design.css" />
<link href="css/lyz.calendar.css" rel="stylesheet" type="text/css" />
</head>
<form id="msform" action="designCompetition.do" method="post">
	<!-- progressbar -->
	<ul id="progressbar">
		<li class="active">step 1</li>
		<li>step 2</li>
		<li>step 3</li>
	</ul>
	<!-- fieldsets -->
	<fieldset>
		<h2 class="fs-title">竞赛信息</h2>
		<!-- <h3 class="fs-subtitle">This is step 1</h3> -->
		<div>
			<div class="design3_left">
				竞赛名称:
			</div>
			<div class="design3_right">
				<input type="text" maxLength="15" id="competition_name" name="name" placeholder="* 必填，4-15个字符 *" />
			</div>
		</div>
		<div>
			<div class="design3_left">开始时间:</div>
			<div class="design3_right"><input type="text" id="beginDate" name="start_time" placeholder="* 必填 *" /></div>
		</div>
		<div>
			<div class="design3_left">结束时间:</div>
			<div class="design3_right"><input type="text" id="endDate" onchange="checkTime()" name="end_time" placeholder="* 必填 *" /></div>
		</div>
		<input type="button" name="next" id="btn1" class="next action-button" style="display: none;margin-left: 218px" value="下一步" />
	</fieldset>
	<fieldset>
		 <h2 class="fs-title">团队信息</h2> 
		<label>竞赛团队数：</label><br>
		<select name="team" id="team">
			<option>--请输入--</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
		</select>
		<label>团队最大人数：</label>
		<select name="member" id="member">
			<option>--请输入--</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
		</select>
		
		<%  
            String id=request.getParameter("id");
        %>
		<input type="hidden" name="teacherId" value="<%=id%>"/>
		<input type="button" name="previous" class="previous action-button" value="返回" />
		<input type="button" id="btn2" name="next" class="next action-button" value="下一步" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">竞赛信息</h2>
		<label>竞赛季度数：</label>
		<select name="quarter">
			<option>5</option>
		</select>
		<label>实体店与网店销售比率：</label>
		<select name="physicalRate" id="physicalRate">
			<option value=0.6>--请输入--</option>
			<option value=0.8>8:2</option>
			<option value=0.7>7:3</option>
			<option value=0.6>6:4</option>
			<option value=0.5>5:5</option>
			<option value=0.4>4:6</option>
			<option value=0.3>3:7</option>
			<option value=0.2>2:8</option>
		</select>
		<input type="button" name="previous" class="previous action-button" style="margin-left: 120px" value="返回" />
		<input type="submit" id="btn3" name="next" class="next action-button" value="提交" />

	</fieldset>
</form>
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/easing.js" type="text/javascript"></script>
<script src="js/zzsc.js" type="text/javascript"></script>
<script type="text/javascript" src="js/lyz.calendar.min.js"></script>
<script type="text/javascript">

	$(function(){
		setInterval(check1,500);
		setInterval(check2,500);
		setInterval(check3,500);
	});

	var flag=false;
	
	$(function(){
		$("#competition_name").blur(function(){
			if($("#competition_name").val().length<3){
				alert("竞赛名称应在4-15个字符之间！");
			}
			var name = $("#competition_name").val();
			$.ajax({
			    url:"checkCompetitionName.do",
				data:{"name":name},
				success:function(data){
			        if("true"==data){
			            alert("竞赛已存在！");
					}
				}
			})
		});
	}); 
</script>
<script>
	function check1(){
		var name_length=$("#competition_name").val().length;
		var begin=$("#beginDate").val();
		var end=$("#endDate").val();
		if(3<name_length&&begin!=""&&end!=""&&flag==true){
            $("#btn1").css("display","block");
		}else{
			$("#btn1").css("display","none");
		}
	}
	function check2(){
		var team=$("#team").val();
		var member=$("#member").val();
		
		if(team=="--请输入--"||member=="--请输入--"){
            $("#btn2").css("display","none");
		}else{
            $("#btn2").css("display","inline");
		}
	}
	function check3(){
		
		var physicalRate=$("#physicalRate").val();
		if(physicalRate=="--请输入--"){
            $("#btn3").css("display","none");

        }else{
            $("#btn3").css("display","inline");

        }
	}
</script>

<script type="text/javascript">
	$(function(){
		$("#beginDate").calendar({
            controlId: "divDate",                  // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                            // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                      // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                        // 目标对象是否设为只读，默认：true
            lowerLimit: new Date(),    				// 日期下限，默认：NaN(不限制)
            callback: function () {                               // 点击选择日期后的回调函数
                checkTime();
            }
        });

        $("#endDate").calendar({
        	lowerLimit: new Date(),
            callback: function () {
                checkTime();
            }
        });
	});
</script>

<script>
    function checkTime(){
        var startTime = document.getElementById("beginDate").value.trim();
        var endTime   = document.getElementById("endDate").value.trim();
        if(startTime != "" && endTime != ""){
            var startTimeArray = startTime.split("-");
            var endTimeArray = endTime.split("-");
            var startDate = new Date(startTimeArray[0],startTimeArray[1],startTimeArray[2]);
            var endDate = new Date(endTimeArray[0],endTimeArray[1],endTimeArray[2]);
            var start = startDate.getTime();
            var end = endDate.getTime();
            if (start >= end) {
                alert('结束时间不得早于开始时间！');
				flag=false;
            }else{
				flag=true;
			}
        }
    }

</script>

</body>
</html>