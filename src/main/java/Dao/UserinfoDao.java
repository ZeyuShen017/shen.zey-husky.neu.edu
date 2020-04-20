package Dao;

import Pojo.Orders;
import Pojo.Userinfo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
*/
public class UserinfoDao {
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

    /*public Collection<Orders> getOrders(int id){
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
    }*/

    public List<Userinfo> getUserinfoList() {

        List<Userinfo> UserinfoList = new ArrayList<Userinfo>();
        Query q = null;


        try {
            beginTransaction();
            Criteria cr=session.createCriteria(Userinfo.class);



            //q = getSession().createQuery("FROM Userinfo ");//query = "SELECT * FROM Userinfo ";

            UserinfoList = cr.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return UserinfoList;
    }
    public Userinfo searchUserById(int id) {

        Query q = null;
        Userinfo usr=null;

        try {
            beginTransaction();
            Criteria cr=session.createCriteria(Userinfo.class);
            Criterion criterion = Restrictions.eq("userId", id);
            cr.add(criterion);
            //String hql="FROM Userinfo u where u.userId= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setInteger("ID",id);

            usr = (Userinfo) cr.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return usr;
    }

    public Userinfo searchUserByName(String name) {

        Query q = null;
        Userinfo usr=null;

        try {
            beginTransaction();
            Criteria cr=session.createCriteria(Userinfo.class);
            Criterion criterion = Restrictions.eq("username", name);
            cr.add(criterion);

            if(cr.list().size()>0) {
                usr = (Userinfo) cr.list().get(0);
            }
           // String hql="FROM Userinfo u where u.username= :Name";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
           // q.setString("Name",name);



            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return usr;
    }


    public int AddUser(Userinfo usr) {
        int rs = 0;
        try {
            beginTransaction();
            getSession().save(usr);
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

    public int DeleteUser(int id) {
        int rs = 0;
        try {
            beginTransaction();
            Userinfo usr = (Userinfo) getSession().get(Userinfo.class, id);
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

    public int UpdateUserinfo(Userinfo usr) {
        int rs = 0;
        try {
            beginTransaction();


            getSession().merge(usr);
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

    public Userinfo searchUser(Userinfo userinfo) {
        Query q = null;
        Userinfo usr=null;

        try {
            beginTransaction();
           // String hql="FROM Userinfo u where u.username= :Name and u.password= :Password";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            //q.setString("Name",userinfo.getUsername());
           // q.setString("Password", userinfo.getPassword());
            Criteria cr=session.createCriteria(Userinfo.class);
            Criterion criterion1 = Restrictions.eq("username", userinfo.getUsername());
            Criterion criterion2 = Restrictions.eq("password", userinfo.getPassword());
            LogicalExpression and = Restrictions.and(criterion1,criterion2);

            cr.add(and);

            //usr = (Userinfo) cr.list().get(0);
            if(cr.list().size()>0) {

                usr = (Userinfo) cr.list().get(0);
            }else

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return usr;
    }
}




