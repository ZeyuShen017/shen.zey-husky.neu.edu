<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>结算页面</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/icons.css" />
<link rel="stylesheet" href="../css/table.css" />
<link rel="stylesheet" href="../css/orderSure.css" />
</head>
<body>
	<!--顶部-->
	<div class="top">
		<div class="top_center">
			<ul class="top_bars">
				<li><a href="../Book/all.action">继续购物</a>|</li>
				<li><a href="listOrder.action">我的订单<span class="jt_down"></span></a>|</li>

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
	<p class="orderButtom" style="text-align: center">填写并核对订单信息</p>
	<!--中间部分-->
	<div class="content">
		<div class="contentCenter">
			
			<div class="centerTop" style="text-align: center">
				<b style="font-size:20px;">收货人信息</b>
				<p style="font-size:15px;">
					<form name="id" action="/Order/balance.action" method="post">

						<p>Username:&nbsp&nbsp<input id="username" type="text" name="username"  value="${user.username}" readonly="true" /></p>
						<p>Address:&nbsp&nbsp&nbsp&nbsp<input type="text" name="address"  /></p>
						<p>Zipcode:&nbsp&nbsp&nbsp&nbsp<input type="text" name="zipcode"  /></p>
						<p>Total:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="total"  value="${sessionScope.total}" readonly="true" /></p>


					</form>
				</p>
			</div>


			<div class="money">
				<span>总商品金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥${total}</span>
				<span><img src="images/sureLogo_18.png" alt="">运费：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包邮</span>

				<span>应付总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥${total}</span>
			</div>
			<input type="image" name="submit" src="images/21_03.png"
				onClick="document.id.submit()">
		</div>

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
	if(document.getElementById("username").textContent===""){
		alert("Please Sign In");
		window.location.href="login.jsp"

	}
</script>
</body>
</html>
