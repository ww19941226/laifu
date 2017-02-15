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
<script src="../../js/jquery-1.11.1.min.js"></script>
<script>
	function validator()
	{
	 var starttime = document.getElementById("starttime").value;
	 var endtime = document.getElementById("endtime").value;
	 var number = document.getElementById("number").value;
	 var deadtime = document.getElementById("deadtime").value;
	 var units = document.getElementById("units").value;
	
	 if(!starttime || !endtime || !number || !deadtime || !units){
	 	alert("内容不能为空,请重新输入");
	 	return false;
	 }
	
 	 if(confirm("确认修改？"))
    		return true;
  	 else
    	    return false;
	}
</script>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改缴费</h1>
        <form method="post" action="<c:url value='/property/updatepayment' />" onsubmit="return validator()">       
        <div class="new-pwd-box">
            <div class="new-pwd">
            	<input type="hidden" name="payment_id" value="${ paymentvo.payment_id}">
                <select name="paymenttypeid">
                <c:forEach items="${paymentvo.paymenttypelist }" var="paymenttype" >
                  <c:choose>
                	  <c:when test="${paymentvo.paymenttype.paymenttype_id eq paymenttype.paymenttype_id}">
                		<option value="${ paymentvo.paymenttype.paymenttype_id}" selected="selected">${ paymentvo.paymenttype.paymenttype_name}</option>
                	  </c:when>
                	  <c:otherwise>
                    	<option value="${ paymenttype.paymenttype_id}">${ paymenttype.paymenttype_name}</option>
                      </c:otherwise>
                  </c:choose>     
                </c:forEach>
                </select>
            </div>
            <div class="new-pwd">
                <label>费用产生起始时间：</label>
                <input type="text" id="starttime" name="payment_starttime" value="${paymentvo.payment_starttime}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>费用产生结束时间：</label>
                <input type="text" id="endtime" name="payment_endtime" value="${paymentvo.payment_endtime}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>用户手机号：</label>
                <input type="text" id="number" name="phone_number" value="${ paymentvo.phone_number}">
            </div>
            <div class="new-pwd">
                <label>缴费截止时间：</label>
                <input type="text" id="deadtime" name="payment_deadtime" value="${paymentvo.payment_deadtime}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="new-pwd">
                <label>单位数量：</label>
                <input type="text" id="units" name="payment_units"  value="${paymentvo.payment_units }">
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" type="submit" value="提交">
            </div>
        </div>
      </form>   
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>