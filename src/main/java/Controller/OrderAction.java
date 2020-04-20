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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@SessionAttributes(value = {"cart", "user"})
@RequestMapping("Order")
public class OrderAction {

    //ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
    private double total;
    private Map<String, CartItem> cart;
    private Userinfo user;
    private Collection list;
    private int oid;

    private String sellnum;
    private OrderDao orderDao = new OrderDao();
    private UserinfoDao userinfoDao = new UserinfoDao();

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public String BalancePage(Model model){
        model.addAttribute("balance", new Orders());
        return "addOrder";
    }

    @RequestMapping("/findOrderByName")
    public String findOrderByName(){
        return "findOrderByName";
    }
    /**********  Place Order *************/
    @RequestMapping(value = "balance.action", method = RequestMethod.POST)
    public String addOrder(@Valid @ModelAttribute("balance") Orders order, BindingResult bindingResult, HttpSession session, HttpServletRequest request) throws IOException, Exception {//添加订单

        if(bindingResult.hasErrors()){
            return "addOrder";
        }
        cart = (Map) session.getAttribute("cart");
        Collection<OrderItem> list = new HashSet<OrderItem>();

        user = userinfoDao.searchUser((Userinfo) session.getAttribute("user"));

        try {
            order.setUserinfoByUserId(user);
            order.setTotal((double) session.getAttribute("total"));
           // order.setAddress(address);
            //order.setZipcode(zipcode);
            //set orderID by time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
            order.setoId(Long.parseLong(sdf.format(System.currentTimeMillis()).toString()));
            CartItem item;
            Set keys = cart.keySet();
            int counter = 0;
            //add order by cascade
            if (keys != null) {
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    item = (CartItem) cart.get(key);
                    OrderItem orderitem = new OrderItem();
                    orderitem.setItemId((int) (new Date().getTime()/1000 +counter));
                    orderitem.setBooksByBid(item.getBooks());
                    orderitem.setOrdersByOid(order);
                    orderitem.setQuantity(item.getQuantity());
                    list.add(orderitem);
                    counter++;

                }
            }
            order.setOrderItemsByOId(list);
            orderDao.AddOrder(order);
            cart.clear();
            session.setAttribute("cart", cart);
            session.setAttribute("total", 0);
            System.out.println(session.getAttribute("cart"));
            return "redirect:../Order/listOrder.action";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorInfo", e.toString());
            return "error";
        }

    }
/************* delete order ***********/
    @RequestMapping("deleteOrder.action")
    public String deleteOrder(String oid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (orderDao.DeleteOrder(Long.parseLong(oid)) == 1) {
            System.out.println("123");

            return "redirect:../Order/listOrder.action";
        } else {
            //RequestDispatcher rd =request.getRequestDispatcher("../error.jsp");
            request.setAttribute("errorInfo", "Delete error");
            //rd.forward(request,response);
            return "error";
        }

    }
/*********** list order by username **********/
    @RequestMapping("listOrder.action")
    public String listOrders(Model model, HttpSession session, String username) throws IOException, Exception {//查询当前用户所有订单
        //Map<String,Object>session=ActionContext.getContext().getSession();

        List<Orders> Orderlist;
        try {
            //OrderDao dao=new OrderDao();
            if (username != null) {
                user = userinfoDao.searchUserByName(username);
                Orderlist = (List<Orders>) orderDao.getUserOrders(user.getUserId());
                model.addAttribute("orders", Orderlist);
            } else {
                user = (Userinfo) session.getAttribute("user");
                Orderlist = (List<Orders>) orderDao.getUserOrders(user.getUserId());
                model.addAttribute("orders", Orderlist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listOrders";
    }

/********* list detail of order ***********/
    @RequestMapping("listOrderItem.action")
    public String listOrderitem(Model model, HttpSession session, String oid) throws IOException, Exception {//查看此订单详细信息

        List<OrderItem> itemList;
        try {
            OrderItemDao dao = new OrderItemDao();
            itemList = dao.searchOrderById(Long.parseLong(oid));

            model.addAttribute("list", itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listOrderItem";
    }


    @RequestMapping("sell.action")
    public String getSellNum(Model model) {//销售额
        //OrdersDao orderDao=(OrdersDao)context.getBean("OrderDAO");
        double total = 0;
        total = orderDao.sellNum();
        List<Orders> list = orderDao.getOrderList();
        model.addAttribute("AllOrder", list);
        model.addAttribute("SellTotal", total);
        //System.out.println("nuciwe"+list.get(0).toString());
        sellnum = list.toString();
        System.out.println(sellnum);
        return "sell";
    }
}
