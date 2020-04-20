<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Sell</title>

</head>

<body>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>

<table border="2" class="formCenter">
    <tr><th>Order ID</th><th>Username</th><th>Address</th><th>Zipcode</th><th>Total</th></tr>
    <c:forEach items="${AllOrder}" var="list">
        <tr><td>${list.oId}</td>
            <td>${user.username}</td>
            <td>${list.address}</td>
            <td>${list.zipcode}</td>
            <td>${list.total}</td>
        </tr>
    </c:forEach>
</table>

<label>Total:</label>${SellTotal}


<script type="application/javascript">
    if(document.getElementById("user").value!=="1"){
        alert("You are not the adminstrator");
        window.location.href="../User/login"

    }
</script>
</body>
</html>
