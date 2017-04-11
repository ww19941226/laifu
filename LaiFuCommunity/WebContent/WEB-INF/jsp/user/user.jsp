<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
</head>
<!-- 重置样式 -->
<link href="../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160928.css" rel="stylesheet" />
<style>
#user_head_img {
	cursor: pointer;
}

#slideUl li>a:hover {
	color:#F00
}
</style>
<style type="text/css">
.asd {
	position: relative;
}
</style>
<body>



	<!-- 导航条 -->

	<nav class="nav-fix" style="display: block" id="navItem">
	<div class="container">
		<div class="nav fr">
			<ul class="clearfix">

				<li class="fl"><a href="<c:url value='/index' />">首页</a></li>
                        <li class="fl"><a href="<c:url value='/market/index'/>">小区超市</a></li>
                        <li class="fl"><a href="<c:url value='/user/topic'/>?communityTopic=2">话题</a></li>
                        <li class="fl"><a href="<c:url value='/user/userCenter'/>">物业服务</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/user/userCenter'/>">个人中心</a></li>
                        <li class="fl"><a class="nav-btn" href="<c:url value='/sysadmin/sysadmin_login'/>">管理平台</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<!-- 个人中心 -->
	<section class="center">
	<div class="container clearfix">
		<!-- 左边导航 -->
		<div class="center-left fl">
			<div class="user-info">
				<div class="user-info-img">
					<img id="user_head_img"
						src="<c:url value='${sessionScope["user"].user_head}'/>"> <input
						hidden id="file0" type="file" name="file"
						accept="image/png,image/gif,image/jpg,image/jpeg" alt="图片">
				</div>
				<div class="user-account active">
					<span>${sessionScope["user"].user_account}</span>
				</div>
				<div class="user-account active">
					<div class="user-account-item  clearfix">
						<span class="age">年龄：${sessionScope["user"].user_age}</span><span
							class="age">性别：${sessionScope["user"].user_sex}</span><span
							class="age">类型：业主</span>
					</div>
				</div>
				<div class="user-account active">
					<span>${sessionScope["user"].user_nickname}</span>
				</div>
				<div class="user-account ">
					<span>${sessionScope["user"].user_realname}</span>
				</div>
			</div>
			<div class="user-nav clearfix">
				<div class="user-nav-btn fl">
					<a href="<c:url value='/user/userCenter' />">修改信息</a>
				</div>
				<div class="user-nav-btn fl">
					<a href="<c:url value='/user/logout' />">注销</a>
				</div>
			</div>
			<div class="user-nav-content">
				<div class="user-nav-item" id="slideUl">
					<ul>
						<li><a style="background-color: #575e51;color:white;"
							href="<c:url value='/user/userInfor'/>" target="mainFrame">个人信息</a>
						</li>
						<li><a href="<c:url value='/user/userSercurity'/>"
							target="mainFrame">安全中心</a>
						</li>
						<li><a href="<c:url  value='/user/user_complains'/>"
							target="mainFrame">投诉建议</a>
						</li>
						<li><a href="<c:url value='/user/user_pay'/>"
							target="mainFrame">费用缴纳</a>
						</li>
						<li><a href="<c:url value='/user/user_repair'/>"
							target="mainFrame">自助报修</a>
						</li>
						<li class="asd" ><a href="<c:url value='/user/user_notify'/>"
							target="mainFrame">日常通知 <c:if test='${sessionScope["notifyvisit_count"]!=0}'>
						<div style=" position:absolute; top:11px;right:15%; float:right; border-radius:20px;height:20px;width:20px;background-color:red;color:#FFF;line-height:20px;text-align:center;" id="redMessage">${sessionScope["notifyvisit_count"]}</div>
								</c:if> </a></li>
						<li><a href="<c:url value='/user/user_caiwutongji'/>"
							target="mainFrame">年度总结图</a>
						</li>
						<li><a href="<c:url value='/user/user_caiwutongji2'/>"
							target="mainFrame">费用百分比</a>
						</li>
					</ul>
				</div>
			</div>

		</div>
		<!-- 右边内容 -->
		<div class="center-right">
			<!-- iframe -->
			<div class="pro-content-iframe">
				<iframe id="mainFrame" name="mainFrame"
					style="margin-left: 50px; background: #FFFFFF; width: 780px; height: 1000px;"
					frameborder=0 scrolling="no"
					src="<c:url value='/user/userInfor' />"></iframe>
			</div>
		</div>
	</div>
	
	<c:if test='${sessionScope["notifyvisit_count"]!=0}'>
	    <div class="panel cssshadow" style="width:250px; background-color:#f5f5f5;position:fixed;right:10px;bottom:0;display:none;" >
			<h5 style="background:#6cd633; height:40px;line-height:40px; ">温馨提示:</h5>
			<div style="padding-top:10px; text-align:center;height:40px;line-height:40px;"><p id="NotifyMessage">您共有<em style="color:#F00;">${sessionScope["notifyvisit_count"]}</em>条通知未读</p></div>
			<div style="padding-bottom:10px;text-align:center;height:40px;line-height:40px;"><p >请前往个人中心查看</p></div>
		</div>
	</c:if>
	
	
	</section>
	<!-- 尾部 -->
	<footer class="login-footer">
	<div class="footer-main">
		<span>welcome laifu community ,thanks @author
			Raindrops、Lin、zepeng、jianshuo、tick、guochu</span>
	</div>
	</footer>


