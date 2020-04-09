<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  </head> 
  <body><h1><s:text name="booksCategoryInfo"/></h1>
  <table>
<s:text name="categorys.cid"/>:<s:property value="categorys.cid"/><br>
<s:text name="categorys.name"/>:<s:property value="categorys.name"/><br>
<s:text name="categorys.description"/>:<s:property value="categorys.description"/><br>
 </table>
 </body>
</html>
