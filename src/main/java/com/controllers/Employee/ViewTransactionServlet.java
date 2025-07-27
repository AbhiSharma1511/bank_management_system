package com.controllers.Employee;


import java.io.IOException;
import java.util.List;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.entity.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewTransactions")
public class ViewTransactionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			TransactionDAO transactionDAO = new TransactionDAOImpl();
			List<Transaction> transactions = transactionDAO.getAllTransactions();

			System.out.println(transactions.size());

			request.getSession().setAttribute("transactions", transactions);
			response.sendRedirect("employee/viewTransaction.jsp");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
