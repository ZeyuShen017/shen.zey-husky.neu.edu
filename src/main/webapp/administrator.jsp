<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>管理员页面</title>


</head>
<body>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>

<!--中间部分div-->
<div class="empty">	
	<div class="orderlist_title">管理员操作</div>

            <button onClick="window.open('addBooks.jsp')">添加图书</button>
            <button onClick="window.open('User/ListUsers.action')">修改权限</button>
            <button onClick="window.open('addUsers.jsp')">添加用户</button>
  	       <button onClick="window.open('findOrderByName.jsp')">查询订单</button>
  	        <button onClick="window.open('Order/sell.action')">销售金额</button>

</div>


        
</body>
<script type="application/javascript">
	if(document.getElementById("user").value!=="1"){
		alert("You are not the adminstrator");
		window.location.href="login.jsp"

	}
</script>
</html>
