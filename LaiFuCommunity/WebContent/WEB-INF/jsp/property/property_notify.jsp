<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>日常通知</title>
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
        <h1>日常通知</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="complains-excel fl"><a href="<c:url value='/notify/notify_add' />">添加</a></div>
            <tr>
                <th width="10%">序号</th>
                <th width="25%">标题</th>
                <th width="20%">正文</th>
                <th width="25%">发布时间</th>
                <th>操作</th>
            </tr>
			<c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="notify">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${id}</td>
                <td>${notify.notify_titile }</td>
                <td><a href="<c:url value='/property/${notify.notify_id}/notify_detail'/>">点击查看</a></td>
                <td><fmt:formatDate value="${notify.notify_datetime }" /></td>
                <td><a href="<c:url value='/property/${notify.notify_id}/gotonotify_edit'/>">修改</a><a href="<c:url value='/property/${notify.notify_id}/notify_delete'/>" onclick="return validator()">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="page complains-page">
        <common:pageV3 url="/property/property_notify" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>