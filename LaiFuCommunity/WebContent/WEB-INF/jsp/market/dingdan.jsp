<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>确认订单-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gwc.css"/>
</head>
<body>
<%@ include file="head.jsp" %>
<div style="width: 1200px;margin:30px auto;background-color:#3b3b3b;height: 50px;line-height: 50px;text-align: center;font-size: 22px;font-family: \5FAE\8F6F\96C5\9ED1;color: #FFFFFF;">
    确认订单-莱福小区超市
</div>
<div class="gwc_outer">
    <div class="gwc_head">
        <div class="gwc_head1 fl">商品图片</div>
        <div class="gwc_head2 fl" style="width: 500px;">商品名称</div>
        <div class="gwc_head3 fl">单价</div>
        <div class="gwc_head4 fl">数量</div>
        <div class="gwc_head5 fl">金额</div>
        <div class="cf" style="height: 0;"></div>
    </div>
    <div class="gwc_info_outer">
        <div class="gwc_info_one" id="商品id1">
            <div class="fl">
                <img src="${pageContext.request.contextPath}/images/sp.jpg"/>
            </div>
            <div class="fl" style="width: 500px;">奥利奥饼干18</div>
            <div class="fl">￥39.00</div>
            <div class="gwc_number fl">1</div>
            <div class="fl">￥39.00</div>
            <div class="cf" style="height: 0;"></div>
        </div>


        <div class="gwc_info_one" id="商品id2">
            <div class="fl">
                <img src="${pageContext.request.contextPath}/images/sp.jpg"/>
            </div>
            <div class="fl" style="width: 500px;">奥利奥饼干18</div>
            <div class="fl">￥39.00</div>
            <div class="gwc_number fl">1</div>
            <div class="fl">￥39.00</div>
            <div class="cf" style="height: 0;"></div>
        </div>


        <div class="gwc_info_one" id="商品id3">
            <div class="fl">
                <img src="${pageContext.request.contextPath}/images/sp.jpg"/>
            </div>
            <div class="fl" style="width: 500px;">奥利奥饼干18</div>
            <div class="fl">￥39.00</div>
            <div class="gwc_number fl">1</div>
            <div class="fl">￥39.00</div>
            <div class="cf" style="height: 0;"></div>
        </div>
    </div>
    <form action="#" method="post">
        <div class="fr" style="width: 450px;height: 35px;margin-top: 20px">
            <label style="width: 100px;font-size: 19px;color: #aaaaaa;">收货地址：</label>
            <input type="text" placeholder="请输入收货地址" style="width: 338px;height: 35px;padding-right: 10px;font-size: 18px;text-align: right;border: 1px solid #aaaaaa;"/>
        </div>
        <div class="cf"></div>
        <div class="gwc_jiesuan_outer" style="background-color: #FFFFFF">
            <div class="fr" style="width: 450px;height: 100px;border: 2px solid #58cf2a;padding: 20px;">
                <p style="color: #3b3b3b;font-size: 15px;font-weight: bold;line-height: 30px;">需付款：<span style="font-size: 28px;color:#dd2727;">￥39.9</span></p>
                <p style="color: #3b3b3b;font-size: 15px;font-weight: bold;line-height: 30px;">送货至：<span style="color: #777;font-weight: normal">1栋352房</span></p>
                <p style="color: #3b3b3b;font-size: 15px;font-weight: bold;line-height: 30px;">收货人：<span style="color: #777;font-weight: normal">钟国楚&nbsp;&nbsp;&nbsp;18318260421</span></p>
            </div>
            <div class="cf"></div>
            <input type="submit" class="fr" style="cursor:pointer;width: 150px;height: 50px;background-color:#58cf2a;text-align: center;line-height: 50px;font-size: 22px;color: #FFFFFF;" value="提交订单">
            <div class="cf"></div>
        </div>
    </form>
</div>
   
<%@ include file="footer.jsp" %>
</body>
</html>