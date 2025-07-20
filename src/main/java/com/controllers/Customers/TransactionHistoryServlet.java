package com.controllers.Customers;

import java.io.IOException;
import java.util.List;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.entity.Customer;
import com.entity.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/transactionHistory")
public class TransactionHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Customer customer = (Customer) session.getAttribute("customer");

            TransactionDAO transactionDAO = new TransactionDAOImpl();

            // Get all transactions where the customer is either sender or receiver
            List<Transaction> transactions = transactionDAO.getTransactionsByAccount(customer.getAccountNo());

            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("customer/transaction_history.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to fetch transaction history.");
            request.getRequestDispatcher("customer/transaction_history.jsp").forward(request, response);
        }
    }
}
