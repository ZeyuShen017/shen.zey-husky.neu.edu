<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title></title>
    
	

  </head>
  <s:head/>
  
  <body>
     <h1><s:text name="userOrderInfo"/></h1>
     <table border="1">
    	<tr><th><s:text name="order.oid"/></th><th><s:text name="books.title"/></th>
    	<th><s:text name="books.author"/></th><th><s:text name="quantity"/></th></tr>
    	<s:iterator value="list">
    		
    		<td><s:property value="order.oid"/></td>
    		<td><s:property value="books.title"/></td>
    		<td><s:property value="books.author"/></td>
    		<td><s:property value="quantity"/></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>
