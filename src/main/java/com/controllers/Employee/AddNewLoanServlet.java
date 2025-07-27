package com.controllers.Employee;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.LoanDAO;
import com.dao.LoanDAOImpl;
import com.entity.Loan;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddNewLoan")
public class AddNewLoanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Add new servlet called.");
    	PrintWriter rt=  resp.getWriter();
    	rt.print("Add new servlet called.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
            // 1. Read form data
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String customerName = request.getParameter("customerName");
            double loanAmount = Double.parseDouble(request.getParameter("amount"));

            System.out.println(customerId);
            System.out.println(customerName);
            System.out.println(loanAmount);

            // 2. Create Loan object
            Loan loan = new Loan();
            loan.setCustomerId(customerId);
            loan.setLoanAmount(loanAmount);
            loan.setStatus("Pending");

            System.out.println(loan);

            // 3. Save to DB
            LoanDAO loanDao = new LoanDAOImpl();
            boolean success = loanDao.applyLoan(loan);

            System.out.println(success);

            // 4. Send response

            if (success) {
                request.getSession().setAttribute("loan_apply_msg","Loan Applied Successfully" );
                response.sendRedirect("loan_management");

            } else {
            	request.getSession().setAttribute("loan_apply_msg","Something went wrong" );
                response.sendRedirect("employee/new_loan.jsp");
            }

            // 5. Forward to same or result page

        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
            request.setAttribute("msg", "Something went wrong: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/employee/new_loan.jsp");
            rd.forward(request, response);
        }
    }


}

