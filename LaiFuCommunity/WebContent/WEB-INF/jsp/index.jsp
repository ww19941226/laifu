<%@page import="com.laifu.module.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>莱福小区主页</title>
</head>
<!-- 重置样式 -->
<link href="css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="css/laifucommunity_main.css" rel="stylesheet" />
<script src="js/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
	function showHint() {
		var xmlhttp = null;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else {// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		if(xmlhttp != null) {
			xmlhttp.open("GET","/LaiFuCommunity/getNotifyvisit_count",true);
			xmlhttp.send(null);
		}
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				var code = xmlhttp.responseText;
				if(code == "1") {
					document.getElementById("NotifyMessage").innerHTML = "<em style='color:#F00;'>您有新的通知</em>";
					showMessage();
				}
				//document.getElementById("datetime").innerHTML=xmlhttp.responseText;
			}
		};
	}
</script>
<script type="text/javascript">
    $(document).ready(function(){
        var wh=$(window).height();
        var b=true;
        $(window).scroll(function(){
            var s=wh-$(window).scrollTop();
            if(s>100&&b==true){
                $("#navItem").fadeOut();
                b=false;
            }
            if(s<100){
                $("#navItem").fadeIn();
                b=true;
            }
        });
        
        showMessage();/*
        setInterval(function() { showHint(); }, 10000);*/
    });
    function showMessage() {
    	$(".panel").slideDown(1500);
	    setTimeout(function() {$(".panel").slideUp(1500);}, 6000);
    }
</script>


<style type="text/css">
	.cssshadow 
   {
   font-family: "Microsoft Yahei","PingHei" ,"sans-serif";
    -moz-box-shadow:3px 3px 3px #bbbbbb;
    -webkit-box-shadow:3px 3px 3px #bbbbbb;
    box-shadow:3px 3px  3px #bbbbbb;
    /* For IE 8 */
    -ms-filter: "progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#bbbbbb')";
    /* For IE 5.5 - 7 */
    filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#bbbbbb');
   }
