package lms.spring;

public class Book {
	
	String name;
	String author;
	String publisher;
	
	public Book(){}
	
	public Book(String name, String author, String publisher) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String toString() {
		return ("Title: " + this.getName() + "\nAuthor: " + this.getAuthor() + "\nPublisher: " + this.getPublisher());
	}
}
