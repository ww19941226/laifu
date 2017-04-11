<%@page import="com.laifu.module.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>话题</title>
    <!-- 重置样式 -->
	<link href="<c:url value='/css/reset.css'/>" rel="stylesheet" />
	<!-- 主要样式 -->
	<link href="<c:url value='/css/laifucommunity_main_20160928.css'/>" rel="stylesheet" />
	<link href="<c:url value='/css/index.css'/>" rel="stylesheet" type="text/css"></link>
  	<link href="<c:url value='/css/user_talk.css'/>" rel="stylesheet" type="text/css"></link>
  	<!--这个css是qqFace的css-->
  	<link href="<c:url value='/css/qqFace.css'/>" rel="stylesheet" type="text/css"></link>
</head>
  
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

<div class="content">
    <!--头像和话题分类-->
    <div class="my_fenlei">
        <img src="<c:url value='<%=((User)session.getAttribute("user")).getUser_head()%>'/>"">
        <span><%=((User)session.getAttribute("user")).getUser_nickname()%></span>
        <div class="fenlei">
            <a id="ht2" href="/LaiFuCommunity/user/topic?communityTopic=2">小区话题</a>
            <a id="ht1" href="/LaiFuCommunity/user/topic?communityTopic=1">活动通知</a>
            <a id="ht4" href="/LaiFuCommunity/user/topic?communityTopic=4">二手转让</a>
            <a id="ht0" href="/LaiFuCommunity/user/topic?communityTopic=0">我的话题</a>
        </div>
    </div>
   <!--小区话题主要内容-->
   <div class="t_main">

       <!--qqFace的模块-->
       <form hidden id="qqFaceform" action="${pageContext.request.contextPath }/user/topic/add" method="post" enctype="multipart/form-data">
           <input type="text" name="topic_type" id="topic_type" hidden/>
       <div class="comment">
           <div class="com_form">
               <textarea name="topic_comment" class="input" id="saytext" contenteditable="true" placeholder="说点什么吧……"></textarea>
               <div class="file_bg"><img src="<c:url value='/images/file_img.png'/>" class="file_img"> <input id="file0" type="file" class="file_input" name="file" accept="image/png,image/gif,image/jpg,image/jpeg"
                multiple="multiple" alt="图片"></div>
               <p><input type="submit" class="sub_btn" value="发表">
                   <select class="sub_select">
                       <optgroup label="话题类型">
                           <option>小区话题</option>
                           <option>二手转让</option>
                           <option>活动通知</option>
                       </optgroup>
                   </select>
                   <span class="emotion"><img id="smileimg" src="<c:url value='/images/smile.png'/>"> </span></p>
           </div>
           <!--图片区-->
           <div id="com_images" class="com_images"></div>
       </div>
       </form>
   </div>
   <img id="to_top" src="/LaiFuCommunity/images/to_top.png" style="width:50px;position: fixed;bottom: 100px;right:50px;cursor: pointer;"/>
    <br>
    <div id="bottom_tip" style="text-align:center;font-size: 22px;color: #555;">数据加载中……</div>
    <br><br>
</div>


<!--底部链接和版权信息等-->
<div class="footer" style="text-align: center">
    <div class="link">
        <a href="<c:url value='/about_us'/>">关于我们</a>
        |
        <a href="index.html">莱福首页</a>
        |
        <a href="#">帮助</a>
    </div>
    <div class="copyright">
        <p>CopyRight @2016 Life All Right Reserved.莱福公司 版权所有</p>
    </div>
</div>
<div class="show_div" hidden>
    <img id="show_img" src=""/>
</div>

