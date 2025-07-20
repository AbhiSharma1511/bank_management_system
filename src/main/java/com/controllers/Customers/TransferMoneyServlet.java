package com.controllers.Customers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
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

@WebServlet("/transferMoney")
public class TransferMoneyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        System.out.println("Transfer Money doPost is called");
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("customer/clogin.html");
            return;
        }
        try {

        	CustomerDAO customerDAO = new CustomerDAOImpl();
        	TransactionDAO transactionDAO = new TransactionDAOImpl();

        	Customer sender = (Customer) session.getAttribute("customer");
        	String amountStr = request.getParameter("amount");
        	String senderAccount = sender.getAccountNo();
        	String receiverAccount = request.getParameter("receiverAccount");
        	double amount = Double.parseDouble(amountStr);

        	System.out.println("SenderId:" + sender.getCustomerId());
        	System.out.println("senderAccount:" + senderAccount);
        	System.out.println("receiverAccount:" + receiverAccount);
        	System.out.println("amount:" + amountStr);

        	Customer receiver = customerDAO.getCustomerByAccountNo(receiverAccount);
        	System.out.println("Receiver: "+ receiver);

        	if (receiverAccount == null || amountStr == null || amountStr.isEmpty()) {
        		request.setAttribute("message", "Invalid details");
        		request.getRequestDispatcher("transfer_money.jsp").forward(request, response);
        		return;
        	}

        	if (receiver == null) {
        		request.setAttribute("message", "Receiver not found. Please check the account number and try again.");
                request.getRequestDispatcher("customer/transfer_money.jsp").forward(request, response);
                return;
            }

        	if (sender.getBalance() < amount) {
        		request.setAttribute("message", "Insufficient balance.");
        		request.getRequestDispatcher("customer/transfer_money.jsp").forward(request, response);
        		return;
        	}
        	
        	if (sender.getAccountNo().equals(receiver.getAccountNo())) {
        		request.setAttribute("message", "You cannot transfer money to your own account.");
        		request.getRequestDispatcher("customer/transfer_money.jsp").forward(request, response);
        		return;
        	}

        	// Update balances

        	boolean senderUpdated = customerDAO.updateBalance(sender.getCustomerId(), sender.getBalance() - amount);
        	boolean receiverUpdated = customerDAO.updateBalance(receiver.getCustomerId(), receiver.getBalance() + amount);

        	System.out.println("senderUpdate: "+ senderUpdated);
        	System.out.println("receiverUpdated: "+ receiverUpdated);

        	if (senderUpdated && receiverUpdated) {
        		// Save transaction
        		int transactionId = generateUniqueTransactionId();

        		Transaction transaction = new Transaction();

        		transaction.setTransactionId(transactionId);
        		transaction.setSenderAccountNo(sender.getAccountNo());
        		transaction.setReceiverAccountNo(receiverAccount);
        		transaction.setAmount(amount);
        		transaction.setTransactionDate(LocalDate.now());

        		transactionDAO.addTransaction(transaction);

        		// Update session balance
        		Customer updatedSender = customerDAO.getCustomerById(sender.getCustomerId());
        		session.setAttribute("customer", updatedSender);

        		request.setAttribute("message", "Money transferred successfully.");
        	} else {
        		request.setAttribute("message", "Transfer failed!.");
        	}

        	request.getRequestDispatcher("customer/transfer_money.jsp").forward(request, response);
		} catch (Exception e) {
		    e.printStackTrace();
		    request.setAttribute("message", "An error occurred: " + e.getMessage());
		    request.getRequestDispatcher("customer/transfer_money.jsp").forward(request, response);
		}

    }

	private int generateUniqueTransactionId() {
		Random rand = new Random();
		int id = 100000 + rand.nextInt(900000); // Generates 10000–99999
		return id;
	}
}
