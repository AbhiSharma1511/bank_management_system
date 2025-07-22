package com.controllers.Employee;

import java.io.IOException;
import java.util.List;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.entity.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/manageCustomers")
public class CustomerManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CustomerDAO dao = new CustomerDAOImpl();

	    List<Customer> activeList = dao.getAllActiveCustomers();
	    List<Customer> inactiveList = dao.getAllInactiveCustomers();

	    // Store in session instead of request
	    request.getSession().setAttribute("activeCustomers", activeList);
	    request.getSession().setAttribute("inactiveCustomers", inactiveList);

	    // Redirect to JSP
	    response.sendRedirect(request.getContextPath() + "/employee/manage_customers.jsp");
	}


}