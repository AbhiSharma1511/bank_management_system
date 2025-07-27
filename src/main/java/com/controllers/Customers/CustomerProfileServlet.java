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
		        response.sendRedirect("customer/clogin.jsp"); // redirect to login if no session
		        return;
		    }
		    else if (session != null && session.getAttribute("customer") != null) {
				Customer loggedInCustomer = (Customer) session.getAttribute("customer");

				CustomerDAO dao = new CustomerDAOImpl();
				Customer fullCustomer = dao.getCustomerById(loggedInCustomer.getCustomerId());

				if (fullCustomer != null) {
					System.out.println("Name: "+fullCustomer.getFirstName());
					System.out.println("Account No: "+fullCustomer.getAccountNo());
					request.getSession().setAttribute("customer", fullCustomer);
					response.sendRedirect("customer/customer_profile.jsp");

				} else {
					request.getSession().setAttribute("errorMessage", "Unable to fetch customer profile.");
					response.sendRedirect("customer/dashboard.jsp");
				}
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

	        System.out.println("Customer details post method called.");

	        Customer updatedCustomer = new Customer();
	        updatedCustomer.setCustomerId(customerId);
	        updatedCustomer.setEmail(email);     // optional if not editable
	        updatedCustomer.setContact(contact);
	        updatedCustomer.setAddress(address);

	        CustomerDAO dao = new CustomerDAOImpl();
	        boolean result = dao.updateCustomerDataByCustomer(updatedCustomer);

	        if (result) {
	        	System.out.println("Customer data updated successfully.");
	            req.getSession().setAttribute("data_update_message", "Customer details updated successfully.");
	        } else {
	            req.getSession().setAttribute("data_update_message", "Failed to update customer details.");
	        }

	        resp.sendRedirect("customerProfile");

	    } catch (Exception e) {
	    	System.out.println("Customer data not updated successfully.");
	        e.printStackTrace();
	        req.getSession().setAttribute("data_update_message", "Something went wrong.");
	        resp.sendRedirect("customerProfile");
	    }
	}


}
