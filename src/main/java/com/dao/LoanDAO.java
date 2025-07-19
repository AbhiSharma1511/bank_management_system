package com.dao;

import java.util.List;
import com.entity.Loan;

public interface LoanDAO {

	    List<Loan> getAllLoans();
	    boolean applyLoan(Loan loan);
	    boolean updateLoanStatus(int loanId, String status);
	    List<Loan> getLoansByCustomerId(int customerId);
	    Loan getLoansById(int loanId);
}


