package service.impl;

import java.util.List;

import Dao.BooksDao;
import Pojo.Books;
import Pojo.Category;
import service.BooksService;

public class BooksServiceImpl implements BooksService {
	private BooksDao booksDAO;
	@Override
	public Books searchById(Integer id) {
		// TODO Auto-generated method stub
		return booksDAO.searchBookById(id);
	}

	@Override
	public int add(Books book) {
		// TODO Auto-generated method stub
		return booksDAO.AddBook(book);
	}

	@Override
	public List listBooks() {
		// TODO Auto-generated method stub
		return booksDAO.getBookList();
	}
	
	/*public Category viewCategory(Category category){
		return booksDAO.viewCategory(category);
	}*/
	
	public BooksDao getBooksDAO() {
		return booksDAO;
	}

	public void setBooksDAO(BooksDao booksDAO) {
		this.booksDAO = booksDAO;
	}

}
