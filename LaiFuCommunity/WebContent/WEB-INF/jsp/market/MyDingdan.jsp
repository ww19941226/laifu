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
</head>
<body>
<%@ include file="head.jsp" %>
<div class="search_div">
    <div class="chaoshilogo fl"></div>
    <div class="search fl"></div>
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
        我的订单
    </div>
    <c:forEach  var="order" items="${page.items }">
    <div class="myDD_DD" id="该订单的id！！！！！">
        <div class="myDD_DD_head">
            <div class="myDD_DD_time fl">${order.order_creattime }</div>
            <div class="myDD_DD_status fl">订单状态：${order.order_state }</div>
            <div class="myDD_DD_allMoney fl">总金额：￥${order.order_money }</div>
            <div class="myDD_DD_again fl">再来一单</div>
            <div class="myDD_DD_delete fl">删除</div>
            <div class="cf" style="height: 0;"></div>
        </div>
        <div class="myDD_DD_content">
        <c:forEach item="${order.orderItems }" var="orderItems">
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

<%@ include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>
    $(document).ready(function () {
        //删除一个商品
        $(".deleteOne").click(function () {
            var product_id = $(this).parent().attr("id");
            console.log(product_id);
            $("#"+product_id).slideUp(350, function () {
                $("#"+product_id).remove();
                if($(".gwc_info_outer>div").length == 0){
                    $(".gwc_info_outer").html("购物车空空如也。");
                }
            });
        });
        //商品数量+
        $(".jia").click(function () {
            var product_id = $(this).parent().parent().attr("id");

        });
        //商品数量-
        $(".jian").click(function () {
            var product_id = $(this).parent().parent().attr("id");
            var product_number = $(this).next().val();
            if(product_number == 1){
                alert("现在该商品数量已为1，若想删除请点击删除该商品。");
            }
            else{

            }
        });
        //输入商品数量
        $(".shuru").keydown(function () {
            var product_id = $(this).parent().parent().attr("id");
            console.log(product_id);
        });
        //删除全部
        $("#deleteAll").click(function () {
            $(".gwc_info_outer>div").slideUp(700, function () {
                $(".gwc_info_outer>div").remove();
                $(".gwc_info_outer").html("购物车空空如也。");
            });
        });
    });
</script>
</body>
</html