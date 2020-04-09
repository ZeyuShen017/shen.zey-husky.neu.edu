<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <s:head/>
  </head>
  
  <body>
    <h1>用户订单</h1>
     <table border="1">
    	<tr><th>订单号</th><th>用户姓名</th><th>地址</th><th>邮编</th><th>总金额</th></tr>
    	<s:iterator value="list">
    		<tr><td><a href="listAllOrder.action?oid=<s:property value="oid"/>">
    		<s:property value="oid"/></a>
    		</td>
    		<td><s:property value="userinfo.username"/></td>
    		<td><s:property value="address"/></td>
    		<td><s:property value="zipcode"/></td>
    		<td><s:property value="total"/></td>
    		</tr>
    	</s:iterator>
    </table>
  </body>
</html>-->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>购物车页面</title>
<link rel="stylesheet" href="css/fullCar.css" />
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />

<style>
	.orderlist_title{
		text-align: center;
		font-size: large;
		font-weight:bolder;
		background-color: #f4f4f4;
		width: 80%;
		margin: 0 auto;
	}
	
	.orderlist_container{
		text-align: center;
		background-color: #f4f4f4;
		width: 80%;
		margin: 10px auto;
	}
	
	.orderlist_header div{
		font-weight: bold;
		text-align: center;
		float: left;
		margin-left: 170px;
	}
	
	.orderlist_content{
		width: 100%;
		padding: 0px;
		margin: 0px;
	}
	
	.orderlist_content div{
		font-weight: bold;
		text-align: center;
		float: left;
		margin-left: 175px;
	}
	
</style>

</head>
<body>
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	
                <li><a href="listBooks.action">继续购物<span class="jt_down"></span></a>|</li>
            
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="#"></a>
        <div class="h3_right">
        	<img src="images/play_03.png" alt="">
        </div>
       
    </div>
    
<!--中间部分div-->
<div class="empty">
	
	<div class="orderlist_title">订单列表</div>
	<div style="margin:auto;">
	<table border="2">
    	<tr><th>订单号</th><th>用户姓名</th><th>地址</th><th>邮编</th><th>总金额</th></tr>
    	<s:iterator value="list">
    		<tr><td><a href="listAllOrder.action?oid=<s:property value="oid"/>">
    		<s:property value="oid"/></a>
    		</td>
    		<td><s:property value="userinfo.username"/></td>
    		<td><s:property value="address"/></td>
    		<td><s:property value="zipcode"/></td>
    		<td><s:property value="total"/></td>
    		</tr>
    	</s:iterator>
    </table>
    </div>
	
</div>
     <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
        
</body>
</html>
