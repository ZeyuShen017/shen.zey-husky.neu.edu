<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>电子商务-登录页</title>
	<link rel="stylesheet" href="/css/common.css" />
	<link rel="stylesheet" href="/css/style.css" />

</head>
<body>
<div class="header1">
	<a href="#"> </a>
</div>
<% out.print( session.getAttribute("user")); %>

<div class="c1_box1">
	<!--背景图片-->
	<div class="login_box">
		<!--登陆框-->
		<div class="center1" div id="top" style="margin:auto;">
			<form action="/User/login.action" method="post">
				<h1>账号登陆</h1>
				<h2>公共场所请不要泄露您的密码，以防止账号丢失</h2>
				<div class="si_box">
					<span class="usr_icon"></span>
					<input type="text" name="username" ></input>
				</div>
				<!--分割条-->
				<div class="c10"></div>
				<div class="si_box">
					<span class="pwd_icon"></span>
					<input type="password" name="password"></input>
				</div>
				<div class="fg_box">
					 <a class="treg" href="addUsers.jsp">Sign up</a>
				</div>
				<div class="sub_box">
					<input type="submit" value="Sign in" />
				</div>
			</form>


		</div>
	</div>
</div>


</body>
</html>