</body>
<script src="../js/laifucommunityMain.js" language="javascript" type="text/javascript">
</script>
<script type="text/javascript">
    startInit('mainFrame', 560);
</script>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	
		//头像的点击事件
        $("#user_head_img").click(function(){
        	$("#file0").trigger('click');
        });
        //图片上传前预览
    	$("#file0").change(function(){
        	if(this.files[0]!=undefined){
        		$.ajaxFileUpload({  
			        //处理文件上传操作的服务器端地址  
			        url:"${pageContext.request.contextPath}/user/uploadPicture",  
			        secureuri:false,                           //是否启用安全提交,默认为false   
			        fileElementId:'file0',               		//文件选择框的id属性  
			        dataType:'text',                           //服务器返回的格式,可以是json或xml等  
			        success:function(data, status){ 			//服务器响应成功时的处理函数  
			            datadata = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀  
			            datadata = data.replace(/<PRE.*?>/g, '');  
			            datadata = data.replace("<PRE>", '');  
			            datadata = data.replace("</PRE>", '');  
			            datadata = data.replace("<pre>", '');  
			            datadata = data.replace("</pre>", '');     //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]  
			           
			            if(data.indexOf("0") != -1){   
			                  //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)  
			                $("#user_head_img").attr("src", data.substring(data.indexOf("`") +1, data.lastIndexOf("<")));  
			            }else{  
			                alert("头像更改失败！");  
			            }  
			        },  
			        error:function(data, status, e){ //服务器响应失败时的处理函数  
			             alert("头像更改失败！");  
			        }  
			    });
        	}
    	});
		$("#slideUl li>a").click(function(){
			$("#slideUl li>a").removeAttr("style");
			$(this).css("background-color","#575e51");
			$(this).css("color","#fff");
		});
		
		
		//setInterval(function() { showHint(); }, 10000);
	});
</script>


<script type="text/javascript">
	function showHint() {
		var xmlhttp = null;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else {// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		if(xmlhttp != null) {
			xmlhttp.open("GET","/LaiFuCommunity/getNotifyvisit_count",true);
			xmlhttp.send(null);
		}
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				var code = xmlhttp.responseText;
				if(code != "0") {
					document.getElementById("NotifyMessage").innerHTML = "<em style='color:#F00;'>您有新的通知</em>";
					document.getElementById("redMessage").innerHTML = code;
					showMessage();
				}
				//document.getElementById("datetime").innerHTML=xmlhttp.responseText;
			}
		};
	}
	
	function showMessage() {
    	$(".panel").slideDown(1500);
	    setTimeout(function() {$(".panel").slideUp(1500);}, 6000);
    }
</script>

</html>