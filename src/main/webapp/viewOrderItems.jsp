<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  </head> 
  <body><h1><s:text name="orderItemInfo"/></h1>
<table border="1">
    <tr><th><s:text name="itemId"/></th><th><s:text name="books.title"/></th><th><s:text name="quantity"/></th></tr>
    <s:iterator value="list">
    <tr>
    <td><s:property value="itemId"/></a></td>
    <td><s:property value="books.title"/></td>
    <td><s:property value="quantity"/></td>
    </tr>
    </s:iterator>
    </table>
 </body>
</html>