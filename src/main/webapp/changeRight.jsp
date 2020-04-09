<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
  <title></title>
  </head> 
  <body>
    
     <table border="1">
         <tr><th><label>Username</label></th><th><label>Right</label>
    </th></tr>
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

     <button onClick="window.open('addBooks.jsp')">addBooks</button>

  	<button onClick="window.open('addUsers.jsp')">addUser</button>

  	<button onClick="window.open('sell.jsp')">sale</button>

  	<button onClick="window.open('findOrderByName.jsp')">earchOrder</button>
  </body>
</html>
