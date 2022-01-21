package lms.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageBook {
	
	private Map<Book, Integer> books = new HashMap<Book, Integer>();
	
	public void addBook(Book book, int quantity) {
		books.put(book, quantity);
	}
	
	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<Book>();
		for (Book book : books.keySet()) {
			if (books.get(book) > 0)
				allBooks.add(book);
		}
		return allBooks;
	}
	
	public Map<Book, Integer> getAllBooksQuantity() {
		return books;
	}
	
	public void empty() {
		books.clear();
	}
	
//	public void editBook(Book book, int quantity) {
//		books.put(book, quantity);
//	}
	public void removeBook(Book book) {
			books.remove(book);
	}
	
	public void addQuantity(Book book, int quantity) {
		int oldQty = books.get(book);
		int newQty = oldQty + quantity;
		books.put(book, newQty);
	}
	
	public void reduceQuantity(Book book, int quantity) {
		int oldQty = books.get(book);
		int newQty = oldQty - quantity;
		books.put(book, newQty);
	}
	
	public boolean borrowBook(Book book) {
		int oldQty = books.get(book);
		if (oldQty != 0) {
			int newQty = oldQty - 1;
			books.put(book, newQty);
			return true;
		} 
		return false;
	}
	
	public void returnBook(Book book) {
		int oldQty = books.get(book);
		int newQty = oldQty + 1;
		books.put(book, newQty);
	}
}
