package com.controllers.Employee;

import java.io.IOException;

import com.dao.LoanDAO;
import com.dao.LoanDAOImpl;
import com.entity.Loan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewEditLoanServlet")
public class ViewEditLoanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDAO dao = new LoanDAOImpl();
		String idParam = request.getParameter("loanId");
		System.out.println("Loan edit servlet called!");

        try {
            int loanId = Integer.parseInt(idParam);
            Loan loan = dao.getLoansById(loanId);
            if (loan != null) {
            	System.out.println("Loan details fetched successfully!");
                request.getSession().setAttribute("loan", loan);
                response.sendRedirect("employee/viewEditLoan.jsp");
            } else {
                request.getSession().setAttribute("error", "Loan not found.");
                response.sendRedirect(request.getContextPath() + "loan_management");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("loan_management");
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int loanId = Integer.parseInt(request.getParameter("loanId"));
            String status = request.getParameter("status");

            // Update the customer via DAO
            System.out.println("LoanId: "+loanId);
            System.out.println("status: "+status);

            LoanDAO loanDao = new LoanDAOImpl();
            boolean updated = loanDao.updateLoanStatus(loanId, status);

            System.out.println(updated);

            HttpSession session = request.getSession();
            if (updated) {
            	System.out.println("Loan status updated");
                session.setAttribute("message", "✅ Loan updated successfully.");
            } else {
                session.setAttribute("error", "❌ Failed to update loan.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "❌ Invalid input data.");
        }

        response.sendRedirect(request.getContextPath() + "/loan_management");
    }
}

