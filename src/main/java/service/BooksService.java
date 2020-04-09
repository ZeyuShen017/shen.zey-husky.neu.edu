package service;

import java.util.List;

import Pojo.Books;
import Pojo.Category;

public interface BooksService {
	public Books searchById(Integer id);
	public int add(Books book);		
	public List listBooks();
	//public Category viewCategory(Category category);
}
