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


@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    	
        int empId = Integer.parseInt(request.getParameter("empId"));
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        Employee employee = employeeDAO.getEmployeeData(empId);
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("msg", "New password and confirm password do not match!");
        } 
        else if(employee==null){
        	request.setAttribute("msg", "Employee does not found!");
        }
        else if (employee.getPassword().equals(newPassword)) {
            request.setAttribute("msg", "New password must be different from the old password.");
        }
        else {
            boolean updated = employeeDAO.updatePassword(empId, newPassword);
            if (updated) {
                request.setAttribute("success_msg", "Password updated successfully!");
            } else {
                request.setAttribute("msg", "Failed to update password!");
            }
        }

        request.getRequestDispatcher("employee/ResetPassword.jsp").forward(request, response);
    }
    
    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{6,10}$";
        return password.matches(regex);
    }

}
