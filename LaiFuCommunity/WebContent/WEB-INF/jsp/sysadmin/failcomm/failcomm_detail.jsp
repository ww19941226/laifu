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
<link href="../../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../../css/laifucommunity.css" rel="stylesheet"/>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>小区详情</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>状态：</label>
                <div class="detail-content failpass">未通过审核</div>
            </div>
            <div class="new-pwd">
                <label>小区名：</label>
                <div class="detail-content">${ community.community_name }</div>
            </div>
            <div class="new-pwd">
                <label>审批人：</label>
                <div class="detail-content">${ community.community_approver }</div>
            </div>
            <div class="new-pwd">
                <label>注册时间：</label>
                <div class="detail-content">${ community.community_regitime }</div>
            </div>
            <div class="new-pwd">
                <label>审批时间：</label>
                <div class="detail-content">${ community.community_approvaltime }</div>
            </div>
            <div class="new-pwd">
                <label>小区位置：</label>
                <div class="detail-content">${ community.community_location }</div>
            </div>
            <div class="new-pwd">
                <label>小区简介：</label>
                <div class="detail-content">${ community.community_indirect }</div>
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>