/**
 * Created by w_w on 2017/2/7.
 */
$(document).ready(function () {

    //banner
    $(".index_banner_outer>div div").hover(function () {
        $(".index_banner_outer>div div").removeClass("zhezhao_on").addClass("zhezhao_off");
        $(this).removeClass("zhezhao_off").addClass("zhezhao_on");
        var index = $(".index_banner_outer>div div").index(this);
        $(".index_banner_center>a").stop().fadeOut(300);
        $(".index_banner_center>a:nth-child("+(index+1)+")").stop().fadeIn(300);
    })

    //nav
    $("#nav_all_a,.all_a_outer").hover(function () {
        $(".all_a_outer").stop().slideDown(500);
    }, function () {
        $(".all_a_outer").stop().slideUp(500);
    });
});