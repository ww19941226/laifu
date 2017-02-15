<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加缴费</title>
    <!-- 引入日期控件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script src="../../js/jquery-1.11.1.min.js"></script>

<body class="grey">
<div class="pro-info-content clearfix">             
    <div class="password-content">    
        <h1>添加缴费类型</h1>      
     <form id="ffff" method="post" action="<c:url value='/property/addpaymenttype' />" >       
        <div class="new-pwd-box">
            
            <div class="new-pwd">
                <label>费用类型名称：</label>
                <input type="text" id="paymenttype_name" name="paymenttype_name"  >
                <label id="tip" style="color:red;display: none;">费用类型名称请不要超过10个字</label>
            </div>
            <div class="new-pwd">
                <label>单价：</label>
                <input type="text" id="paymenttype_money" name="paymenttype_money" >
                <label id="number_tip" style="color:red;display: none;">单价必须为数值</label>
            </div>
            <div class="new-pwd">
                <label>单位：</label>
                <input type="text" id="paymenttype_unit" name="paymenttype_unit" >
                <label id="unit_tip" style="color:red;display: none;">单位请不要超过10个字</label>
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="添加">
                <label id="null_tip" style="color:red;display: none;">费用类型名称、单价和单位都不能为空</label>
                <label id="toolong_tip" style="color:red;display: none;">请按要求填写数据</label>
            </div>
        </div>
      </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script>
	$(document).ready(function(){
		$("#paymenttype_name").keyup(function(){
			if($(this).val().length>10){
				$("#tip").show();
			}
			else{
				$("#tip").hide();
			}
		});
		$("#paymenttype_money").keyup(function(){
			if(isNaN($(this).val())){
				$("#number_tip").show();
			}
			else{
				$("#number_tip").hide();
			}
		});
		$("#paymenttype_unit").keyup(function(){
			if($(this).val().length>10){
				$("#unit_tip").show();
			}
			else{
				$("#unit_tip").hide();
			}
		});
		$(".new-pwd-btn").click(function(){
			if($("#paymenttype_money").val()==""||$("#paymenttype_name").val()==""||$("#paymenttype_unit").val()==""){
				$("#null_tip").show();
				return false;
			}
			else{
				$("#null_tip").hide();
			}
			console.log(parseFloat($("#paymenttype_money").val()));
			if(isNaN($("#paymenttype_money").val())||$("#paymenttype_name").val().length>10||$("#paymenttype_unit").val().length>10){
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