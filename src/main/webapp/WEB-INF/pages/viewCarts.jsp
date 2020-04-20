<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="Controller.CartItem" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>My Cart</title>
<link rel="stylesheet" href="../css/fullCar.css" />
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/icons.css" />
<link rel="stylesheet" href="../css/table.css" />



</head>
<body>
	<!--顶部-->
	<div class="top">

		<div class="top_center">
			<ul class="top_bars">
				<li><a href="../Book/all.action">Continue Shoppong <span class="jt_down"></span></a>|</li>
				<li><a href="../User/logout.action">Sign Out<span class="jt_down"></span></a>|</li>
				<li><a href="../Order/listOrder.action">My Order<span class="jt_down"></span></a>|</li>
			</ul>
		</div>
	</div>
	<!--头部-->
	<div class="header3">
		<a href="#"></a>


		<div class="h3_right">
		</div>

	</div>
	<!--中间部分div-->
	<div class="empty">


		<div class="mainCenter">
			<div class="allCheck">
				
				<p class="leftM">Item</p>
				<p class="rightM">Price</p>
				<p class="leftM">Quantity</p>
				<p class="leftM">Totoal</p>

			</div>
			<div class="mainPro">

				<div class="aa">
					<a href="../Book/all.action" style="margin-left: 18px">Continue Shopping</a>
				</div>
				<c:forEach items="${sessionScope.cart}" var="Cart">
					<div class="bb">

						<img src="../images/Books/${Cart.value.books.isbn}.jpg" style="height:50px"> <span>${Cart.value.books.title}<br> </span>
						<div class="mm">
							<span>${Cart.value.books.price}</span>
						</div>

						<button type="button" onclick="window.location.href='../Book/deleteCart.action?bid=${Cart.value.books.bid}'"> - </button>
						<input type="text" value="${Cart.value.quantity}"
							name="num" readonly="true" />
						<button type="button" onclick="window.location.href='../Book/addCart.action?bid=${Cart.value.books.bid}'"> + </button>

							<div class="ri">$ ${Cart.value.total}</div>
						
					</div>
					
				</c:forEach>
			</div>
			<div class="allButtom">
			
				<a href="../Order/balance">
					<p class="caozuo">Checkout</p>
				</a> <span>Total：<font>$ ${total}
				</font></span>
			
			</div>

		</div>
	</div>
	<!--脚部-->
	<div class="footer3">
		<div class="f3_top">
			<div class="f3_center">
				<div class="ts1">Created by Zeyu Shen</div>
			</div>
		</div>


	</div>
</body>
</html>