<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>管理员页面</title>
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
		font-size: large;
		background-color: #f4f4f4;
		width: 80%;
		margin: 60px auto;
	}
	
	.orderlist_header div{
		font-weight: bold;
		text-align: center;
		float: left;
		margin-left: 170px;
	}
	
</style>

</head>
<body>
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="#">退出</a>|</li>
            </ul>
        </div>
    </div>
    
<!--中间部分div-->
<div class="empty">	
	<div class="orderlist_title">管理员操作</div>
	<div class="orderlist_container">
		<div class="orderlist_header">
            <div><button onClick="window.open('addBooks.jsp')">添加图书</button></div>
            <div><button onClick="window.open('changeRight.jsp')">修改权限</button></div>
            <div><button onClick="window.open('addUsers.jsp')">添加用户</button></div>
  	        <div><button onClick="window.open('findOrderByName.jsp')">查询订单</button></div>
  	        <div><button onClick="window.open('sell.jsp')">销售金额</button></div>            
		</div>		
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
