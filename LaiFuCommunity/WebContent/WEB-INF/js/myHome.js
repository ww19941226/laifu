/**
 * Created by w_w on 2016/9/21.
 */
$(document).ready(function () {
    $(".page>div").click(function () {
        $(this).siblings().removeAttr("id");
        $(this).attr("id","on");
    });
});