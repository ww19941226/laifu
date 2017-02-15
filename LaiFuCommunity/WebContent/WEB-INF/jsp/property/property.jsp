<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="UTF-8">
    <title>莱福物业管理</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<script src="../js/jquery-1.11.1.min.js"></script>
<body>
    <!-- 顶部导航 -->
    <div class="nav">
        <div class="nav-left-box fl">
            <span class="laifu">
                莱福物业管理
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
                    <li id="navImg" class="nav-item-img"><a><img src="<c:url value='${ sessionScope["admin"].user.user_head }' />"></a></li>
                    <ul class="nav-item-hid" id="infoItem">
                        <li><a>${ sessionScope["admin"].user.user_account }</a></li>
                        <li><a href="<c:url value='/property/property_infor' />" target="mainFrame">个人信息</a></li>
                        <li><a href="<c:url value='/property/logout' />">注销</a></li>
                    </ul>
                </ul>
            </div>
        </div>
    </div>
    <section class="property-content">
        <!-- 左边导航 -->
         <script type="text/javascript">
            $(function(){
                $(".hideUl/*:not(:first)*/").hide();
                $(".slideLi").mouseenter(function(){
                    $(this).next(".hideUl").slideToggle()
                            .siblings(".hideUl").slideUp();
                });
                $(".HideUl").mouseleave(function(){
                	$(".hideUl/*:not(:first)*/").hide(1000);	
                });
            });
        </script>
        <div class="pro-content-left-bg">aaa</div>
        <div class="pro-content-left">
            <ul class="HideUl">
                <li><a class="slideLi" href="<c:url value='/property/property_infor' />" target="mainFrame">个人信息</a> </li>
                <li><a class="slideLi" href="<c:url value='/property/property_complains' />" target="mainFrame">投诉建议</a>
                	<ul class="hideUl">
                		<li><a target="mainFrame" href="<c:url value='/property/property_complains' />?complains_state=0">未回复</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_complains' />?complains_state=1">已回复</a></li>
                	</ul>
                </li>
                <!-- <li><a href="<c:url value='/property/property_payment' />" target="mainFrame">费用缴纳</a></li> -->
                <li><a class="slideLi" href="<c:url value='/property/property_paymentcontent' />" target="mainFrame">费用管理</a>
                	<ul class="hideUl">
                		<li><a target="mainFrame" href="<c:url value='/property/property_paymentcontent' />?payment_paystate=0">待缴费</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_paymentcontent' />?payment_paystate=1">已缴费</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_paymentcontent' />?payment_paystate=2">未缴费</a></li>
                	</ul>
                </li>
                <li><a class="slideLi" href="<c:url value='/property/property_repair' />" target="mainFrame">自助报修</a>
                	<ul class="hideUl">
                		<li><a target="mainFrame" href="<c:url value='/property/property_repair' />?repair_state=0">未处理</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_repair' />?repair_state=1">已处理</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_repair' />?repair_state=2">处理中</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_repair' />?repair_state=3">拒处理</a></li>
                	</ul>
                </li>
                <li><a class="slideLi" href="<c:url value='/property/property_notify' />" target="mainFrame">日常通知</a></li>
                <li><a class="slideLi" href="<c:url value='/property/property_caiwutongji' />" target="mainFrame">财务统计</a>
                	<ul class="hideUl">
                		<li><a target="mainFrame" href="<c:url value='/property/property_caiwutongji' />">年度总结图</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/property_caiwutongji2' />">费用百分比</a></li>
                	</ul>
                </li>
                <li><a class="slideLi" href="<c:url value='/property/sysadmin_community' />" target="mainFrame">小区管理</a></li>
                <li><a class="slideLi" href="<c:url value='/property/comadmin_user' />"  target="mainFrame">业主管理</a>
                	<ul class="hideUl">
                		<li><a target="mainFrame" href="<c:url value='/property/comadmin_user' />?user_checkstate=none">未完善</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/comadmin_user' />?user_checkstate=0">待审核</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/comadmin_user' />?user_checkstate=1">审核通过</a></li>
                		<li><a target="mainFrame" href="<c:url value='/property/comadmin_user' />?user_checkstate=2">审核不通过</a></li>
                	</ul>
                </li>
                <!-- <li><a class="slideLi" href="<c:url value='/property/property_house' />" target="mainFrame">房屋管理</a></li> -->
                <li><a class="slideLi" href="<c:url value='/property/property_paymenttype' />" target="mainFrame">缴费类型管理</a></li>
            </ul>
        </div>
        <!-- iframe -->
        <div class="pro-content-right">
            <div class="pro-content-iframe">
                <iframe id="mainFrame" name="mainFrame"  style=" width: 100%; height: 1000px;" frameborder=0 scrolling="no" src="property_infor.html"></iframe>
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