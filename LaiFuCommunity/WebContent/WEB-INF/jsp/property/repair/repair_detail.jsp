<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/header.jsp"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>投诉建议</title>
</head>
<!-- cssreset -->
<link href="../../css/reset.css" rel="stylesheet"/>
<!-- main -->
<link href="../../css/laifucommunity.css" rel="stylesheet"/>
<script src="../../js/jquery-1.11.1.min.js"></script>
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
<div class="pro-info-content clearfix">
    <div class="password-content">
        <h1>自助报修</h1>
        <div class="new-pwd-box detail-box">
            <div class="new-pwd">
                <label>报修人手机：</label>
                <div class="detail-content">${repairdetail.user.user_account }</div>
            </div>
            <div class="new-pwd">
                <label>报修人住址：</label>
                <div class="detail-content">${repairdetail.house.house_floornumber}栋---${repairdetail.house.house_roomnumber }房</div>
            </div>
            <div class="new-pwd">
                <label>报修时间：</label>
                <div class="detail-content"><fmt:formatDate value="${repairdetail.repair.repair_decldatatime }" pattern="yyyy-MM-dd  HH:mm:ss" /></div> 
            </div>
            <div class="new-pwd">
                <label>预期上门维修时间段：</label>
                <div class="detail-content"><fmt:formatDate value="${repairdetail.repair.repair_starttime }" pattern="yyyy-MM-dd  HH:mm:ss" />---<fmt:formatDate value="${repairdetail.repair.repair_endtime}" pattern="yyyy-MM-dd  HH:mm:ss" /></div>
            </div>
            <div class="new-pwd">
                <label>内容：</label>
                <div class="detail-content">
                  ${ repairdetail.repair.repair_project }
                </div>
            </div>
            <div style="margin-top:20px;" class="new-pwd">
            	<c:choose>
            		<c:when test="${repairdetail.repair.repair_state == 0}">
            			<br><br>
      					<a style="color:blue" href="<c:url value='/property/${repairdetail.repair.repair_id}/2/updateRepair'/>" onclick="return validator()"><button style="background-color:#5FAFE4;border:0;color:white;height:35px;width:100px;font-weight:bold;">确认维修</button></a></td>
                 		<a style="color:blue" href="<c:url value='/property/${repairdetail.repair.repair_id}/3/updateRepair'/>" onclick="return validator()"><button style="background-color:#5FAFE4;border:0;color:white;height:35px;width:100px;font-weight:bold;margin-left:20px;">拒绝维修</button></a></td>	
   				    </c:when>
                 	<c:when test="${repairdetail.repair.repair_state == 2}">
                 		<a style="color:blue" href="<c:url value='/property/${repairdetail.repair.repair_id}/1/updateRepair'/>" onclick="return validator()"><button style="background-color:#5FAFE4;border:0;color:white;height:35px;width:100px;font-weight:bold;">完成维修</button></a></td>	
                 	</c:when>
                 </c:choose>	
            </div>
        </div>
        <h1>莱福欢迎您！</h1>
    </div>
</div>
</body>
</html>