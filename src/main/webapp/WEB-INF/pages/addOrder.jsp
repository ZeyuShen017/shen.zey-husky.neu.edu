<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Final Project</title>
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
				<li><a href="../Book/all.action">Continue Shopping</a>|</li>
				<li><a href="listOrder.action">My Order<span class="jt_down"></span></a>|</li>

			</ul>
		</div>
	</div>
	<!--头部-->
	<div class="header3">
		<a href="#"></a>




	</div>
	<p class="orderButtom" style="text-align: center">Place Your Order</p>
	<!--中间部分-->
	<div class="content">
		<div class="contentCenter">
			
			<div class="centerTop" style="text-align: center">
				<b style="font-size:20px;">Shipping Address</b>
				<form:form name="id" action="/Order/balance.action" method="post" modelAttribute="balance">



				<p style="font-size:15px;">

						<p>Username:&nbsp&nbsp<input id="username" type="text" name="username"  value="${sessionScope.user.username}" readonly="true" /></p>
						<p>Address:&nbsp&nbsp&nbsp&nbsp<form:input type="text" path="address"  /></p>
						<p>Zipcode:&nbsp&nbsp&nbsp&nbsp<form:input type="text" path="zipcode"  /></p>

						<p>Total:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<form:input type="text" id="#Total" path="total"  value="${sessionScope.total}" readonly="true" /></p><br>

					<p><form:errors type="text" path="address"  /></p><br>

					<p><form:errors type="text" path="zipcode"  /></p>
				</form:form>

				</p>
			</div>

			<div class="money">
				<span>Items：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$${total}</span>
				<span>Shipping fee：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$0</span>

				<span>Oder Total：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$${total}</span>
			</div>
			<input type="image" name="submit" src="../images/21_03.png"
				   onClick="document.id.submit()">


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
<script type="application/javascript">
	var flag=0;
	if(document.getElementById("username").value===""){
		alert("Please Sign In");
		window.location.href="../User/login";
		flag=1;
	}
	if(document.getElementById("#Total").value === "0.0" && flag !==1)
	{
		alert("Your cart is empty");
		window.location.href="../Book/all.action"
	}
</script>
</body>
</html>
