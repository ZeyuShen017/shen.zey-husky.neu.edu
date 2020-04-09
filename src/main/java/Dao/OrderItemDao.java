package Dao;

import Pojo.OrderItem;
import Pojo.Orders;
import Pojo.Userinfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 */
public class OrderItemDao {
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();

    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public Collection<OrderItem> getOrderItem(int id){
        Query q;
        Collection<OrderItem> OI=null;
        try {
            beginTransaction();
            String hql="FROM OrderItem oi where oi.orderByOid= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",id);
            OI = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return OI;
    }

    public List<OrderItem> getOrderList() {

        List<OrderItem> OrderList = new ArrayList<OrderItem>();
        Query q = null;


        try {
            beginTransaction();

            q = getSession().createQuery("FROM OrderItem ");//query = "SELECT * FROM Order ";

            OrderList = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return OrderList;
    }
    public List<OrderItem> searchOrderById(int oid) {

        Query q = null;
        List<OrderItem> oi=null;

        try {
            beginTransaction();
            String hql="FROM OrderItem oi where oi.itemId= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",oid);

            oi = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return oi;
    }


    public int AddOrderItem(OrderItem od) {
        int rs = 0;
        try {
            beginTransaction();
            getSession().save(od);
            commit();
            rs = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return rs;
    }

    public int DeleteOrderItem(int id) {
        int rs = 0;
        try {
            beginTransaction();
            OrderItem usr = (OrderItem) getSession().get(OrderItem.class, id);
            getSession().delete(usr);
            commit();
            rs=1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return rs;
    }

    public int UpdateOrderItem(OrderItem oi) {
        int rs = 0;
        try {
            beginTransaction();


            getSession().update(oi);
            commit();
            rs = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return rs;
    }
}




