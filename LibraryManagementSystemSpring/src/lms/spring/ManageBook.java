package lms.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManageBook {
	
	private Map<Book, Integer> books = new HashMap<Book, Integer>();
	Scanner scanner = new Scanner(System.in);
	Book book;
	
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
	
	public Book[] getBookList() {
		Map<Book, Integer> books = getAllBooksQuantity();
		int bookIndex = 0;
		Book[] booksArr = new Book[books.size()];
		for (Book book : books.keySet()) {
			booksArr[bookIndex] = book;
			bookIndex++;
		}
		return booksArr;
	}
	
	// TO DISPLAY ALL THE BOOKS
		public void displayAllBooks() {
			
			Map <Book, Integer> books = getAllBooksQuantity();
			
			if(!books.isEmpty()) {
				System.out.println("\n------------------");
				System.out.println(" ALL BOOKS");
				System.out.println("-------------------\n");
				System.out
					.println("-----------------------------------------------------------------------------------------------");
				System.out.println("No.\t\tTITLE\t\t\tAUTHOR\t\tPUBLISHER\t\tQUANTITY");
				System.out
					.println("-----------------------------------------------------------------------------------------------\n");
				int bookIndex = 0;
				for (Book book : books.keySet()) {
					bookIndex++;
					System.out.printf("%1$d.\t\t%2$S\t\t\t%3$S\t\t%4$S\t\t\t%5$d\n",
						bookIndex, book.getName(), book.getAuthor(), book.getPublisher(), books.get(book));
				}
			} else {
				System.out.println("NO BOOK IN DATABASE!");
			}
		}
		
		public void addBookToLMS() {
			book = new Book();
			System.out.println("\nInsert Book Title:");
			String title = scanner.nextLine();
			
			System.out.println("\nInsert Author:");
			String author = scanner.nextLine();
			
			System.out.println("\nInsert Publisher:");
			String publisher = scanner.nextLine();
			
			System.out.println("\nHow many copies:");
			int copies = Integer.parseInt(scanner.nextLine());
			
			book.setName(title);
			book.setAuthor(author);
			book.setPublisher(publisher);

			addBook(book, copies);
			System.out.println("Book is added successfully.\n");
			System.out.println();
		}
		
		
		public Book selectRemoveBook() {
			displayAllBooks();
			Book[] books = getBookList();
			if (books.length != 0) {
				System.out.println("\nPlease select book no. to remove:");

				int selectedIndex = Integer.parseInt(scanner.nextLine());
			
				if (selectedIndex > 0 && selectedIndex <= books.length) {
					Book book = books[selectedIndex - 1];
					return book;
				}
			}
			return null;
		}
		

		public Book selectEditBook() {
			displayAllBooks();
			Book[] books = getBookList();
		
			System.out.println("\nPlease select book no. to edit:");

			int selectedIndex = Integer.parseInt(scanner.nextLine());
		
			if (selectedIndex > 0 && selectedIndex <= books.length) {
				Book book = books[selectedIndex - 1];
				return book;
			}
			return null;
		}
		
		public void editBookFromLMS(Book book) {
			System.out.println("\nPlease select field to edit (Title(1) - Author(2) - Publisher(3) - Quantity(4)):");
			int field = Integer.parseInt(scanner.nextLine());

			if(field == 1) {
				System.out.println("\nUpdate fieldname: ");
				String fieldName = scanner.nextLine();
				book.setName(fieldName);
			} else if (field == 2) {
				System.out.println("\nUpdate fieldname: ");
				String fieldName = scanner.nextLine();
				book.setAuthor(fieldName);
			} else if (field == 3) {
				System.out.println("\nUpdate fieldname: ");
				String fieldName = scanner.nextLine();
				book.setPublisher(fieldName);
			} else if (field == 4) {
				System.out.println("\nADD(1) OR REDUCE(2) quantity?: ");
				int type = Integer.parseInt(scanner.nextLine());
				if(type == 1) {
					System.out.println("Add quantity: ");
					int quantity = Integer.parseInt(scanner.nextLine());
					addQuantity(book, quantity);
				} else if (type == 2) {
					System.out.println("Reduce quantity: ");
					int quantity = Integer.parseInt(scanner.nextLine());
					reduceQuantity(book, quantity);
				}
			}
			
			System.out.println("Book is edited successfully.\n");
		}

		public Book getBorrowBook() {
			displayAllBooks();
			Book[] books = getBookList();
			System.out.println("\nSelect book No. to borrow:");
			int selectedIndex = Integer.parseInt(scanner.nextLine());
			
			if (selectedIndex > 0 && selectedIndex <= books.length) {
				Book book = books[selectedIndex - 1];
				return book;
			}
			return null;
		}
		
		// REMOVE BOOK
		public void removeBookFromLMS(Book book) {
			removeBook(book);
			System.out.println("Book is removed successfully.\n");
			System.out.println();
		}
}
