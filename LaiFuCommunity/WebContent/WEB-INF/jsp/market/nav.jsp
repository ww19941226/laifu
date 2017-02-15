<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>导航条</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .nav_out{
            width: 100%;
            height: 50px;
            background-color: #59cf2b;
        }
        .nav_main{
            width: 1200px;
            height: 50px;
            margin: 0 auto;
        }
        .nav_a{
            padding: 0 30px;
            text-decoration: none;
            color: #ffffff;
            line-height: 50px;
            font-size: 20px;
            text-align: center;
        }
    </style>
</head>
  
  <body>
  <div class="nav_out">
    <div class="nav_main">
        <a target="_parent" id="nav_all_a" style="background-color: rgba(16, 16, 16, 0.74);display: block;float: left;" class="nav_a" href="javascript:void(0)">全部商品分类</a>
        <a target="_parent" style="padding-left: 55px" class="nav_a" href="index.html">首页</a>
        <a target="_parent" class="nav_a" href="cuxiao.html">促销</a>
        <a target="_parent" class="nav_a" href="xinpin.html">新品</a>
        <a target="_parent" class="nav_a" href="jinkou.html">进口</a>
    </div>
</div>
  </body>
</html>
