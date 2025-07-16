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
        rt.print("1 point");
        rt.print(password);

        EmployeeDAO dao = new EmployeeDAOImpl();
        Employee employee = dao.login(id, password);
       
        rt.print("2 point");
        
        rt.print("  Employee:"+employee);
        
        if (employee != null) {
            // Set employee in session
            HttpSession session = request.getSession();
            session.setAttribute("employee", employee);

            // Redirect to employee dashboard
            response.sendRedirect("employee/employee_home.html");
        } else {
            // Send back to login with error
    		rt.print(" Employee Not found");
    		rt.print(" EmpId:" + id);
    		rt.print(" Password: "+ password);
//            request.setAttribute("error", "Invalid Employee ID or Password.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("employee/login.html");
//            dispatcher.forward(request, response);
        }
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter rt = resp.getWriter();
		rt.print("Here is the error");
	}
}
