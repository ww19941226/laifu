<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>首页-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
</head>
     
  <body>
      <%--  <table  border="1">
        <tr>
                        <th>编号</th>
                        <th>名称1</th>                   
        </tr>
       <c:forEach items="${sessionScope.categoryList }" var="c">
       <tr>
        <th> ${c.category_id }</th>
                <th><a href="<c:url value='/market/${c.category_id }/getAllcategorySecond/'/>"> ${c.category_name }</a></th>
       </tr>
       
       </c:forEach>
       
       </table>
<a href="<c:url value='/market/jinkou/'/>">点击进入进口页面</a> --%>
<%@ include file="head.jsp" %>
<!-- <iframe src="head.html" style="width: 100%;height: 30px;border: 0;"></iframe> -->
<div class="search_div">
    <div class="chaoshilogo fl"></div>
    <div class="search fl">
    	<form action="${pageContext.request.contextPath}/market/search/" method="post">
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
<div class="banner_bg">
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
</div>
<div class="index_cuxiao_outer">
    <div class="index_cuxiao_img fl">
        <a href="<c:url value='/market/cuxiao/'/>">
            <img src="${pageContext.request.contextPath}/images/cuxiao.png">
        </a>
    </div>
    <div class="index_cuxiao_shangpin fl">
    <c:forEach items="${cxProducts }" var="cxp">
        <div class="cuxiao_content fl">
            <a href="商品详情" target="_blank">
                <img src="${pageContext.request.contextPath}/images/sp.jpg">
                <p class="cuxiao_content_ms">${cxp.product_name }</p>
               <%--  <p class="cuxiao_content_dj">￥${cxp.product_price }</p> --%>
               <p class="hot_content_content_djy"><del>原价:￥${cxp.product_price}</del></p>
                <p class="hot_content_content_dj">促销价:￥ <c:out value="${cxp.product_price*cxp.product_discount/10}"></c:out></p>
            </a>
            <div class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
        </div>
        </c:forEach>
 
        </div>
        <div class="cf"></div>
    </div>
    <div class="cf"></div>
</div>

<div class="index_cuxiao_outer" style="margin-top: 70px">
    <div class="index_cuxiao_img fl">
        <a href="${pageContext.request.contextPath}/remai.html">
            <img src="${pageContext.request.contextPath}/images/remai.png">
        </a>
    </div>
    <div class="index_cuxiao_shangpin fl">
    <c:forEach items="${rmProducts }" var="rmp">
        <div class="cuxiao_content fl">
            <a href="商品详情" target="_blank">
                <img src="${pageContext.request.contextPath}/images/sp.jpg">
                <p class="cuxiao_content_ms">${rmp.product_name }</p>
                <p class="cuxiao_content_dj">￥<c:out value="${rmp.product_price*rmp.product_discount/10}"></c:out></p>
                <p class="cuxiao_content_dj">销量${rmp.product_deal }</p>
            </a>
            <div class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
        </div>
        </c:forEach>
        </div>
        <div class="cf"></div>
    </div>
    <div class="cf"></div>
</div>

<div class="index_cuxiao_outer" style="margin-top: 100px">
    <div class="index_cuxiao_img fl">
        <a href="<c:url value='/market/newProducts/'/>">
            <img src="${pageContext.request.contextPath}/images/xinpin.png">
        </a>
    </div>
    <div class="index_cuxiao_shangpin fl">
    <c:forEach items="${ xpProducts}" var="xpp">
        <div class="cuxiao_content fl">
            <a href="商品详情" target="_blank">
                <img src="${pageContext.request.contextPath}/images/sp.jpg">
                <p class="cuxiao_content_ms">${xpp.product_name }</p>
                <p class="cuxiao_content_dj">￥<c:out value="${xpp.product_price*xpp.product_discount/10}"></c:out></p>
            </a>
            <div class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
        </div>
       </c:forEach>
        </div>
        <div class="cf"></div>
    </div>
    <div class="cf"></div>
</div>

<div class="index_cuxiao_outer" style="margin-top: 30px">
    <div class="index_cuxiao_img fl">
        <a href="<c:url value='/market/jinkou/'/>">
            <img src="${pageContext.request.contextPath}/images/jinkou.png">
        </a>
    </div>
    <div class="index_cuxiao_shangpin fl">
    <c:forEach items="${ jkProducts}" var="jkp">
        <div class="cuxiao_content fl">
            <a href="商品详情" target="_blank">
                <img src="${pageContext.request.contextPath}/images/sp.jpg">
                     <p class="cuxiao_content_ms">${jkp.product_name }</p>
                <p class="cuxiao_content_dj">￥<c:out value="${jkp.product_price*jkp.product_discount/10}"></c:out></p>
            </a>
            <div class="gwc_div">
                <img src="${pageContext.request.contextPath}/images/gouwuche.png" alt="加入购物车" title="加入购物车">
            </div>
            </div>
            </c:forEach>
        </div>
        
        <div class="cf"></div>
    </div>
    <div class="cf"></div>
</div>
<div class="gwc_right">
	<div class="all_right">
		<div id="end" style="height: 0;"></div>
		<div class="right_gwc" title="购物车"></div>
		<div id="msg">已成功加入购物车！</div>
		<div class="right_dingdan" title="我的订单"></div>
		<div class="right_choujiang" title="积分抽奖"></div>
	</div>
</div>
<%@ include file="footer.jsp" %>
<!-- <iframe src="footer.html" style="width: 100%;height: 550px;border: 0;"></iframe> -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.min.js"></script>
<script src="${pageContext.request.contextPath}/js/requestAnimationFrame.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
