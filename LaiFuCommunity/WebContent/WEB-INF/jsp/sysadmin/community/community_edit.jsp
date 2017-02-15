<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改小区</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script>
	$(document).ready(function(){
		$("#notify_titile").keyup(function(){
			if($(this).val().length>200){
			console.log(123);
				$("#tip").show();
			}
			else{
				$("#tip").hide();
			}
		});
		$(".new-pwd-btn").click(function(){
			if($("#notify_titile").val()==""){
				$("#null_tip").show();
				return false;
			}
			else{
				$("#null_tip").hide();
			}
			if($("#notify_content").val().length>200){
				$("#toolong_tip").show();
				return false;
			}
			else{
				$("#toolong_tip").hide();
			}
		});
	});
</script>
<body class="grey">

<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改小区</h1>
        <form method="post" action="<c:url value='/property/${ community.community_id }/updateCommunity' />" onsubmit="return validator()">
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>小区名：</label>
                <input disabled="disabled" id="name" type="text" name="community_name" value="${ community.community_name }">
            </div>
            <div class="new-pwd">
                <label>小区位置：</label>
                <input disabled="disabled" id="location" type="text" name="community_location" value="${ community.community_location }">
            </div>
            <div class="new-pwd">
                <label>小区简介：</label>
                <textarea id="notify_titile" name="community_indirect">${ community.community_indirect }</textarea>
                <label id="tip" style="color:red;display: none;">小区简介请不要超过200个字</label>
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交">
                <label id="null_tip" style="color:red;display: none;">小区简介不能为空</label>
                <label id="toolong_tip" style="color:red;display: none;">请按要求填写数据</label>
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>

</body>
</html>