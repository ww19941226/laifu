<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>个人信息</title>
</head>
<!-- 重置样式 -->
<link href="../css/reset.css" rel="stylesheet" />
<!-- 主要样式 -->
<link href="../css/laifucommunity_main_20160928.css" rel="stylesheet" />
<script src="../js/jquery-1.11.1.min.js"></script>
<body>
<form action="<c:url value='/user/complete'/>" method="post" >
<section class="user-content">
    <div class="table-box">
        <h1>个人信息</h1>
        <table class="user-table-add user-table-info" id="user_info">
            <tr>
                <td class="user-table-td" width="15%" id="user_nickname">昵&nbsp;&nbsp;称：</td>
                <td width="60%" ><input type="text" name="user_nickname" value='${sessionScope["user"].user_nickname }'></td>
                <td style="color:#ea3f18;" hidden>昵称不能为空</td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="user_realname">姓&nbsp;&nbsp;名：</td>
                <td width="60%" ><input disabled="disabled" type="text" name="user_realname" value='${sessionScope["user"].user_realname }'></td>
            	<td style="color:#ea3f18;" hidden>姓名不能为空</td>
            </tr>
            <tr id="user_sex_tr">
                <td class="user-table-td" width="15%" id="user_sex">性&nbsp;&nbsp;别：</td>
                <% String sex = ((com.laifu.module.entity.User)session.getAttribute("user")).getUser_sex(); %>
                <td >&nbsp;&nbsp;<%=sex %></td>
            </tr>
            <tr id="user_age_tr">
                <td class="user-table-td" width="15%" id="user_age">年&nbsp;&nbsp;龄：</td>
                <td width="60%" >&nbsp;&nbsp;${sessionScope["user"].user_age}</td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="user_account">手机号码：</td>
                <td width="60%" > <input  type="text" name="user_account" disabled="disabled" value='${sessionScope["user"].user_account}' > </td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="user_email">邮&nbsp;&nbsp;箱：</td>
                <td width="60%" > <input type="text" name="user_email" value='${sessionScope["user"].user_email}' > </td>
                <td style="color:#ea3f18;" hidden>邮箱格式不正确</td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="user_card">身份证号：</td>
                <td width="60%" > <input disabled="disabled" type="text" name="user_card" value='${sessionScope["user"].user_card}' > </td>
                <td style="color:#ea3f18;" hidden>身份证号码不正确</td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="user_community">小&nbsp;&nbsp;区：</td>
                <td width="60%" ><input type="text" value="幸福小区" disabled="disabled" name="user_community"> </td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="houseFloorNumber">楼&nbsp;&nbsp;号：</td>
                <td width="60%" ><input disabled="disabled" type="text" placeholder="例如：8" name="house_floornumber" value='${houseFloorNum}' > </td>
                <td style="color:#ea3f18;" hidden>请按提示填写楼号</td>
            </tr>
            <tr>
                <td class="user-table-td" width="15%" id="houseRoomNumber">房&nbsp;&nbsp;号：</td>
                <td width="60%" > <input disabled="disabled" type="text" name="house_roomnumber" placeholder="例如：352" value='${houseRoomNum }'> </td>
                <td style="color:#ea3f18;" hidden>请按提示填写房号</td>
            </tr>
            <tr>
                <td align="right"  width="15%"></td>
                <td  width="60%" >
                    <input id="update_submit" type="submit" value="更新信息" >
                </td>
            </tr>
        </table>
    </div>
