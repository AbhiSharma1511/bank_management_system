package com.controllers.Employee;

import java.io.IOException;

import com.entity.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/employeeProfile")
public class EmployeeProfileServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session != null && session.getAttribute("loggedInEmployee") != null) {
      Employee emp = (Employee) session.getAttribute("loggedInEmployee");
      request.setAttribute("employee", emp);
      request.getRequestDispatcher("/employee/employee_profile.jsp").forward(request, response);
    } else {
      response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
  }
}


//request.getSession().setAttribute("activeCustomers", activeList);
//request.getSession().setAttribute("inactiveCustomers", inactiveList);
//
//// Redirect to JSP
//response.sendRedirect(request.getContextPath() + "/employee/manage_customers.jsp");