<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>尾部</title>
    <style>
        .footer-repeat{
            background:url("${pageContext.request.contextPath}/images/footer-repeat.png") repeat-x;
            height: 10px;
            width: 100%;
            margin-top: 25px;
        }
        .question{
            border-left: 1px dashed #cccccc;
            padding:10px 50px 20px 32px;
            line-height: 30px;
            height: 110px;
            float: left;
        }
        .phone{
            background: url("${pageContext.request.contextPath}/images/head.png") -237px -134px no-repeat;
            width: 310px;
            padding: 0;
            height: 140px;
            border-right: 1px dashed #cccccc;
        }
        a{
            text-decoration: none;
            color: #666;
        }
    </style>
</head>
  
<body>
<div class="footer-repeat"></div>
<div style="width: 1200px;margin: 30px auto 0;">
    <img src="${pageContext.request.contextPath}/images/service.png" style="width: 1000px;margin-left: 100px">
</div>
<div style="width: 100%;border-top: 1px solid #cccccc;margin-top: 20px;">
    <div style="width: 1000px;margin: 15px auto 0;">
        <div class="question">
            <div style="font-size: 22px;">新手指南</div>
            <a href="<c:url value='/market/gouwuliucheng/'/>">购物流程</a><br/>
            <a href="<c:url value='/market/jifenzhidu/'/>">积分制度</a>
        </div>
        <div class="question">
            <div style="font-size: 22px;">付款说明</div>
            <a href="<c:url value='/market/tuikuan/'/>">办理退款</a><br/>
            <a href="<c:url value='/market/zhifu/'/>">支付方式</a>
        </div>
        <div class="question">
            <div style="font-size: 22px;">配送说明</div>
            <a href="<c:url value='/market/yunfei/'/>">配送运费</a><br/>
            <a href="<c:url value='/market/shijian/'/>">配送时间</a>
        </div>
        <div class="question">
            <div style="font-size: 22px;">客户服务</div>
            <a href="<c:url value='/market/helpCenter/'/>">常见问题</a>
        </div>
        <div class="question phone"></div>
        <div style="clear: both"></div>
    </div>
</div>
<div class="footer-repeat"></div>
<div style="text-align: center;font-family: 'Microsoft YaHei';margin-top: 30px;">
    <div style="margin:10px 0 20px 0">
        <a href="关于我们">关于我们</a>|
        <a href="联系我们">联系我们</a>|
        <a href="网站地图">网站地图</a>|
        <a href="友情链接">友情链接</a>
    </div>
    <div style="color: #666;line-height: 16px;font-size: 14px;margin-bottom: 30px">
        <p style="margin:20px 0 20px 0">Copyright © 莱福智能生活小区超市 2016,All Rights Reserved</p>
        <p style="margin:20px 0 20px 0">凡本网注明“laifu.com”的所有作品，包括文字、图片、程序等，版权均属于莱福智能生活小区所有，未经同意，不得用于商业用途。</p>
        <p style="margin:20px 0 20px 0">本站通用网址：莱福智能生活小区</p>
        <p style="margin:20px 0 20px 0">全国统一服务电话：400-678-678</p>
    </div>
</div>
</body>
</html>
