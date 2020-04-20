package Controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;

import Dao.CategoryDao;
import Pojo.Orders;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.BooksDao;
import Pojo.Books;
import Pojo.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes(value = {"cart","total","category"})
@RequestMapping("/Book")
public class BooksAction  {
	private Books books;
	private List list;
	private Map<String,CartItem> cart;
	private double total=0;
	private BooksDao bookdao = new BooksDao();
	private CategoryDao categoryDao = new CategoryDao();
	private Books book = new Books();

	@RequestMapping("viewcart")
	public String viewcart(){
		return "viewCarts";
	}
	@RequestMapping(value = "addBook",method = RequestMethod.GET)
	public String AddBookPage(Model model){
		model.addAttribute("addBook", new Books());
		return "addBooks";
	}
	/*********** Add Book ************/
	@RequestMapping("addBook.action")
	public String addBooks(@Valid @ModelAttribute("addBook")Books bk, BindingResult result, HttpServletRequest request){
		if(result.hasErrors()){
			return "addBooks";
		}
		if(bookdao.searchBookByIsbn(bk.getIsbn())>0){
			request.setAttribute("errorInfo", "Isbn has existed");
			return "error";
		}
		//to save at the project folder
		String path1= "/Users/zeyushen/Desktop/CSYE 6220/final project/src/main/webapp/WEB-INF/pages/images/books/";
		//to save at the root of tomcat
		String path2 = request.getSession().getServletContext().getRealPath("/WEB-INF/pages/images/Books/");
		bk.setCategoryByCid(categoryDao.searchCategoryByName(bk.getCategory()));
		bk.setPath(path1 + bk.getIsbn()+".jpg");
		if (bookdao.AddBook(bk) == 1) {
			System.out.println("123456789");
			CommonsMultipartFile photo=bk.getPhoto();
			File file1=new File(path1, bk.getIsbn()+".jpg");
			File file2=new File(path2, bk.getIsbn()+".jpg"); //to save at the root of tomcat

			try {
				photo.transferTo(file2);
				Files.copy(file2.toPath(),file1.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("errorInfo", e.toString());
				return "error";
			}
			//System.out.println(photo);
			System.out.println(bk.getPrice()+bk.getIsbn());
			request.setAttribute("successInfo", "Add book successfully");
			return "success";
		} else {
			request.setAttribute("errorInfo", "error");
			return "error";
		}

		//return null;
	}
	/*public String addBooks(HttpServletRequest request, String title, String isbn, String author, String price, String category) throws Exception{// 添加

		DiskFileItemFactory factoy=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factoy);
		//set file size
		sfu.setFileSizeMax(50*8*1024);
		List<FileItem> list=sfu.parseRequest(request);
		for (FileItem fileItem : list) {

			fileItem.getFieldName();
			System.out.println(fileItem.getString("utf-8").length());
		}

		title=list.get(0).getString("utf-8");
		isbn=list.get(1).getString("utf-8");
		author=list.get(2).getString("utf-8");
		price=list.get(3).getString("utf-8");
		category=list.get(4).getString("utf-8");


		System.out.println("springmvc upload file...");
		//上传的位置
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/pages/images/Books/");
		System.out.println(path);
		//判断该路径是否存在
		File file = new File(path,isbn+".jpg" );
		list.get(5).write(file);
		System.out.println(file.length());

		System.out.println(category);

		if(bookdao.searchBookByIsbn(Integer.parseInt(isbn))>0){
			request.setAttribute("errorInfo", "Isbn has existed");
			return "error";
		}
		Books bk = new Books();
		bk.setCategoryByCid(categoryDao.searchCategoryByName(category));
		bk.setTitle(title);
		bk.setIsbn(isbn);
		bk.setPrice(Double.parseDouble(price));
		bk.setAuthor(author);

		if (bookdao.AddBook(bk) == 1) {
			request.setAttribute("successInfo", "Add book successfully");
			return "success";
		} else {
			request.setAttribute("errorInfo", "error");
			return "error";
		}
	}*/
	/********* List the book   ***************/
	@RequestMapping("/all.action")
	public String listBooks( Model model, String category) {
		List<Category> c=null;
		c=categoryDao.getCategoryList();
		if(category==null) {     //list all the book
			list = bookdao.getBookList();
			model.addAttribute("booklist", list);
			model.addAttribute("category",c);
		}else{   				//list book by category
			list=bookdao.searchBooksByCategory(category);
			model.addAttribute("booklist", list);
			model.addAttribute("category",c);
		}
		return "listBooks";
	}

	@RequestMapping("viewDetail.action")
	public String viewDetails(int bid,Model model) {
		book.setBid(bid);
		books = bookdao.searchBookById(book.getBid());
	model.addAttribute("books",books);

	return "viewBooks";
	}

	/******  Add book to shopping cart *********/
	@RequestMapping("addCart.action")
	public String addCart (int bid, Model model, HttpSession session) throws IOException, Exception{// 添加购物车
		cart = (Map) session.getAttribute("cart");
		//total= (double)session.getAttribute("total");
		total=0;

		if (cart == null) {
			cart = new HashMap();
			model.addAttribute("cart", cart);
		}
		book.setBid(bid);
		books = bookdao.searchBookById(book.getBid());
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
		BigDecimal bg = new BigDecimal(total);
		double t = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		model.addAttribute("total", t);
		return "viewCarts";
	}

	/******  Add book to shopping cart *********/
	@RequestMapping("deleteCart.action")
	public String deleteCart (int bid, Model model, HttpSession session) throws IOException, Exception{// 添加购物车
		cart = (Map) session.getAttribute("cart");
		total=0;

		if (cart == null) {
			cart = new HashMap();
			model.addAttribute("cart", cart);
		}

		book.setBid(bid);
		books = bookdao.searchBookById(book.getBid());
		CartItem cartItem = (CartItem) cart.get(books.getIsbn());

		if (cartItem != null && cartItem.getQuantity()>1) {
			cartItem.setQuantity(cartItem.getQuantity() - 1);
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
		} else if(cartItem !=null && cartItem.getQuantity()==1){
			cart.remove(books.getIsbn());
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
		}
		BigDecimal bg = new BigDecimal(total);
		double t = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		model.addAttribute("total", t);
		return "viewCarts";
	}

}
