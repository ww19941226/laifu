<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>费用详情</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>费用详情</h1>
        <div class="table-content">
            <h2>${ payvo.paymenttype.paymenttype_name}</h2>
            <span><fmt:formatDate value="${ payvo.payment.payment_starttime}"  />----<fmt:formatDate value="${ payvo.payment.payment_endtime}"  /></span>
            <p>缴费截止时间:<span><fmt:formatDate value="${ payvo.payment.payment_deadtime}"  /> </span></p>
            <p>费用总价:<span>${payvo.paymenttype.paymenttype_money * payvo.payment.payment_units }元</span></p>
        </div>
        <div class="user-solve-btn">
            <a href="<c:url value='/user/pay/${payvo.payment.payment_id}/${payvo.paymenttype.paymenttype_money * payvo.payment.payment_units}/paymoney'/>">缴费</a>
        </div>
    </div>
</section>
</body>
</html>