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

    public Collection<Orders> getOrders(int id){
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

    public List<Userinfo> getUserinfoList() {

        List<Userinfo> UserinfoList = new ArrayList<Userinfo>();
        Query q = null;


        try {
            beginTransaction();

            q = getSession().createQuery("FROM Userinfo ");//query = "SELECT * FROM Userinfo ";

            UserinfoList = q.list();

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
            String hql="FROM Userinfo u where u.id= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",id);

            usr = (Userinfo) q.list().get(0);

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
            String hql="FROM Userinfo u where u.username= :Name";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setString("Name",name);

            usr = (Userinfo) q.list().get(0);

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
            String hql="FROM Userinfo u where u.username= :Name and u.password= :Password";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setString("Name",userinfo.getUsername());
            q.setString("Password", userinfo.getPassword());
            if(q.list().size()>0) {

                usr = (Userinfo) q.list().get(0);
            }

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