<!--以下三个js文件为qqFace的js-->
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.qqFace.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/qqFace.js'/>"></script>
<script>
    $(document).ready(function () {
    	var interval=0;
    	var now_topic_number=0;
    	/* 话题类型，即是小区话题还是区间趣事还是活动通知还是二手转让 */
    	var topic_type='<%=request.getParameter("communityTopic")%>';
    	if(topic_type!=1&&topic_type!=2&&topic_type!=4){
    		topic_type=0;
    	}
    	/* 我的话题 */
    	if(topic_type==0){
    		$("#qqFaceform").show();
    		$(".user_talk>.user_good>a:nth-child(3)").show();
    	}
    	/* 根据话题类型给分类的类名添加样式 */
    	$("#ht"+topic_type).addClass("on");
    	
    	/* 当前用户的头像路径 */
    	var user_head='<%=((User)session.getAttribute("user")).getUser_head()%>';
    	console.log("当前用户的头像路径"+user_head);
    	/* 当前用户的用户id */
    	var user_id='<%=((User)session.getAttribute("user")).getUser_id()%>';
    	console.log("当前用户的用户id"+user_id);
    	/* 当前用户的昵称 */
    	var user_nickname='<%=((User)session.getAttribute("user")).getUser_nickname()%>';
    	console.log("当前用户的用户昵称"+user_nickname);
    	
    	/* ajax获取五条话题数据 */
    	if(topic_type==0){
    		$.get("<c:url value='/user/topic/"+user_id+"/0/5/getTopicListByUser'/>",{},function(data){
    			data=JSON.parse(data);
    			console.log("我的话题数据为：");
    			console.log(data);
    			if(data.length==0){
    				interval=2;
    				$("#bottom_tip").html("没有更多话题了");
    			}else{
    				now_topic_number+=data.length;
    				ajax_topic(data,topic_type);
    			}
    		});
    	}
    	else{
    		$.get("<c:url value='/user/topic/0/5/getTopicList?communityTopic="+topic_type+"'/>",{},function(data){
    			data=JSON.parse(data);
    			if(data.length==0){
    				interval=2;
    				$("#bottom_tip").html("没有更多话题了");
    			}else{
    				now_topic_number+=data.length;
    				ajax_topic(data,topic_type);
    			}
    		});
    	}
    	
    	
    	//页面滚动事件
		window.onscroll= function (){
			if(isscrollside()&&interval==0){
				$("#bottom_tip").html("数据加载中……");
				console.log("现在话题数为："+now_topic_number);
				if(topic_type==0){
					var ajax_url="<c:url value='/user/topic/"+user_id+"/"+now_topic_number+"/5/getTopicListByUser'/>";
				}
				else{
					var ajax_url="<c:url value='/user/topic/"+now_topic_number+"/5/getTopicList?communityTopic="+topic_type+"'/>";
				}
				console.log(ajax_url);
					$.get(ajax_url,{},function(data){
					data=JSON.parse(data);
    				if(data.length==0){
    					interval=2;
    					$("#bottom_tip").html("没有更多话题了");
    				}else{
    				now_topic_number+=data.length;
    				ajax_topic(data,topic_type);
    				}
    			});
			}
		};
    	
    	$(".show_div").click(function () {
            $(this).stop().fadeOut(300);
            $("body").css({"overflow":"visible","padding-right":"37px"});
        });
    	$("#to_top").click(function(){
    		$('body,html').animate({scrollTop:0},500);
    		return false;
    	});
    	
    });
//按照yyyy-mm-dd hh:mm:ss的格式格式化传进来的时间
function getDate(date){
	var year=date.year+1900;
	var month=date.month+1;
	if(month<10){
        month="0"+month;
	}
	var day=date.date;
	if(day<10){
        day="0"+day;
	}
	var hour=date.hours;
	if(hour<10){
        hour="0"+hour;
	}
	var minute=date.minutes;
	if(minute<10){
        minute="0"+minute;
	}
	var second=date.seconds;
	if(second<10){
        second="0"+second;
	}
	var formatDate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return formatDate;
}

//判断是否滚动到底端
/*
 * 思想：通过判断现在底部链接距离顶端的距离与屏幕的高度加上鼠标滚动的高度对比，如果前者小，则说明到达底端了，返回true
 * */
function isscrollside(){
    var lastboxHeight=$(".footer").offset().top+$(".footer").height()-1;
    var documentHeight=document.body.clientHeight;
    var scrollHeight=$(window).scrollTop();
    console.log(lastboxHeight+"  "+documentHeight+"  "+scrollHeight);
    return (lastboxHeight<documentHeight+scrollHeight)?true:false;
}



