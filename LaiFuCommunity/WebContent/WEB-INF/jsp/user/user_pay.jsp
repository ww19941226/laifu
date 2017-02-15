<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>费用缴纳</title>
</head>
<!-- 重置样式 -->
<link href="../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>费用缴纳</h1>

        <div class="complains-find">

        </div>
        <table>
            <div class="table-btn fl"></div>
            <tr>
                <th width="15%">序号</th>
                <th width="30%">费用类型</th>
                <th width="40%">费用清单</th>
                <th>缴费状态</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="pay">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${id}</td>
            	<td>${ pay.paymenttype.paymenttype_name }</td>
                <td><a href="<c:url value='/user/${pay.payment.payment_id}/pay_detail'/>">点击查看</a></td>
                <td>${ pay.payment.payment_paystate==0?"未缴费":"已缴费" }</td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="page pay-page">
    	<common:pageV3 url="/user/user_pay"></common:pageV3>
    </div>
    <!-- <div class="page complains-page">
        <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a>
    </div> -->
</section>
</body>
</html>