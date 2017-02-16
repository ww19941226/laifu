<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div style="background-color: #cccccc;height: 30px;line-height: 30px;">
    <div style="width: 1200px;margin-left: auto;margin-right: auto">
        <div style="float: left;color: #666666;">
            您好，欢迎来到莱福智能生活小区超市！
        </div>
        <div style="float: right;">
          <a style="text-decoration: none;color: #666;" href="<c:url value='/user/userCenter'/>">${sessionScope["user"].user_nickname }，欢迎您</a>|
            <a style="text-decoration: none;color: #666;" href="<c:url value='/market/helpCenter/'/>">帮助中心</a>|
             <a style="text-decoration: none;color: #666;" href="<c:url value='/index' />">小区首页</a>
        </div>
        <div style="clear: both"></div>
    </div>
</div>
</body>
</html>
