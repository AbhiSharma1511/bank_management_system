package com.dao;

import java.sql.*;
import com.entity.Employee;
import com.utils.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Override
	public Employee login(String id, String password) {
		
		String sql = "SELECT * FROM employee WHERE id = ? AND password = ?";
		try (Connection conn = DBUtil.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, id);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return extractEmployeeFromResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
	    Employee emp = new Employee();

	    emp.setEmpId(rs.getInt("id"));
	    emp.setName(rs.getString("name"));
	    emp.setEmail(rs.getString("email"));
	    emp.setPassword(rs.getString("password"));
	    emp.setRole(rs.getString("role")); // If you have roles like "manager", "staff", etc.
	   
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
	public Employee getEmployeeData() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean updateEmployeeData() {
		// TODO Auto-generated method stub
		return false;
	}

}
