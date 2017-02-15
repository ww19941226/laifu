<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		div.panel {
			width:400px;
			background-color:#0088bb;
			position:fixed;
			right:0;
			bottom:0;
			height:300px;
			display:none;
		}
	</style>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript"> 
		$(document).ready(function(){
		    $(".panel").slideDown(3000);
		    setTimeout(function() {$(".panel").slideUp(3000);}, 6000);
		});
	</script>
 

  </head>
  
  <body>
  	<div style="border-radius:20px;height:20px;width:20px;background-color:red;color:#FFF;line-height:20px;text-align:center;">20</div>
    <div class="panel">
		<p>W3School - 领先的 Web 技术教程站点</p>
		<p>在 W3School，你可以找到你所需要的所有网站建设教程。</p>
	</div>
 </body>
</html>
