package com.controllers.Employee;

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

@WebServlet("/ViewEditCustomerServlet")
public class ViewEditCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomerDAO dao = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("customerId");

        try {
            int customerId = Integer.parseInt(idParam);
            Customer customer = dao.getCustomerById(customerId);

            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/employee/viewEditCustomer.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("error", "Customer not found.");
                response.sendRedirect(request.getContextPath() + "/manageCustomers");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("manageCustomers");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String address = request.getParameter("address");
            boolean active = "on".equals(request.getParameter("active")); // checkbox logic

            // Create customer object with only updatable fields
            Customer updatedCustomer = new Customer();
            updatedCustomer.setCustomerId(customerId);
            updatedCustomer.setEmail(email);
            updatedCustomer.setContact(contact);
            updatedCustomer.setAddress(address);
            updatedCustomer.setActiveAccount(active);

            // Update the customer via DAO
            boolean updated = dao.updateCustomerData(updatedCustomer);

            HttpSession session = request.getSession();
            if (updated) {
                session.setAttribute("message", "✅ Customer updated successfully.");
            } else {
                session.setAttribute("error", "❌ Failed to update customer.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "❌ Invalid input data.");
        }

        response.sendRedirect(request.getContextPath() + "/manageCustomers");
    }

}
