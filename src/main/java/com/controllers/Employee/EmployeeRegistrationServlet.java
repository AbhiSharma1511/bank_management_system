package com.controllers.Employee;

import java.io.IOException;
import java.util.Random;

import com.dao.EmployeeDAO;
import com.dao.EmployeeDAOImpl;
import com.entity.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employeeRegisterServlet")
public class EmployeeRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int employeeId = generateUniqueEmployeeId();
            req.getSession().setAttribute("employeeId", employeeId);
            resp.sendRedirect("employee/eRegistration.jsp");  // Make sure this JSP file exists
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        EmployeeDAO employeeDao = new EmployeeDAOImpl();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String password = request.getParameter("password");

            Employee employee = new Employee();
            employee.setEmpId(id);
            employee.setName(name);
            employee.setEmail(email);
            employee.setContact(mobile);
            employee.setAddress(address);
            employee.setPassword(password);

            boolean success = employeeDao.registerEmployee(employee);
            if (success) {
                request.setAttribute("success", "✅ Employee registered successfully!");
                request.getRequestDispatcher("employee/eRegistration.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "❌ Something went wrong. Please try again.");
                request.getRequestDispatcher("employee/eRegistration.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Exception occurred during registration.");
            request.getRequestDispatcher("employee/eRegistration.jsp").forward(request, response);
        }
    }

    private int generateUniqueEmployeeId() {
        return 100000 + new Random().nextInt(90000);
    }
}
