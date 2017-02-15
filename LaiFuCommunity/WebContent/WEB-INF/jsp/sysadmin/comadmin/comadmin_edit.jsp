<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改小区管理员</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<body class="grey">
<script>
function validator()
{
	var password = document.getElementById("password").value;
	if(!password)
	{
		alert("密码不能为空,请重新输入!");
		return false;
	}

 	if(confirm("确认要修改吗？"))
    	return true;
  	else
    	return false;
}
</script>
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改小区管理员</h1>
        <div class="new-pwd-box">
        	<form method="post" action="<c:url value='/sysadmin/updateComadmin' />?user_id=${user.user_id}" onsubmit="return validator()">
            <div class="new-pwd">
                <label>账号：</label>
                <input type="text" disabled="disabled" value="${ user.user_account }">
            </div>
            <div class="new-pwd">
                <label>密码：</label>
                <input id="password" type="password" name="user_password" value="${ user.user_password }">
            </div>
            <!--  
            <div class="new-pwd new-pwd-img">
                <label>头像：</label>
                <a href="javascript:;" class="info-upload">
                    <input type="file" name="" id="" >修改头像
                </a>
                <div class="detail-content">
                    <img src="<c:url value='${ user.user_head }' />">
                </div>
            </div>
            -->
            <div class="new-pwd">
                <label>性别：</label>
                <div class="clearfix sex">
                    <div class="fl sex-box">
                        <p class="male fl" >
                            <img src="<c:url value='/images/male.png' />" />
                        </p>
                        <input disabled="disabled" checked="checked" name="sex" type="radio" value="male"/>
                    </div>
                    <div class="fl sex-box">
                        <p class="female fl">
                            <img src="<c:url value='/images/female.png' />" />
                        </p>
                        <input disabled="disabled" name="sex" type="radio" value="female"/>
                    </div>
                </div>
            </div>
            <div class="new-pwd">
                <label>昵称：</label>
                <input type="text" name="user_nickname" value="${ user.user_nickname }">
            </div>
            <div class="new-pwd">
                <label>邮箱：</label>
                <input type="text" name="user_email" value="${ user.user_email }">
            </div>
            <div class="new-pwd">
                <label>身份证：</label>
                <input type="text" disabled="disabled" value="${ user.user_card }">
            </div>
            <div class="new-pwd">
                <label>真实姓名：</label>
                <input type="text" disabled="disabled" value="${ user.user_realname }">
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交">
            </div>
            </form>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>