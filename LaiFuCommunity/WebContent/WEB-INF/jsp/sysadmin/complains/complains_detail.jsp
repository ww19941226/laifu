<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>投诉建议</title>
</head>
<!-- cssreset -->
<link href="<c:url value='/css/reset.css' />" rel="stylesheet"/>
<!-- main -->
<link href="<c:url value='/css/laifucommunity.css' />" rel="stylesheet"/>
<script src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<body class="grey">
<script>
function validator()
{
	var content = document.getElementById("reply").value;
	if(content == "")
	{
		alert("回复内容不能为空,请重新输入!");
		return false;
	}

 	if(confirm("确认要回复吗？"))
    	return true;
  	else
    	return false;
}
</script>${ complains.complains_replycontent }
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>投诉建议</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>标题：</label>
                <div class="detail-content">${ complains.complains_title }</div>
            </div>
                  <div class="new-pwd">
                <label>投诉人姓名：</label>
                <div class="detail-content">${ user.user_realname}</div>
            <div class="new-pwd">
                <label>投诉人手机：</label>
                <div class="detail-content">${ complains.complains_phone }</div>
            </div>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <div class="detail-content">${ complains.complains_content }</div>
            </div>
            <form method="post" action="<c:url value='/sysadmin/replyComplains' />?complains_id=${ complains.complains_id }" onsubmit="return validator()">
            <div class="new-pwd">
                <label>回复内容：</label>
         
                <textarea name="complains_replycontent" id="reply">${ complains.complains_replycontent }</textarea>
                      <p></p>
                <div style="color:#b32424;" id="reply1"></div>
            </div>
            <div class="new-pwd">
                <input class="new-pwd-btn" id="replycontent" type="submit" value="回复">
            </div>
            </form>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function (){
  $("#replycontent").click(function (){
  if($("#reply").val()==""){
	$("#reply1").text("输入内容不能为空！");
				return false;
    }
    if($("#reply").val().length>50){
    $("#reply1").text("请输入少于50个字符的内容");
    return false;
    }
  });
});
    $(document).ready(function (){
            var $tex = $("#reply");
            var $but = $("#replycontent");
            var ie = jQuery.support.htmlSerialize;
            var str = 0;
            var abcnum = 0;
            var maxNum = 100;
            var texts= 0;
            var num = 0;
            var sets = null;

            //$tex.val("");

            //顶部的提示文字
            $tex.focus(function(){
                if($tex.val()==""){
                    $("p").html("您还可以输入的字数<span>50</span>");
                }
            })

            //文本框字数计算和提示改变
            if(ie){
                $tex[0].oninput = changeNum;
            }else{
                $tex[0].onpropertychange  = changeNum;
            }

            function changeNum(){
                //汉字的个数
                str = ($tex.val().replace(/\w/g,"")).length;
                //非汉字的个数
                abcnum = $tex.val().length-str;

                total = str*2+abcnum;

                if(str*2+abcnum<maxNum || str*2+abcnum == maxNum){
                    $but.removeClass()
                    $but.addClass("but");
                    texts =Math.ceil((maxNum - (str*2+abcnum))/2);
                    $("p").html("您还可以输入的字数<span>"+texts+"</span>").children().css({"color":"blue"});
                }else if(str*2+abcnum>maxNum){
                    $but.removeClass("")
                    $but.addClass("grey");
                    texts =Math.ceil(((str*2+abcnum)-maxNum)/2);
                    $("p").html("您输入的字数超过了<span>"+texts+"</span>").children("span").css({"color":"red"});
                }
            }

            //按钮点击
            $but.click(function(){
                if($(this).is(".grey")){
                    sets = setInterval(flash,100);
                    $tex.addClass("textColor");
                }

                function flash(){
                    num++;
                    console.log(num);
                    if(num >= 5){
                        num=0;
                        clearInterval(sets);
                    }else if(num%2 == 1){
                        $tex.addClass("textColor")
                    }else{
                        $tex.removeClass("textColor")
                    }
                }
            })
        });
 
    </script>

</script>
</html>