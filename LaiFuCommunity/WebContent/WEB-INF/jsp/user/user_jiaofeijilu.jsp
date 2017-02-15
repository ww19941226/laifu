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
    
    <title>缴费记录</title>
    <link rel="stylesheet" href="<c:url value='css/index.css'/>"/>
    <link rel="stylesheet" href="<c:url value='css/user_jiaofeijilu.css'/>"/>

</head>
<body>
<!--头部logo和导航条-->
<div class="header">
    <div class="logo">
        <img style="height: 50px;width: auto;" src="images/logo.png" alt="jpg"/>
    </div>
    <div class="nav">
        <a href="#">首页</a>
        <a href="#">APP下载</a>
        <a href="#">自助报修</a>
        <a href="#">小区话题</a>
        <a href="#">物业缴费</a>
        <a href="#" style="font-weight: bold;border-bottom: 2px solid #0062AB;">缴费记录</a>
        <a href="#">个人中心</a>
        <a href="#">管理平台</a>
    </div>

</div>
<!--选择查询年份和费用类型-->
<div class="gongneng">
    <select>
        <option>请选择年份</option>
        <option>2016年</option>
        <option>2015年</option>
        <option>2014年</option>
        <option>2013年</option>
    </select>

    <select>
        <option>请选则缴费类型</option>
        <option>全部</option>
        <option>水费</option>
        <option>电费</option>
        <option>物业费</option>
        <option>停车费</option>
    </select>
    <button type="button">查询</button>
</div>
<!--柱状图插件chart.js-->
<canvas id="myChart" width="1000" height="500" class="chart"></canvas>
<br><br><br><br><br><br><br><br><br><br>

<!--底部链接和版权信息等-->
<div class="footer">
    <div class="link">
        <a href="#">关于我们</a>
        |
        <a href="#">莱福首页</a>
        |
        <a href="#">帮助</a>
    </div>
    <div class="copyright">
        <p>CopyRight @2016 Life All Right Reserved.莱福公司 版权所有</p>
    </div>
</div>
<script src="<c:url value='js/Chart-1.0.1-beta.4.js'/>"></script>
<script src="<c:url value='js/jiaofeijilu.js'/>"></script>

</body>
</html>