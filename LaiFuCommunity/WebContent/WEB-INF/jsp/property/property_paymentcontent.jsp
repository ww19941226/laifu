<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<script src="../js/jquery-1.11.1.min.js"></script>
<body class="grey">

<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>费用管理</h1>
        <div class="complains-find">
        </div>
        <form hidden id="form" action="<c:url value='/property/property_paymentcontent/exceladd'/>" method="post" enctype="multipart/form-data">
			<span>导入数据：</span><input id="beclicked_btn1" type="file" name="file"> <input id="beclicked_btn2" type="submit"
			 	value="导入excel">
		</form>
		
        <table>
	        <div class="complains-excel fl"><a href="<c:url value='/paymentcontent/payment_add' />">单条添加</a></div>
	        <div class="complains-excel fl"><a style="cursor: pointer;" id="click_btn">数据导入添加</a></div>
	        <div class="complains-excel fl"><a href="<c:url value='/property/property_paymentcontent/excelInput'/>">批量导出</a></div>
            <div class="complains-excel fl"><a style="cursor: pointer;" href="<c:url value='/file/fyb.xls' />">样板下载</a></div>
            <tr>
                <th width="10%">序号</th>
                <th width="10%">缴费类型</th>
                <th width="12%">起始时间</th>
                <th width="12%">结束时间</th>
                <th width="10%">用户</th>
                <th width="12%">截止时间</th>
                <th width="10%">单位数量</th>
                <th width="10%">缴费状态</th>
                <th>操作</th>
            </tr>
            <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="pay">
            <c:set var="id" value="${id+1}" scope="page" />
          
            <tr>
                <td>${ id }</td>
                <td>${ pay.paymenttype.paymenttype_name}</td>
                <td><fmt:formatDate value="${ pay.payment.payment_starttime }"  pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${ pay.payment.payment_endtime }"  pattern="yyyy-MM-dd"/></td>
                <td>${pay.user.user_realname }</td>
                <td><fmt:formatDate value="${ pay.payment.payment_deadtime }"  pattern="yyyy-MM-dd"/></td>
                <td>${ pay.payment.payment_units }</td>
                <td>
                <c:choose>
    				<c:when test="${pay.payment.payment_paystate==0&&(nowDate-pay.payment.payment_deadtime.time>0)}">
      			    	未缴费
    				</c:when>
    				<c:when test="${pay.payment.payment_paystate==0}">
      					待缴费   				    
      					</c:when>
    				<c:when test="${pay.payment.payment_paystate==1}">
      			  	  	已缴费
    				</c:when>
    </c:choose>
                
                
                </td>
                <td><a href="<c:url value='/paymentcontent/${pay.payment.payment_id}/payment_edit'/>">修改</a></td>
            </tr>
           	</c:forEach>
        </table>

    </div>
    <div class="page complains-page">
        <common:pageV3 url='/property/property_paymentcontent${ payment_paystate==null?"":"?payment_paystate=" }${ payment_paystate==null?"":payment_paystate }' optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
${ message }
<script src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#click_btn").click(function(){
			$("#beclicked_btn1").trigger('click');
		});
		$("#beclicked_btn1").change(function(){
			$("#beclicked_btn2").trigger('click');
		});
	});
</script>
</body>
</html>