package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.Employee;
import com.utils.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public boolean login(String id, String password) {
	    String sql = "SELECT * FROM employees WHERE id = ? AND password = ?";
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, id);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            System.out.println("Employee exists: true");
	            return true;
	        } else {
	            System.out.println("Employee exists: false");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}




	private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
	    Employee emp = new Employee();

	    emp.setEmpId(rs.getInt("id"));
	    emp.setName(rs.getString("name"));
	    emp.setEmail(rs.getString("email"));
	    emp.setPassword(rs.getString("password"));
	    emp.setRole(rs.getString("role"));
	    emp.setAddress(rs.getString("address"));// If you have roles like "manager", "staff", etc.
	    emp.setContact(rs.getString("contact"));// If you have roles like "manager", "staff", etc.

	    return emp;
	}



//	public int generateUniqueEmployeeId() {
//	    Random rand = new Random();
//	    int id;
//	    do {
//	        id = 10000 + rand.nextInt(90000); // Generates 10000–99999
//	    } while (isCustomerExists(id));    // Retry until unique
//	    return id;
//	}
//
//	public boolean isCustomerExists(int id) {
//	    String sql = "SELECT id FROM employee WHERE id = ?";
//	    try (Connection conn = DBUtil.getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(sql)) {
//	        stmt.setInt(1, id);
//	        ResultSet rs = stmt.executeQuery();
//	        return rs.next();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	    return true;
//	}



	@Override
	public Employee getEmployeeData(int empId) {
		try {
			Employee employee = null;
	        String sql = "select * from employees WHERE id=?";
	        Connection conn = DBUtil.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, empId);

	        ResultSet rt = ps.executeQuery();
	        if(rt!=null) {
	        	while(rt.next()) {
	        		employee = extractEmployeeFromResultSet(rt);
	        	}
	        	ps.close();
	        	conn.close();
	        	return employee;
	        }
	        else {
	        	throw new Exception();
	        }

	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	    }

	    return null;
	}


	@Override
	public boolean updateEmployeeData(int id, String contact, String address) {

	    try {
	        String sql = "UPDATE employees SET contact=?, address=? WHERE id=?";
	        Connection conn = DBUtil.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, contact);
	        ps.setString(2, address);
	        ps.setInt(3, id);

	        int rows = ps.executeUpdate();
	        if(rows>0) {
	        	ps.close();
	        	conn.close();
	        	return true;
	        }
	        else {
	        	throw new Exception();
	        }

	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean updatePassword(int empId, String newPassword) {

	    try {
	    	Connection conn = DBUtil.getConnection();
	        String sql = "UPDATE employees SET password = ? WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, newPassword);
	        ps.setInt(2, empId);

	        int rows = ps.executeUpdate();
	        if(rows>0) {
	        	ps.close();
	        	conn.close();
	        	return true;
	        }
	        else {
	        	throw new Exception();
	        }
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}




	@Override
	public boolean registerEmployee(Employee employee) {
	    String sql = "INSERT INTO employees (id, name, email, password, role, address, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employee.getEmpId());
	        ps.setString(2, employee.getName());
	        ps.setString(3, employee.getEmail());
	        ps.setString(4, employee.getPassword());
	        ps.setString(5, "Employee");
	        ps.setString(6, employee.getAddress());
	        ps.setString(7, employee.getContact());


	        if(ps.executeUpdate() >0) {
	        	System.out.println("Employee Registrated successfully!");
	        	ps.close();
	        	conn.close();
	        	return true ;
	        }
	        else {
	        	throw new Exception();
	        }

	    } catch (Exception e) {
	    	System.out.println("Employee not registrated.");
	        e.printStackTrace();
	    }

	    return false;
	}






}
