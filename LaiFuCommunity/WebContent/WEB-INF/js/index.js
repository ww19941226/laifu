/**
 * Created by w_w on 2017/2/7.
 */
$(document).ready(function () {
	$(".right_gwc").click(function(){
		location.href="/LaiFuCommunity/market/myCart/"
	});
	$(".right_dingdan").click(function(){
		location.href="/LaiFuCommunity/market/myDingdan?state=0"
	});


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
    
    
    var offset = $("#end").offset();  //结束的地方的元素
	$(".gwc_div").click(function(event){   //是$(".gwc_div")这个元素点击促发的 开始动画的位置就是这个元素的位置为起点
		var addcar = $(this);
		var product_id=addcar.attr("id");
		var img = addcar.parent().find('img').attr('src');
		var flyer = $('<img class="u-flyer" src="'+img+'">');
		flyer.fly({
			start: {
				left: event.clientX,
				top: event.clientY
			},
			end: {
				left: offset.left,
				top: offset.top,
				width: 0,
				height: 0
			},
			onEnd: function(){
				$.ajax({
		        	type: "POST",
		        	url: "/LaiFuCommunity/market/addCart",
		        	dataType: "json",
		        	data:{"product_id":product_id},
		        	success: function(data){ 
		        		console.log(data);
		       			$("#msg").show().animate({width: '220px'}, 500,function(){
							$("#msg").animate({width: '1px'},1);
						}).fadeOut(1000);
		        	}
		        });
				this.destory();
			}
		});
	});
});