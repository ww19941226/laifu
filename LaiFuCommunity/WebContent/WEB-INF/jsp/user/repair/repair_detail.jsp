<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>报修详情</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>报修详情</h1>
        <div class="table-content">
            <h2>${repair.getRepair().repair_project}</h2>
            <span><fmt:formatDate value="${repair.getRepair().repair_decldatatime}" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
            <p>
            ${repair.getRepair().repair_project}
            </p>
        </div>
        <div class="table-solve">
            <h1>回复</h1>
            <div class="solve-item-box">
                <div class="solve-item clearfix">
                    <!-- <div class="solve-item-man fl">
                        <img src="<c:url value='${ user.user_head }' />">
                    </div> -->
                    
                    <div class="solve-item-content fl">
                        <p >
                        <c:choose>
    					<c:when test="${repair.getRepair().repair_state == 0}">
      						未处理
   				    	</c:when>
    					<c:when test="${repair.getRepair().repair_state == 1}">
      			    		已处理
    					</c:when>
    					<c:when test="${repair.getRepair().repair_state == 2}">
      			    		处理中
    					</c:when>
    					<c:when test="${repair.getRepair().repair_state == 3}">
      			    		拒处理
    					</c:when>
						</c:choose>
                        </p>
                        <span><fmt:formatDate value="${repair.getRepair().repair_completetime }" pattern="yyyy-MM-dd  HH:mm:ss" /> </span>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
</body>
</html>