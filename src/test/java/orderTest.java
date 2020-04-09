import Dao.*;
import Pojo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import javax.swing.text.InternationalFormatter;
import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.*;

public class orderTest {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;

    @Before
    public void init() {
        config = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    //增加
    @Test
    public void test1234() {
        Orders od=new Orders();
        od.setAddress("as");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        System.out.println(Long.parseLong(sdf.format(System.currentTimeMillis()).toString()+1));
        od.setoId(Long.parseLong(sdf.format(System.currentTimeMillis()).toString()));
        System.out.println("abcd");
        od.setUserinfoByUserId(new UserinfoDao().searchUserById(1));
        od.setZipcode("21448");
        od.setTotal(122);
        //session.save(od);
        Collection<OrderItem> list = new HashSet<OrderItem>();
        OrderItem oi1 = new OrderItem();
        oi1.setItemId((int) new Date().getTime());
        oi1.setBooksByBid(new BooksDao().searchBookById(1));
        oi1.setQuantity(1);
        oi1.setOrderByOid(od);

        OrderItem oi2 = new OrderItem();
        oi2.setItemId((int) new Date().getTime());
        oi2.setBooksByBid(new BooksDao().searchBookById(2));
        oi2.setQuantity(1);
        oi2.setOrderByOid(od);
        //new OrderItemDao().AddOrderItem(oi1);
        list.add(oi1);
        list.add(oi2);
        //session.save(oi1);
        od.setOrderItemsByOId(list);
        new OrderDao().AddOrder(od);
        //session.beginTransaction().commit();
        //Session session = SessionFactoryUtils.getCurrentSession();

  /*
    6.开启事务
  */

        //oi1.setOrderByOid(new OrderDao().searchOrderById());

    }
}