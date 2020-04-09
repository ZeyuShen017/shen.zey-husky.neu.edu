package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import Dao.OrderDao;
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
					orderitem.setOrderByOid(order);
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
	
	public String listOrders(Model model, HttpSession session) throws IOException, Exception{//查询当前用户所有订单
		//Map<String,Object>session=ActionContext.getContext().getSession();
		user=(Userinfo)session.getAttribute("user");
		
		try{
			//OrderDao dao=new OrderDao();
			list=this.getOrderService().listOrders(user);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "SUCCESS";
	}
	
	
	
	public String listAllOrder(Model model, HttpSession session) throws IOException, Exception{//查看此订单详细信息
		
		//Map<String,Object>session=ActionContext.getContext().getSession();
		//user=(Userinfo)session.get("user");
		
		try{
			/*OrderitemDao dao=new OrderitemDao();
			
			Order or=new Order();
			
			or.setOid(getOid());
			
			System.out.println("nui"+getOid());*/
			
			
			
			list=this.getOrderitemService().listOrders(getOid());
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "SUCCESS";
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

	public String getSellNum(){//销售额
		
		//OrdersDao orderDao=(OrdersDao)context.getBean("OrderDAO");
		list=orderDao.sellNum();
		//System.out.println("nuciwe"+list.get(0).toString());
		sellnum=list.toString();
		return "SUCCESS";
	}
}
