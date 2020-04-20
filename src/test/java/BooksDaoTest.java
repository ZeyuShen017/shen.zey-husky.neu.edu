import Pojo.Books;
import Pojo.Category;
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
import java.util.List;

public class BooksDaoTest {
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
	public void test1234(){
		System.out.println(new CategoryDao().searchCategoryByName("Computer"));
	}
	@Test
	public void insert() {
		Category ct= new CategoryDao().searchCategoryById(1);
		// new category
		/*ct.setCid(1);
		ct.setName("A");
		ct.setDescription("ABC");
		session.save(ct);*/
		Books bk=new Books();
		bk.setAuthor("AAA");
		bk.setBid(3);
		bk.setIsbn("12333");
		bk.setPrice(12.99);
		bk.setTitle("Java");
		bk.setCategoryByCid(ct);
		bk.setPath("123456");
		new BooksDao().AddBook(bk);
		tx.commit();
	}
	//修改

	@Test
	public void testall(){
		System.out.println(new BooksDao().searchBookByIsbn("123456"));
		//System.out.println(new BooksDao().searchBooksByCategory("Computer"));
	}
	@Test
	public void testCId() {
		int cid=1;
		Collection<Books> bk=new CategoryDao().getBooks(cid);
		for(Books b:bk){
			System.out.println(b.getIsbn());
		}
	}
	@Test
	public void showAll(){
		List<Books> bk=new BooksDao().getBookList();
		for(Books b: bk)
			System.out.println(b.getTitle());
	}/*
	//查找
	@Test
	public void getById() {
		Movies user = (Movies) session.get(Movies.class, "1917");
		tx.commit();
		session.close();
		System.out.println("ID号：" + user.getActor() + "；用户名：" + user.getActress() +
				"；密码：" + user.getGenre() + "；邮件：" + user.getYear());
	}
	//删除
	@Test
	public void delete() {
		Movies user = (Movies) session.get(Movies.class, "CCC");
		session.delete(user);
		tx.commit();
		session.close();
	}*/
}
