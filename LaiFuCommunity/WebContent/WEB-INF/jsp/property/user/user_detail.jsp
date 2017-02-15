<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>业主详情</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script>
function validator()
{
 	if(confirm("确认执行此操作吗？"))
    	return true;
  	else
    	return false;
}
</script>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>业主详情</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>账号：</label>
                <div class="detail-content">${ user.user_account }</div>
            </div>
            <!-- 
            <div class="new-pwd new-pwd-img">
                <label>头像：</label>
                <div class="detail-content">
                    <img src="<c:url value='${ user.user_head }' />">
                </div>
            </div>
             -->
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
                <!-- <a class="new-notify-btn" href="<c:url value='/property/user/${user.user_id}/user_edit' />" >修改</a>-->
                <c:if test="${ user.user_checkstate == 0 }">
                	 <a class="new-notify-btn" href="<c:url value='/property/user/${user.user_id}/1/usercheck' />" onclick="return validator()">通过</a><a class="new-notify-btn" href="<c:url value='/property/user/${user.user_id}/2/usercheck' />"  onclick="return validator()">不通过</a>
                </c:if>
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>