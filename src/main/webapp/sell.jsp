<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:text name="sale"/></title>

</head>

<body>
	
	<s:form id="id" action="getSellNum" validate="true">
		<s:textfield name="sellnum" key="sellnum" readonly="true" />
		<s:submit key="getSellNum" align="left"/>
	</s:form>
</body>
</html>
