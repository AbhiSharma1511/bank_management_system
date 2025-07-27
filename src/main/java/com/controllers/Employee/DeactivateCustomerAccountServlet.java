package com.controllers.Employee;

import java.io.IOException;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deactiveCustomer")
public class DeactivateCustomerAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("customerId");

        try {
            int customerId = Integer.parseInt(idParam);

            CustomerDAO dao = new CustomerDAOImpl();



            boolean success = dao.setDeactiveAccount(customerId);
            System.out.println("Customer deactive: "+success);
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("message", "✅ Customer deactived successfully.");
            } else {
                session.setAttribute("error", "❌ Failed to deactive customer.");
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid Customer ID format.");
        }

        // Redirect back to manage customers page
        response.sendRedirect(request.getContextPath() + "/manage_customers");
    }
}
