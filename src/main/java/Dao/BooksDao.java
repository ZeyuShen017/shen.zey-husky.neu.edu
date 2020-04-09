package Dao;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Pojo.Books;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 */
public class BooksDao {
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

    public List<Books> getBookList() {

        List<Books> BooksList = new ArrayList<Books>();
        Query q = null;


        try {
            beginTransaction();

                q = getSession().createQuery("FROM Books");//query = "SELECT * FROM Books ";

            BooksList = q.list();

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return BooksList;
    }
    public Books searchBookById(int id) {

        Query q = null;
        Books bk=null;

        try {
            beginTransaction();
            String hql="FROM Books b where b.bid= :ID";
            q = getSession().createQuery(hql);//query = "SELECT * FROM Books ";
            q.setInteger("ID",id);

            bk = (Books) q.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return bk;
    }


    public int AddBook(Books bk) {
        int rs = 0;
        try {
            beginTransaction();
            getSession().save(bk);
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

    public int DeleteBook(int bid) {
        int rs = 0;
        try {
            beginTransaction();
            Books bk = (Books) getSession().get(Books.class, bid);
            getSession().delete(bk);
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

    public int UpdateBook(Books bk) {
        int rs = 0;
        try {
            beginTransaction();


            getSession().update(bk);
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



