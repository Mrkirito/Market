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
<link rel="stylesheet" type="text/css" href="css/frameset.css">
</head>
    <frameset rows="120,540" frameborder="0" framespacing="0" >
        <frame id="top" src="top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="tops" />

        <frameset cols="190,*"   id="frame">

            <frame src="left1.jsp" name="leftFrame" noresize="noresize" marginwidth="" marginheight="" frameborder="0" scrolling="no" target="lefts" />

            <frameset rows="25,*">
                <frame id="main-top" src="top2.jsp" name="leftFrame" noresize="noresize" marginwidth="" marginheight="" frameborder="0" scrolling="no" target="lefts" />

                <frame id="main" src="welcome.jsp" name="main" marginwidth="100" marginheight="0" frameborder="0" scrolling="auto" target="main" />
            </frameset>    
        </frameset>
    </frameset>
<noframes>
  <body>
  </body>
</noframes>
</html>