</section>	
</form>
</body>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	var user_card='${sessionScope["user"].user_card}';
	console.log(user_card);
	if(user_card==""){
    	$("input[name='user_realname']").removeAttr("disabled");
    	$("input[name='user_card']").removeAttr("disabled");
    	$("input[name='house_floornumber']").removeAttr("disabled");
    	$("input[name='house_roomnumber']").removeAttr("disabled");
    	$("#user_sex_tr").hide();
    	$("#user_age_tr").hide();
    }
    $("#update_submit").click(function(){
    	if($("input[name='user_nickname']").val()==""){
    		$("input[name='user_nickname']").parent().next().show();
    		return false;
    	}
    	else{
    		$("input[name='user_nickname']").parent().next().hide();
    	}
    	if($("input[name='user_realname']").val()==""){
    		$("input[name='user_realname']").parent().next().show();
    		return false;
    	}
    	else{
    		$("input[name='user_realname']").parent().next().hide();
    	}
    	if(!$("input[name='user_email']").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
    		$("input[name='user_email']").parent().next().show();
    		return false;
    	}
    	else{
    		$("input[name='user_email']").parent().next().hide();
    	}
    	if($("input[name='user_card']").val().length!=18){
    		$("input[name='user_card']").parent().next().show();
    		return false;
    	}
    	else{
    		$("input[name='user_card']").parent().next().hide();
    	}
    	$.post("<c:url value='/user/checkHouse'/>",{"house_floornumber":$("input[name='house_floornumber']").val()},function(data){
    	console.log(data);
    			if(data==1){
    				$("input[name='house_floornumber']").parent().next().hide();
    				$.post("<c:url value='/user/checkRoom'/>",{house_floornumber:$("input[name='house_floornumber']").val(),house_roomnumber:$("input[name='house_roomnumber']").val()},function(data){
    					if(data==1){
    						$("input[name='house_roomnumber']").parent().next().hide();
    						var confirm_bool = true;
    						if(user_card == ""){
    							confirm_bool = confirm("是否确认提交，提交后姓名和身份证号码将不能再修改");
    						}
    						if(confirm_bool){
    							$("form").submit();
    						}
    					}
    					else{
    						$("input[name='house_roomnumber']").parent().next().show();
    						return false;
    					}
    				});
    			}
    			else{
    				$("input[name='house_floornumber']").parent().next().show();
    				return false;
    			}
    	});
    	return false;
    });
    	
});
    <%-- $(document).ready(function(){
    	var user_id='<%=((User)session.getAttribute("user")).getUser_id()%>';
    	var user_account='<%=((User)session.getAttribute("user")).getUser_account()%>';
    	var user_card='<%=((User)session.getAttribute("user")).getUser_card()%>';
    	var user_sex='<%=((User)session.getAttribute("user")).getUser_sex()%>';
    	var user_realname='<%=((User)session.getAttribute("user")).getUser_realname()%>';
    	var user_nickname='<%=((User)session.getAttribute("user")).getUser_nickname()%>';
    	var user_email='<%=((User)session.getAttribute("user")).getUser_email()%>';
    	//var user_head='<%=((User)session.getAttribute("user")).getUser_head()%>';
    	var houseFloorNum='<%=request.getAttribute("houseFloorNum")%>';
    	var houseRoomNum='<%=request.getAttribute("houseRoomNum")%>';
    	$("#user_account>input").val(user_account);
    	$("#user_nickname>input").val(user_nickname);
    	//$("#user_head>img").attr("src","/LaiFuCommunity"+user_head);
    	if(user_card!="null"){
    		if(user_sex=="男"){
    			$("#user_sex>input:nth-child(2)").attr("checked", "true").attr("disabled", "disabled");
    			$("#user_sex>input:nth-child(3)").attr("disabled", "disabled");
    		}else{
    			$("#user_sex>input:nth-child(3)").attr("checked", "true").attr("disabled", "disabled");
    			$("#user_sex>input:nth-child(2)").attr("disabled", "disabled");
    		}
    		}
    	
    		
    		$("#user_sex>input").attr("value",user_sex).attr("readOnly","true");
    		$("#user_email>input").attr("value",user_email).attr("readOnly","true");
    		$("#user_realname>input").attr("value",user_realname).attr("readOnly","true");
    		$("#user_card>input").attr("value",user_card).attr("readOnly","true");
    		$("#user_address>input:nth-child(4)").attr("value",houseFloorNum).attr("readOnly","true");
    		$("#user_address>input:nth-child(6)").attr("value",houseRoomNum).attr("readOnly","true");
    	} --%>
    	
/*         var i=2;
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
        }); */
    /*     $(".submit").click(function(){
            if($("#yzm").val()!=random){
                alert("验证码不正确");
                return false;
            }
        }); */
        
    /*     //头像的点击事件
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
} */

</script>
</html>