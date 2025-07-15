package com.controllers.Employee;

import java.io.IOException;
import java.util.List;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.entity.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/manageCustomers")
public class CustomerManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CustomerDAO dao = new CustomerDAOImpl();

	    List<Customer> activeList = dao.getAllActiveCustomers();
	    List<Customer> inactiveList = dao.getAllInactiveCustomers();

	    request.setAttribute("activeCustomers", activeList);
	    request.setAttribute("inactiveCustomers", inactiveList);

	    request.getRequestDispatcher("/employee/manage_customers.jsp").forward(request, response);
	}

}

//<c:forEach var="cust" items="${activeCustomers}">
//<tr>
//	<td>${cust.customerId}</td>
//	<td>${cust.firstName}${cust.lastName}</td>
//	<td>${cust.email}</td>
//	<td>₹${cust.balance}</td>
//	<td><a href="ViewCustomerServlet?customerId=101"
//		class="btn-view">👁 View</a>
//		<button onclick="confirmDelete(customerId)" class="btn-delete">🗑
//			Delete</button></td>
//</tr>
//</c:forEach>