//ajax加载数据
function ajax_topic(data,topic_type){
    		console.log(data);
    		for(var topic_item=0;topic_item<data.length;topic_item++){
    			var data_item=data[topic_item];
    			
    			//获取topic_type然后转回相应的中文
    			var topicType=data_item.topic.topic_type;
				var topicName;
				switch(topicType){
					case 1:
					topicName="活动通知";
					break;
					case 2:
					topicName="小区话题";
					break;
					case 3:
					topicName="区间趣事";
					break;
					case 4:
					topicName="二手转让";
					break;
				}
    			
    			//获取话题内容并对内容中的表情进行转义
    			var topic_neir=data_item.topic.topic_comment;
				topic_neir=replace_em(topic_neir);
    			
    			//对时间的解析和格式化时间格式
    			var topic_datetime=data_item.topic.topic_datetime;
    			topic_datetime = getDate(topic_datetime);
    			
    			var a_talk="<div id='"+data_item.topic.topic_id+"' class='user_talk'></div>";
    			$(".t_main").append(a_talk);
    			
    			//用户头像昵称，话题类型，来自小区，时间，话题内容
    			var a_talk_k1=$('<div class="user_img"><img class="user_imgli" src="/LaiFuCommunity'+data_item.author.user_head+'"></div><div class="user_info"><p class="name"><a href="/LaiFuCommunity/user/topic/other_topic?user_id='
    			+data_item.author.user_id+'" target="_blank">'+data_item.author.user_nickname+'</a></p><p class="time_address">'+topicName+'&nbsp;&nbsp;&nbsp;来自幸福小区&nbsp;&nbsp;&nbsp;'
    			+topic_datetime+'</p></div><div class="user_neirong"><p>'+topic_neir+'</p></div><div class="user_neirongimg"></div>');
    			$("#"+data_item.topic.topic_id).append(a_talk_k1);
    			
    			//话题图片
    			var topic_picture = data_item.topic.topicpicture_path;
    			var imgArray = topic_picture.split(",");
    			var user_neirongimg=$("#"+data_item.topic.topic_id+">.user_neirongimg");
				if(imgArray!=""){
					for(var i in imgArray){
					imgArray[i]="/LaiFuCommunity/"+imgArray[i];
					var imgouterdiv="<div></div>";
					user_neirongimg.append(imgouterdiv);
					imgouterdiv=$("#"+data_item.topic.topic_id+">.user_neirongimg>div");
					var img="<img src=''/>";
					imgouterdiv.append(img);
					img=$("#"+data_item.topic.topic_id+">.user_neirongimg>div:eq("+i+")>img");
					img.attr('src',imgArray[i]);
					/* 点击图片进行全屏看图的功能 */
        			img.click(function () {
            			$(".show_div").stop().fadeIn(300);
            			$("#show_img").attr("src",$(this).attr("src"));
            			$("body").css({"overflow":"hidden","padding-right":"17px"});
        			});
					}
				}
				
				//浏览点赞评论数量的显示和按钮的放置
				var praiseNumber = data_item.praiseVo.length;
				var commentNumber = data_item.commentVo.length;
				var praiseConfirm = data_item.praise;
				if(praiseConfirm){
					$("#"+data_item.topic.topic_id).append('<div class="user_good">&nbsp;|&nbsp;<a>取消赞('+praiseNumber+')</a>&nbsp;|&nbsp;<a>评论('
					+commentNumber+')</a>&nbsp;|&nbsp;<a hidden class="delete_topic">删除</a></div><div class="cominput_div" hidden><textarea rows="2" cols="88"></textarea><button>评论</button></div>');
				}
				else{
					$("#"+data_item.topic.topic_id).append('<div class="user_good">&nbsp;|&nbsp;<a>点赞('+praiseNumber+')</a>&nbsp;|&nbsp;<a>评论('
					+commentNumber+')</a>&nbsp;|&nbsp;<a hidden class="delete_topic">删除</a></div><div class="cominput_div" hidden><textarea rows="2" cols="88"></textarea><button>评论</button></div>');
				}
				
				
				//删除按钮的显示和添加删除事件
				if(topic_type!=1&&topic_type!=2&&topic_type!=4){
					$("#"+data_item.topic.topic_id+" .delete_topic").show();
					$("#"+data_item.topic.topic_id+" .delete_topic").click(function(){
						var delete_boolean=confirm("确定删除此条动态吗？原文和评论都会被删除");
						if(delete_boolean){
						var d_topic_id=$(this).parents(".user_talk").attr("id");
						console.log(d_topic_id);
						$.get("/LaiFuCommunity/user/topic/"+d_topic_id+"/deleteTopic",{},function(){
							$("#"+d_topic_id).slideUp(500,function(){
								$("#"+d_topic_id).remove();
							});
						});
						}
					});
				}
				
				
				/* 评论事件 */
    			$("#"+data_item.topic.topic_id+">.cominput_div>button").click(function(){
    				var dtopicid=$(this).parent().parent().attr("id");
    				console.log("该评论话题的id为："+dtopicid);
    				
    				if($(this).prev().val()==""){
    					//隐藏评论框
    					$(this).parent().fadeOut(300);
    				}else{
    				//隐藏评论框
    				$(this).parent().fadeOut(300);
    				
    				
    				//异步提交评论数据
    				$.post("<c:url value='/user/topic/"+dtopicid+"/0/0/commentTopic'/>",{comment_content:$(this).prev().val()},function(data){
    					$("#"+dtopicid+">.cominput_div>textarea").val("");
    					$("#"+dtopicid+">.comment").empty();
    					console.log(data);
    					data=JSON.parse(data);
    					console.log(data.length);
    					//评论数量
    					$("#"+dtopicid+">.user_good>a:nth-child(2)").text("评论("+data.length+")");
    					//评论内容的显示
    					comment_xianshi(data, dtopicid);
    				});
    				
    				
    		
    		
    				
    				}
    			});
				
				/* 点击评论弹出评论框事件 */
    			$("#"+data_item.topic.topic_id+">.user_good>a:nth-child(2)").click(function(){
    				$(this).parent().next().slideToggle(300);
    				$(this).parent().next().children("textarea").focus();
    			});
    			
    			/*评论框失去焦点事件*/
    			$("#"+data_item.topic.topic_id+">.cominput_div>textarea").blur(function(){
    				if($(this).val()==""){
    					$(this).parent().slideUp(300);
    				}
    			});
				
				//点赞评论div的添加
				$("#"+data_item.topic.topic_id).append('<div class="praise"></div><div class="comment"></div>');
				
				//点赞用户的显示
				var praiseList = data_item.praiseVo;
				if(praiseList.length!=0){
					$("#"+data_item.topic.topic_id+">.praise").append("<img src='/LaiFuCommunity/images/good.png'/>");
					for(var image in praiseList){
    					$("#"+data_item.topic.topic_id+">.praise").append("<a target='_blank' href='/LaiFuCommunity/user/topic/other_topic?user_id="+praiseList[image].user_id+"'><img title='"+praiseList[image].user_nickname+"' id='p"+praiseList[image].user_id+"' src='/LaiFuCommunity"+praiseList[image].user_head+"'/></a>");
    				}
				}
				
				//评论用户内容等的显示
				var commentList = data_item.commentVo;
				var topic_id=data_item.topic.topic_id;
				comment_xianshi(commentList,topic_id);
				
				
				/* 点赞和取消赞事件 */
    			$("#"+data_item.topic.topic_id+">.user_good>a:nth-child(1)").click(function(){
    				var dtopicid=this.parentNode.parentNode.id;//该点赞话题的id
    				console.log("准备点赞话题的id为"+dtopicid);
    				var dzval=this.text.substring(0,1);
    		
    				/* 点赞 */
    				if(dzval=="点"){
    				
    					//异步提交点赞
    					$.get("<c:url value='/user/topic/"+dtopicid+"/praiseTopic'/>",{},function(data){
    						//这里会返回一个当前话题的点赞列表
    						data=JSON.parse(data);
    						console.log(data);
    						$("#"+dtopicid+">.praise").empty();
    						
    						//根据拿到的点赞列表重新加载点赞列表
    						if(data.length!=0){
								$("#"+dtopicid+">.praise").append("<img src='/LaiFuCommunity/images/good.png'/>");
								for(var image in data){
    								$("#"+dtopicid+">.praise").append("<a target='_blank' href='/LaiFuCommunity/user/topic/other_topic?user_id="+data[image].user_id+"'><img title='"+data[image].user_nickname
    								+"' id='p"+data[image].user_id+"' src='/LaiFuCommunity"+data[image].user_head+"'/></a>");
    							}
							}
    						
    						
    						$("#"+dtopicid+">.user_good>a:nth-child(1)").text("取消赞("+data.length+")");
    					});
    				}
    				/* 取消赞 */
    				else if(dzval=="取"){
    		
    					//异步提交取消赞
    					$.get("<c:url value='/user/topic/"+dtopicid+"/deletePraise'/>",{},function(data){
    						//这里会返回一个当前话题的点赞列表
    						//data=JSON.parse(data);
    						console.log(data);
    						$("#"+dtopicid+">.praise").empty();
    						
    						//根据拿到的点赞列表重新加载点赞列表
    						if(data.length!=0){
								$("#"+dtopicid+">.praise").append("<img src='/LaiFuCommunity/images/good.png'/>");
								for(var image in data){
    								$("#"+dtopicid+">.praise").append("<a target='_blank' href='/LaiFuCommunity/user/topic/other_topic?user_id="+data[image].user_id
    								+"'><img title='"+data[image].user_nickname+"' id='p"+data[image].user_id+"' src='/LaiFuCommunity"+data[image].user_head+"'/></a>");
    							}
							}
    						
    						
    						
    						$("#"+dtopicid+">.user_good>a:nth-child(1)").text("点赞("+data.length+")");
    					},"json");
    				}
    			});
				
				
				
				
    		}
}

