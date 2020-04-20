package Dao;

import Pojo.Orders;
import Pojo.Userinfo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 */
public class OrderDao {
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

    public Collection<Orders> getUserOrders(int userid){
        Query q;
        Collection<Orders> od=null;
        try {
            beginTransaction();
            //String hql="FROM Orders o where o.userinfoByUserId= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setInteger("ID",id);s
            Criteria cr1=session.createCriteria(Orders.class);
            Criteria cr2=cr1.createCriteria("userinfoByUserId");
            //Criterion criterion = Restrictions.eq("userinfoByUserId.userid", userid);
            cr2.add(Restrictions.eq("userId", userid));
            od = cr1.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return od;
    }

    public List<Orders> getOrderList() {

        List<Orders> OrderList = new ArrayList<Orders>();
       // Query q = null;


        try {
            beginTransaction();
            Criteria cr=session.createCriteria(Orders.class);
            OrderList = cr.list();
           // q = getSession().createQuery("FROM Orders ");//query = "SELECT * FROM Order ";

           // OrderList = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return OrderList;
    }
    public Orders searchOrderById(int oid) {

        Query q = null;
        Orders od=null;

        try {
            beginTransaction();
            //String hql="FROM Orders o where o.oId= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setInteger("ID",id);
            Criteria cr=session.createCriteria(Orders.class);
            Criterion criterion = Restrictions.eq("oId", oid);
            cr.add(criterion);
            od = (Orders) cr.list().get(0);
            //od = (Orders) q.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return od;
    }
    public double sellNum(){//计算销售额
        Query q = null;
        double od=0;
        try {
            beginTransaction();
           // String hql="select sum(total) from Orders";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setInteger("ID",id);
            Criteria cr=session.createCriteria(Orders.class);
            //Criterion criterion = Restrictions.eq("userId", userid);
            cr.setProjection(Projections.sum("total"));
            if(cr.list().get(0)!=null) {
                od = (double) cr.list().get(0);
            }
            //od = (double) q.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return od;
    }

    public int AddOrder(Orders od) {
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

    public int DeleteOrder(long id) {
        int rs = 0;
        try {
            beginTransaction();
            Orders usr = (Orders) getSession().get(Orders.class, id);
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


    public int UpdateOrder(Orders usr) {
        int rs = 0;
        try {
            beginTransaction();


            getSession().update(usr);
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




