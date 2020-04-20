<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Final Project</title>


</head>
<body>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>

<!--中间部分div-->
<div class="empty">	
	<div class="orderlist_title">Options</div>

            <button onClick="window.open('../Book/addBook')">Add Book</button>
            <button onClick="window.open('../User/ListUsers.action')">Change Right</button>
            <button onClick="window.open('../User/signup')">Add user</button>
  	       <button onClick="window.open('../Order/findOrderByName')">Search Order</button>
  	        <button onClick="window.open('../Order/sell.action')">Sell Total</button>

</div>


        
</body>
<script type="application/javascript">
	if(document.getElementById("user").value!=="1"){
		alert("You are not the adminstrator");
		window.location.href="../User/login"

	}
</script>
</html>
