<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Final Project</title>
</head>
<body><h1>Add Books</h1>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>
<form:form  modelAttribute="addBook" name="addbook" action="../Book/addBook.action" method="post" enctype="multipart/form-data">

   <form:errors type="text" path="title" /><br>
   <form:errors type="text" path="isbn" /><br>
   <form:errors type="text" path="author"/><br>
   <form:errors type="text" path="price" /><br>
   <form:errors path="category"/>
   <label>Title&nbsp&nbsp&nbsp&nbsp</label><form:input type="text" path="title" /><br>
   <label>ISBN&nbsp&nbsp&nbsp&nbsp</label><form:input type="text" path="isbn" /><br>
   <label>Author&nbsp&nbsp</label><form:input type="text" path="author"/><br>
   <label>Price&nbsp&nbsp&nbsp&nbsp</label><form:input type="text" path="price" /><br>
   <label>Category</label>
   <form:select path="category">
      <c:forEach items="${sessionScope.category}" var="category">
         <option value="${category.name}">${category.name}&nbsp;&nbsp;
         </option>
      </c:forEach>
   </form:select><br>
   <label>Upload Image</label><input type="file" name="photo" required><br>

   <input type="submit"/>
</form:form>
<!--<form  name="addbook" action="../Book/addBook.action" method="post" enctype="multipart/form-data">
   <label>Title&nbsp&nbsp&nbsp&nbsp</label><input type="text" name="title" required/><br>
   <label>ISBN&nbsp&nbsp&nbsp&nbsp</label><input type="text"name="isbn" required/><br>
   <label>Author&nbsp&nbsp</label><input type="text"name="author"required/><br>
   <label>Price&nbsp&nbsp&nbsp&nbsp</label><input type="text"name="price" required/><br>
   <label>Category</label>
   <select name="category">
      <c:forEach items="${sessionScope.category}" var="category">
         <option value="${category.name}">${category.name}&nbsp;&nbsp;
         </option>
      </c:forEach>
   </select><br>
   <label>Upload Image</label><input type="file" name="upload" required><br>

   <input type="submit"/>
</form>-->
<script type="application/javascript">



   if(document.getElementById("user").value!=="1"){
      alert("You are not the administrator");
      window.location.href="../User/login"

   }
</script>
</body>
</html>