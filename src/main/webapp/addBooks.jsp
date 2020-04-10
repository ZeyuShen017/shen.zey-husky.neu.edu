<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
<title></title>

<s:head/> 
</head>
<body><h1>Add Books</h1>
<input id="user" type="hidden" value="${sessionScope.user.rright}"/>
<form  action="../Book/addBook.action">
   <label>Title&nbsp&nbsp&nbsp&nbsp</label><input type="text" name="title"/><br>
   <label>ISBN&nbsp&nbsp&nbsp&nbsp</label><input type="text"name="isbn" /><br>
   <label>Author&nbsp&nbsp</label><input type="text"name="author"/><br>
   <label>Price&nbsp&nbsp&nbsp&nbsp</label><input type="text"name="price"/><br>
   <label>Category</label>
   <select>
      <option name="category" value="Computer">Computer&nbsp;&nbsp;
      </option>
      <option name="category" value="Art">Art&nbsp;&nbsp;
      </option>
   </select><br>
   <input type="submit"/>
</form>
<script type="application/javascript">
   if(document.getElementById("user").value!=="1"){
      alert("You are not the adminstrator");
      window.location.href="login.jsp"

   }
</script>
</body>
</html>