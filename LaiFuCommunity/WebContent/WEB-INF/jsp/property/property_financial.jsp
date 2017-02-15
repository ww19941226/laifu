<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>财务统计</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<script src="../js/jquery-1.11.1.min.js"></script>
<script>
	function validator()
	{
 	 if(confirm("确认要删除吗？"))
    		return true;
  	 else
    	    return false;
	}
</script>
<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>财务统计</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="complains-excel fl"><a href="<c:url value='/property/gotofinancial_add'/>" >添加</a></div>
            <tr>
                <th width="10%">序号</th>
                <th width="25%">支出款项</th>
                <th width="20%">支出金额</th>
                <th width="25%">支出时间</th>
                <th>操作</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="financial">
            <c:set var="id" value="${id+1}" scope="page" />
           	 <tr>
                <td>${id }</td>
                <td>${financial.financial_funds }</td>
                <td>${financial.financial_money }</td>
                <td><fmt:formatDate value="${financial.financial_datetime }" /></td>
                <!--<td><fmt:formatDate value="${financial.financial_datetime }" /></td> --> 
                <td><a href="<c:url value='/property/${financial.financial_id}/financial/financial_edit' />">修改</a><a  href="<c:url value='/property/${financial.financial_id}/financial_delete'/>"  onclick="return validator()">删除</a></td>
            </tr>
            </c:forEach>
        </table>

    </div>
    <div class="page complains-page">
        <common:pageV3 url="/property/property_financial" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>



</html>