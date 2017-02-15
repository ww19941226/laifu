<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>小区详情</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css'/>" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css'/>" rel="stylesheet"/>
<body class="grey">
<script>
function validator()
{
 	if(confirm("是否确认此操作？"))
    	return true;
  	else
    	return false;
}
</script>
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>小区详情</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>小区名：</label>
                <div class="detail-content">${ community.community_name }</div>
            </div>
            <div class="new-pwd">
                <label>注册时间：</label>
                <div class="detail-content"><fmt:formatDate value="${ community.community_regitime }" pattern="yyyy-MM-dd  HH:mm:ss" /></div>     
            </div>
            <div class="new-pwd">
                <label>小区位置：</label>
                <div class="detail-content">${ community.community_location }</div>
            </div>
            <div class="new-pwd">
                <label>小区简介：</label>
                <div class="detail-content">${ community.community_indirect }</div>
            </div>
            <div class="new-pwd">
                <a class="new-notify-btn" href="<c:url value='/sysadmin/${ community.community_id }/1/checkCommunity' />"  onclick="return validator()">通过</a><a class="new-notify-btn" href="<c:url value='/sysadmin/${ community.community_id }/2/checkCommunity' />"  onclick="return validator()">不通过</a>
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>