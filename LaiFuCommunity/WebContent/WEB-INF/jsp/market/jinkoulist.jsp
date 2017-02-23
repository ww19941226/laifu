<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>商品列表-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
        <link href="${pageContext.request.contextPath}/css/laifucommunity_main_20160926.css" rel="stylesheet" />
</head>
<body>
<%@ include file="head.jsp" %>
<div class="search_div">
    <div class="chaoshilogo fl"></div>
    <div class="search fl">
    	<form action="${pageContext.request.contextPath}/market/search" method="get">
    		<input name="searchText" style="font-size:18px;border:3px solid #59cf2b;padding-left: 10px;width:418px;height:43px;" type="text" placeholder="搜索……">
    		<input style="cursor:pointer;margin-left:-10px;color:#fff;font-size:18px;border:0;background-color:#59cf2b;width:108px;height:49px;" type="submit" value="搜索"/>
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

<div style="width: 1200px;margin: 20px auto;color: #aaaaaa;font-size: 18px;font-family: \5FAE\8F6F\96C5\9ED1">
   首页>>${sessionScope.search}
</div>
<div class="hot_content">
<c:forEach items="${page.items }" var="sp">
    <div class="hot_content_content fl">
        <img src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/images/sp.jpg"/>
        <p class="hot_content_content_ms">${sp.product_name }</p>
        <p class="hot_content_content_dj">销量:${sp.product_deal }</p>
        <p class="hot_content_content_dj">单价:￥<c:out value="${sp.product_price*sp.product_discount/10}"></c:out></p>
        <div id="${sp.product_id }" class="gwc_div">
            <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
        </div>
     </div>
   </c:forEach>
    </div>
     <div class="cf"></div>
    </div>
</div>
<div class="page list_page" style="text-align:center;">
    <common:pageV3 url="/market/searchjinkou?search=${sessionScope.search}" optimize="true"></common:pageV3>
    <div class="cf"></div>
</div>
<div class="cf"></div>

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
<%@ include file="footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.min.js"></script>
<script src="${pageContext.request.contextPath}/js/requestAnimationFrame.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
