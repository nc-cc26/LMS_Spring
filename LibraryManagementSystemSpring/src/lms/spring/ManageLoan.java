package lms.spring;

import java.util.HashMap;
import java.util.Map;

public class ManageLoan {
	private Map<Loan, String> loans = new HashMap<Loan, String>();

	public void addLoan(Loan loan, String available) {
		loans.put(loan, available);
	}
	
	public Map<Loan, String> getAllLoansQuantity() {
		return loans;
	}
}
