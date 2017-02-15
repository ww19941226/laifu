<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>我要投诉</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<body>
<section class="user-content">
    <div class="table-box">
        <h1>我要投诉</h1> 
       <form method="post" action="<c:url value='/user/addComplain' />">
        <table class="user-table-add">
            <tr>
                <td align="right"  width="15%">标题：</td>
                <td width="60%" ><input id="title" type="text" name="complains_title"> </td>
            </tr>
            <tr>
            	<td colspan="2" id="title_tip" style="color:red;display: none;">标题请不要超过30个字</td>
            </tr>
            <!-- <tr>
                <td align="right"  width="15%">投诉对象：</td>
                <td width="60%" >
                    <select name="usertype_id">
                    	<c:forEach items="${ usertypelist }" var="usertype">
                        <option value="${usertype.usertype_id}"> ${usertype.usertype_name} </option>
                        </c:forEach>
                    </select>
                </td>
            </tr> -->
            <tr>
                <td align="right" width="15%" >内容：</td>
                <td width="60%">
                    <textarea id="content" name="complains_content"></textarea>
                </td>
            </tr>
            <tr>
            	<td id="content_tip" colspan="2" style="color:red;display: none;">内容请不要超过200个字</td>
            </tr>
            <tr>
            	<td id="null_tip" colspan="2" style="color:red;display: none;">标题和内容不能为空</td>
                <td id="toolong_tip" colspan="2" style="color:red;display: none;">请按要求填写数据</td>
            </tr>
            <tr>
                <td align="right"  width="15%"></td>
                <td  width="60%" >
                    <input id="f_login" type="submit" value="提 交">
                </td>
                
            </tr>
        </table>
     </form>
    </div>
</section>
<script src="/LaiFuCommunity/js/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#title").keyup(function(){
			if($(this).val().length>30){
				$("#null_tip").hide();
				$("#title_tip").show();
			}
			else{
				$("#title_tip").hide();
			}
		});
		$("#content").keyup(function(){
			if($(this).val().length>200){
				$("#null_tip").hide();
				$("#content_tip").show();
			}
			else{
				$("#content_tip").hide();
			}
		});
		$("#f_login").click(function(){
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