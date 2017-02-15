<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加小区管理员</title>
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
	var account = document.getElementById("account").value;
	if(!password || !account)
	{
		alert("账号和密码不能为空,请重新输入!");
		return false;
	}

 	if(confirm("确认要添加吗？"))
    	return true;
  	else
    	return false;
}
</script>
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>添加小区管理员</h1>
        <form method="post" action="<c:url value='/sysadmin/addComadmin' />" onsubmit="return validator()">
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>账号：</label>
                <input id="account" type="text" name="user_account">
            </div>
            <div class="new-pwd">
                <label>密码：</label>
                <input id="password" type="password" name="user_password">
            </div>
            <!--  
            <div class="new-pwd new-pwd-img">
                <label>头像：</label>
                <a href="javascript:;" class="info-upload">
                    <input type="file" name="" id="" >添加头像
                </a>
                <div class="detail-content">
                    <img src="<c:url value='/upload/default.jpg' />">
                </div>
            </div>
            -->
            <div class="new-pwd">
                <label>性别：</label>
                <div class="clearfix sex">
                    <div class="fl sex-box">
                        <p class="male fl" >
                            <img src="../../images/male.png" />
                        </p>
                        <input checked="checked" name="user_sex" type="radio" value="男"/>
                    </div>
                    <div class="fl sex-box">
                        <p class="female fl">
                            <img src="../../images/female.png" />
                        </p>
                        <input name="user_sex" type="radio" value="女"/>
                    </div>
                </div>
            </div>
            <div class="new-pwd">
                <label>昵称：</label>
                <input type="text" name="user_nickname">
            </div>
            <div class="new-pwd">
                <label>邮箱：</label>
                <input type="text" name="user_email">
            </div>
            <div class="new-pwd">
                <label>身份证：</label>
                <input type="text" name="user_card">
            </div>
            <div class="new-pwd">
                <label>真实姓名：</label>
                <input type="text" name="user_realname">
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交">
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>