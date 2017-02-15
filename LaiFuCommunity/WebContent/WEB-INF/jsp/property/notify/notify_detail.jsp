<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>日常通知</title>
</head>
<!-- cssreset -->
<link href="../../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../../css/laifucommunity.css" rel="stylesheet"/>
<script src="../../js/jquery-1.11.1.min.js"></script>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>日常通知</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>标题：</label>
                <div class="detail-content">${ notify.notify_titile }</div>
            </div>

            <div class="new-pwd">
                <label>时间：</label>
                <div class="detail-content"><fmt:formatDate value="${notify.notify_datetime}" pattern="yyyy-MM-dd  HH:mm:ss" /></div>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <div class="detail-content">
                   ${ notify.notify_content }
                </div>
            </div>
            <div class="new-pwd">
          
                <a  href="<c:url value='/property/${notify.notify_id}/gotonotify_edit'/>"  class="new-notify-btn" >修改</a>
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>