package com.controllers.Employee;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.entity.Transaction;

@WebServlet("/viewTransactions")
public class ViewTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TransactionDAO dao = new TransactionDAOImpl();
        List<Transaction> transactions = dao.getAllTransactions();

        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("employee/view_transactions.jsp").forward(request, response);
    }
}
