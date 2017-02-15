<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>自助报修</title>
</head>
<!-- 重置样式 -->
<link href="../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../js/jquery-1.11.1.min.js"></script>
<body>
    <section class="user-content">
        <div class="table-box">
            <h1>我的建议</h1>

            <div class="complains-find">
            </div>
            <table>
                <div class="table-btn fl"><a href="<c:url value='/user/user_repair/repair_add'/>">添加</a></div>
                <tr>
                    <th width="15%">序号</th>
                    <th width="30%">报修内容</th>
                    <th width="20%">报修时间</th>
                    <th width="20%">维修状态</th>
                    <!-- <th>预期上门服务时间</th> -->
                    <th>查看详情</th>
                </tr>
                <c:set var="id" value="0" scope="page"></c:set>
            <c:forEach items="${page.items }" var="repair">
            <c:set var="id" value="${id+1}" scope="page"></c:set>
                <tr>
                    <td>${id }</td>
                    <td>${repair.repair_project }</td>
                    <td> <fmt:formatDate value="${repair.repair_decldatatime }" /></td>
                    <!-- <td>${repair.repair_starttime }</td> -->
                    <td>
                    	<c:choose>
    					<c:when test="${repair.repair_state == 0}">
      						未处理
   				    	</c:when>
    					<c:when test="${repair.repair_state == 1}">
      			    		已处理
    					</c:when>
    					<c:when test="${repair.repair_state == 2}">
      			    		处理中
    					</c:when>
    					<c:when test="${repair.repair_state == 3}">
      			    		拒处理
    					</c:when>
						</c:choose>
                    </td>
                    <td><a href="<c:url value='/user/${repair.repair_id}/repair_detail'/>">查看详情</a></td>
                </tr>
                </c:forEach>
            </table>
        </div>
         <div class="page repair_page">
    <common:pageV3 url="/user/user_repair"></common:pageV3>
    </div>
    </section>
</body>
</html>