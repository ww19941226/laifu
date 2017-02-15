/**
 * Created by w_w on 2016/9/3.
 */
$(function(){
    $('.emotion').qqFace({
        id : 'facebox',
        assign:'saytext',
        path:'/LaiFuCommunity/images/arclist/'	//表情存放的路径
    });
    //话题发表
    $(".sub_btn").click(function(){
        
        var topic_type=$(".sub_select option:selected").val();
        console.log(topic_type);
        switch(topic_type){
            case "活动通知":
                $("#topic_type").val("1");
                break;
            case "小区话题":
                $("#topic_type").val("2");
                break;
            case "区间趣事":
                $("#topic_type").val("3");
                break;
            case "二手转让":
                $("#topic_type").val("4");
                break;
            default :
                alert("草泥马敢改老子代码！");
                break;
        }
    });
    //图片上传前预览
    $("#file0").change(function(){
        $("#com_images").empty();
        var fileslength=this.files.length;
        if(fileslength>8){
            $("#com_images").append("请不要发表多于8张图片");
        }
        else{
            var objUrl;
            for(var i=0;i<fileslength;i++){
                objUrl = getObjectURL(this.files[i]);
                if (objUrl) {
                    var img='<img src="'+objUrl+'"/>';
                    $("#com_images").append(img);
                }
            }
        }
    }) ;
});
//查看结果
function replace_em(str){
    str = str.replace(/\</g,'&lt;');
    str = str.replace(/\>/g,'&gt;');
    str = str.replace(/\n/g,'<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="/LaiFuCommunity/images/arclist/$1.gif" border="0" />');
    return str;
}

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