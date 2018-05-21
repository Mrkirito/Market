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
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <style type="text/css">
        body{
            margin:0;
            /*border:2px solid blue;*/
        }
        a{
            font-size: 12px;
            

        }
        #menu{
            text-align: right;
        /*    border:1px solid #444;*/
            background: #f6f6f6;
            margin-right:-15px;
            padding-top: 2px;

        }
        #menu a{  
            /*border:1px solid red;*/
            margin-right:50px;
            margin-bottom: 5px;
            margin-top:5px;
            color:#666;
            cursor:pointer;
        }
        #menu a:hover{
            text-decoration: none;
        }
       /* #menu a:nth-last-child(1){
            margin-right:35px;
        }
        #menu a:nth-last-child(1):hover{
            margin-right:35px;
            color: yellow;
        }*/
    </style>
</head>
<body>
    <div id="menu">
       <a
        data-toggle="tooltip" data-placement="left" data-original-title="设置" title="">
            <span><i class="fa fa-cog fa-2x"></i></span>
        </a>

        <a
        data-toggle="tooltip" data-placement="left" data-original-title="帮助" title="">
            <span><i class="fa fa-question fa-2x"></i></span>
        </a>

        <a
        data-toggle="tooltip" data-placement="left" data-original-title="退出" title="">
            <span><i class="fa fa-sign-out fa-2x"></i></span>
        </a>

        <!-- <a href=""><span><i class="fa fa-cog fa-2x"></i></span>&nbsp;设置</a>
        <a href=""><span><i class="fa fa-question fa-2x"></i></span>&nbsp;帮助</a>
        <a href=""><span><i class="fa fa-remove fa-2x"></i></span>&nbsp;退出</a> -->
    </div>
    <script type="text/javascript" src="js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
    $(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
    </script>

</body>
</html>