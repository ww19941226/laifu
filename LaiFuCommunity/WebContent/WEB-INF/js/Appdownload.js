/**
 * Created by w_w on 2016/8/31.
 */
$(document).ready(function () {
    var winwidth=$(window).width();
    marfunction(winwidth);

    //屏幕改变的事件
    $(window).resize(function () {
        winwidth=$(window).width();
        marfunction(winwidth);
    });
});

//用于图片以中间对其的函数
var marfunction= function (winwidth) {
    if(winwidth>1440){
        m_left=(winwidth-1920)/2;
        $(".A_content>.Appdownload>img").css("margin-left",m_left);
        $(".A_content>.Apptesediv>img").css("margin-left",m_left);
    }
    else if(winwidth<=1440 && winwidth>=1000){
        m_left=(winwidth-1440)/2;
        $(".A_content>.Appdownload>img").css("margin-left",m_left);
        $(".A_content>.Apptesediv>img").css("margin-left",m_left);
    }
    else{
        m_left=-220;
        $(".A_content>.Appdownload>img").css("margin-left",m_left);
        $(".A_content>.Apptesediv>img").css("margin-left",m_left);
    }
}