<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Final Project</title>
	<link rel="stylesheet" href="../css/common.css" />
	<link rel="stylesheet" href="../css/style.css" />

</head>
<body>
<div class="header1">
	<a href="#"> </a>
</div>

<div class="c1_box1">
	<!--背景图片-->
	<div class="login_box">
		<!--登陆框-->
		<div class="center1" div id="top" style="margin:auto;">

			<form:form action="/User/login.action" method="post" modelAttribute="userinfo">
				<h1>Sign In</h1>
				<h2>Don't leak your password</h2>
				<div class="si_box">
					<span class="usr_icon"></span>
					<form:input type="text" path="username"></form:input>


				</div>
				<form:errors type="text" path="username"></form:errors>
				<!--分割条-->
				<div class="c10"></div>
				<div class="si_box">
					<span class="pwd_icon"></span>
					<form:input type="password" path="password"></form:input>

				</div>
				<form:errors type="password" path="password"></form:errors>

				<div class="fg_box">
					 <a class="treg" href="/User/signup">Sign up</a>
				</div>
				<div class="sub_box">
					<input type="submit" value="Sign in" />
				</div>
			</form:form>


		</div>
	</div>
</div>


</body>
</html>