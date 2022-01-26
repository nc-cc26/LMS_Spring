package lmsSpringClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lms.spring.Book;
import lms.spring.Loan;
import lms.spring.ManageBook;
import lms.spring.ManageLoan;
import lms.spring.ManageMember;
import lms.spring.Member;

public class LMSspringClient {
	Scanner scanner = new Scanner(System.in);
	
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	ManageBook manageBookBean = (ManageBook) context.getBean("manageBookBean");
	ManageMember manageMemberBean = (ManageMember) context.getBean("manageMemberBean");
	ManageLoan manageLoanBean = (ManageLoan) context.getBean("manageLoanBean");
	
	public static void main(String[] args) {
		LMSspringClient ui = new LMSspringClient();
		int userChoice = ui.mainMenu();

		while (userChoice > 0 && userChoice <= 11) {

			switch (userChoice) {

			case 1:
				ui.addBookToLMS();
				break;

			case 2:
				Book editBook = ui.editBook();
				ui.editBookFromLMS(editBook);
				break;
				
			case 3:
				Book removeBook = ui.removeBook();
				ui.removeBookFromLMS(removeBook);
				break;
				
			case 4:
				ui.viewBook();
				break;
			
			case 5:
				ui.addMemberToLMS();
				break;
			
			case 6:
				Member editMember = ui.editMember();
				ui.editMemberFromLMS(editMember);
				break;
				
			case 7:
				Member removeMember = ui.removeMember();
				ui.removeMemberFromLMS(removeMember);
				break;
				
			case 8:
				ui.displayAllMembers();
				break;
				
			case 9:
				Book borrowedBook = ui.getBorrowBook();
				Member borrower = ui.getBorrower();
				String borrowedBookName = borrowedBook.getName();
				String borrowerName = borrower.getName();
				if (ui.borrowBookInLMS(borrowedBook)) {
					ui.addLoanInLMS(borrowedBook, borrowedBookName, borrowerName);
				} else {
					System.out.println("BOOK INSUFFICIENT!");
				}
				break;
			
			case 10:
				Loan thisLoan = ui.changeLoan();
				if (thisLoan != null) {
					Book returnBook = ui.getReturnBook(thisLoan);
					ui.changeLoanInLMS(thisLoan);
					ui.returnBookInLMS(returnBook);
				} else {
					System.out.println("BOOK IS ALREADY RETURNED!");
				}
				break;
				
			case 11:
				ui.displayAllLoans();
				break;

			default:
				break;
			}

			userChoice = ui.mainMenu();

		}

	}
	
	
	// ADD BOOK
	public void addBookToLMS() {
		manageBookBean.addBookToLMS();
	}
	
	// SELECT WHICH BOOK TO EDIT
	public Book editBook() {
		return manageBookBean.selectEditBook();
	}
		
		// EDIT BOOK FIELD NAME
	public void editBookFromLMS(Book book) {
		manageBookBean.editBookFromLMS(book);
	}
	
	// SELECT WHICH BOOK TO REMOVE
	public Book removeBook() {
		return manageBookBean.selectRemoveBook();
	}
	
	// REMOVE BOOK
	public void removeBookFromLMS(Book book) {
		manageBookBean.removeBook(book);
	}
	public void viewBook() {
		manageBookBean.displayAllBooks();
	}
	
	// ADD MEMBER
	public void addMemberToLMS() {
		manageMemberBean.addMemberToLMS();
	}
	
	// SELECT WHICH MEMBER TO EDIT
	public Member editMember() {
		return manageMemberBean.selectEditMember();
	}
	
	// EDIT MEMBER
	public void editMemberFromLMS(Member member) {
		manageMemberBean.editMemberFromLMS(member);
	}
	
	
	// SELECT WHICH MEMBER TO REMOVE
	public Member removeMember() {
		return manageMemberBean.selectRemoveMember();
	}
	
	// REMOVE MEMBER
	public void removeMemberFromLMS(Member member) {
		manageMemberBean.removeMemberFromLMS(member);
	}
	
	
	// TO DISPLAY ALL THE MEMBERS
	public void displayAllMembers() {
		manageMemberBean.displayAllMembers();
	}
	
	
	// TO DISPLAY ALL THE BORROW AND RETURN RECORD
	public void displayAllLoans() {
		manageLoanBean.displayAllLoans();
	}
	
	// SELECT WHICH BOOK TO BORROW
	public Book getBorrowBook() {
		return manageBookBean.getBorrowBook();
	}
	
	
	// SELECT WHICH MEMBER THAT WANTS TO BORROW
	public Member getBorrower() {
		return manageMemberBean.getBorrower();
	}
	
	
	// GET THE BOOK OBJECT FROM LOAN
	public Book getReturnBook(Loan loan) {
		return manageLoanBean.getReturnBook(loan);
	}
	
	// BORROW BOOK AND ADD LOAN RECORD
	public void addLoanInLMS(Book book, String borrowedBookName, String borrower) {
		manageLoanBean.addLoanInLMS(book, borrowedBookName, borrower);
	}
	
	// SELECT WHICH LOAN TO RETURN (RETURN BOOK)
	public Loan changeLoan() {
		return manageLoanBean.changeLoan();
	}
	
	
	// QUANTITY -1 AFTER BORROWED
	public boolean borrowBookInLMS(Book book) {
		return manageBookBean.borrowBook(book);
	}
	
	
	// QUANTITY +1 AFTER RETURNED
	public void returnBookInLMS(Book book) {
		manageBookBean.returnBook(book);
	}
	
	
	// RETURN BOOK AND ADD RETURNED DATE
	public void changeLoanInLMS(Loan loan) {
		manageLoanBean.changeLoanInLMS(loan);
	}
	
	public int mainMenu() {
		System.out.println("\n---------------------------------");
		System.out.println(" Library Management System");
		System.out.println("--------------------------------\n");

		System.out.println("1. Add Book");
		System.out.println("2. Edit Book");
		System.out.println("3. Remove Book");
		System.out.println("4. View Book");
		System.out.println();
		System.out.println("5. Add Member");
		System.out.println("6. Edit Member");
		System.out.println("7. Remove Member");
		System.out.println("8. View Member");
		System.out.println();
		System.out.println("9. Borrow Book");
		System.out.println("10. Return Book");
		System.out.println("11. View Borrowed Book");
		System.out.println();
		System.out.println("12. Exit");
		System.out.println("\nChoose a function:");
		return Integer.parseInt(scanner.nextLine());
	}
	


}
