package Dao;

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

    public Collection<Orders> getUserOrders(int id){
        Query q;
        Collection<Orders> bk=null;
        try {
            beginTransaction();
            String hql="FROM Orders o where o.userinfoByUserId= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",id);
            bk = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return bk;
    }

    public List<Orders> getOrderList() {

        List<Orders> OrderList = new ArrayList<Orders>();
        Query q = null;


        try {
            beginTransaction();

            q = getSession().createQuery("FROM Orders ");//query = "SELECT * FROM Order ";

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
    public Orders searchOrderById(int id) {

        Query q = null;
        Orders od=null;

        try {
            beginTransaction();
            String hql="FROM Orders o where o.oId= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",id);

            od = (Orders) q.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return od;
    }
    public List sellNum(){//计算销售额
        Query q = null;
        List<Orders> od=null;

        //String hql="select sum(total) from Order";
        //List list=this.getHibernateTemplate().find(hql);
        //Iterator it=list.iterator();
       // System.out.println("nuciwe"+list.get(0).toString());
      //  return list;
        try {
            beginTransaction();
            String hql="select sum(total) from Orders";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setInteger("ID",id);

            od =  q.list();

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

    public int DeleteOrder(int id) {
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




