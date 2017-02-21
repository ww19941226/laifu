<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head lang="en">
    <meta charset="UTF-8">
    <title>进口商品-国外商品-莱福智能生活小区之小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
</head>
<body>
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
<div class="area">
<div class="wrap">
        <a class="area1" href="#oum"><img src="${pageContext.request.contextPath}/images/area1.jpg"></a>
        <a class="area2" href="#han"><img src="${pageContext.request.contextPath}/images/area2.jpg"></a>
        <a class="area3" href="http://www.lingshi.com/list/c228_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area3.jpg"></a>
        <a class="area4" href="http://www.lingshi.com/list/c229_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area4.jpg"></a>
        <a class="area5" href="#fei"><img src="${pageContext.request.contextPath}/images/area5.jpg"></a>
        <a class="area6" href="http://www.lingshi.com/list/c179_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area6.jpg"></a>
        <a class="area7" href="ditu.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area7.jpg"></a>
        <a class="area8" href="http://www.lingshi.com/list/c148_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area8.jpg"></a>
        <a class="area9" href="#tai"><img src="${pageContext.request.contextPath}/images/area9.jpg"></a>
        <a class="area10" href="#yue"><img src="${pageContext.request.contextPath}/images/area10.jpg"></a>
        <a class="area11" href="http://www.lingshi.com/list/c143_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area11.jpg"></a>
        <a class="area12" href="http://www.lingshi.com/list/c227_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area12.jpg"></a>
        <a class="area13" href="http://www.lingshi.com/list/c222_o1.htm" target="_blank"><img src="${pageContext.request.contextPath}/images/area13.jpg"></a>
        <a class="area14" href="#ma"><img src="${pageContext.request.contextPath}/images/area14.jpg"></a>
    </div>
</div>

<div class="hot_new">
    <div class="hot_title">
        <a class="fr" href="热卖进口商品查看更多" style="line-height: 50px;">》更多</a>
    </div>
    <!--热卖进口商品20条数据-->
    <div class="hot_content">
    	<c:forEach  items="${ jinkouHotList}" var="jk">
        <div class="hot_content_content fl">
             	<img src="${pageContext.request.contextPath}/${jk.product_photo1}">
                <p class="hot_content_content_ms">${jk.product_name }</p>
                <p class="hot_content_content_dj">￥<c:out value="${jk.product_price*jk.product_discount/10}"></c:out></p>
            <div id="${jk.product_id }" class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
        </div>
        </c:forEach>
        <div class="cf"></div>

    </div>

    <div class="new_title">
        <a class="fr" href="新到进口商品查看更多" style="line-height: 50px;">》更多</a>
    </div>
    <!--新到进口商品20条数据-->
    <div class="hot_content">
    	<c:forEach items="${jinkoNewList}" var="jkn">
        <div class="hot_content_content fl">
                <img src="${pageContext.request.contextPath}/${jkn.product_photo1}">
                <p class="hot_content_content_ms">${jkn.product_name }</p>
                <p class="hot_content_content_dj">￥<c:out value="${jkn.product_price*jkn.product_discount/10}"></c:out></p>
            <div id="${jkn.product_id }" class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
        </div>
        </c:forEach>

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
