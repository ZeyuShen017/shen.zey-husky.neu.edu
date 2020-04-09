<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<html>
<head>
<title></title>

<s:head/> 
</head>
<body><h1>添加图书</h1>

<font><s:text name="addBooks"/></font>
<table>
<s:form id="id" action="addBooks">
   <s:textfield name="books.title" key="books.title"/>
   <s:textfield name="books.isbn" key="books.isbn"/>
   <s:textfield name="books.author" key="books.author"/>
   <s:textfield name="books.price" key="books.price"/>
   <s:textfield name="books.categorys.cid" key="books.categorys.cid"/>
   <s:submit key="add" align="center"/>
</s:form>
</table>
</body>
</html>