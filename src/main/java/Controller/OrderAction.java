package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import Dao.OrderDao;
import Dao.OrderItemDao;
import Dao.UserinfoDao;
import Pojo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes(value = {"cart","user"})
@RequestMapping("Order")
public class OrderAction extends BaseAction {

	//ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	private Orders order=new Orders();
	private double total;
	private Map<String,CartItem> cart;
	private Userinfo user;
	private Collection list;
	private int oid;
	
	private String sellnum;
	private OrderDao orderDao=new OrderDao();


	public String getSellnum() {
		return sellnum;
	}
	public void setSellnum(String sellnum) {
		this.sellnum = sellnum;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Map getCart() {
		return cart;
	}
	public void setCart(Map cart) {
		this.cart = cart;
	}
	public Userinfo getUser() {
		return user;
	}
	public void setUser(Userinfo user) {
		this.user = user;
	}
	public Collection getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	@RequestMapping("balance.action")
	public String addOrder(String address, String zipcode, Model model, HttpSession session, HttpServletRequest request) throws IOException, Exception{//添加订单

		
		//Map<String,Object>session=ActionContext.getContext().getSession();
		cart=(Map)session.getAttribute("cart");
		Collection<OrderItem> list = new HashSet<OrderItem>();

		//UserInfoDao dao=(UserInfoDao)context.getBean("userinfoDAO");
		
		user=new UserinfoDao().searchUser((Userinfo)session.getAttribute("user"));




		//OrdersDao orderDao=(OrdersDao)context.getBean("OrderDAO");
		//OrderitemDao itemDao=(OrderitemDao)context.getBean("OrderitemDao");
		//Orderitem orderitem=(Orderitem)context.getBean("orderitem");
		try{
		order.setUserinfoByUserId(user);
		order.setTotal((double)session.getAttribute("total"));
		order.setAddress(address);
		order.setZipcode(zipcode);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		order.setoId(Long.parseLong(sdf.format(System.currentTimeMillis()).toString()));

			//this.getOrderService().add(order);
			CartItem item;
			Set keys=cart.keySet();
			int counter=0;
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					Object key=iterator.next();
					item=(CartItem)cart.get(key);
					OrderItem orderitem=new OrderItem();
					orderitem.setItemId((int) new Date().getTime()+counter);
					orderitem.setBooksByBid(item.getBooks());
					orderitem.setOrdersByOid(order);
					orderitem.setQuantity(item.getQuantity());
					list.add(orderitem);
					counter++;

					//System.out.println(new Date().getTime());


					
					//this.getOrderitemService().add(orderitem);//����嵥��orderitem����
				}
			}
			order.setOrderItemsByOId(list);
			new OrderDao().AddOrder(order);
			cart.clear();
			session.setAttribute("cart", cart);
			session.setAttribute("total",0);
			System.out.println(session.getAttribute("cart"));
			return "/viewCarts";
		}catch(Exception e){
			e.printStackTrace();
			return "ERROR";
		}
		
	}
	@RequestMapping("listOrder.action")
	public String listOrders(Model model, HttpSession session, String username) throws IOException, Exception{//查询当前用户所有订单
		//Map<String,Object>session=ActionContext.getContext().getSession();

		List<Orders> Orderlist;
		try{
			//OrderDao dao=new OrderDao();
			if(username!=null){
				user=new UserinfoDao().searchUserByName(username);
				Orderlist = (List<Orders>) new OrderDao().getUserOrders(user.getUserId());
				model.addAttribute("orders", Orderlist);
			}else {
				user = (Userinfo) session.getAttribute("user");
				Orderlist = (List<Orders>) new OrderDao().getUserOrders(user.getUserId());
				model.addAttribute("orders", Orderlist);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "listOrders";
	}
	
	
	@RequestMapping("listOrderItem.action")
	public String listOrderitem(Model model, HttpSession session, String oid) throws IOException, Exception{//查看此订单详细信息
		
		//Map<String,Object>session=ActionContext.getContext().getSession();
		//user=(Userinfo)session.get("user");
		List<OrderItem> itemList;
		try{
			OrderItemDao dao=new OrderItemDao();
			itemList=dao.searchOrderById(Long.parseLong(oid));

			model.addAttribute("list",itemList);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "listOrderItem";
	}
	
	
	public String balance(Model model, HttpSession session) throws IOException, Exception{
		//Map<String,Object>session=ActionContext.getContext().getSession();
		cart=(Map)session.getAttribute("cart");
		user=(Userinfo)session.getAttribute("user");
		
		//System.out.println("用户："+user.getUsername());
		
		CartItem item;
		Set keys=cart.keySet();
		if(keys!=null){
			Iterator iterator=keys.iterator();
			while(iterator.hasNext()){
				Object key=iterator.next();
				item=(CartItem)cart.get(key);
				total+=item.getQuantity()*item.getBooks().getPrice();
			}
		}
		return "SUCCESS";
	}
	@RequestMapping("sell.action")
	public String getSellNum(Model model){//销售额
		//OrdersDao orderDao=(OrdersDao)context.getBean("OrderDAO");
		double total=0;
		total=orderDao.sellNum();
		List<Orders> list=orderDao.getOrderList();
		model.addAttribute("AllOrder",list);
		model.addAttribute("SellTotal", total);
		//System.out.println("nuciwe"+list.get(0).toString());
		sellnum=list.toString();
		System.out.println(sellnum);
		return "sell";
	}
}
