<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
    <%@page import="com.laifu.module.vo.UserVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<!-- cssreset -->
<link href="../../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../../css/laifucommunity.css" rel="stylesheet"/>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改密码</h1>
        <form method="post" action="<c:url value='/comadmin/updatePassword' />">
       <div class="new-pwd-box">
            <div class="new-pwd">
                <label>旧密码：</label>
             <input type="password" id="oldpsw" name="oldpassword">
                <div style="color:#ea3f18;"  id="old"></div>
            </div>
            <div class="new-pwd">
                <label>新密码：</label>
                <input type="password" id="newpsw" name="user_password">
                <div style="color:#16f223;" id="new"></div>
            </div>
            <div class="new-pwd">
                <label>重复密码：</label>
         <input type="password"  id="newpsw1">
                <div style="color:#ea3f18;" id="new1"></div>
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交" id="update_psw">
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
<script type="text/javascript">
  $(document).ready(function(){
  $("#update_psw").click(function(){
    if($("#oldpsw").val()==""){
    $("#old").css("color","#b32424");
	$("#old").text("旧密码不能为空！");
				return false;
    }
    if($("#newpsw").val()==""){
   $("#new").css("color","#b32424");
	$("#new").text("新密码不能为空！");
				return false;
    }
       if($("#newpsw1").val()==""){
   $("#new1").css("color","#b32424");
	$("#new1").text("重复密码不能为空！");
				return false;
    }
      if($("#newpsw").val()!=$("#newpsw1").val()){
   $("#new1").css("color","#b32424");
	$("#new1").text("两次密码输入不一样！");
				return false;
    }
    var user_account = '<%=((UserVo)session.getAttribute("admin")).getUser().getUser_account()%>';
    
    $.post("<c:url value='/sysadmin/checkAccountAndPassword'/>",{user_account:user_account,user_password:$("#oldpsw").val()},function(data){

   if(data==1){
   $("#old").css("color","#16f223");
   $("#old").text("旧密码正确");
   $("form").submit();
  //window.location.href = "<c:url value='/user/user_login'/>";
   }
   else{
   $("#old").css("color","#16f223");
   $("#old").text("旧密码错误");
      return false;
   }
   });
   return false;
});
	return false;
  });

  </script> 
</html>