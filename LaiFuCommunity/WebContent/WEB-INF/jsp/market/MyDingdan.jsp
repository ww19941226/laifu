<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myDD.css"/>
    <link href="${pageContext.request.contextPath}/css/laifucommunity_main_20160926.css" rel="stylesheet" />
    <style type="text/css">
    	.myDD_head>a{
    		font-size: 18px;
    		padding: 20px 15px 5px 15px;
    		margin-bottom: 10px;
    	}
    	.myDD_head>a:hover{
    		border-bottom: 2px solid #59cf2b;
    	}
    	.state${dState}{
    		border-bottom: 2px solid #59cf2b;
    	}
    </style>
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

<div class="myDD_outer">
    <div class="myDD_head">
        <a class="state0" href="${pageContext.request.contextPath}/market/myDingdan?state=0">全部订单</a>
        <a class="state1" href="${pageContext.request.contextPath}/market/myDingdan?state=1">未付款</a>
        <a class="state2" href="${pageContext.request.contextPath}/market/myDingdan?state=2">未接单</a>
        <a class="state3" href="${pageContext.request.contextPath}/market/myDingdan?state=3">已接单</a>
        <a class="state4" href="${pageContext.request.contextPath}/market/myDingdan?state=4">送货中</a>
        <a class="state5" href="${pageContext.request.contextPath}/market/myDingdan?state=5">完成交易</a>
    </div>
    <c:forEach  var="order" items="${page.items }">
    <div class="myDD_DD">
        <div class="myDD_DD_head" id="${order.order_id }">
            <div class="myDD_DD_time fl">${order.order_creattime }</div>
            
            <div class="myDD_DD_status fl">订单状态：
            
           					<c:if test="${order.order_state==1}">
								<a href="#">未付款</a>
							</c:if>
							<c:if test="${order.order_state==2}">
								未接单
							</c:if>
							<c:if test="${order.order_state==3}">
								已接单
							</c:if>
							<c:if test="${order.order_state==4}">
								送货中
							</c:if>
							<c:if test="${order.order_state==5}">
								交易完成
							</c:if>
            
            </div>
            <div class="myDD_DD_allMoney fl">总金额：￥${order.order_money }</div>
            <div class="myDD_DD_again fl">再来一单</div>
            <div class="myDD_DD_delete fl">删除</div>
            <div class="cf" style="height: 0;"></div>
        </div>
        <div class="myDD_DD_content">
        <c:forEach items="${order.orderItems }" var="orderItems">
            <div class="myDD_DD_sp">
                <div class="myDD_DD_sp_img fl"><img src="${pageContext.request.contextPath}/images/sp.jpg"/> </div>
                <div class="myDD_DD_sp_ms fl">${orderItems.product.product_name } </div>
                <div class="myDD_DD_sp_dj fl">￥<c:out value="${orderItems.product.product_price*orderItems.product.product_discount/10}"></c:out></div>
                <div class="myDD_DD_sp_number fl">${orderItems.orderItems_count}</div>
                <div class="myDD_DD_sp_money fl">￥${orderItems.orderItems_subtotal}</div>
                <div class="cf" style="height: 0;"></div>
            </div>
          </c:forEach>
        </div>
    </div>
    </c:forEach>
    </div>
</div>
<div class="cf"></div>
 <div class="page list_page" style="text-align:center;">
    <common:pageV3 url="/market/myDingdan?state=${dState }"></common:pageV3>
    <div class="cf"></div>
</div>
<div class="cf"></div>
<%@ include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>
    $(document).ready(function () {
        //删除一个商品
        $(".myDD_DD_delete").click(function () {
        var deleteConfirm = confirm("确定删除该订单？");
            var order_id = $(this).parent().attr("id");
            console.log(order_id);
            if(deleteConfirm){
	            $.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/removeDD",
		        	dataType: "json",
		        	data:{"order_id":order_id},
		        	success: function(data){ 
		       			$("#"+order_id).parent().slideUp(350, function () {
			                $("#"+order_id).parent().remove();
			            });
		        	}
		        });
            }
        });
        
        //再来一单
        $(".myDD_DD_again").click(function () {
            var order_id = $(this).parent().attr("id");
            console.log(order_id);
	        location.href="/LaiFuCommunity/market/findByOid/"+order_id;
	    });
    });
</script>
</body>
</html