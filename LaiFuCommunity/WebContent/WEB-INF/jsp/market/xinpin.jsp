<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>新品-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
    <link href="${pageContext.request.contextPath}/css/laifucommunity_main_20160926.css" rel="stylesheet" />
</head>
<body>
<!-- <iframe src="head.html" style="width: 100%;height: 30px;border: 0;"></iframe> -->
<%@ include file="head.jsp" %>
<div class="search_div">
    <div class="chaoshilogo fl"></div>
    <div class="search fl">
    	<form action="${pageContext.request.contextPath}/market/search" method="get">
    		<input name="searchText" style="font-size:18px;border:3px solid #59cf2b;padding-left: 10px;width:418px;height:43px;" type="text" placeholder="搜索……">
    		<input style="cursor:pointer;margin-left:-6px;color:#fff;font-size:18px;border:0;background-color:#59cf2b;width:108px;height:49px;" type="submit" value="搜索"/>
    	</form>
    </div>
    <div class="house fl"></div>
    <div class="cf"></div>
</div>
<div class="nav_out">
    <div class="nav_main">
        <a target="_parent" id="nav_all_a" style="background-color: rgba(16, 16, 16, 0.74);display: block;float: left;" class="nav_a" href="javascript:void(0)">全部商品分类</a>
    <a target="_parent" style="padding-left: 55px" class="nav_a" href="<c:url value='/market/index/'/>">首页</a>
        <a target="_parent" class="nav_a" href="<c:url value='/market/cuxiao/'/>">促销</a>
        <a target="_parent" class="nav_a" href="<c:url value='/market/newProducts/'/>">新品</a>
        <a target="_parent" class="nav_a" href="<c:url value='/market/jinkou/'/>">进口</a>
    </div>
    </div>
   <div class="all_a_outer_outer">
        <div class="all_a_outer">
            <div class="all_a">
                <div class="all_a_nav">
                <c:forEach items="${sessionScope.categoryList }" var="c" >
                    <div class="all_a_nav1">
                        <a href="<c:url value='/market/findbycategory/${c.category_id}' />">${c.category_name }</a>
                    </div>
                    <div class="all_a_nav2">
                    <c:forEach var="cs" items="${c.categorySeconds}">
                        <a href="<c:url value='/market/findbycategorysecond/${cs.categorysecond_id}' />">${cs.categorysecond_name}</a>
                  
                          </c:forEach>
                    </div> 
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- <div class="banner_bg">
    <div class="index_banner_outer">
        <div class="index_banner_left fl">
            <a href="banner1">
                <img src="${pageContext.request.contextPath}/images/banner1.png">
                <div class="zhezhao banner1_zhezhao zhezhao_off"></div>
            </a>
            <a href="banner2">
                <img src="${pageContext.request.contextPath}/images/banner2.png">
                <div class="zhezhao banner2_zhezhao zhezhao_off"></div>
            </a>
            <a href="banner3">
                <img src="${pageContext.request.contextPath}/images/banner3.png">
                <div class="zhezhao banner3_zhezhao zhezhao_off"></div>
            </a>
        </div>
        <div class="index_banner_center fl">
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner1.png"></a>
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner2.png"></a>
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner3.png"></a>
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner4.png"></a>
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner5.png"></a>
            <a href="banner1"><img src="${pageContext.request.contextPath}/images/banner6.png"></a>
        </div>
        <div class="index_banner_right fl">
            <a href="banner4">
                <img src="${pageContext.request.contextPath}/images/banner4.png">
                <div class="zhezhao banner1_zhezhao zhezhao_off"></div>
            </a>
            <a href="banner5">
                <img src="${pageContext.request.contextPath}/images/banner5.png">
                <div class="zhezhao banner2_zhezhao zhezhao_off"></div>
            </a>
            <a href="banner6">
                <img src="${pageContext.request.contextPath}/images/banner6.png">
                <div class="zhezhao banner3_zhezhao zhezhao_off"></div>
            </a>
        </div>
        <div class="cf"></div>
    </div>
</div> --%>
<div style="width: 1200px;margin: 55px auto 5px;color: #FF8989;font-size: 22px">
    新进商品
</div>
<div class="hot_content">
<c:forEach items="${page.items }" var="xpp">
    <div class="hot_content_content fl">
            <img src='<c:url value="${xpp.product_photo1 }"/>'>
            <p class="hot_content_content_ms">${xpp.product_name }</p>
            <p class="hot_content_content_dj">单价:￥<c:out value="${xpp.product_price*xpp.product_discount/10}"></c:out></p>
        <div id="${xpp.product_id }" class="gwc_div">
            <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
        </div>
    </div>
     </c:forEach>
     </div>
        <div class="cf"></div>
        <div class="page xinpin_page" style="text-align: center;">
    <common:pageV3 url="/market/newProducts"></common:pageV3>
    </div>
   
    <div class="cf"></div>
    </div>
</div>
<div class="gwc_right">
	<div class="all_right">
		<div id="end" style="height: 0;"></div>
		<div class="right_gwc" title="购物车">
		  <%--  <a href="<c:url value='/market/myCart/'/>"> --%>
		</div>
		<div id="msg">已成功加入购物车！</div>
		<div class="right_dingdan" title="我的订单"></div>
		<div class="right_choujiang" title="积分抽奖"></div>
	</div>
</div>
<!-- <iframe src="footer.html" style="width: 100%;height: 550px;border: 0;"></iframe> -->
<%@ include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.min.js"></script>
<script src="${pageContext.request.contextPath}/js/requestAnimationFrame.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
