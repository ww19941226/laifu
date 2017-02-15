<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    <link rel="stylesheet" href="<c:url value='/css/index.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/myHome.css'/>"/>
</head>
<body>
<!--头部logo和导航栏-->
<div class="header">
    <div class="logo">
        <img style="height: 50px;width: auto;" src="images/logo.png" alt="jpg"/>
    </div>
    <div class="nav">
        <a href="#">首页</a>
        <a href="#">APP下载</a>
        <a href="#">话题</a>
        <a href="#" style="font-weight: bold;border-bottom: 2px solid #0062AB;">个人中心</a>
        <a href="#">管理平台</a>
    </div>
</div>

<!--个人中心功能选项-->
<div class="page">
    <div id="on">我的通知</div>
    <div>财务统计</div>
    <div>自主修理</div>
    <div>投诉建议</div>
    <div>物业缴费</div>
    <div>缴费记录</div>
    <div>完善信息</div>
    <div>注销</div>
</div>
<iframe class="iframe" src="<c:url value='/user/perfectInformation'/>"></iframe>

<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script>
$(document).ready(function () {	
    $(".page>div").click(function () {
        $(this).siblings().removeAttr("id");
        $(this).attr("id","on");
    });
});
</script>
</body>
</html>