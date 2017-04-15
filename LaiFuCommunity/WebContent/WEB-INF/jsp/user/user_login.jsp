<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>登录</title>
</head>
<!-- 重置样式 -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet" />
<!-- 主要样式 -->
<link href="<c:url value='/css/laifucommunity_main.css' />" rel="stylesheet" />
<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>

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
                                <img src="images/logo.png">
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
                        <li class="fl"><a class="nav-btn" href="<c:url value='/sysadmin/sysadmin_login'/>">管理平台</a></li>
                    </ul>
            </div>
        </div>
    </div>
</header>



<!-- advert -->
<section class="login">
    <div class="little-container">
        <div class="login-top"><h1><span class="fl"></span>欢迎登录</h1></div>
        <div class="location">
            <a href="<c:url value='/user/user_register'/>">我要注册？</a>
        </div>
        <div class="login-content">
        	<form class="l_form" action="<c:url value='/user/login'/>" method="post">
            <table>
                <tr>
                    <td width="26%">用户名</td>
                    <td><input id="user_account" type="text" name="user_account" placeholder="请输入用户名  ( :">
                        <div id="account_yz" class="table-error" style="color:#484873;"></div>
                    </td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td>
                        <input id="user_password" type="password" name="user_password" placeholder="请输入密码  ( :">
                        <div id="password_yz" class="table-error" style="color:#484873;"></div>
                    </td>
                </tr>
                <tr>
                    <td>验证码</td>
                    <td class="validate">
                        <input id="yzm" type="text" placeholder="请输入验证码">
                        <div class="validatebox fl">
                            <div class="validateImg fl"><span id="yzmimage"> e Q r T</span></div>
                            <a id="hyg" class="fl" href="javascript:void(0);" style="color: #66b3ff;">换一个</a>
                            <div id="yzm_yz" class="table-error validate-error" style="color:#484873;"></div>
                        </div>

                    </td>
                </tr>
            </table>
            <div class="login-btn">
                <input type="submit" id="f_login" value="登 录">
            </div>
            </form>
        </div>
    </div>
</section>

<!-- 尾部 -->
<footer class="login-footer">
    <div class="footer-main">
        <span>welcome laifu community ,thanks @author Raindrops、Lin、zepeng、jianshuo、tick、guochu</span>
    </div>
</footer>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		var i=2;
		var random=(Math.random()+"").slice(2,8);		
		$("#yzmimage").text(random);
		/* $("#yzmimage").css("background-image","url('/LaiFuCommunity/images/huawen/h1.png')"); */
		$("#hyg").click(function(){
			var imgurl="<c:url value='/images/huawen/h"+i+".png'/>";
			/* $("#yzmimage").css("background-image","url("+imgurl+")"); */
			//$("#yzmimage").css("color","#"+i+i+i);
			random=(Math.random()+"").slice(2,8);
			$("#yzmimage").text(random);
			i++;
			if(i==21){
				i=1;
			}
		});
		$(".login_btn").click(function(){
			if($("#yzm").val()!=random){
				alert("验证码不正确");
				return false;
			}
		});
		
		$("#f_login").click(function(){
			if($("#user_account").val()==""){
			$("#account_yz").css("color","#b32424");
				$("#account_yz").text("用户名不能为空！");
				return false;
			}
			if($("#user_password").val()==""){
				$("#password_yz").css("color","#b32424");
				$("#password_yz").text("密码不能为空！");
				return false;
			}
			$.post("<c:url value='/user/exit'/>",{user_account:$("#user_account").val()},function(data){
    			if(data==1){
    				$("#account_yz").css("color","#16f223");
    				$("#account_yz").text("用户名存在！");
    			}
    			else{
    				$("#account_yz").css("color","#b32424");
    				$("#account_yz").text("用户名不存在！");
    				return false;
    			}
    		});
    		$.post("<c:url value='/user/checkAccountAndPassword'/>",{user_account:$("#user_account").val(),user_password:$("#user_password").val()},function(data){
    			if(data==1){
    				$("#password_yz").css("color","#16f223");
    				$("#password_yz").text("密码正确！");
    				var yzm = $("#yzmimage").text();
    				if(yzm == $("#yzm").val()){
    					$("#yzm_yz").css("color","#16f223");
    					$("#yzm_yz").text("验证码正确！");
    					$(".l_form").submit();
    				}
    				else{
    					$("#yzm_yz").css("color","#b32424");
    					$("#yzm_yz").text("验证码不正确！");
    					return false;
    				}
    			}
    			else{
    				$("#password_yz").css("color","#b32424");
    				$("#password_yz").text("密码不正确！");
    				return false;
    			}
    		});
    		
			return false;
		});
	});
</script>

</html>