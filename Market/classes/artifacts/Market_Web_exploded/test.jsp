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

<link href="css/lyz.calendar.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-2.2.4.js" type="text/javascript"></script>
<script type="text/javascript" src="js/lyz.calendar.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#beginDate").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                       // 目标对象是否设为只读，默认：true
            upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)
            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
            callback: function () {                               // 点击选择日期后的回调函数
            }
        });
        $("#endDate").calendar();
    });
</script>

</head>
<body>
<h1>test</h1>
<!-- <img alt="背景" src="images/bgm1.jpg">
<form action="query" method="post">
	<input type="text" name="username"/>
	<input type="submit" value="提交">
	<br><br>
	
</form> -->
asdfsadfsadfasdfasd<input type="text" id="beginDate">
asdfasdfsadfsd<input type="text" id="endDate">
<input type="file">
</body>
</html>