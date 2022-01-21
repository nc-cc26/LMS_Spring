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
	Book book;
	Member member;
	Loan loan;
	ManageBook manageBookBean = (ManageBook) context.getBean("manageBookBean");
	ManageMember manageMemberBean = (ManageMember) context.getBean("manageMemberBean");
	ManageLoan manageLoanBean = (ManageLoan) context.getBean("manageLoanBean");
	
	
	// TO DISPLAY ALL THE BOOKS
	public void displayAllBooks() {
		
		Map <Book, Integer> books = manageBookBean.getAllBooksQuantity();
		
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
	
	
	// TO DISPLAY ALL THE MEMBERS
	public void displayAllMembers() {
		Map <Member, String> members = manageMemberBean.getAllMembersQuantity();
		
		if(!members.isEmpty()) {
			System.out.println("\n------------------");
			System.out.println(" ALL MEMBERS");
			System.out.println("-------------------\n");
			System.out
				.println("-------------------------------------------------");
			System.out.println("No.\t\tNAME");
			System.out
				.println("-----------------------------------------------\n");
			int memberIndex = 0;
			for (Member member : members.keySet()) {
				memberIndex++;
				System.out.printf("%1$d.\t\t%2$S\n",
					memberIndex, member.getName());
			}
		} else {
			System.out.println("NO MEMBER IN DATABASE!");
		}
	}
	
	
	// TO DISPLAY ALL THE BORROW AND RETURN RECORD
	public void displayAllLoans() {
		Map <Loan, String> loans = manageLoanBean.getAllLoansQuantity();
		
		if(!loans.isEmpty()) {
			System.out.println("\n------------------");
			System.out.println(" ALL LOANS");
			System.out.println("-------------------\n");
			System.out
				.println("----------------------------------------------------------------------------------------------------------------");
			System.out.println("No.\t\tBOOK NAME\t\tMEMBER NAME\t\tBORROWED DATE\t\t\tRETURNED DATE");
			System.out
				.println("---------------------------------------------------------------------------------------------------------------\n");
			int loanIndex = 0;
			for (Loan loan : loans.keySet()) {
				loanIndex++;
				System.out.printf("%1$d.\t\t%2$S\t\t\t%3$S\t\t\t%4$S\t\t%5$S\n",
					loanIndex, loan.getBookName(), loan.getBorrower(), loan.getBorrowedDate(), loan.getReturnedDate());
			}
		} else {
			System.out.println("NO LOAN RECORD IN DATABASE!");
		}
	}
	
	
	// TO GET ALL THE BOOKS AND STORE IN AN ARRAY
	public Book[] getBookList() {
		Map<Book, Integer> books = manageBookBean.getAllBooksQuantity();
		int bookIndex = 0;
		Book[] booksArr = new Book[books.size()];
		for (Book book : books.keySet()) {
			booksArr[bookIndex] = book;
			bookIndex++;
		}
		return booksArr;
	}
	
	
	// TO GET ALL THE MEMBERS AND STORE IN AN ARRAY
	public Member[] getMemberList() {
		Map<Member, String> members = manageMemberBean.getAllMembersQuantity();
		int memberIndex = 0;
		Member[] membersArr = new Member[members.size()];
		for (Member member : members.keySet()) {
			membersArr[memberIndex] = member;
			memberIndex++;
		}
		return membersArr;
	}
	
	
	// TO GET ALL THE BORROW AND RETURN RECORD IN AN ARRAY
	public Loan[] getLoanList() {
		Map<Loan, String> loans = manageLoanBean.getAllLoansQuantity();
		int loanIndex = 0;
		Loan[] loansArr = new Loan[loans.size()];
		for (Loan loan : loans.keySet()) {
			loansArr[loanIndex] = loan;
			loanIndex++;
		}
		return loansArr;
	}
	
	
	// ADD BOOK
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

		manageBookBean.addBook(book, copies);
		System.out.println("Book is added successfully.\n");
		System.out.println();
	}
	
	
	// ADD MEMBER
	public void addMemberToLMS() {
		member = new Member();
		System.out.println("\nInsert member name:");
		String name = scanner.nextLine();
		
		member.setName(name);

		manageMemberBean.addMember(member, "available");
		System.out.println("Member is added successfully.\n");
		System.out.println();
	}
	
	
	// SELECT WHICH BOOK TO EDIT
	public Book editBook() {
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
	
	
	// EDIT BOOK FIELD NAME
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
				manageBookBean.addQuantity(book, quantity);
			} else if (type == 2) {
				System.out.println("Reduce quantity: ");
				int quantity = Integer.parseInt(scanner.nextLine());
				manageBookBean.reduceQuantity(book, quantity);
			}
		}
		
		System.out.println("Book is edited successfully.\n");
	}
	
	
	// SELECT WHICH MEMBER TO EDIT
	public Member editMember() {
		displayAllMembers();
		Member[] members = getMemberList();
	
		System.out.println("\nPlease select member no. to edit:");

		int selectedIndex = Integer.parseInt(scanner.nextLine());
	
		if (selectedIndex > 0 && selectedIndex <= members.length) {
			Member member = members[selectedIndex - 1];
			return member;
		}
		return null;
	}
	
	
	// EDIT MEMBER
	public void editMemberFromLMS(Member member) {
		System.out.println("\nUpdate new name: ");
		String newName = scanner.nextLine();
		member.setName(newName);
		
		System.out.println("Member is edited successfully.\n");
	}
	
	
	// SELECT WHICH BOOK TO REMOVE
	public Book removeBook() {
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
	
	
	// REMOVE BOOK
	public void removeBookFromLMS(Book book) {
		manageBookBean.removeBook(book);
		System.out.println("Book is removed successfully.\n");
		System.out.println();
	}
	
	
	// SELECT WHICH MEMBER TO REMOVE
	public Member removeMember() {
		displayAllMembers();
		Member[] members = getMemberList();
		
		System.out.println("\nPlease select member no. to remove:");

		int selectedIndex = Integer.parseInt(scanner.nextLine());
		
		if (selectedIndex > 0 && selectedIndex <= members.length) {
			Member member = members[selectedIndex - 1];
			return member;
		}
		return null;
	}
	
	
	// REMOVE MEMBER
	public void removeMemberFromLMS(Member member) {
		manageMemberBean.removeMember(member);
		System.out.println("Member is removed successfully.\n");
		System.out.println();
	}
	
	
	// SELECT WHICH BOOK TO BORROW
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
	
	
	// SELECT WHICH MEMBER THAT WANTS TO BORROW
	public Member getBorrower() {
		displayAllMembers();
		Member[] members = getMemberList();
		System.out.println("\nSelect member No. of borrower:");
		int selectedIndex = Integer.parseInt(scanner.nextLine());
		
		if (selectedIndex > 0 && selectedIndex <= members.length) {
			Member member = members[selectedIndex - 1];
			return member;
		}
		return null;
	}
	
	
	// GET THE BOOK OBJECT FROM LOAN
	public Book getReturnBook(Loan loan) {
		return loan.getBook();
	}
	
	
	// SELECT WHICH LOAN TO RETURN (RETURN BOOK)
	public Loan changeLoan() {
		displayAllLoans();
		Loan[] loans = getLoanList();
		
		System.out.println("\nSelect loan No. to return:");
		int selectedIndex = Integer.parseInt(scanner.nextLine());
		
		if (selectedIndex > 0 && selectedIndex <= loans.length) {
			Loan loan = loans[selectedIndex - 1];
			if(loan.getReturnedDate() == "") {
				return loan;
			}
		}
		return null;
	}
	
	
	// QUANTITY -1 AFTER BORROWED
	public boolean borrowBookInLMS(Book book) {
		return manageBookBean.borrowBook(book);
	}
	
	
	// QUANTITY +1 AFTER RETURNED
	public void returnBookInLMS(Book book) {
		manageBookBean.returnBook(book);
	}
	
	
	// BORROW BOOK AND ADD LOAN RECORD
	public void addLoanInLMS(Book book, String borrowedBookName, String borrower) {
		loan = new Loan();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		loan.setBook(book);
		loan.setBookName(borrowedBookName);
		loan.setBorrower(borrower);
		loan.setBorrowedDate(dtf.format(now));
		loan.setReturnedDate("");
		manageLoanBean.addLoan(loan, "available");
		
		System.out.println("Book is borrowed successfully.\n");
		System.out.println();
	}
	
	
	// RETURN BOOK AND ADD RETURNED DATE
	public void changeLoanInLMS(Loan loan) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		loan.setReturnedDate(dtf.format(now));
		
		System.out.println("Book is returned successfully.\n");
		System.out.println();
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
				ui.displayAllBooks();
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
				ui.displayAllMembers();;
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

}
