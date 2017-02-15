<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<!-- cssreset -->
<link href="../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../css/laifucommunity.css" rel="stylesheet"/>
<script src="../js/jquery-1.11.1.min.js"></script>
<script>
	function validator()
	{		
 	 if(confirm("是否完成此操作？"))
    		return true;
  	 else
    	    return false;
	}
</script>
<body class="grey">
<div class="pro-complains-content clearfix">
    <div class="complains-box">
        <h1>自助报修列表</h1>
        <!-- 搜索框 -->
        <div class="complains-find">
        </div>
        <table>
            <!-- 操作 -->
            <div class="complains-excel fl"></div>
            <tr>
                <th width="10%">序号</th>
                <th width="20%">用户</th>
                <th width="20%">报修内容</th>
                <th width="25%">报修时间</th>
                <th width="15%">操作</th>
                <th>报修状态</th>
            </tr>
            <c:set var="id" value="0" scope="page" />
            <c:forEach items="${ page.items }" var="r">
            <c:set var="id" value="${id+1}" scope="page" />
            <tr>
                <td>${ id }</td>
                <td>${ r.user.user_account }</td>
                <td><a href="<c:url value='/property/${r.repair.repair_id}/repair_detail' />">报修内容</a></td>
                <td><fmt:formatDate value="${ r.repair.repair_decldatatime }" /></td>
                <td>
                	 <c:choose>
                	 	<c:when test="${ r.repair.repair_state == 0}">
      						<a href="<c:url value="/property/${r.repair.repair_id}/2/updateRepair"/>"  onclick="return validator()">确认维修</a> 
   				       </c:when>
                		<c:when test="${ r.repair.repair_state == 2}">
      						<a href="<c:url value="/property/${r.repair.repair_id}/2/updateRepair"/>"  onclick="return validator()">完成维修</a>
   				       </c:when>
   				       <c:when test="${ r.repair.repair_state == 1}">
      						- - -
   				       </c:when>
                	</c:choose>	
                </td>
                <td>
                  <c:choose>
    				<c:when test="${ r.repair.repair_state == 0}">
      					未处理
   				    </c:when>
    				<c:when test="${ r.repair.repair_state == 1}">
      			  	  	已处理
    				</c:when>
    				<c:when test="${ r.repair.repair_state == 2}">
      			    	处理中
    				</c:when>
    				<c:when test="${ r.repair.repair_state == 3}">
      			   		 拒处理
    				</c:when>
				</c:choose>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="page complains-page">
        <common:pageV3 url='/property/property_repair${ repair_state==null?"":"?repair_state=" }${ repair_state==null?"":repair_state }' optimize="true"/>
        <!-- <a href="#">上一页</a>1<a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">8</a><a href="#">下一页</a>(共8条记录)2/8页
        <input type="text"><a href="#">跳转</a> -->
    </div>
</div>
</body>
</html>