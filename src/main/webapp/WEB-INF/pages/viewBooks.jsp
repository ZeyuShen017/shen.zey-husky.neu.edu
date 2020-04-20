<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Final Project</title>
<link rel="stylesheet" href="../css/common.css"/>
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/icons.css" />
<link rel="stylesheet" href="../css/table.css" />
</head>
<body>
	<!--顶部-->
	<div class="top">
	<c:set value="${books}" var="books"></c:set>
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="/all.action">Continue Shopping</a>|</li>
                <li><a href="../Order/listOrder.action">My Order<span class="jt_down"></span></a>|</li>
              
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	
    	
        <div class="h3_right">
        	<div class="myyy">
                <span class="sj_down"></span>
             </div>
            <div class="tsc"><a href="http://localhost:8080/viewCarts.jsp">
                View Carts <span class="sj_right"></span></a>
            </div>
        </div>
    </div>
    <!--头部导航-->
    <div class="nav_top">
    	<div class="nav_top_center">
           
        </div>
    </div>
    
	<div class="container5">
    	<div class="cn5_top">
         
            <div class="cn5_top_y center01">
            	<div class="cn5topy_1">
                	<div class="cn5topy_imgview">
                    	<img src="../images/Books/${books.isbn}.jpg" style="height:320px"/>
                        
                    </div>
                </div>
                <div class="cn5topy_2">
                	<h1 class="pro_title font15">${books.getTitle()}</h1>
                    <div class="pro_price">
                            <div class="pro_price_x">
                                <p>Price<b>￥${books.getPrice()}</b> </p>
                            </div>
                            
                    </div>
                    <div class="pro_ship">
                       
                    </div>
                    
                    
                    <div class="pro_buy">
                    	
                      <ul class="c4b5_option">
								<li class="shopcar_box"><span class="shopcar01"></span><a
									href="addCart.action?bid=${books.getBid()}">Add to Cart</a></li>
							</ul>
                       		
                    </div>
                    
                </div>
            </div>
        </div>
    	<div class="c5_b2">
        	
            <div class="c5_b2_right">
            	<!--选项卡-->
            	<ul class="c5_b2_tabs">
                    <li class="current">Info</li>
                </ul>
                <!--内容-->
              	<div class="c5_b2_right_1 box">
                        <div class="introduce_item">
                          <ul>
                        	<li>Title：${books.getTitle()}</li>
                            <li>iIsbn：${books.getIsbn()}</li>
                            <li>Author：${books.getAuthor()}</li>
                            <li>Category：<a href="viewCategorys.action?cid=${books.getCategoryByCid().getCid()}">${books.getCategoryByCid().getName()}</a></li>
                          </ul>
                        </div>
               		</div>
                   
                </div>
                
                
           
            
           
        </div>
    </div>
    	
    <div class="c20"></div>
    <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">Created by Zeyu Shen</div>

            </div>
        </div>
        
    </div>

</body>
</html>