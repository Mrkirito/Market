<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈立阳
  Date: 2017/9/25
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath %>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>最终检查</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        .subtitle2 {
            margin-left: 20px;
        }

        body {
            width: 99%;
            margin: 5px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${flag}">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    最终决策
                </h3>
            </div>
            <div class="panel-body">
                <label style="color: green">检查通过</label>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    最终决策
                </h3>
            </div>
            <div class="panel-body">

                <div class="subtitle">
                    <label>检查不通过，决策错误信息如下:</label><br>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>错误详情</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${hashMap.values()}" var="value">
                            <tr>
                                <td>
                                    <label style="color: red">${value}</label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<input type="hidden" id="flag" value="${flag}"/>
<button type="button" id="btnsubmit" class="btn btn-default" onclick="submit(${quarter},${flag})">提交</button>
<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">

    function submit(quarter, flag) {
        if (flag == false) {
            alert("提交失败");
            return;
        } else {
            alert("提交成功！");
            $.ajax({
                url: "submit.do",
                data: {quarter: quarter,flag:flag},
                type: "post",
                success: function (data, status) {
                    /* alert(data) */
                }
            });
        }
    }
</script>
<script>

    $(function(){
       var flag=$("#flag").val();
       if(flag=="false"){
           $("#btnsubmit").attr("disabled",true);
       }
    });

</script>
</body>
</html>