<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>业主管理</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
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
        <h1>业主管理</h1>
        <div class="complains-find">
        </div>
        <table>
            <div class="complains-excel fl"><a href="<c:url value='/comadmin/user/user_add' />">添加</a></div>
            <tr>
                <th width="5%">序号</th>
                <th width="15%">账号</th>
                <th width="5%">性别</th>
                <th width="15%">昵称</th>
                <th width="15%">邮箱</th>
                <th width="15%">身份证号</th>
                <th width="10%">真实姓名</th>
                <th width="5%">审核状态</th>
                <th>操作</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="user">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${ id }</td>
                <td>${ user.user_account }</td>
                <td>${ user.user_sex }</td>
                <td>${ user.user_nickname }</td>
                <td>${ user.user_email }</td>
                <td>${ user.user_card }</td>
                <td>${ user.user_realname }</td>
                <td>
                <c:choose>
    				<c:when test="${user.user_checkstate == 0}">
       						未审核
    				</c:when>
   				   <c:when test="${user.user_checkstate == 1}">
       						已通过
    				</c:when>
    				<c:when test="${user.user_checkstate == 2}">
       						未通过
    				</c:when>
			    </c:choose>
                 </td>
                <td><a href="<c:url value='/comadmin/user/${user.user_id}/user_detail' />">详情</a><a href="<c:url value='/comadmin/user/${user.user_id}/user_edit' />">修改</a><a href="<c:url value='/comadmin/${user.user_id}/deleteUser' />" onclick="return validator()">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="page complains-page">
    	<common:pageV3 url="/comadmin/comadmin_user" optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>