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
    <title>错误及警告</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        .subtitle2{
            margin-left: 20px;
        }
        body{
            width: 99%;
            margin:5px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${flag}">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    错误及警告
                </h3>
            </div>
            <div class="panel-body">
                <h2>${finalCheck}</h2>
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
                    <label>创建公司</label><br>
                    <div class="subtitle2 row">
                        <c:choose>
                            <c:when test="${not empty finalCheck.companyName}">
                                ${finalCheck.companyName}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="subtitle">
                    <label>目标策略</label><br>
                    <div class="subtitle2 row">
                        <c:choose>
                            <c:when test="${not empty finalCheck.strategyInfo}">
                                ${finalCheck.strategyInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="subtitle">
                    <label>人力资源</label><br>
                    <div class="subtitle2 row">
                        <label>职位分配</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.positionInfo}">
                                ${finalCheck.positionInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                    <div class="subtitle2 row">
                        <label>团队规则</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.ruleInfo}">
                                ${finalCheck.ruleInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="subtitle">
                    <label>销售渠道</label><br>
                    <div class="subtitle2 row">
                        <c:choose>
                            <c:when test="${not empty finalCheck.companyName}">
                                ${finalCheck.companyName}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="subtitle">
                    <label>生产制造</label><br>
                    <div class="subtitle2 row">
                        <label>品牌管理</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.productInfo}">
                                ${finalCheck.productInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                    <div class="subtitle2 row">
                        <label>固定产能</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.capacityInfo}">
                                ${finalCheck.capacityInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="subtitle">
                    <label>财务</label><br>
                    <div class="subtitle2 row">
                        <label>公司持股</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.stockInfo}">
                                ${finalCheck.stockInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                    <div class="subtitle2 row">
                        <label>定期存款</label>
                        <c:choose>
                            <c:when test="${not empty finalCheck.depositInfo}">
                                ${finalCheck.depositInfo}
                            </c:when>
                            <c:otherwise>决策合理</c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<script type="text/javascript" src="js/jquery-2.2.4.js"></script>
<script type="text/javascript">

</script>
</body>
</html>