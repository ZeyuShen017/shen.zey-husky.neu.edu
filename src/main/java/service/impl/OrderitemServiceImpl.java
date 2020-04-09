package service.impl;

import java.util.List;

import Dao.OrderItemDao;
import Pojo.OrderItem;
import service.OrderitemService;

public class OrderitemServiceImpl implements OrderitemService{

	private OrderItemDao orderitemDao;
	
	public OrderItemDao getOrderitemDao() {
		return orderitemDao;
	}

	public void setOrderitemDao(OrderItemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}
	
	@Override
	public int add(OrderItem orderitem) {

		return orderitemDao.AddOrderItem(orderitem);
	}

	@Override
	public List listOrders(Integer oid) {

		return orderitemDao.searchOrderById(oid);
	}

	

}
