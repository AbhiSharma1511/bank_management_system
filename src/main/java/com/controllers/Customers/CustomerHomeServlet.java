package com.controllers.Customers;

import java.io.IOException;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.entity.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customer_home")
public class CustomerHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

	    if (session == null || session.getAttribute("customer") == null) {
	        String empIdStr = (String) session.getAttribute("customerId");

	        if (empIdStr != null) {
	            try {
	                int id = Integer.parseInt(empIdStr);
	                System.out.println("id: " + id);

	                CustomerDAO customerDao = new CustomerDAOImpl();
	                Customer customer = customerDao.getCustomerById(id);

	                if (customer != null) {
	                    session.setAttribute("customer", customer);
	                    resp.sendRedirect("customer/customer_home.jsp");
	                    return;
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace(); // log the actual cause
	            }
	        }

	        // If session is invalid or employee not found
	        resp.sendRedirect("customer/clogin.htm;"); // redirect to login
	    } else {
	        resp.sendRedirect("customer/customer_home.jsp");
	    }
	}

}
