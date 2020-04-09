package Controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BooksService;
import service.OrderService;
import service.OrderitemService;
import service.UserInfoService;



public class BaseAction {

	private OrderService orderService;
	private UserInfoService userService;
	private BooksService booksService;
	private OrderitemService orderitemService;


	public OrderitemService getOrderitemService() {
		return orderitemService;
	}

	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}

	public BooksService getBooksService() {
		return booksService;
	}

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public UserInfoService getUserService() {
		return userService;
	}

	public void setUserService(UserInfoService userService) {
		this.userService = userService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
