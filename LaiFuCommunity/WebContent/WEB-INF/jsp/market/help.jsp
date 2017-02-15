<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>常见问题-帮助中心</title>
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
        当前位置：帮助中心>>常见问题
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
                常见问题
            </div>
            <div class="help_content_right_2">
                <p style="text-indent:0px;">
                    <strong>Q:在你们超市买的是正品吗？</strong><br>
                    A:莱福小区超市所出售的商品均是正品<br>
                    <br>
                    <strong>Q:在你们网站买东西有什么保障啊？</strong><br>
                    A:有以下服务保障：<br>
                    1）最大程度保障你的购物<br>
                    购物过程中有任何问题都可以拨打我们的服务热线进行协商解决。<br>
                    2）服务热线<br>
                    中国零食网为您提供贴心的服务，通过在线客服或拨打400-678-678即有专人为您服务；<br>
                    <br>
                    <strong>Q:怎样进行电话订购？</strong><br>
                    A: 您可以拨打400-678-678让客服专员帮您下订。只要您提供相关信息完成网站注册，客服专员会根据您提供的订购商品名称、数量及配送地址，即可完成下订。<br>
                    <strong>&nbsp;<br>
                        Q:有什么支付方式？</strong><br>
                    A:莱福小区超市提供付款方式有：在线支付、银行汇款、货到付款等，详情请查看“支付方式”。<br>
                    <br>
                    <strong>Q:打开支付页面，提示“该页无法显示”或空白页，是什么原因？</strong><br>
                    A:可能是由以下原因造成的：<br>
                    1）可能是暂时性的网络问题，建议您刷新试试；<br>
                    2）没有升级IE浏览器，导致加密级别过低，无法进入银行系统；<br>
                    3）上网环境或上网方式受限，可能是网络服务商限制，建议更换另一种上网方式；<br>
                    4）尝试刷新页面，如果刷新不能解决问题，可能由于浏览器缓存设置的原因，请在IE菜单-&gt;工具-&gt;Internet选项-&gt;点击 “删除cookies”和“删除文件”，用以清除临时文件； <br>
                    <br>
                    <strong>Q:银行页面打不开的是怎么回事？</strong><br>
                    A:可能是由以下原因造成的：<br>
                    1）可能是暂时性的网络问题，建议您刷新试试；<br>
                    2）没有升级IE浏览器，导致加密级别过低，无法进入银行系统；<br>
                    3）如果是个人电脑，请关闭您的防火墙再试；<br>
                    4）在IE浏览器“工具”菜单-&gt;Internet选项-&gt;高级-&gt;安全，在SSL2.0和SSL3.0的选项前打勾。。<br>
                    5）请您把银行网址在受信任的站点中添加进去，添加方法为：打开IE浏览器-&gt;工具-&gt;Internet选项-&gt;安全-&gt;受信任的站点-&gt;站点-&gt;添加银行的网站，重启IE浏览器再重新登录进行相关操作。<br>
                    <br>
                    <strong>Q:我查询银行已经扣款了，怎么订单状态显示未付款啊？</strong><br>
                    A:可能是由于银行返回的通知信息比较晚，超过了此次交易的超时时间，交易已关闭，请您联系客服人员查询，若核实确已付款，我们将为您修改订单状态，不会影响订单的发货及配送；<br>
                    <br>
                    <strong>Q:重复扣款了怎么办？</strong><br>
                    A:请您联系客服人员查询，若核实确属重复扣款，将按退款流程为您退回重复扣除的款项；<br>
                    &nbsp;<br>
                    <strong>Q：可以修改订单吗？</strong><br>
                    A：您好，只要您的订单还未付款的话，您想增加商品或减少商品都可以的。如果您的订单我们已经在配货中，那就修改不了，非常抱歉！<br>
                    <br>
                    <strong>Q:你们网站有什么优惠活动？</strong><br>
                    A: 我们每天都会有促销商品，详情可留意促销页面哦！<br>
                    <br>
                    </p>
                    <br>
                    <strong>Q:为什么我的订单没有送积分给我？</strong><br>
                    A:每一成功交易的订单，都会自动添加相应积分，若您已收到货但积分没有添加，请您联系客服人员查询处理。<br>
                    <br>
                    <strong>Q:为什么购物车会自动被清空？</strong><br>
                    A: 若您在把东西放进购物车2小时之后都没下单或者关闭了网页购物车会自动清空。<br>
                    <strong><br>
                        Q:厂商如何加入莱福小区超市？</strong><br>
                    A:很抱歉，暂时莱福小区超市不支持厂商加盟。<br>
                    <br>
                    &nbsp;</p>
                    <br>
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
