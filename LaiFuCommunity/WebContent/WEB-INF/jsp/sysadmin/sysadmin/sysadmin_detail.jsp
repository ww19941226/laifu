<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>系统管理员详情</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>系统管理员详情</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>账号：</label>
                <div class="detail-content">${ user.user_account }</div>
            </div>
            <div class="new-pwd new-pwd-img">
                <label>头像：</label>
                <div class="detail-content">
                    <img src="<c:url value='${ user.user_head }' />">
                </div>
            </div>
            <div class="new-pwd">
                <label>性别：</label>
                <div class="detail-content">${ user.user_sex }</div>
            </div>
            <div class="new-pwd">
                <label>昵称：</label>
                <div class="detail-content">${ user.user_nickname }</div>
            </div>
            <div class="new-pwd">
                <label>邮箱：</label>
                <div class="detail-content">${ user.user_email }</div>
            </div>
            <div class="new-pwd">
                <label>身份证：</label>
                <div class="detail-content">${ user.user_card }</div>
            </div>
            <div class="new-pwd">
                <label>真实姓名：</label>
                <div class="detail-content">${ user.user_realname }</div>
            </div>
            <div class="new-pwd">
                <a class="new-notify-btn" href="<c:url value='/sysadmin/sysadmin/${user.user_id}/sysadmin_edit' />" >修改</a>
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>