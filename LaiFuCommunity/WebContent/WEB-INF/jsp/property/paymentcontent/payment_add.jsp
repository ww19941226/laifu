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
<script>
function validator()
{
	 var starttime = document.getElementById("starttime").value;
	 var endtime = document.getElementById("endtime").value;
	 var number = document.getElementById("number").value;
	 var deadtime = document.getElementById("deadtime").value;
	 var units = document.getElementById("units").value;
	
	 if(starttime ==""|| endtime =="" || number=="" || deadtime=="" || units==""){
	 	alert("内容不能为空,请重新输入");
	 	return false;
	 }
	 
	 if(units.length>7)
	{
		alert("费用数量过大,请重新输入!");
		return false;
	}

	
	if( !(IsNum(number)) ||  number.length != 11 )
	{
		alert("用户手机号必须为11位数字,请重新输入!");
		return false;
	}
	$.post("<c:url value='/property/isLaterTime'/>",{payment_starttime:starttime,payment_endtime:endtime},function(data){
		if(data==0){
			alert("费用产生结束时间早于开始时间，请重新选择");
		}
		else{
			$.post("<c:url value='/property/isLaterNow'/>",{payment_deadtime:deadtime},function(data){
				if(data==0){
					alert("缴费截止时间早于现在，请重新选择");
					return false;
				}
				else{
					if(confirm("将发布通知给业主，是否确定提交？"))
				    	$("#ffff").submit();
				  	 else
				    	return false;
				}
			});
		}
	});
	return false;
}
	
function IsNum(num){
 	var reNum=/^\d*$/;
	return(reNum.test(num));
}
</script>

<body class="grey">
<div class="pro-info-content clearfix">             
    <div class="password-content">    
        <h1>添加缴费</h1>      
     <form id="ffff" method="post" action="<c:url value='/property/addpayment' />" >       
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>缴费类型</label>                   
                <select name="paymenttype_id">
                <c:forEach items="${ paymenttypelist }" var="paymenttype" >
                    <option value="${ paymenttype.paymenttype_id}">${ paymenttype.paymenttype_name }</option>
                  </c:forEach>
                </select>
            </div>
            <div class="new-pwd">
                <label>费用产生起始时间：</label>
                <input type="text" id="starttime" name="payment_starttime" value="${command.registerDate}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>费用产生结束时间：</label>
                <input type="text" id="endtime"  name="payment_endtime" value="${command.registerDate}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>用户手机号：</label>
                <input type="text" id="number" name="phone_number" maxlength="11">
            </div>
            <div class="new-pwd">
                <label>缴费截止时间：</label>
                <input type="text" id="deadtime" name="payment_deadtime" value="${command.registerDate}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>单位数量：</label>
                <input type="text" id="units" name="payment_units">
            </div>
            <div class="new-pwd">
                <input onclick="return validator()" class="new-pwd-btn" type="submit" value="提交">
            </div>
        </div>
      </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>