<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>我要报修</title>
</head>
<!-- 重置样式 -->
<link href="../../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../../css/laifucommunity_main_20160926.css" rel="stylesheet" />
<script src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<body>
<form action="<c:url value='/user/user_repair/add'/>" method="post">
<section class="user-content">
    <div class="table-box">
        <h1>我要报修</h1>
        <table class="user-table-add">
            <tr>
                <td align="right"  width="15%">预期上门服务起始时间：</td>
                <td width="60%" >
                	<input type="text" name="starttime"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                </td>
                <td style="color:red;" hidden>上门服务起始时间不能早于当前时间</td>
             <!--    <td style="color:red; ">标题非法</td> -->
            </tr>
            <tr>
                <td align="right">预期上门服务结束时间：</td>
                <td>
                	<input type="text" name="endtime"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                </td>
                <td style="color:red;" hidden>结束时间不能早于开始时间</td>
               <!--  <td style="color:red;">时间非法</td> -->
            </tr>
            <tr>
                <td align="right">内容：</td>
                <td>
                    <textarea id="content" name="repair_project"></textarea>
                </td>
                <td style="color:red;" hidden>内容不能为空</td>
            </tr>
            <tr>
                <td align="right"  width="15%"></td>
                <td  width="60%" >
                    <input id="f_login" type="submit" value="提 交">
                </td>
                <td style="color:red;"></td>
            </tr>
        </table>
    </div>
</section>
</form>
<script src="/LaiFuCommunity/js/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#f_login").click(function(){
			if($("input[name='starttime']").val()==""){
				$("input[name='starttime']").parent().next().show();
				return false;
			}
			else{
				$("input[name='starttime']").parent().next().hide();
			}
			if($("input[name='endtime']").val()==""){
				$("input[name='endtime']").parent().next().show();
				return false;
			}
			else{
				$("input[name='endtime']").parent().next().hide();
			}
			if($("#content").val()==""){
				$("#content").parent().next().show();
				return false;
			}
			else{
				$("#content").parent().next().hide();
			}
			$.get("<c:url value='/user/isLaterNow'/>",{startTime:$("input[name='starttime']").val()},function(data){
    			if(data==1){
    				$("input[name='starttime']").parent().next().hide();
    				$.get("<c:url value='/user/isLaterTime'/>",{startTime:$("input[name='starttime']").val(),endTime:$("input[name='endtime']").val()},function(data){
    					if(data==1){
    						$("input[name='endtime']").parent().next().hide();
    						$("form").submit();
    					}
    					else{
    						$("input[name='endtime']").parent().next().show();
    						return false;
    					}
    				});
    			}
    			else{
    				$("input[name='starttime']").parent().next().show();
    				return false;
    			}
    		});
    		return false;
		});
	});
</script>
</body>
</html>