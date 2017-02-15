<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>财务统计</title>
</head>
<!-- 重置样式 -->
<link href="../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>财务统计</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="table-btn fl"></div>
            <tr>
                <th width="15%">序号</th>
                <th width="20%">支出款项</th>
                <th width="20%">支出金额</th>
                <th width="20%">支出时间</th>    
                <th>详情</th>      
            </tr>
            <c:set var="id" value="0" scope="page"></c:set>
            <c:forEach items="${page.items }" var="financial">
            <c:set var="id" value="${id+1}" scope="page"></c:set>
            <tr>
                <td>${id }</td>
                <td>${financial.financial_funds }</td>
                <td>${financial.financial_money }</td>
                <td><fmt:formatDate value="${financial.financial_datetime }" /></td>
            <td><a href="<c:url value='/financial/${financial.financial_id}/financial_detail'/>">查看详情</a></td> 
            </tr>
            </c:forEach>
        </table>
    </div>
      <div class="page notify_page">
    <common:pageV3 url="/user/user_financial"></common:pageV3>
    </div>
   <!--  <div class="page complains-page">
        <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a>
    </div> -->
</section>
</body>
</html>