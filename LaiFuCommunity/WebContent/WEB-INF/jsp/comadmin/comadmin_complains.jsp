<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/css.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投诉建议</title>
</head>
<!-- cssreset -->
<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>投诉建议</h1>
        <div class="complains-find">
        </div>
        <table>
            <tr>
                <th width="10%">序号</th>
                <th width="20%">投诉标题</th>
                <th width="15%">查看内容</th>
                <th width="15%">用户手机</th>
                <th width="20%">投诉时间</th>
                <th >回复操作</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="c">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${ id }</td>
                <td>${ c.complains_title }</td>
                <td><a href="<c:url value='/comadmin/${ c.complains_id }/complains/complains_detail' />">内容详情</a></td>
                <td>${ c.complains_phone }</td>
                <td><fmt:formatDate value="${ c.complains_datetime }" /></td>
                <td><a href="<c:url value='/comadmin/${ c.complains_id }/complains/complains_detail' />">回复</a></td>
            </tr>
            </c:forEach>
        </table>

    </div>
    <div class="page complains-page">
    	<common:pageV3 url="/comadmin/comadmin_complains" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>
