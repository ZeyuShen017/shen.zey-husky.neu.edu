<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
  <title>Final Project</title>
  </head> 
  <body>
  <input id="user" type="hidden" value="${sessionScope.user.rright}"/>

  <table border="1">
      <th>Username</th><th>Right</th><th>Action</th></tr>
    <c:forEach items="${list}" var="u">
    <form>
    <tr>
    <td>${u.username}</td>
    <td>${u.rright}</td>
    <td><a href="changeRight.action?username=${u.username}">
    		Modify</a></td>
    </tr>
    </form>
    </c:forEach>
     </table>



  <script type="application/javascript">
      if(document.getElementById("user").value!=="1"){
          alert("You are not the adminstrator");
          window.location.href="../User/login"

      }
  </script>
  </body>

</html>
