package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.Employee;
import com.utils.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee login(String empId, String password) {
		String sql = "SELECT * FROM employee WHERE id = ? AND password = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, empId);
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
	

	@Override
	public Employee getEmployeeById(String empId) {
		String sql = "SELECT * FROM employees WHERE emp_id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, empId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return extractEmployeeFromResultSet(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM employees";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				list.add(extractEmployeeFromResultSet(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean addEmployee(Employee emp) {
		String sql = "INSERT INTO employees (emp_id, name, email, password, role) VALUES (?, ?, ?, ?, ?)";
		int empId = generateUniqueEmployeeId();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, empId+"");
			stmt.setString(2, emp.getName());
			stmt.setString(3, emp.getEmail());
			stmt.setString(4, emp.getPassword());
			stmt.setString(5, emp.getRole());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	

	@Override
	public boolean deleteEmployee(String empId) {
		String sql = "DELETE FROM employees WHERE emp_id = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, empId);
			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// Helper method to extract Employee from ResultSet
	private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
		return new Employee(rs.getString("id"), rs.getString("name"), rs.getString("email"),
				rs.getString("password"), rs.getString("role"));
	}
	
	public int generateUniqueEmployeeId() {
	    Random rand = new Random();
	    int id;
	    do {
	        id = 10000 + rand.nextInt(90000); // Generates 10000–99999
	    } while (isEmployeeIdExists(id));    // Retry until unique
	    return id;
	}
	
	@Override
	public boolean isEmployeeIdExists(int id) {
	    String sql = "SELECT id FROM employee WHERE id = ?";
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        return rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return true;
	}
}
