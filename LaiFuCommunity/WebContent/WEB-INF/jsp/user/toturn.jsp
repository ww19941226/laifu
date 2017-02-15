<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
</head>
<body style="text-align: center;">
	<p>正在跳转到登陆界面……</p>
	如果没有自动跳转  <a href="<c:url value='/user/user_login'/>">请点击这里</a>
	<script type="text/javascript" src="<c:url value='js/jquery-1.11.1.min.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			setTimeout(function() {
            	location.href="<c:url value='/user/user_login'/>"
        	},2000);
		});
		
	</script>
</body>
</html>