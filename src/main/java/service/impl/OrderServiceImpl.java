package service.impl;

import java.util.Collection;
import java.util.List;

import Dao.OrderDao;
import Pojo.Orders;
import Pojo.Userinfo;
import service.OrderService;



public class OrderServiceImpl implements OrderService {

	private OrderDao OrderDAO;
	public OrderDao getOrderDAO() {
		return OrderDAO;
	}

	public void setOrderDAO(OrderDao orderDAO) {
		OrderDAO = orderDAO;
	}

	@Override
	public int add(Orders order) {
		// TODO Auto-generated method stub
		if(OrderDAO.AddOrder(order)==1){
			return 1;
		}else{
			return 0;	
		}
	}

	@Override
	public Collection<Orders> listOrders(Userinfo user) {
		// TODO Auto-generated method stub
		return OrderDAO.getUserOrders(user.getUserId());
	}

}
