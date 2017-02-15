<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
</head>
<!-- 重置样式 -->
<link href="<c:url value='/css/reset.css'/>"  rel="stylesheet" />
<!-- 主要样式 -->
<link href="<c:url value='/css/laifucommunity_main.css'/>" rel="stylesheet" />
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>

<body>
<!--头部-->
<header>
    <div class="header-container clearfix">
        <div class="header-left">
                <!--logo-->
                <h1 class="fl">
                    <a href="index.html">
                        <div class="logo">
                            <div class="logo-header"> <div class="logo-img">
                                <img src="<c:url value='/images/logo.png'/>">
                            </div></div>
                        </div>
                    </a>
                </h1>
                <!-- phone -->
                <div class="header-phone fl">
                    <em>欢迎致电:</em>
                    <p class="clearfix"><span class="fl"></span>18318260149.<i>(Mon - Sun. 8:00.AM - 6:00.PM)</i></p>
                </div>
            </div>
        <!-- nav -->
        <div class="header-right fr">
            <div class="nav">
                <ul class="clearfix">
                        <li class="fl"><a href="<c:url value='/index' />">首页</a></li>
                         <li class="fl"><a href="<c:url value='/market/index'/>">小区超市</a></li>
                        <li class="fl"><a href="<c:url value='/user/topic'/>?communityTopic=2">话题</a></li>
                        <li class="fl"><a href="<c:url value='/user/userCenter'/>">物业服务</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/user/userCenter'/>">个人中心</a></li>
                        <li class="fl"><a class="nav-btn" href="">管理平台</a></li>
                    </ul>
            </div>
        </div>
    </div>
</header>



<!-- advert -->
<section class="login">
    <div class="little-container">
        <div class="login-top"><h1><span class="fl"></span>欢迎注册</h1></div>
        <div class="location">
            <a href="<c:url value='/user/user_login'/>">我要登录？</a>
        </div>
      <form class="l_form" action="<c:url value='/user/register'/>" method="post">
        <div class="login-content">
            <table>
                <tr>
                    <td width="26%">用户名</td>
                    <td><input type="text" id="phonenumber" name="user_account" placeholder="请输入手机号  ( :">
                        <div id="account_yz" class="table-error" style="color: #484873;">
                    
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td>
                        <input type="password"  id="user_password" name="user_password" placeholder="请输入密码  ( :">
                        <div id="pass_yz" class="table-error" style="color:#484873;">
                     
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>确认密码</td>
                    <td>
                        <input id="user_repassword" type="password"  name="password1" placeholder="请输入重复密码  ( :">
                        <div id="password_yz" class="table-error" style="color: #484873;">
                    
                        </div>
                    </td>
                </tr>
                <tr>
                    <td >验证码</td>
                    <td class="validate ">
                        <input type="text" id="codeNumber" placeholder="请输入验证码" name="codeNumber">
                        <input id="getcode" type="button" style="width:160px;margin-left: 1.8em;" value="获取手机验证码" onclick="settime(this)" />
                        <div class="validatebox fl">
                            <div class="validateImg fl"></div>
                            <div class="table-error validate-error" style="color:#484873;">
                              <div style="width: 350px;" class="showmsg"></div>
                            </div>
                        </div>

                    </td>
                </tr>
            </table>
            <div class="login-btn">
                <input type="submit"  class="register_btn" value="注 册">
            </div>
        </div>
        </form>
    </div>
</section>

<!-- 尾部 -->
<footer class="login-footer" >
    <div class="footer-main">
        <span>welcome laifu community ,thanks @author Raindrops、Lin、zepeng、jianshuo、tick、guochu</span>
    </div>
</footer>

<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">

$(document).ready(function(){
    $(".register_btn").click(function(){
        var phoneNumber=$("#phonenumber").val();
        var codeNumber=$("#codeNumber").val();
        var user_password=$("#user_password").val();
        var user_repassword=$("#user_repassword").val();
        if(phoneNumber.length!=11){
    		$("#account_yz").css("color","#b32424");
    		$("#account_yz").text("请输入正确的手机号码！");
    		return false;
    	}
    	else{
    		$("#account_yz").css("color","#16f223");
    		$("#account_yz").text("手机号码格式正确！");
    	}
    	if(user_password==""){
    		$("#pass_yz").css("color","#b32424");
    		$("#pass_yz").text("请输入密码！");
    		return false;
    	}
    	else{
    		$("#pass_yz").css("color","#16f223");
    		$("#pass_yz").text("密码可用！");
    	}
        if(user_password==user_repassword){
        	$("#password_yz").css("color","#16f223");
    		$("#password_yz").text("两次密码一致！");
        }
        else{
        	$("#password_yz").css("color","#b32424");
    		$("#password_yz").text("两次密码不一致！");
    		return false;
        }
        $.post("<c:url value='/user/checkCode'/>",{code:codeNumber},function(data){
            console.log(data);
            if(data=="1"){
                $(".l_form").submit();
            }else{
            	$(".showmsg").css("color","#b32424");
                $(".showmsg").text("手机验证码不正确");
                return false;
            }
        },"json");
        return false;
    });
});

var countdown=60;
function settime(val) {
    //拿到手机号码
    var phonenumber=document.getElementById("phonenumber").value;
    if(phonenumber.length!=11){
    	$("#account_yz").css("color","#b32424");
    	$("#account_yz").text("请输入正确的手机号码！");
    	return false;
    }
    $.post("<c:url value='/user/exit'/>",{"user_account":phonenumber},function(data){
    	if(data==1){
    		$("#account_yz").css("color","#b32424");
    		$("#account_yz").text("手机号码已存在，请登录！");
    		return false;
    	}
    	else{
    		$("#account_yz").css("color","#16f223");
    		$("#account_yz").text("手机号码可以使用！");
    		//发送异步请求
    		$.post("<c:url value='/user/code'/>",{"user_account":phonenumber},function(){
    		},"json");
    		//开始倒计时
    		timefunction(val);
    		}
    	
    },"json");
    console.log(phonenumber);
    
}
function timefunction(val){
    if (countdown == 0) {
        val.removeAttribute("disabled");
        val.value="获取验证码";
        countdown = 60;
        window.clearInterval(f);
    } else {
        val.setAttribute("disabled", true);
        val.value="重新发送(" + countdown + ")";
        countdown--;
        f=setTimeout(function() {
            timefunction(val)
        },1000)
    }
}

</script>
</body>
</html>