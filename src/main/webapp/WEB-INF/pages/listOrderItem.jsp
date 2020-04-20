<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Final Project</title>
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

            <li><a href="../Book/all.action">Continue Shoppong <span class="jt_down"></span></a>|</li>
            <li><a href="../User/logout.action">Sign Out<span class="jt_down"></span></a>|</li>

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

    <div class="orderlist_title">Order Item</div>

    <table border="2" class="formCenter">
        <tr><th>Item ID</th><th>Order ID</th><th>Book Title</th><th>Book Author</th><th>Quantity</th></tr>
        <c:forEach items="${list}" var="list">
            <tr><td>${list.itemId}</td>
                <td>${list.ordersByOid.oId}</td>
                <td>${list.booksByBid.getTitle()}</td>
                <td>${list.booksByBid.getAuthor()}</td>
                <td>${list.quantity}</td>

            </tr>
        </c:forEach>
    </table>


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
    if(document.getElementById("username").value===""){
        alert("Please Sign In");
        window.location.href="../User/login"

    }
</script>
</body>
</html>
