<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>送货费用-帮助中心</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/help.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
</head>
<body>
<%@ include file="head.jsp" %>
<div class="search_div">
    <div class="chaoshilogo fl"></div>
    <div class="search fl">
    	<form action="">
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
<div class="help_outer">
    <div class="help_content_1">
        当前位置：帮助中心>>送货费用
    </div>
    <div class="help_content">
        <div class="help_content_left fl">
            <div class="help_content_left_1">
                <div class="help_content_left_1_title">
                    新手指南
                </div>
                <div class="help_content_left_1_content">
                     <a href="<c:url value='/market/gouwuliucheng/'/>">购物流程</a><br/>
            <a href="<c:url value='/market/jifenzhidu/'/>">积分制度</a>
                </div>
            </div>
            <div class="help_content_left_1">
                <div class="help_content_left_1_title">
                    付款说明
                </div>
                <div class="help_content_left_1_content">
                    <a href="<c:url value='/market/tuikuan/'/>">办理退款</a><br/>
            <a href="<c:url value='/market/zhifu/'/>">支付方式</a>
                </div>
            </div>
            <div class="help_content_left_1">
                <div class="help_content_left_1_title">
                    配送说明
                </div>
                <div class="help_content_left_1_content">
                    <a href="<c:url value='/market/yunfei/'/>">配送运费</a><br/>
            <a href="<c:url value='/market/shijian/'/>">配送时间</a>
                </div>
            </div>
            <div class="help_content_left_1">
                <div class="help_content_left_1_title">
                    客户服务
                </div>
                <div class="help_content_left_1_content">
                     <a href="<c:url value='/market/helpCenter/'/>">常见问题</a>
                </div>
            </div>
        </div>
        <div class="help_content_right fl">
            <div class="help_content_right_1">
                送货费用
            </div>
            <div class="help_content_right_2">
                满50元免送货费用。<br>低于50元收取5元送货费用。
            </div>
        </div>
        <div class="cf"></div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
