package Dao;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Pojo.Books;
import Pojo.Category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 */
public class CategoryDao {
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

    public Collection<Books> getBooks(int cid){
        Query q;
        Collection<Books> bk=null;
        try {
            beginTransaction();
            String hql="FROM Books b where b.categoryByCid= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",cid);
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

    public List<Category> getCategoryList() {

        List<Category> CategoryList = new ArrayList<Category>();
        Query q = null;


        try {
            beginTransaction();

            q = getSession().createQuery("FROM Category");//query = "SELECT * FROM Category ";

            CategoryList = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return CategoryList;
    }
    public Category searchCategoryById(int id) {

        Query q = null;
        Category ct=null;

        try {
            beginTransaction();
            String hql="FROM Category c where c.cid= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Category ";
            q.setInteger("ID",id);

            ct = (Category) q.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return ct;
    }


    public int AddCategory(Category ct) {
        int rs = 0;
        try {
            beginTransaction();
            getSession().save(ct);
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

    public int DeleteCategory(int cid) {
        int rs = 0;
        try {
            beginTransaction();
            Category ct = (Category) getSession().get(Category.class, cid);
            getSession().delete(ct);
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

    public int UpdateCategory(Category ct) {
        int rs = 0;
        try {
            beginTransaction();


            getSession().update(ct);
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




