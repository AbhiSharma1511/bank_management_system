package com.controllers.Customers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.dao.LoanDAO;
import com.dao.LoanDAOImpl;
import com.entity.Customer;
import com.entity.Loan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customer_loan")
public class CustomerLoanServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    if (req.getSession() == null || req.getSession().getAttribute("customer") == null) {
	        resp.sendRedirect("customer/clogin.jsp");
	        return;
	    }

	    try {
	        Customer customer = (Customer) req.getSession().getAttribute("customer");
	        int customerId = customer.getCustomerId();

	        String loanIdParam = req.getParameter("loanId");

	        // Handle deletion if loanId is present
	        if (loanIdParam != null) {
	        	System.out.println("Loan id: "+ loanIdParam);
	            int loanId = Integer.parseInt(loanIdParam);
	            LoanDAO loanDao = new LoanDAOImpl();
	            boolean deleted = loanDao.deleteLoan(loanId);

	            if (deleted) {
	            	System.out.println("loan is deleted successfully!");
	                req.getSession().setAttribute("loan_delete_message", "Loan deleted successfully!");
	            } else {
	                req.getSession().setAttribute("loan_delete_message", "Failed to delete loan.");
	            }

	            resp.sendRedirect("customer_loan"); // prevent reprocessing
	            return;
	        }

	        // Otherwise, load loan list
	        LoanDAO loanDao = new LoanDAOImpl();
	        List<Loan> loanList = loanDao.getLoansByCustomerId(customerId);
	        req.getSession().setAttribute("loanList", loanList);

	        resp.sendRedirect("customer/loan_details.jsp");

	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendRedirect("customer_home?alert=true");
	    }
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    if (req.getSession() == null || req.getSession().getAttribute("customer") == null) {
	        resp.sendRedirect("customer/clogin.jsp");
	        return;
	    }
	    System.out.println("Loan post method called");
	    try {
	        Customer customer = (Customer) req.getSession().getAttribute("customer");
	        int customerId = customer.getCustomerId();

	        Timestamp time = new Timestamp(System.currentTimeMillis());
	        System.out.println("Time: "+time);

	        String loanAmountStr = req.getParameter("loanAmount");
	        double loanAmount = Double.parseDouble(loanAmountStr);

	        Loan loan = new Loan();
	        loan.setCustomerId(customerId);
	        loan.setLoanAmount(loanAmount);
	        loan.setStatus("Pending");
	        loan.setCreatedAt(time);
	        loan.setUpdatedAt(time);

	        LoanDAO loanDao = new LoanDAOImpl();
	        boolean success = loanDao.applyLoan(loan);

	        // Store message in session
	        if (success) {
	            req.getSession().setAttribute("loan_add_message", "Loan application submitted successfully!");
	        } else {
	            req.getSession().setAttribute("loan_add_message", "Failed to submit loan application.");
	        }

	        // Redirect to avoid form resubmission
	        resp.sendRedirect("customer_loan");

	    } catch (Exception e) {
	        e.printStackTrace();
	        req.getSession().setAttribute("loan_add_message", "An error occurred while applying for the loan.");
	        resp.sendRedirect("customer_loan");
	    }
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String loanIdParam = req.getParameter("loanId");

	    if (loanIdParam == null) {
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        resp.getWriter().write("Loan ID is required");
	        System.out.println("loan id is required");
	        return;
	    }

	    try {
	        int loanId = Integer.parseInt(loanIdParam);
	        LoanDAO loanDao = new LoanDAOImpl();
	        boolean deleted = loanDao.deleteLoan(loanId);

	        System.out.println("Loan Id:"+loanId);

	        if (deleted) {
	        	System.out.println("Loan is deleted successfully with id: "+ loanId);
	            req.getSession().setAttribute("loan_delete_message", "Loan deleted successfully!");
	            resp.sendRedirect("customer_loan");
	        } else {
	        	System.out.println("Loan is not deleted successfully");
	            req.getSession().setAttribute("loan_delete_message", "Error: try again later!");
	            resp.sendRedirect("customer_loan");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Loan is not deleted successfully");
            req.getSession().setAttribute("loan_delete_message", "Error: try again later!");
            resp.sendRedirect("customer_loan");
	    }
	}

}
