<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>建议详情</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>建议详情</h1>
        <div class="table-content">
            <h2>${ complain.complains_title }</h2>
            <span><fmt:formatDate value="${ complain.complains_datetime }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
            <p>
                ${ complain.complains_content }
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
                        <p>
                           ${complain.complains_replycontent } 
                        </p>
                        <span><fmt:formatDate value="${complain.complains_replytime}" pattern="yyyy-MM-dd  HH:mm:ss" />  </span>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
</body>
</html>