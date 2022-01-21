package lms.spring;

public class Loan {
	
	Book book;
	String bookName;
	String borrower;
	String borrowedDate;
	String returnedDate;
	
	public Loan() {
		
	}
	
	public Loan(Book book, String bookName, String borrower, String borrowedDate, String returnedDate) {
		this.book = book;
		this.bookName = bookName;
		this.borrower = borrower;
		this.borrowedDate = borrowedDate;
		this.returnedDate = returnedDate;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBorrower() {
		return borrower;
	}
	
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	
	public String getBorrowedDate() {
		return borrowedDate;
	}
	
	public void setBorrowedDate(String borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	
	public String getReturnedDate() {
		return returnedDate;
	}
	
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}
}
