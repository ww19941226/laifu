<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加通知</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>

<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>添加通知</h1>
        <form action="<c:url value='/property/addNotify' />" method="post">
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>标题：</label>
                <input type="text" id="notify_titile" name="notify_titile" maxlength="30">
                <label id="title_tip" style="color:red;display: none;">标题请不要超过30个字</label>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <textarea id="notify_content" name="notify_content" maxlength="200"></textarea>
                <label id="content_tip" style="color:red;display: none;">内容请不要超过200个字</label>
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交">
                <label id="null_tip" style="color:red;display: none;">标题和内容不能为空</label>
                <label id="toolong_tip" style="color:red;display: none;">请按要求填写数据</label>
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
<script src="/LaiFuCommunity/js/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#notify_titile").keyup(function(){
			if($(this).val().length>30){
			console.log(123);
				$("#title_tip").show();
			}
			else{
				$("#title_tip").hide();
			}
		});
		$("#notify_content").keyup(function(){
			if($(this).val().length>200){
				$("#content_tip").show();
			}
			else{
				$("#content_tip").hide();
			}
		});
		$(".new-pwd-btn").click(function(){
			if($("#notify_titile").val()==""||$("#notify_content").val()==""){
				$("#null_tip").show();
				return false;
			}
			else{
				$("#null_tip").hide();
			}
			if(($("#notify_titile").val().length>30)||($("#notify_content").val().length>200)){
				$("#toolong_tip").show();
				return false;
			}
			else{
				$("#toolong_tip").hide();
			}
		});
	});
</script>
</body>
</html>