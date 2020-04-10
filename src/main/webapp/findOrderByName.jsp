<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>

<html>
<head>
<tltle></tltle>

</head>
<body><h1>Find Order By Name</h1>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>
<table width="40%" height="76" border="0">
  <form id="id" action="/Order/listOrder.action">
    <label>Username:  </label><input type="text" name="username"/>
    <input type="submit"/>
  </form>
</table>

<script type="application/javascript">
  if(document.getElementById("user").value!=="1"){
    alert("You are not the adminstrator");
    window.location.href="login.jsp"

  }
</script>
  </body>
</html>