package com.controllers.Employee;

import com.dao.EmployeeDAO;
import com.dao.EmployeeDAOImpl;
import com.entity.Employee;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		
		PrintWriter rt = response.getWriter();

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        System.out.println("id: "+id+", passowrd: "+password);

        EmployeeDAO dao = new EmployeeDAOImpl();
        boolean isEmployeeExist = dao.login(id, password);
        HttpSession session = request.getSession();
        System.out.println("Session ID: " + session.getId());
       
        if (isEmployeeExist) {
            session.setAttribute("employeeId", id);
            response.sendRedirect("employee_home");
        } else {
            request.setAttribute("errorMessage", "❌ Invalid Employee ID or Password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("employee/login.jsp");
            dispatcher.forward(request, response);
        }
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter rt = resp.getWriter();
		rt.print("Here is the error");
	}
}
