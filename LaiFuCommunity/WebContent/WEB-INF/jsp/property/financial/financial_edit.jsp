<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改财务统计</title>
    <!-- 引入日期控件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
<script>
	function validator()
	{
	 var funds = document.getElementById("funds").value;
	 var money = document.getElementById("money").value;
	 var time  = document.getElementById("time").value;
	 if(funds=="" || money=="" || time=="" ){
	 	alert("内容不能为空,请重新输入!");
	 	return false;
	 }
 	 else if(confirm("确认要修改吗？"))
    		return true;
  	 else
    	    return false;
	}
</script>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改财务统计</h1>
      <form action="<c:url value='/property/${finacial.financial_id}/updateFinancial' />" method="post" id="form" onsubmit="return validator()">
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>支出款项：</label>
                <input type="text" id="funds" name="financial_funds" value="${finacial.financial_funds }">
            </div>
            <div class="new-pwd">
                <label>支出金额：</label>
                <input type="text" id="money" name="financial_money" value="${finacial.financial_money }">
            </div>
            <div class="new-pwd">
                <label>支出时间：</label>
                <input type="text" id = "time"  name="datetime" value="${finacial.financial_datetime }" readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> 
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