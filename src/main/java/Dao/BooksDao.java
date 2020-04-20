package Dao;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Pojo.Books;

import java.util.ArrayList;
import java.util.List;

import Pojo.Userinfo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


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
    public int searchBookByIsbn(String isbn) {

        Query q = null;
        int bk=0;

        try {
            beginTransaction();
           // String hql="FROM Books b where b.bid= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Books ";
            //q.setInteger("ID",id);
            Criteria cr=session.createCriteria(Books.class);
            Criterion criterion = Restrictions.eq("isbn", isbn);
            cr.add(criterion);
            bk =  cr.list().size();
            System.out.println("1234" + bk);
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return bk;
    }

    public Books searchBookById(int bid) {

        Query q = null;
        Books bk=null;

        try {
            beginTransaction();
            // String hql="FROM Books b where b.bid= :ID";
            //q = getSession().createQuery(hql);//query = "SELECT * FROM Books ";
            //q.setInteger("ID",id);
            Criteria cr=session.createCriteria(Books.class);
            Criterion criterion = Restrictions.eq("bid", bid);
            cr.add(criterion);
            bk = (Books) cr.list().get(0);

            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return bk;
    }

    public List<Books> searchBooksByCategory(String category) {

        Query q = null;
        List<Books> bk=null;

        try {
            beginTransaction();
            Criteria cr1= session.createCriteria(Books.class);
            Criteria cr2=cr1.createCriteria("categoryByCid");
            cr2.add(Restrictions.eq("name",category));
            //String hql="FROM Books b where b.categoryByCid.name= :name";
           // q = getSession().createQuery(hql);//query = "SELECT * FROM Books ";
           //q.setString("name",category);

            bk = cr1.list();

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



