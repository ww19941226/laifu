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
    
    <title>日常通知</title>
    <link rel="stylesheet" href="<c:url value='/css/xitong_index.css'/>"/>
</head>
<body>
<!--头部logo和导航栏-->
<div class="header">
    <div class="logo">
        <p>欢迎来到 莱福 物业管理员后台</p>
    </div>
    <div class="nav">
        <a href="#" style="font-weight: bold;border-bottom: 3px solid white;">日常通知</a>
        <a href="#">投诉处理</a>
        <a href="#">缴费通知</a>
        <a href="#">保修处理</a>
        <a href="#">业主身份验证</a>
        <a href="#">提交财务信息</a>

    </div>

</div>


<!--日常通知列表-->
<div class="w_outer">
    <p class="info">日常通知</p>
    <div class="w_content">
        <div class="c_head">
            <div class="h_title">
                标题
            </div>
            <div class="h_time">
                发布时间
            </div>
        </div>
        <hr>
		<!-- 一条通知 -->
        <div class="c_content">
            <div class="c_title">
                关于缴纳水费电费的通知
            </div>
            <div class="c_time">
                2016-09-01
            </div>
            <div class="c_neirong off">
                8月份的费用清单已经出来，请大家于9月5号前缴费，不然将作停水停电处理，特此通知！
            </div>
        </div>
        <!-- 一条通知 -->
        <div class="c_content">
            <div class="c_title">
                关于缴纳水费电费的通知
            </div>
            <div class="c_time">
                2016-09-01
            </div>
            <div class="c_neirong off">
                8月份的费用清单已经出来，请大家于9月5号前缴费，不然将作停水停电处理，特此通知！
            </div>
        </div>
        <!-- 一条通知 -->
        <div class="c_content">
            <div class="c_title">
                关于缴纳水费电费的通知
            </div>
            <div class="c_time">
                2016-09-01
            </div>
            <div class="c_neirong off">
                8月份的费用清单已经出来，请大家于9月5号前缴费，不然将作停水停电处理，特此通知！
            </div>
        </div>
        <!-- 一条通知 -->
        <div class="c_content">
            <div class="c_title">
                关于缴纳水费电费的通知
            </div>
            <div class="c_time">
                2016-09-01
            </div>
            <div class="c_neirong off">
                8月份的费用清单已经出来，请大家于9月5号前缴费，不然将作停水停电处理，特此通知！
            </div>
        </div>
		<!-- 一条通知 -->
        <div class="c_content">
            <div class="c_title">
                关于停水停电的通知
            </div>
            <div class="c_time">
                2016-09-05
            </div>
            <div class="c_neirong off">
                在2016年09月08日上午10点到下午4点，我们将对旧水管进行翻新，届时停水6个小时，不便之处，敬请谅解，特此通知！
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br><br><br>

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


<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script src="<c:url value='/js/wuye_index.js'/>"></script>
</body>
</html>