package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Dao.CategoryDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.BooksDao;
import Pojo.Books;
import Pojo.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = {"cart","total"})
@RequestMapping("/Book")
public class BooksAction extends BaseAction {


	private Books books;
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategorys(Category categorys) {
		this.category = category;
	}

	private List list;
	//private Integer bid;
	private Map<String,CartItem> cart;
	private Integer cid;
	private double total=0;
	private BooksDao dao = new BooksDao();
	private Category cat = new Category();
	private Books book = new Books();
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	/*public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
*/
	public Map getCart() {
		return cart;
	}

	public void setCart(Map cart) {
		this.cart = cart;
	}
@RequestMapping("addBook.action")
	public String addBooks(String title, String isbn, String author, String price, String category) {// 添加
		Books bk = new Books();
		bk.setCategoryByCid(new CategoryDao().searchCategoryByName(category));
		bk.setTitle(title);
		bk.setIsbn(isbn);
		bk.setPrice(Double.parseDouble(price));
		bk.setAuthor(author);
		if (dao.AddBook(bk) == 1) {
			return "success";
		} else {
			return "error";
		}
	}

@RequestMapping("/all.action")
	public String listBooks(Model model, String category) {// 列出全部书
		if(category==null) {
			list = dao.getBookList();
			model.addAttribute("booklist", list);
		}else{
			list=dao.searchBooksByCategory(category);
			model.addAttribute("booklist", list);
		}
		return "listBooks";
	}

	/*public String viewCategorys() {
		cat.setCid(getCid());
		category = dao.viewCategory(cat);
		return SUCCESS;
	}*/
@RequestMapping("viewDetail.action")
	public String viewDetails(int bid,Model model) {
		book.setBid(bid);
		books = dao.searchBookById(book.getBid());
	model.addAttribute("books",books);

	return "viewBooks";
	}
	@RequestMapping("addCart.action")
	public String addCart (int bid, Model model, HttpSession session) throws IOException, Exception{// 添加购物车
		//Map<String, Object> session = ActionContext.getContext().getSession();
		cart = (Map) session.getAttribute("cart");
		//total= (double)session.getAttribute("total");
		total=0;
		// cart.remove("cart");
		// session.remove("cart");
		if (cart == null) {
			cart = new HashMap();
			model.addAttribute("cart", cart);
		}
		//BooksDao dao = (BooksDao) context.getBean("booksDao");
		//Books book = (Books) context.getBean("book");
		// System.out.println(bid);
		book.setBid(bid);
		books = dao.searchBookById(book.getBid());
		CartItem cartItem = (CartItem) cart.get(books.getIsbn());

		if (cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			cartItem.setTotal(cartItem.getQuantity() * books.getPrice());
			Set keys=cart.keySet();
			CartItem item;
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					Object key=iterator.next();
					item=(CartItem)cart.get(key);
					total+=item.getQuantity() * item.getBooks().getPrice();
				}
			}
		} else {
			CartItem cartItem1 = new CartItem();
			cartItem1.setBooks(books);
			cartItem1.setQuantity(1);
			cartItem1.setTotal(cartItem1.getQuantity() * books.getPrice());
			cart.put(books.getIsbn(), cartItem1);
			session.setAttribute("cart", cart);
			Set keys=cart.keySet();
			CartItem item;
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					Object key=iterator.next();
					item=(CartItem)cart.get(key);
					total+=item.getQuantity()*item.getBooks().getPrice();
				}
			}
		}
		model.addAttribute("total", total);
		return "viewCarts";
	}

}
