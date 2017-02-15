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
    
    <title>小区列表</title>
    <link rel="stylesheet" href="<c:url value='/css/xitong_index.css'/>"/>
</head>
<body>
<!--头部logo和导航栏-->
<div class="header">
    <div class="logo">
        <p>欢迎来到 莱福 系统管理员后台</p>
    </div>
    <div class="nav">
        <a href="#" style="font-weight: bold;border-bottom: 3px solid white;">管理小区</a>
        <a href="#">审核小区</a>
        <a href="#">建议处理</a>
        <a href="#">添加小区管理员</a>

    </div>

</div>

<div class="xt_outer">
    <p class="info">小区列表</p>
    <div class="xt_content">
        <table class="xt_table" border="1">
            <thead>
            <tr>
                <td width="80%">小区名称</td>
                <td width="10%">修改</td>
                <td width="10%">删除</td>
            </tr>
            </thead>
            <tbody>
		
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr><tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>
            <tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr><tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr><tr>
                <td width="80%">莱福生活小区</td>
                <td width="10%"><a href="#">修改</a> </td>
                <td width="10%"><a href="#">删除</a></td>
            </tr>




            </tbody>

        </table>
    </div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!--底部链接和版权信息等-->
<div class="footer">
    <div class="link">
        <a href="#">关于我们</a>
        |
        <a href="#">莱福首页</a>
        |
        <a href="#">帮助</a>
    </div>
    <div class="copyright">
        <p>CopyRight @2016 Life All Right Reserved.莱福公司 版权所有</p>
    </div>
</div>
</body>
</html>