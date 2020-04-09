package service;

import java.util.Collection;
import java.util.List;

import Pojo.Orders;
import Pojo.Userinfo;

public interface OrderService {

	public int add(Orders order);//添加
	public Collection<Orders> listOrders(Userinfo user);
}