//评论内容的显示
function comment_xianshi(commentList,topic_id){
				for(var j=0;j<commentList.length;j++){
    				var comimg='<img id="c'+commentList[j][0].user.user_id+'" title="'+commentList[j][0].user.user_nickname+'" src="/LaiFuCommunity/'+commentList[j][0].user.user_head+'"/>';
    				$("#"+topic_id+">.comment").append(comimg);
    				var com_info='<div class="com_info '+j+'"></div>';
    				$("#"+topic_id+">.comment").append(com_info);
    				var com_user='<span class="com_user"><a href="/LaiFuCommunity/user/topic/other_topic?user_id='+commentList[j][0].user.user_id+'" target="_blank">'+commentList[j][0].user.user_nickname+'</a></span>';
    				$("#"+topic_id+">.comment>div."+j).append(com_user);
    				var com_neirong='<span class="com_neirong">：'+commentList[j][0].comment_content+'</span>';
    				$("#"+topic_id+">.comment>div."+j).append(com_neirong);
    				var comment_time = getDate(commentList[j][0].comment_time);
    				var com_time='<div class="com_time">'+comment_time+'&nbsp;<a class="comment_huifu">回复</a>&nbsp;&nbsp;<a hidden class="comment_delete">删除</a></div>';
    				$("#"+topic_id+">.comment>div."+j).append(com_time);
    				var user_id='<%=((User)session.getAttribute("user")).getUser_id()%>';
    				/* if(user_id==commentList[j][0].user.user_id){
    					$("#c"+user_id).next().find(".comment_delete").show();
    					$("#c"+user_id).next().find(".comment_delete").click(function(){
    						
    					});
    					//user/topic/评论id/deleteComment
    					
    				} */
    				var huifu_div='<div id="comment'+commentList[j][0].comment_id+'"></div>';
    				$("#"+topic_id+">.comment").append(huifu_div);
    				for(var k=1;k<commentList[j].length;k++){
    					var comimg='<img style="margin-left:3.5em;" id="c'+commentList[j][k].user.user_id+'" title="'+commentList[j][k].user.user_nickname+'" src="/LaiFuCommunity/'+commentList[j][k].user.user_head+'"/>';
    					$("#"+topic_id+">.comment>#comment"+commentList[j][0].comment_id).append(comimg);
    					var com_info='<div class="com_info '+j+k+'"></div>';
    					$("#"+topic_id+">.comment>#comment"+commentList[j][0].comment_id).append(com_info);
    					var com_user='<span class="com_user"><a target="_blank" href="/LaiFuCommunity/user/topic/other_topic?user_id='+commentList[j][k].user.user_id+'">'+commentList[j][k].user.user_nickname+'</a>回复<a target="_blank" href="/LaiFuCommunity/user/topic/other_topic?user_id='+commentList[j][k].replyUser.user_id+'">'+commentList[j][k].replyUser.user_nickname+'</a></span>';
    					$("#"+topic_id+">.comment>#comment"+commentList[j][0].comment_id+">div."+j+k).append(com_user);
    					var com_neirong='<span class="com_neirong">：'+commentList[j][k].comment_content+'</span>';
    					$("#"+topic_id+">.comment>#comment"+commentList[j][0].comment_id+">div."+j+k).append(com_neirong);
    					var comment_time = getDate(commentList[j][k].comment_time);
    					var com_time='<div class="com_time">'+comment_time+'&nbsp;<a class="comment_huifu">回复</a>&nbsp;&nbsp;<a hidden class="comment_delete">删除</a></div>';
    					$("#"+topic_id+">.comment>#comment"+commentList[j][0].comment_id+">div."+j+k).append(com_time);
    					/* if(user_id==commentList[j][k].user.user_id){
    						$("#c"+user_id).next().find(".comment_delete").show();
    					} */
    				}  
    			}
    			//为回复按钮添加事件
    			$("#"+topic_id+">.comment a.comment_huifu").click(function(){
    				$(this).parent().parent().append('<textarea rows="2" cols="88"></textarea><button>回复</button>');
    				$(this).parent().next().focus();
    				//为回复输入框添加失去焦点事件
    				$("#"+topic_id+">.comment textarea").blur(function(){
    					if($(this).val()==""){
    						$(this).next().remove();
    						$(this).remove();
    					}
    				});
    				//为回复框下面的回复按钮添加点击事件
    				$("#"+topic_id+">.comment button").click(function(){
    					console.log("触发了回复的点击事件");
    					var h_parent_id=$(this).parent().parent().attr("id");
    					var h_reply_userid=$(this).parent().prev().attr("id");
    					var h_topic_id=$(this).parents(".user_talk").attr("id");
    					if(h_parent_id == undefined){
    						console.log(123456);
    						h_parent_id=$(this).parent().next().attr("id");
    						
    					}
    					console.log(h_parent_id);
    					h_parent_id=h_parent_id.slice(7,h_parent_id.lenght);
    					h_reply_userid=h_reply_userid.slice(1,h_reply_userid.length);
    					console.log("评论id"+h_parent_id+"  被回复者id"+h_reply_userid+"  话题id"+h_topic_id);
    					var user_id='<%=((User)session.getAttribute("user")).getUser_id()%>';
    					if(h_reply_userid==user_id){
    						alert("请不要回复自己！");
    						$(this).prev().remove();
    						$(this).remove();
    						return false;
    					}
    					
    					//异步提交评论数据
    					$.post("<c:url value='/user/topic/"+h_topic_id+"/"+h_reply_userid+"/"+h_parent_id+"/commentTopic'/>",{comment_content:$(this).prev().val()},function(data){
    						$("#"+h_topic_id+">.comment textarea").remove();
    						$("#"+h_topic_id+">.comment button").remove();
    						$("#"+h_topic_id+">.comment").empty();
    						console.log(data);
    						data=JSON.parse(data);
    						console.log(data.length);
    						//评论数量
    						$("#"+h_topic_id+">.user_good>a:nth-child(2)").text("评论("+data.length+")");
    						comment_xianshi(data, h_topic_id);
    					});
    				});
    			});
}
</script>    
</body>
</html>
