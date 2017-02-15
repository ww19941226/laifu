<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>投诉建议</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script>
function validator()
{
 	if(confirm("确认要回复吗？"))
    	return true;
  	else
    	return false;
}
</script>	
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>投诉建议</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>标题：</label>
                <div class="detail-content">${ complains.complains_id }</div>
            </div>
            <div class="new-pwd">
                <label>投诉人手机：</label>
                <div class="detail-content">${ complains.complains_phone }</div>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <div class="detail-content">${ complains.complains_content }</div>
            </div>
            <form id="reply" method="post" action="<c:url value='/comadmin/replyComplains' />?complains_id=${ complains.complains_id }"  onsubmit="return validator()">
            <div class="new-pwd">
                <label>回复内容：</label>
                <textarea id="complains_replycontent" name="complains_replycontent">${ complains.complains_replycontent }</textarea>
            </div>
            <div id="massage" style="color:red;font-size:15px">
            
            </div>
            <div class="new-pwd">
                <input id="submitAction" class="new-pwd-btn" type="submit" value="回复">
            </div>
            </form>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>