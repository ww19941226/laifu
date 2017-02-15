<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改缴费</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>

<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改缴费</h1>
        <form id="ffff" method="post" action="<c:url value='/property/updatepaymenttype' />?paymenttype_id=${paymenttype.paymenttype_id}" >       
        <div class="new-pwd-box">
            
            <div class="new-pwd">
                <label>费用类型名称：</label>
                <input type="text" id="paymenttype_name" disabled="disabled" name="paymenttype_name" value="${paymenttype.paymenttype_name}"  >
            </div>
            <div class="new-pwd">
                <label>单价：</label>
                <input type="text" id="paymenttype_money" name="paymenttype_money" value="${paymenttype.paymenttype_money}" >
            </div>
            <div class="new-pwd">
                <label>单位：</label>
                <input type="text" id="paymenttype_unit" disabled="disabled" name="paymenttype_unit" value="${paymenttype.paymenttype_unit}" >
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="修改">
                <label id="null_tip" style="color:red;display: none;">单价不能为空</label>
                <label id="toolong_tip" style="color:red;display: none;">单价必须为数值</label>
            </div>
        </div>
      </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script>
	$(document).ready(function(){
		
		$(".new-pwd-btn").click(function(){
			if($("#paymenttype_money").val()==""){
				$("#null_tip").show();
				return false;
			}
			else{
				$("#null_tip").hide();
			}
			console.log(parseFloat($("#paymenttype_money").val()));
			if(isNaN($("#paymenttype_money").val())){
				$("#toolong_tip").show();
				return false;
			}
			else{
				$("#toolong_tip").hide();
			}
		});
	});
</script>
</body>
</html>