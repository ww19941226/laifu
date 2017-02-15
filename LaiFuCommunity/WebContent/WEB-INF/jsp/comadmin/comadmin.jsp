<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <title>莱福小区管理</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<body>
<!-- 顶部导航 -->
<div class="nav">
    <div class="nav-left-box fl">
            <span class="laifu">
                莱福小区管理
            </span>
    </div>
    <div class="nav-right-box clearfix">
        <div class="left-item fl">
            <ul class="clearfix">
                <li class="fl"><a href="#">莱福首页</a></li>
            </ul>
        </div>
        <div class="right-item fr">
            <ul  onmouseover="listItems()" onmouseout="hidenItems()" >
                <li id="navImg" class="nav-item-img"><a><img src="<c:url value='${ user.user_head }' />"></a></li>
                <ul class="nav-item-hid" id="infoItem">
                    <li><a>${ user.user_account }</a></li>
                    <li><a href="<c:url value='/comadmin/comadmin_infor' />" target="mainFrame">个人信息</a></li>
                    <li><a href="<c:url value='/sysadmin/logout'/>">注销</a></li>
                </ul>
            </ul>
        </div>
    </div>
</div>
<section class="property-content">
    <!-- 左边导航 -->
    <div class="pro-content-left">
        <ul>
            <li><a href="<c:url value='/comadmin/comadmin_infor' />" target="mainFrame">个人信息</a> </li>
            <li><a href="<c:url value='/comadmin/comadmin_complains' />" target="mainFrame">投诉建议</a></li>
            <li><a href="<c:url value='/comadmin/comadmin_community' />" target="mainFrame">小区管理</a></li>
            <li><a href="<c:url value='/comadmin/comadmin_user' />" target="mainFrame">业主管理</a></li>
            <li><a href="<c:url value='/comadmin/comadmin_property' />" target="mainFrame">物业用户管理</a></li>
        </ul>
    </div>
    <!-- iframe -->
    <div class="pro-content-right">
        <div class="pro-content-iframe">
            <iframe id="mainFrame" name="mainFrame"  style=" width: 100%; height: 1000px;" frameborder=0 scrolling="no" src="<c:url value='/comadmin/comadmin_infor' />"></iframe>
        </div>
    </div>
</section>
</body>
<script src="../js/laifucommunity.js" language="javascript" type="text/javascript">
</script>
<script type="text/javascript">
    startInit('mainFrame', 560);
</script>
</html>