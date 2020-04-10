<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>购物车页面</title>
<link rel="stylesheet" href="../css/fullCar.css" />
<link rel="stylesheet" href="../css/common.css"/>
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/icons.css" />
<link rel="stylesheet" href="../css/table.css" />
	<link rel="stylesheet" href="../css/orderList.css" />


<body>
	<input type="hidden" id="username"  value="${user.username}">
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	
                <li><a href="../Book/all.action">继续购物<span class="jt_down"></span></a>|</li>
            
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="#"></a>
        <div class="h3_right">
        	<img src="images/play_03.png" alt="">
        </div>
       
    </div>
    
<!--中间部分div-->
<div class="empty">
	
	<div class="orderlist_title">Order List</div>

	<table border="2" class="formCenter">
    	<tr><th>Order ID</th><th>Username</th><th>Address</th><th>Zipcode</th><th>Total</th></tr>
    	<c:forEach items="${orders}" var="list">
    		<tr><td><a href="listOrderItem.action?oid=${list.oId}">
					${list.oId}</a>
    		</td>
    		<td>${user.username}</td>
    		<td>${list.address}</td>
    		<td>${list.zipcode}</td>
    		<td>${list.total}</td>
    		</tr>
		</c:forEach>
    </table>

	
</div>
     <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
	</div>
<script type="application/javascript">
	if(document.getElementById("username").value===""){
		alert("Please Sign In");
		window.location.href="../login.jsp"

	}
</script>
</body>
</html>
