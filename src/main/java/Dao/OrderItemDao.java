package Dao;

import Pojo.OrderItem;
import Pojo.Orders;
import Pojo.Userinfo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

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

    /*public Collection<OrderItem> getOrderItem(int id){
        Query q;
        Collection<OrderItem> OI=null;
        try {
            beginTransaction();
            //String hql="FROM OrderItem oi where oi.ordersByOid= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
           // q.setInteger("ID",id);
            Criteria cr=session.createCriteria(OrderItem.class);
            Criterion criterion = Restrictions.eq("oid", id);
            cr.add(criterion);
            OI = cr.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return OI;
    }*/

    public List<OrderItem> getOrderList() {

        List<OrderItem> OrderList = new ArrayList<OrderItem>();
        Query q = null;


        try {
            beginTransaction();

            //q = getSession().createQuery("FROM OrderItem ");//query = "SELECT * FROM Order ";
            Criteria cr=session.createCriteria(OrderItem.class);

            OrderList = cr.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return OrderList;
    }
    public List<OrderItem> searchOrderById(long oid) {

        Query q = null;
        List<OrderItem> oi=null;

        try {
            beginTransaction();
            //String hql="FROM OrderItem oi where oi.ordersByOid.oId= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setLong("ID",oid);
            Criteria cr1=session.createCriteria(OrderItem.class);
            Criteria cr2=cr1.createCriteria("ordersByOid");
            Criterion criterion = Restrictions.eq("oId", oid);
            cr2.add(criterion);

            oi = cr1.list();

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




