package com.controllers.Customers;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.entity.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	
    	System.out.println("Customer Login Servlet doPost is called");
        
        String id = request.getParameter("customerId");
        String password = request.getParameter("custPassword");
        System.out.println("Customer ID: " + id + ", Password: " + password);

        int customerId = Integer.parseInt(id);
        CustomerDAO dao = new CustomerDAOImpl();
        boolean isCustomerExist = dao.login(customerId, password);
        
       
        if (isCustomerExist) {
        	HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            // ✅ Create a new session for the logged-in user
            HttpSession session = request.getSession(true);
            session.setAttribute("customerId", id);
            response.sendRedirect("customer_home"); // ✅ change to customer home
        } else {
            request.setAttribute("errorMessage", "❌ Invalid Customer ID or Password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/clogin.jsp"); // ✅ change path if needed
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        System.out.println("Customer Login Servlet doGet is called");
        out.print("Customer Login Servlet");
    }
}
