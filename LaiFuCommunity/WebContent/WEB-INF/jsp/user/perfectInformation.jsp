<%@page import="com.laifu.module.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>完善个人信息</title>
    <link rel="stylesheet" href="<c:url value='/css/perfectInformation.css'/>"/>
</head>
<body>
<form action="<c:url  value='/user/complete'/>" method="post" class="outer"  enctype="multipart/form-data">
    <!--账号昵称等信息-->
    <div class="user_info">
        <div class="user_nickname">
            <label>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
            <input type="text" name="user_nickname">
        </div>
        <div class="user_sex">
            <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
            <input type="radio" name="user_sex" value="m">男
            <input type="radio" name="user_sex" value="w">女
        </div>
        <div class="user_account">
            <label>手机号码：</label>
            <input  type="text" name="user_account" readonly="true">
        </div>
        <div class="user_email">
            <label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
            <input type="text" name="user_email">
        </div>
        <div class="user_realname">
            <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
            <input type="text" name="user_realname">
        </div>


    </div>

    <!--用户头像-->
    <div class="user_head">
        <img src="">
        <input hidden id="file0" type="file" name="file" accept="image/png,image/gif,image/jpg,image/jpeg" alt="图片">
    </div>

    <!--身份证号码等-->
    <div class="user_card">
        <label>身份证号码：</label>
        <input type="text" name="user_card">
    </div>

    <!--小区楼房房号信息-->
    <div class="user_address">
        <label>小区:</label>
        <input value="幸福小区" disabled="disabled" type="text" name="user_community">
        <label>楼号:</label>
        <input type="text" placeholder="例如：8栋" name="houseFloorNumber">
        <label>房号:</label>
        <input type="text" name="houseRoomNumber" placeholder="例如：352房">
    </div>

    <!--验证码和提交按钮-->
    <div class="yzm_submit">
        <span></span>
        <input id="yzm" type="text" placeholder="验证码">
        <input class="submit" type="submit" value="提交">
    </div>
</form>

<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	var user_id='<%=((User)session.getAttribute("user")).getUser_id()%>';
    	var user_account='<%=((User)session.getAttribute("user")).getUser_account()%>';
    	var user_card='<%=((User)session.getAttribute("user")).getUser_card()%>';
    	var user_sex='<%=((User)session.getAttribute("user")).getUser_sex()%>';
    	var user_realname='<%=((User)session.getAttribute("user")).getUser_realname()%>';
    	var user_nickname='<%=((User)session.getAttribute("user")).getUser_nickname()%>';
    	var user_email='<%=((User)session.getAttribute("user")).getUser_email()%>';
    	var user_head='<%=((User)session.getAttribute("user")).getUser_head()%>';
    	var houseFloorNum='<%=request.getAttribute("houseFloorNum")%>';
    	var houseRoomNum='<%=request.getAttribute("houseRoomNum")%>';
    	$(".user_account>input").val(user_account);
    	$(".user_nickname>input").val(user_nickname);
    	$(".user_head>img").attr("src","/LaiFuCommunity"+user_head);
    	if(user_card!="null"){
    		if(user_sex=="男"){
    			$(".user_sex>input:nth-child(2)").attr("checked", "true").attr("disabled", "disabled");
    			$(".user_sex>input:nth-child(3)").attr("disabled", "disabled");
    		}else{
    			$(".user_sex>input:nth-child(3)").attr("checked", "true").attr("disabled", "disabled");
    			$(".user_sex>input:nth-child(2)").attr("disabled", "disabled");
    		}
    		
    		function setCheckBoxReadOnly(obj, checkStatus) {//checkStatus：true 是readonly效果
 				obj.onclick = function(){return !checkStatus;};
			}
    		
    		$(".user_sex>input").attr("value",user_sex).attr("readOnly","true");
    		$(".user_email>input").attr("value",user_email).attr("readOnly","true");
    		$(".user_realname>input").attr("value",user_realname).attr("readOnly","true");
    		$(".user_card>input").attr("value",user_card).attr("readOnly","true");
    		$(".user_address>input:nth-child(4)").attr("value",houseFloorNum).attr("readOnly","true");
    		$(".user_address>input:nth-child(6)").attr("value",houseRoomNum).attr("readOnly","true");
    	}
        var i=2;
        var random=Math.floor(Math.random()*1000000);
        $(".yzm_submit>span").text(random);
        $(".yzm_submit>span").click(function(){
            var imgurl="images/huawen/h"+i+".png";
            $(this).css("background-image","url("+imgurl+")");
            $(this).css("color","#"+i+i+i);
            random=Math.floor(Math.random()*1000000);
            $(this).text(random);
            i++;
            if(i==21){
                i=1;
            }
        });
        $(".submit").click(function(){
            if($("#yzm").val()!=random){
                alert("验证码不正确");
                return false;
            }
        });
        
        //头像的点击事件
        $(".user_head>img").click(function(){
        	$("#file0").trigger('click');
        });
        
        //图片上传前预览
    	$("#file0").change(function(){
        	if(this.files[0]!=undefined){
        		$(".user_head>img").attr("src",getObjectURL(this.files[0]));
        	}
    	});
    });
    //建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
</script>
</body>
</html>