package com.controllers.Employee;

import java.io.IOException;
import java.util.List;

import com.dao.LoanDAO;
import com.dao.LoanDAOImpl;
import com.entity.Loan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loan_management")
public class LoanManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Loan Management Servlet is called!!");
			LoanDAO loanDao = new LoanDAOImpl();
			List<Loan> allLoans = loanDao.getAllLoans();

			// set attribute for JSP
			req.getSession().setAttribute("loanList", allLoans);

			// forward to JSP page
			resp.sendRedirect("employee/loan_management.jsp");


		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("msg", "Failed to fetch loans: " + e.getMessage());
			resp.sendRedirect("employee/manage_customers.jsp");

		}
	}
}
