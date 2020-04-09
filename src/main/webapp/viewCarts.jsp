<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="Controller.CartItem" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <html>
  <head>
  </head>
  <body><h1>查看购物车</h1>
<s:form>
<table>
    <tr><th>ISBN号</th><th>书名</th><th>数量</th><th>金额</th></tr>
    <s:iterator value="#session.cart">
    <tr>
    <td><s:property value="key"/></a></td>
    <td><s:property value="value.books.title"/></a></td>
    <td><s:property value="value.quantity"/></td>
    <td><s:property value="value.total"/></td>
    </tr>
    </s:iterator>
    <tr>
    <td>
    <s:submit type="button" action="listBooks" key="toviewbook" align="center"/>
    <s:submit type="button" action="balance" key="balance" align="center"/>
    </td>
    </tr>
    </table>
    </s:form>
  </body>
</html>-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>购物车页面</title>
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
				<li><a href="../Book/all.action">继续购物</a>|</li>
				<li><a href="listOrder.action">我的订单<span class="jt_down"></span></a>|</li>
				<% out.print(session.getAttribute("cart")); %>
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

		<div class="peisong">
			<pre><a href="addCart.action">我的购物车</a></pre>
		</div>
		<div class="mainCenter">
			<div class="allCheck">
				
				<p class="leftM">商品</p>
				<p class="rightM">单价(元)</p>
				<p class="leftM">数量</p>
				<p class="leftM">价格</p>

			</div>
			<div class="mainPro">

				<div class="aa">
					<a href="../Book/all.action" style="margin-left: 18px">继续购物</a>
				</div>
				<c:forEach items="${sessionScope.cart}" var="Cart">
					<div class="bb">

						<img src="../images/1.png"> <span>${Cart.value.books.title}<br> </span>
						<div class="mm">
							<span>${Cart.value.books.price}</span>
						</div>


						<input type="text" value="${Cart.value.quantity}"
							name="num" readonly="true" />
							<div class="ri">¥${Cart.value.total}元</div>
						
					</div>
					
				</c:forEach>
			</div>
			<div class="allButtom">
			
				<a href="../addOrder.jsp">
					<p class="caozuo">去结算</p> 				
				</a> <span>总价(不含运费)：<font>¥${total}元
				</font></span>
			
			</div>

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
</body>
</html>