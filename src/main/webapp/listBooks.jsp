<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
				<li><a href="../Order/listOrder.action">我的订单<span class="jt_down"></span></a>|</li>
			</ul>
		</div>
	</div>
	<!--头部-->
	<div class="header3">
		<a href="#"></a>
		<div class="h3_center">
			<div class="search_box">
				
			</div>

		</div>
		<div class="h3_right">
			<div class="tsc"><a href="http://localhost:8080/viewCarts.jsp">
				View Carts <span class="sj_right"></span></a>
			</div>
		</div>
	</div>
	<!--头部导航-->
	<div class="nav_top">
		<div class="nav_top_center">
			
		</div>
	</div>

	<!--内容-->
	<div class="container4">
		<!--热卖-->
		
		<div class="c4_b2">
			<h1 class="c4_b2_x">
				<select onChange=javascript:window,location.href=this.options[this.selectedIndex].value >
					<option selected>Category
					</option>
					<option value="all.action">All&nbsp;&nbsp;
					</option>
				<option value="all.action?category=Computer">Computer&nbsp;&nbsp;
				</option>
					<option value="all.action?category=Art">Art&nbsp;&nbsp;
					</option>
				</select>
			</h1>

			<!-- <ul class="c4_b2_y">
            	<li>
                	<span class="search_key">价格：</span>
                    <span class="search_val">0-99</span>
                    <span class="search_del"></span>
                </li>
                <li>
                	<span class="search_key">出版社：</span>
                    <span class="search_val">清华出版社</span>
                    <span class="search_del"></span>
                </li>
            </ul> -->
		</div>
		<div class="c4_b3">
			<h1></h1>
			
		</div>
		
		<!--商品列表-->
		<div class="c4_b5">
			<div class="c4_b5_content">
				<ul class="c4_b5_c_boxes">

					<c:forEach items='<%= request.getAttribute("booklist")%>' var="book">
						<li class="c4_b5_c_box">
							<!--图片-->
							<div class="c4_b5_c_box_pic">
								<div class="c4b5_pic_view">
									<a href="viewDetail.action?bid=${book.bid}"><img src="../images/list_p1.png"></a>
								</div>
							</div> <!--价钱-->
							<div class="c4_b5_c_box_txt">
								<h1><c:out value="${book.getPrice()}"/></h1>
								<h2>
									<a href="viewDetail.action?bid=${book.bid}">${book.getTitle()}</a>
								</h2>
								
							</div> <!--购买等操作-->
							<div class="c4b5_el">
								<div class="c4b5_el_x">
									<span class="wjx01"></span>
								</div>
							</div>
							<ul class="c4b5_option">
								<li class="shopcar_box"><span class="shopcar01"></span><a
									href="addCart.action?bid=${book.bid}">加入购物车</a></li>
							</ul>
						</li>
					</c:forEach>

				</ul>
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

