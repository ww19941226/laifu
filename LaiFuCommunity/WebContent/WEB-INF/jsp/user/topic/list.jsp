<%@page import="com.laifu.module.entity.Topic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <a href="<c:url value='/user/topic/add'/>">新增</a><br/>
 
 <a href="<c:url value='/user/topic/1/getAllSelfTopic'/>">查看我的所有话题</a><br/>
 
    <table border="1">
		<tbody>
			<tr>
			    <th>编号</th>
				<th>标题</th>
				<th>内容</th>
				<th>话题类型</th>
				<th>话题创建时间</th>
				
				 
			</tr>
			<c:if test="${!empty topicList}">
				<c:forEach items="${topicList }" var="topic" varStatus="topicindex">
					<tr>
					<td>${topic.topic_id}</td>
						<td><a href="<c:url value=''/>">${topic.topic_title }</a></td>
						<td>${topic.topic_comment} </td>
						<td>${topic.topic_type }</td>
						
						<td><img src='<c:url value="/upload/2016091305455030765379924.jpg"/>'/></td>
						<td>${topic.topicpicture_path}</td>
		                 
						<td>
							<%-- <a href="/test_ssh/user/getUser?id=${user.id }">编辑</a> --%>
							<a href="<c:url value='/user/topic/${topic.topic_id}/deleteTopic' />">删除</a> 
						
						
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<script>
	var topiclist='<%=request.getAttribute("topicList")%>';
	var topic_datetime='<%=request.getAttribute("topic_datetime")%>';
	topic_datetime=topic_datetime.substring(1,topic_datetime.length-1);
	topic_datetime=topic_datetime.split(",");
	topiclist=eval(topiclist);
	for(var o in topiclist){
	console.log(topic_datetime[o]);
		var imgArray=topiclist[o].topicpicture_path.split(",");
		console.log(imgArray);
	}
</script>
  </body>
</html>
