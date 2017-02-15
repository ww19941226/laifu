<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>  
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
   function submitInforAction() {
	   if(document.getElementById("user_nickname").value.trim()=="") {
		   alert("称昵不能为空！");
		   return;
	   }
    	document.getElementById("inforForm").submit();
    }
    	
    function ajaxFileUpload(){
		if(!confirm("是否修改头像")) return;
    $.ajaxFileUpload({  
        //处理文件上传操作的服务器端地址  
        url:"${pageContext.request.contextPath}/comadmin/uploadPicture",  
        secureuri:false,                           //是否启用安全提交,默认为false   
        fileElementId:'upheadImage',               //文件选择框的id属性  
        dataType:'text',                           //服务器返回的格式,可以是json或xml等  
        success:function(data, status){            //服务器响应成功时的处理函数  
            datadata = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀  
            datadata = data.replace(/<PRE.*?>/g, '');  
            datadata = data.replace("<PRE>", '');  
            datadata = data.replace("</PRE>", '');  
            datadata = data.replace("<pre>", '');  
            datadata = data.replace("</pre>", '');     //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]  
           
            if(data.indexOf("0") != -1){         //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)  
                $("img[id='updateHeadImage']").attr("src", data.substring(data.indexOf("`") +1, data.lastIndexOf("<")));  
            }else{  
                alert("头像更改失败！");  
            }  
        },  
        error:function(data, status, e){ //服务器响应失败时的处理函数  
             alert("头像更改失败！");  
        }  
    }); }
    </script>

</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<body class="grey">
<div class="pro-info-content clearfix">
    <div class="info-left fl">
        <a href="javascript:;" class="info-upload">
            <input type="file" name="file" id="upheadImage" onchange="ajaxFileUpload()">更换头像
        </a>
        <div class="info-pic"><img id="updateHeadImage" src="<c:url value='${ sessionScope["admin"].user.user_head }' />"></div>
        <div class="info-edit"><a href="javascript:submitInforAction();">更新信息</a></div>
        <div class="info-edit"><a href="<c:url value='/comadmin/information/password_update' />">修改密码</a></div>
    </div>
    <div class="info-right fl">
        <div class="info-rows">
            <h1>基本资料</h1>
            <form id="inforForm" action="<c:url value='/comadmin/updateInfor' />" method="post">
            <div class="info-row">
                <div class="clearfix sex">
                    <span class="fl">性别:</span>
                    <div class="fl sex-box">
                        <p class="male fl" >
                            <img src="../images/male.png"/>
                        </p>
                        <input disabled="disabled" ${ sessionScope["admin"].user.user_sex=="男"?"checked='checked'":"" } name="sex" type="radio" value="男"/>
                    </div>
                    <div class="fl sex-box">
                        <p class="female fl">
                            <img src="../images/female.png" />
                        </p>
                        <input disabled="disabled" ${ sessionScope["admin"].user.user_sex=="女"?"checked='checked'":"" } name="sex" type="radio" value="女"/>
                    </div>
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>用户名:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].user.user_account }' />
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>昵称:</span>
                    <input type="text" id="user_nickname" name="user_nickname" value='${ sessionScope["admin"].user.user_nickname }' />
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>真实姓名:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].user.user_realname }'/>
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>年龄:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].user.user_age }'/>
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>邮箱:</span>
                    <input type="text" name="user_email" value='${ sessionScope["admin"].user.user_email }' />
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>角色类型:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].usertype.usertype_name }' />
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>小区:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].community.community_name }' />
                </div>
            </div>
            <div class="info-row">
                <div class="clearfix">
                    <span>房屋:</span>
                    <input type="text" disabled="disabled" value='${ sessionScope["admin"].house.house_floornumber }楼${ sessionScope["admin"].house.house_roomnumber }房' />
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>