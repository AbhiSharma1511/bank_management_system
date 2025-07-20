package com.controllers.Employee;

import java.io.IOException;

import com.dao.EmployeeDAO;
import com.dao.EmployeeDAOImpl;
import com.entity.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/UpdateEmployeeDetails")
public class UpdateEmployeeDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            int empId = Integer.parseInt(request.getParameter("empId"));
            String address = request.getParameter("address");
            String contact = request.getParameter("contact");

            boolean isUpdated = employeeDAO.updateEmployeeData(empId, contact, address);

            HttpSession session = request.getSession();
            if (isUpdated) {
            	Employee employee = employeeDAO.getEmployeeData(empId);
            	System.out.println(employee);
            	session.setAttribute("employee", employee);
                session.setAttribute("message", "Employee updated successfully!");
                response.sendRedirect("/Bank_Management_System/employee/profile.jsp");
            } else {
            	session.setAttribute("message", "Failed to update employee.");
                throw new Exception();
            }

        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("message", "Employee data not updated");
            response.sendRedirect("/Bank_Management_System/employee/profile.jsp");
        }
    }
}
