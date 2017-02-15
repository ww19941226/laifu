<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>我的通知</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>我的通知</h1>
        <div class="table-content">
            <h2>${ notify.notify_titile}</h2>
            <span><fmt:formatDate value="${notify.notify_datetime }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
            <span>${user.user_realname}</span>
            <p>
            ${notify.notify_content}
            </p>
        </div>

    </div>
</section>
</body>
</html>