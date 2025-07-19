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

@WebServlet("/employee_home")
public class EmployeeHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false);

	    if (session == null || session.getAttribute("employee") == null) {
	        String empIdStr = (String) session.getAttribute("employeeId");

	        if (empIdStr != null) {
	            try {
	                int id = Integer.parseInt(empIdStr);
	                System.out.println("id: " + id);

	                EmployeeDAO employeeDao = new EmployeeDAOImpl();
	                Employee employee = employeeDao.getEmployeeData(id);

	                if (employee != null) {
	                    session.setAttribute("employee", employee);
	                    resp.sendRedirect("employee/employee_home.jsp");
	                    return;
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace(); // log the actual cause
	            }
	        }

	        // If session is invalid or employee not found
	        resp.sendRedirect("employee/login.jsp"); // redirect to login
	    } else {
	        resp.sendRedirect("employee/employee_home.jsp");
	    }
	
	}

}
