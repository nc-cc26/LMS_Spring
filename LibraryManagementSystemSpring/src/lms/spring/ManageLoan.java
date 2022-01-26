package lms.spring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManageLoan {
	
	Scanner scanner = new Scanner(System.in);
	private Map<Loan, String> loans = new HashMap<Loan, String>();
	Loan loan;
	Book book;
	
	public void addLoan(Loan loan, String available) {
		loans.put(loan, available);
	}
	
	public Map<Loan, String> getAllLoansQuantity() {
		return loans;
	}
	
	public void displayAllLoans() {
		Map <Loan, String> loans = getAllLoansQuantity();
		
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
	
	public Loan[] getLoanList() {
		Map<Loan, String> loans = getAllLoansQuantity();
		int loanIndex = 0;
		Loan[] loansArr = new Loan[loans.size()];
		for (Loan loan : loans.keySet()) {
			loansArr[loanIndex] = loan;
			loanIndex++;
		}
		return loansArr;
	}
	
	public Book getReturnBook(Loan loan) {
		return loan.getBook();
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
			addLoan(loan, "available");
			
			System.out.println("Book is borrowed successfully.\n");
			System.out.println();
		}
	
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
	
	// RETURN BOOK AND ADD RETURNED DATE
		public void changeLoanInLMS(Loan loan) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 
			loan.setReturnedDate(dtf.format(now));
			
			System.out.println("Book is returned successfully.\n");
			System.out.println();
		}
}
