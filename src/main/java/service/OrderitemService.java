package service;


import java.util.List;

import Pojo.OrderItem;

public interface OrderitemService {

	public int add(OrderItem orderitem);
	public List listOrders(Integer oid);
}
