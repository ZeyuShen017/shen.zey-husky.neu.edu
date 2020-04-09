import Dao.UserinfoDao;
import Pojo.*;
import Dao.BooksDao;
import Dao.CategoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.Book;
import java.util.Collection;

public class UserTest {
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
    public void test() {
        Userinfo usr=new Userinfo();
       // usr.setUserId(2);
        usr.setUsername("1222");
        usr.setPassword("cbaaaa");
        usr.setRright("1");
        //new UserinfoDao().AddUser(usr);
        //System.out.println(new UserinfoDao().searchUserById(1).getUserId());
        new UserinfoDao().AddUser(usr);
        tx.commit();
    }
}