<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>购物车-莱福小区超市</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/import.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gwc.css"/>
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

<div class="gwc_outer">
    <div class="gwc_head">
        <div class="gwc_head1 fl">商品图片</div>
        <div class="gwc_head2 fl">商品名称</div>
        <div class="gwc_head3 fl">单价</div>
        <div class="gwc_head4 fl">数量</div>
        <div class="gwc_head5 fl">金额</div>
        <div class="gwc_head6 fl" id="deleteAll">删除全部</div>
        <div class="cf" style="height: 0;"></div>
    </div>
    <div class="gwc_info_outer">
    <c:if test="${sessionScope.cart.cartItems=='[]'}">
     	购物车空空如也。
     </c:if>
     <c:if test="${sessionScope.cart.cartItems!='[]'}">
     	<c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
        <div class="gwc_info_one" id="${cartItem.product.product_id}">
            <div class="fl">
                <img src="${pageContext.request.contextPath}/images/sp.jpg"/>
            </div>
            <div class="fl">${cartItem.product.product_name}</div>
            <div class="fl">￥<c:out value="${cartItem.product.product_price*cartItem.product.product_discount/10}"></c:out></div>
            <div class="gwc_number fl">
                <button class="jian fl">-</button>
                <input class="shuru fl" maxlength="3" type="text " placeholder="数量" value="${cartItem.count}"/>
                <button class="jia fl">+</button>
                <div class="cf"></div>
            </div>
            <div class="fl">￥${cartItem.subtotal}</div>
            <div class="deleteOne fl">
                删除该商品
            </div>
            <div class="cf" style="height: 0;"></div>
        </div>
</c:forEach>
     </c:if>
     


    </div>
    <div class="gwc_jiesuan_outer">
        <div class="gwc_jiesuan fr">
            <div class="fl">商品共${sessionScope.cart.totalcount}件</div>
            <div class="fl">合计(不含运费)：￥${sessionScope.cart.total}</div>
            <div id="tijiaodingdan" class="fl">提交订单</div>
            <div class="cf" style="height: 0;"></div>
        </div>
        <div class="cf"></div>
    </div>
</div>

<%@ include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>
    $(document).ready(function () {
        //删除一个商品
        $(".deleteOne").click(function () {
        	var deleteOneConfirm = confirm("确定删除该商品？");
            var product_id = $(this).parent().attr("id");
            if(deleteOneConfirm){
	            $.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/removeCart",
		        	dataType: "json",
		        	data:{"product_id":product_id},
		        	success: function(data){ 
		        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
		        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
		       			$("#"+product_id).slideUp(350, function () {
			                $("#"+product_id).remove();
			                if($(".gwc_info_outer>div").length == 0){
			                    $(".gwc_info_outer").html("购物车空空如也。");
			                }
			            });
		        	}
		        });
            }
            
        });
        //商品数量+
        $(".jia").click(function () {
            var product_id = $(this).parent().parent().attr("id");
			$.ajax({
	        	type: "POST",
	        	url: "/LaiFuCommunity/market/addOneCart",
	        	dataType: "json",
	        	data:{"product_id":product_id},
	        	success: function(data){ 
	        		console.log(data);
	        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
	        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
	       			$("#"+product_id+">.gwc_number>.shuru").val(data[0].count);
	       			$("#"+product_id+">div:nth-child(5)").html("￥"+data[0].subtotal);
	        	},
	        	error: function(XMLHttpRequest, textStatus, errorThrown) {
                   alert(XMLHttpRequest.status);
                   alert(XMLHttpRequest.readyState);
                   alert(textStatus);
               }
	        });
        });
        //商品数量-
        $(".jian").click(function () {
            var product_id = $(this).parent().parent().attr("id");
            var product_number = $(this).next().val();
            if(product_number == 1){
                alert("现在该商品数量已为1，若想删除请点击删除该商品。");
            }
            else{
				$.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/subCart",
		        	dataType: "json",
		        	data:{"product_id":product_id},
		        	success: function(data){ 
		        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
		        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
		       			$("#"+product_id+">.gwc_number>.shuru").val(data[0].count);
		       			$("#"+product_id+">div:nth-child(5)").html("￥"+data[0].subtotal);
		        	}
		        });
            }
        });
        //输入商品数量
        $(".shuru").keyup(function () {
            var product_id = $(this).parent().parent().attr("id");
            var product_number = $(this).val();
            if(product_number != ""){
            	if(!parseInt(product_number, 10)){
	            	alert("请输入整数！");
	            	$(this).val("");
	            }
	            else{
	            	$.ajax({
			        	type: "POST",
			        	url: "/LaiFuCommunity/market/addCountCart",
			        	dataType: "json",
			        	data:{"product_id":product_id,"count":product_number},
			        	success: function(data){ 
			        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
			        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
			       			$("#"+product_id+">div:nth-child(5)").html("￥"+data[0].subtotal);
			        	}
			        });
	            }
            }
            /* var reg = new RegExp("^[0-9]*$");
		    if(!reg.test(product_number)){
		        alert("请输入数字!");
		    }
		    else{
		    	$.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/addCountCart",
		        	dataType: "json",
		        	data:{"product_id":product_id},
		        	success: function(data){ 
		        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
		        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
		       			$("#"+product_id+">div:nth-child(5)").html("￥"+data[0].subtotal);
		        	}
		        });
		    } */
        });
        //删除全部
        $("#deleteAll").click(function () {
        	var deleteAllConfirm = confirm("确定删除购物车全部商品？");
        	if(deleteAllConfirm){
	        	$.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/cleanCart",
		        	dataType: "json",
		        	success: function(data){ 
		        		$(".gwc_jiesuan>div:nth-child(1)").html("商品共"+data[0].totalCount+"件");
		        		$(".gwc_jiesuan>div:nth-child(2)").html("合计(不含运费)："+data[0].total);
		       			$(".gwc_info_outer>div").slideUp(700, function () {
			                $(".gwc_info_outer>div").remove();
			                $(".gwc_info_outer").html("购物车空空如也。");
			            });
		        	}
		        });
        	}
        });
    });
</script>
</body>
</html>