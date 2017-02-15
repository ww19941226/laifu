<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>费用缴纳</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<script src="../js/jquery-1.11.1.min.js"></script>
<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>费用缴纳</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="complains-excel fl"><a href="#">导出Excel</a></div>
            <div class="complains-excel"><a href="#">导入Excel</a></div>
            <tr>
                <th width="10%">序号</th>
                <th width="10%">缴费类型</th>
                <th width="20%">费用时间</th>
                <th width="15%">用户</th>
                <th width="12%">缴费时间</th>
                <th width="12%">截止时间</th>
                <th>费用总价</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="r" varStatus="loop">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${ id }</td>
                <td>${ paymenttype[loop.count-1].paymenttype_name}</td>
                <td><fmt:formatDate value="${ r.payment_starttime }" /> -  <fmt:formatDate value="${ r.payment_endtime }" /> </td>
                <td>${user[loop.count-1].user_realname }</td>
                <td><fmt:formatDate value="${ r.payment_complettime }" /></td>
                <td><fmt:formatDate value="${ r.payment_deadtime }" /></td>
                <td>${ r.payment_units }元</td>
            </tr>
           	</c:forEach>
        </table>

    </div>
    <div class="page complains-page">
        <common:pageV3 url="/property/property_payment" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>