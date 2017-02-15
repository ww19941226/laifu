<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>APP下载</title>
    <!-- 重置样式 -->
	<link href="<c:url value='/css/reset.css'/>" rel="stylesheet" />
	<!-- 主要样式 -->
	<link href="<c:url value='/css/laifucommunity_main_20160928.css'/>" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/css/index.css'/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/css/APPdownload.css'/>" type="text/css"/>
</head>
<body>
<!-- 导航条 -->

<nav class="nav-fix" style="display: block" id="navItem">
    <div class="container">
        <div class="nav fr">
            <ul class="clearfix">
                <li class="fl"><a href="<c:url value='/index' />">首页</a></li>
                        <li class="fl"><a href="<c:url value='/user/APPdownload' />">APP下载</a></li>
                        <li class="fl"><a href="<c:url value='/user/topic'/>?communityTopic=2">话题</a></li>
                        <li class="fl"><a href="<c:url value='/user/userCenter'/>">物业服务</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/user/userCenter'/>">个人中心</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/sysadmin/sysadmin_login'/>">管理平台</a></li>
            </ul>
        </div>
    </div>
</nav>
<!--下面的正文内容-->
<div class="A_content">
    <!--第一张图片，APP下载的图片-->
    <div class="Appdownload">
        <img src="images/APPdownloadfirst.png" alt="APP下载">
    </div>
    <!--第二张图片，APP特色功能的图片-->
    <div class="Apptesediv">
        <img src="images/Apptese.png">
    </div>
</div>


<!--底部链接和版权信息等-->
<div class="footer">
    <div class="link">
        <a href="#">关于我们</a>
        |
        <a href="index.html">莱福首页</a>
        |
        <a href="#">帮助</a>
    </div>
    <div class="copyright">
        <p>CopyRight @2016 Life All Right Reserved.莱福公司 版权所有</p>
    </div>
</div>
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/Appdownload.js'/>"></script>
</body>
</html>