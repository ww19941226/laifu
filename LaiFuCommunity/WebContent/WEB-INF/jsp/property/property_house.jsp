<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../inc/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>房屋管理</title>
    
    <!-- cssreset -->
	<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
	<!-- main -->
	<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
	<script src="../js/jquery-1.11.1.min.js"></script>
	<script>
		function validator()
		{
		
	 	 if(confirm("确认要添加吗？"))
	    		return true;
	  	 else
	    	    return false;
		}
	</script>
</head>

<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>房屋管理</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="complains-excel fl"><a href="<c:url value='/notify/notify_add' />">添加</a></div>
            <tr>
                <th width="10%">序号</th>
                <th width="10%">楼号</th>
                <th width="10%">房号</th>
            </tr>
			<c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="h">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${id}</td>
                <td>${h.house.house_floornumber }</td>
                <td>${h.house.house_roomnumber }</td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="page complains-page">
        <common:pageV3 url="/property/property_house" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>
