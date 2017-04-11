<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>缴费</title>
</head>
	<!-- 重置样式 -->
	<link href="<c:url value='/css/reset.css'/>" rel="stylesheet" />
	<!-- 主要样式 -->
	<link href="<c:url value='/css/laifucommunity_main_20160926.css'/>" rel="stylesheet" />
	<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>缴费</h1>
        <form action="<c:url value='/user/pay/paymoney'/>" method="post" target="_blank">
        <table class="user-table-add">
            <tr>
                <td align="right"  width="15%">订单号：</td>
                <td width="60%" ><input type="text" name="p2_Order"  value="${paymoneyvo.paymentid }"> </td>
            </tr>
            <tr>
            	 <td align="right"  width="15%">支付金额：</td>
            	 <td width="60%" ><input type="text" name="p3_Amt"   value="${paymoneyvo.paymoney}"> </td>
            </tr>
            <tr>
                <td align="right"  width="15%">选择银行：</td>
                <td width="60%" >
                    <p>中国农业银行<input type="radio" style="width: 5%;" name="pd_FrpId" value="ABC-NET-B2C"></p>
                    <p>建设银行<input type="radio" style="width: 5%;"  name="pd_FrpId"  value="CCB-NET-B2C"></p>
                    <p>中国银行<input type="radio" style="width: 5%;"  name="pd_FrpId"  value="BOC-NET-B2C"></p>
                    
                </td>
            </tr>
            <tr>
                <td align="right"  width="15%"></td>
                <td  width="60%" >
                    <input type="submit" value="支付">
                </td>
                <td style="color:red;"></td>
            </tr>
        </table>
        </form>
    </div>
</section>
</body>
</html>