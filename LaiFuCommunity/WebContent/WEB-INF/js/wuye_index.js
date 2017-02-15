/**
 * Created by w_w on 2016/9/2.
 */
$(document).ready(function () {
    outheightresize();
    $(window).resize(function () {
        outheightresize();
    });

    //标题的点击事件
    $(".c_title").click(function () {
        $(".w_content .c_neirong").stop().slideUp();
        $(this).siblings(".c_neirong").stop().slideToggle(300);
    });
});

//初始化的函数，用于适应列表div的高度，让版权信息那些能在最下面
var outheightresize= function () {
    var winheight=$(window).height();
    var outheight=$(".w_outer").height();
    if(winheight-330>outheight){
        $(".w_outer").height(winheight-330);
    }
}