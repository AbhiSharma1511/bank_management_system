package com.controllers.Customers;

import java.io.IOException;

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

@WebServlet("/customerProfile")
public class CustomerProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 HttpSession session = request.getSession(false); // don’t create session
			 System.out.println("Customer profile servlet is called");
		    if (session == null || session.getAttribute("customer") == null) {
		        response.sendRedirect("customer/clogin.html"); // redirect to login if no session
		        return;
		    }
		    else if (session != null && session.getAttribute("customer") != null) {
				Customer loggedInCustomer = (Customer) session.getAttribute("customer");

				CustomerDAO dao = new CustomerDAOImpl();
				Customer fullCustomer = dao.getCustomerById(loggedInCustomer.getCustomerId());

				if (fullCustomer != null) {
					System.out.println("Name: "+fullCustomer.getFirstName());
					System.out.println("Account No: "+fullCustomer.getAccountNo());
					request.setAttribute("customer", fullCustomer);
					RequestDispatcher dispatcher = request.getRequestDispatcher("customer/customer_profile.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Unable to fetch customer profile.");
					request.getRequestDispatcher("customer/dashboard.jsp").forward(request, response);
				}
			}
		    else {
				response.sendRedirect("customer/clogin.html");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        int customerId = Integer.parseInt(req.getParameter("customerId"));

	        String email = req.getParameter("email");
	        String contact = req.getParameter("contact");
	        String address = req.getParameter("address");

	        Customer updatedCustomer = new Customer();
	        updatedCustomer.setCustomerId(customerId);
	        updatedCustomer.setEmail(email);     // optional if not editable
	        updatedCustomer.setContact(contact);
	        updatedCustomer.setAddress(address);

	        CustomerDAO dao = new CustomerDAOImpl();
	        boolean result = dao.updateCustomerDataByCustomer(updatedCustomer);

	        if (result) {
	            HttpSession session = req.getSession();
	            session.setAttribute("message", "Customer details updated successfully.");
	        } else {
	            req.setAttribute("error", "Failed to update customer details.");
	        }

	        resp.sendRedirect("customerProfile");

	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("error", "Something went wrong. " + e.getMessage());
	        req.getRequestDispatcher("customerProfile").forward(req, resp);
	    }
	}


}
