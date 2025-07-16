package com.controllers.Employee;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.entity.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Read form parameters
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dobStr = request.getParameter("dob");
            String contact = request.getParameter("contact");
            String address = request.getParameter("address");
            String aadhar = request.getParameter("aadhar");
            String pan = request.getParameter("pan");
//            String accountNo = request.getParameter("accountNo");
            double balance = Double.parseDouble(request.getParameter("balance"));
            

            // Parse date
            LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Create Customer object
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setDob(dob);
            customer.setContact(contact);
            customer.setAddress(address);
            customer.setAadhar(aadhar);
            customer.setPan(pan);
//            customer.setAccountNo(accountNo);
            customer.setBalance(balance);
            //customer.setActiveAccount(false);

            // Save to DB
            CustomerDAO dao = new CustomerDAOImpl();
            boolean result = dao.addNewCustomer(customer);

            if (result) {
                response.sendRedirect("employee/manage_customers.jsp"); // Redirect after success
            } else {
                response.getWriter().write("Failed to add customer.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}
