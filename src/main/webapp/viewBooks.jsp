<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  <html>
  <head>
  </head> 
  <body><h1>图书详细信息</h1>
  <table>
  <s:form id="id" action="addCart"><s:hidden name="books.bid"/>
书名: <s:property value="books.title"/><s:hidden name="books.title"/><br>
ISBN:<s:property value="books.isbn"/><s:hidden name="books.isbn"/><br>
价格:<s:property value="books.price"/><s:hidden name="books.author"/><br>
作者:<s:property value="books.author"/><s:hidden name="books.price"/><br>
 <s:submit key="add"/>
 </s:form>
 </table>
 </body>
</html>-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
            	<li><a href="#">继续购物</a>|</li>
                <li><a href="#">我的订单<span class="jt_down"></span></a>|</li>
              
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	
    	
        <div class="h3_right">
        	<div class="myyy">
            	个人信息
                <span class="sj_down"></span>
             </div>
            <div class="tsc">
            <a href="addCart.action">
            	去购物车结算
                </a>
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
                    	<img src="../images/viewBook.png"/>
                        
                    </div>
                </div>
                <div class="cn5topy_2">
                	<h1 class="pro_title font15">${books.getTitle()}</h1>
                    <div class="pro_price">
                            <div class="pro_price_x">
                                <p> 价格<b>￥${books.getPrice()}</b> </p>
                            </div>
                            
                    </div>
                    <div class="pro_ship">
                       
                    </div>
                    
                    
                    <div class="pro_buy">
                    	
                      <ul class="c4b5_option">
								<li class="shopcar_box"><span class="shopcar01"></span><a
									href="addCart.action?bid=${books.getBid()}/>">加入购物车</a></li>
							</ul>
                       		
                    </div>
                    
                </div>
            </div>
        </div>
    	<div class="c5_b2">
        	
            <div class="c5_b2_right">
            	<!--选项卡-->
            	<ul class="c5_b2_tabs">
                    <li class="current">规格参数</li>                 
                </ul>
                <!--内容-->
              	<div class="c5_b2_right_1 box">
                        <div class="introduce_item">
                          <ul>
                        	<li>商品名称：${books.getTitle()}</li>
                            <li>isbn编号：${books.getIsbn()}</li>
                            <li>作者：${books.getAuthor()}</li>
                            <li>商品类别：<a href="viewCategorys.action?cid=${books.getCategoryByCid().getCid()}">${books.getCategoryByCid().getCid()}</a></li>
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
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
        
    </div>

</body>
</html>