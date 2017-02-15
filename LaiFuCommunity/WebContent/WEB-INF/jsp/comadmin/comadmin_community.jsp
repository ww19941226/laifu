<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
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
<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>小区管理</h1>
        <div class="complains-find">
        </div>
        <table>
            <tr>
                <th width="15%">序号</th>
                <th width="20%">小区名</th>
                <th width="25%">小区位置</th>
                <th width="20%">小区简介</th>
                <th>操作</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
           <c:forEach items="${ page.items }" var="c">
           <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${ id }</td>
                <td>${ c.community_name }</td>
                <td>${ c.community_location }</td>
                <td><a href="<c:url value='/comadmin/${ c.community_id }/community/community_detail' />">查看详情</a></td>
                <td><a href="<c:url value='/comadmin/${ c.community_id }/community/community_edit' />">修改</a><!-- <a href="<c:url value='/comadmin/${ c.community_id }/deleteCommunity' />">删除</a></td> -->
            </tr>
            </c:forEach>
        </table>

    </div>
    <div class="page complains-page">
    	<common:pageV3 url="/comadmin/comadmin_community" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>