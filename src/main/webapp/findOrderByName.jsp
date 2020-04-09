<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<tltle></tltle>
<s:head/>
</head>
<body><h1><s:text name="searchOrderInfo"/></h1>
<table width="40%" height="76" border="0">
  <s:form id="id" action="findOrderByUser">
    <s:textfield name="username" key="username"/>
    <s:submit key="findOrderByUser" align="center"/>
  </s:form>
</table>
  </body>
</html>