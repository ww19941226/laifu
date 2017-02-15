<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改小区</title>

</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script>
function validator()
{
	var name = document.getElementById("community_name").value;
	var location = document.getElementById("community_location").value;
	var indirect = document.getElementById("community_indirect").value;
	if(!name){
		alert("小区名不能为空,请重新输入!");
		return false;
	}
	if(!location){
		alert("小区地址不能为空,请重新输入!");
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
        <h1>修改小区</h1>
        <form  method="post" action="<c:url value='/comadmin/${ community.community_id }/updateCommunity' />" onsubmit="return validator()">
        <div class="new-pwd-box">
            <div class="new-pwd">
                <label>小区名：</label>
                <input type="text"  id="community_name" name="community_name" value="${ community.community_name }">
            </div>
       <div id="massage1" style="color:red;font-size:20px">
            
            </div>
            <div class="new-pwd">
                <label>小区位置：</label>
                <input type="text" id="community_location" name="community_location" value="${ community.community_location }">
            </div>
              <div id="massage2" style="color:red;font-size:20px">
            
            </div>
            <div class="new-pwd">
                <label>小区简介：</label>
                <textarea id="community_indirect" name="community_indirect">${ community.community_indirect }</textarea>
            </div>
               <div id="massage3" style="color:red;font-size:20px">
            
            </div>
            <div class="new-pwd">
                <input  id="submitAction" class="new-pwd-btn" type="submit" value="提交">
            </div>
        </div>
        </form>
        <h1>莱福欢迎您！</h1>
    </div>
</div>

</body>

</html>