</style>
<body>
    <!--头部-->
    <header>
        <div class="header-container clearfix">
            <div class="header-left">
                <!--logo-->
                <h1 class="fl">
                    <a href="index.html">
                        <div class="logo">
                            <div class="logo-header"> <div class="logo-img">
                                <img src="images/logo.png">
                            </div></div>
                        </div>
                    </a>
                </h1>
                <!-- phone -->
                <div class="header-phone fl">
                    <em>欢迎致电:</em>
                    <p class="clearfix"><span class="fl"></span>18318260149.<i>(Mon - Sun. 8:00.AM - 6:00.PM)</i></p>
                </div>
            </div>
            <!-- nav -->
            <div class="header-right fr">
                <div class="nav">
                    <ul class="clearfix">
                        <li class="fl"><a href="<c:url value='/index' />">首页</a></li>
                        <li class="fl"><a href="<c:url value='/market/index'/>">小区超市</a></li>
                        <li class="fl"><a href="<c:url value='/user/topic'/>?communityTopic=2">话题</a></li>
                        <li class="fl"><a href="<c:url value='/user/userCenter'/>">物业服务</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/user/userCenter'/>">个人中心</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/sysadmin/sysadmin_login'/>">管理平台</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>


    <!-- 导航条 -->

    <nav class="nav-fix" id="navItem">
        <div class="container">
            <div class="nav fr">
                <ul class="clearfix">
                    <li class="fl"><a href="">首页</a></li>
                    <li class="fl"><a href="<c:url value='/user/' />">小区超市</li>
                    <li class="fl"><a href="<c:url value='/user/topic'/>?communityTopic=2">话题</a></li>
                    <li class="fl"><a href="<c:url value='/user/userCenter'/>">物业服务</a></li>
                   <li class="fl"><a class="nav-btn" href="<c:url value='/user/userCenter'/>">个人中心</a></li>
                    <li class="fl"><a class="nav-btn" href="<c:url value='/sysadmin/sysadmin_login'/>">管理平台</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- advert -->
    <section class="advert">
        <div class="container">
            <div class="img-box">
                <img src="images/banner.jpg" width="100%" height="100%">
            </div>
        </div>
    </section>

    <!-- 小区介绍 -->
    <section class="intro clearfix">
        <div class="little-container">
            <h2><span></span><p>关于我们的莱福</p></h2>
            <h3>莱福一直为着一个温馨的社区而努力。</h3>
            <h3>我们一直有着一颗赤诚的心。</h3>
            <p>莱福是一个很棒的智能小区，在这里。你可以享受到：</p>
            <p>便捷的智能系统。</p>
            <p>即时的人工交流。</p>
            <p>愉悦轻松的小区话题。</p>
            <p>无所不在，渗透全区的物业服务。</p>
            <div class="intro-pic">
                <div class="pic-left fl">
                    <img src="images/introright.jpg">
                </div>
                <div class="pic-top fl">
                    <img src="images/introtop.jpg">
                </div>
                <div class="pic-bottom fl">
                    <img src="images/introbottom.jpg">
                </div>
            </div>
        </div>
    </section>



    <!-- 小区通知 -->
    <section class="notify clearfix">
        <div class="little-container">
            <h2><span></span><p>小区日常的通知</p></h2>
        </div>
        <div class="container">
            <div class="notify-left fl">
                <ul class="clearfix">
                    <li class="fl"><img src="images/notify.jpg"></li>
                   <!-- <li class="fl"><img src="images/introtop.jpg"></li>
                    <li class="fl"><img src="images/introtop.jpg"></li>
                    <li class="fl"><img src="images/introtop.jpg"></li>-->
                </ul>
            </div>
            <div class="notify-right fl">
                <h5><a href="<c:url value='/user/userCenter'/>">更多 >></a></h5>
                <ul>
                    <c:forEach items="${ notifylist}" var="notify">
                    <li>
                        <div class="notify-box clearfix">
                            <div class="notify-date fl"><span><fmt:formatDate value="${notify.notify_datetime}" pattern="MM-dd" /></span><p><fmt:formatDate value="${notify.notify_datetime}" pattern="yyyy" /></p></div>
                            <div class="notify-message  fl">
                                <div class="notify-message-title">
                                    <h4>${ notify.notify_titile }</h4>
                                </div>
                                <div class="notify-message-content">
                                    <p>${ notify.notify_content }</p>
                                </div>
                            </div>
                            <div class="notify-btn fr "><a href="<c:url value='/user/userCenter'/>"><span></span></a></div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </section>

    <!-- 小区服务 -->
    <section class="service clearfix">
        <div class="little-container">
            <h2><span></span><p>体贴多众的温馨服务</p></h2>
            <p>更多服务尽在物业服务.</p>
            <div class="service-show">
                <ul>
                    <li class="clearfix">
                        <div class="show-left fl">
                            <h5 class="fl"><span>item 1</span></h5>
                            <em><a href="<c:url value='/user/userCenter'/>">自助修理</a></em>
                            <p>方便即时的报修,为你解决烦恼。</p>
                        </div>
                        <div class="show-right fr">
                            <div class="show-img">
                                <img src="images/repair.jpg">
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="show-left fl">
                            <h5 class="fl"><span>item 2</span></h5>
                            <em><a href="<c:url value='/user/userCenter'/>">网上缴费</a></em>
                            <c:forEach items="${ paymenttypelist}" var="paymenttype">
                           		 <p>${ paymenttype.paymenttype_name },最新价格:${ paymenttype.paymenttype_money }/${paymenttype.paymenttype_unit}</p>
                            </c:forEach>
                        </div>
                        <div class="show-right fr">
                            <div class="show-img">
                                <img src="images/fee.jpg">
                            </div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="show-left fl">
                            <h5 class="fl"><span>item 3</span></h5>
                            <em><a href="http://map.baidu.com/" target="_blank">搜周边好玩</a></em>
                            <p>即时的车位信息，实时的停车服务。</p>
                        </div>
                        <div class="show-right fr">
                            <div class="show-img">
                                <img src="images/around.jpg">
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </section>



    <!-- platform -->
    <section class="platform clearfix">
        <div class="little-container">
            <h2><span></span><p>多姿多彩的小区平台</p></h2>
        </div>
        <div class="container">
            <div class="platform-show">
                <ul>
                    <li class="fl">
                        <div class="platform-img">
                            <img src="images/school.jpg">
                        </div>
                        <div class="platform-content">
                            <h4>学校平台</h4>
                            <span></span>
                        </div>
                        <div class="platform-btn">
                            <a>我的小区</a>
                        </div>
                    </li>
                    <li class="fl">
                        <div class="platform-img">
                            <img src="images/oldman.jpg">
                        </div>
                        <div class="platform-content">
                            <h4>老人之家</h4>
                            <span></span>
                        </div>
                        <div class="platform-btn">
                            <a>我的小区</a>
                        </div>
                    </li>
                    <li class="fl">
                        <div class="platform-img">
                            <img src="images/activity.jpg">
                        </div>
                        <div class="platform-content">
                            <h4>小区活动</h4>
                            <span></span>
                        </div>
                        <div class="platform-btn">
                            <a>我的小区</a>
                        </div>
                    </li>
                    <li class="fl">
                        <div class="platform-img">
                            <img src="images/shop.jpg">
                        </div>
                        <div class="platform-content">
                            <h4>超市特惠</h4>
                            <span></span>
                        </div>
                        <div class="platform-btn">
                            <a>我的小区</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </section>
    <c:if test='${sessionScope["notifyvisit_count"]!=0 && sessionScope["notifyvisit_count"]!=null}'>
	    <div class="panel cssshadow" style="width:250px; background-color:#f5f5f5;position:fixed;right:10px;bottom:0;display:none;" >
			<h5 style="background:#6cd633; height:40px;line-height:40px; ">温馨提示:</h5>
			<div style="padding-top:10px; text-align:center;height:40px;line-height:40px;"><p id="NotifyMessage">您共有<em style="color:#F00;">${sessionScope["notifyvisit_count"]}</em>条通知未读</p></div>
			<div style="padding-bottom:10px;text-align:center;height:40px;line-height:40px;"><p >请前往个人中心查看</p></div>
		</div>
	</c:if>
    <!-- 尾部 -->
    <footer>
        <div class="footer-main">
            <span>welcome laifu community ,thanks @author Raindrops、Lin、zepeng、jianshuo、tick、guochu</span>
        </div>
    </footer>
</body>
</html>


















