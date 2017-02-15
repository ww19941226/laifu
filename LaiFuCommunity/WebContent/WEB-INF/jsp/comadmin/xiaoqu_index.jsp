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
    
    <title>业主列表</title>
    <link rel="stylesheet" href="<c:url value='/css/xitong_index.css'/>"/>
</head>
<body>
<!--头部logo和导航栏-->
<div class="header">
    <div class="logo">
        <p>欢迎来到 莱福 小区管理员后台</p>
    </div>
    <div class="nav">
        <a href="#" style="font-weight: bold;border-bottom: 3px solid white;">管理业主</a>
        <a href="#">注册小区</a>
        <a href="#">发布招标</a>
        <a href="#">投诉处理</a>
        <a href="#">添加物业管理员</a>
        <a href="#">管理物业管理员</a>

    </div>

</div>


<!--业主信息-->
<div class="x_outer">
    <p class="info">业主列表</p>
    <div class="x_content">
        <table class="x_table" border="1">
            <thead>
            <tr>
                <td width="12%">头像</td>
                <td width="10%">昵称</td>
                <td width="10%">姓名</td>
                <td width="6%">性别</td>
                <td width="20%">邮箱</td>
                <td width="12%">手机号码</td>
                <td width="18%">身份证号码</td>
                <td width="6%">楼号</td>
                <td width="6%">房号</td>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td width="12%"><img src="../images/logo.png"> </td>
                <td width="10%">w_w</td>
                <td width="10%">钟国楚</td>
                <td width="6%">男</td>
                <td width="20%">1146654010@qq.com</td>
                <td width="12%">18318260421</td>
                <td width="18%">440902199412260000</td>
                <td width="6%">D1</td>
                <td width="6%">352</td>
            </tr>
            <tr>
                <td width="12%"><img src="../images/erweima.png"> </td>
                <td width="10%">w_w</td>
                <td width="10%">钟国楚</td>
                <td width="6%">男</td>
                <td width="20%">1146654010@qq.com</td>
                <td width="12%">18318260421</td>
                <td width="18%">440902199412260000</td>
                <td width="6%">D1</td>
                <td width="6%">352</td>
            </tr>
            <tr>
                <td width="12%"><img src="../images/error.gif"> </td>
                <td width="10%">w_w</td>
                <td width="10%">钟国楚</td>
                <td width="6%">男</td>
                <td width="20%">1146654010@qq.com</td>
                <td width="12%">18318260421</td>
                <td width="18%">440902199412260000</td>
                <td width="6%">D1</td>
                <td width="6%">352</td>
            </tr>
            <tr>
                <td width="12%"><img src="../images/123.jpg"> </td>
                <td width="10%">w_w</td>
                <td width="10%">钟国楚</td>
                <td width="6%">男</td>
                <td width="20%">1146654010@qq.com</td>
                <td width="12%">18318260421</td>
                <td width="18%">440902199412260000</td>
                <td width="6%">D1</td>
                <td width="6%">352</td>
            </tr>







            </tbody>

        </table>
    </div>
</div>
<br><br><br><br><br><br><br><br>

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