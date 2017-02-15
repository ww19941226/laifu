<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改通知</title>
</head>
<!-- cssreset -->
<link href="../../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../../css/laifucommunity.css" rel="stylesheet"/>
<script src="../../js/jquery-1.11.1.min.js"></script>
<script>
	function validator()
	{
	 var title = document.getElementById("title").value;
	 var content = document.getElementById("content").value;
	 
	 if(!title | !content)
	 {
	 alert("内容不能为空,请重新输入!");
	 return false;
	 }
	 	
	 
	 	
 	 if(confirm("确认要修改吗？"))
    		return true;
  	 else
    	    return false;
	}
</script>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>修改通知</h1>
        <form action="<%=basePath%>property/${notify.notify_id}/update_notify" method="post" onsubmit="return validator()">
        <div class="new-pwd-box">

            <div class="new-pwd">
                <label>标题：</label>
                <input type="text" id="title" name ="notify_titile"  value="${ notify.notify_titile }">
                <label id="title_tip" style="color:red;display: none;">标题请不要超过30个字</label>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <textarea  id="content" name = "notify_content">  ${ notify.notify_content } </textarea>
                <label id="content_tip" style="color:red;display: none;">内容请不要超过200个字</label>
            </div>
            <div class="new-pwd">
            	
                <input class="new-pwd-btn" type="submit" value="提交">
                <label id="null_tip" style="color:red;display: none;">标题和内容不能为空</label>
                <label id="toolong_tip" style="color:red;display: none;">请按要求填写数据</label>
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
<script src="/LaiFuCommunity/js/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#title").keyup(function(){
			if($(this).val().length>30){
			console.log(123);
				$("#title_tip").show();
			}
			else{
				$("#title_tip").hide();
			}
		});
		$("#content").keyup(function(){
			if($(this).val().length>200){
				$("#content_tip").show();
			}
			else{
				$("#content_tip").hide();
			}
		});
		$(".new-pwd-btn").click(function(){
			if($("#title").val()==""||$("#content").val()==""){
				$("#null_tip").show();
				return false;
			}
			else if($("#title").val().length>30||$("#content").val().length>200){
				$("#toolong_tip").show();
				return false;
			}
		});
	});
</script>
</body>
</html>