<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.laifu.module.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta charset="UTF-8">
    <title>安全中心</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160928.css" rel="stylesheet" />
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
  
  <body>
  <form action="<c:url value='/user/updatepsw'/>" target="_parent"  method="post">
    <section class="user-content">
    <div class="table-box">
        <h1>安全中心</h1>
        <table class="user-table-add user-table-info">
      
            <tr>
                <td class="user-table-td" width="15%">旧&nbsp;密&nbsp;码：</td>
                <td width="60%" ><input type="password" placeholder="请输入您的旧密码" id="oldpsw" name="user_password"> </td>
                <td style="color:#b32424;"  id="old"></td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%">新&nbsp;密&nbsp;码：</td>
                <td width="60%" ><input type="password" placeholder="请输入您的新密码" id="newpsw" name="newpassword"> </td>
                <td style="color:#b32424;" id="new"></td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%">重&nbsp;复&nbsp;密&nbsp;码：</td>
                <td width="60%" ><input type="password" placeholder="请重复输入" id="newpsw1"> </td>
                <td style="color:#b32424;" id="new1"></td>
            </tr>
            <tr>
                <td align="right"  width="15%"></td>
                <td  width="60%" >
                    <input type="submit" value="修改密码" id="update_psw">
                </td>
            </tr>
        </table>
    </div>
</section>
</form>
  </body>
  <script type="text/javascript">
  $(document).ready(function(){
  $("#update_psw").click(function(){
    if($("#oldpsw").val()==""){
	$("#old").text("旧密码不能为空！");
				return false;
    }
    if($("#newpsw").val()==""){
	$("#new").text("新密码不能为空！");
				return false;
    }
       if($("#newpsw1").val()==""){
	$("#new").text("重复密码不能为空！");
				return false;
    }
      if($("#newpsw").val()!=$("#newpsw1").val()){
	$("#new1").text("两次密码输入不一样！");
				return false;
    }
    var user_account = '<%=((User)session.getAttribute("user")).getUser_account()%>';
    
    $.post("<c:url value='/user/checkAccountAndPassword'/>",{user_account:user_account,user_password:$("#oldpsw").val()},function(data){

   if(data==1){
   $("#old").text("旧密码正确");
   $("form").submit();
  //window.location.href = "<c:url value='/user/user_login'/>";
   }
   else{
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
