package com.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.Customer;
import com.utils.DBUtil;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection conn;

    public CustomerDAOImpl() {
        try {
            this.conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Customer login(String customerId, String password) {
        String sql = "SELECT * FROM customer WHERE customer_id = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(customerId));
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCustomerFromResultSet(rs); // This method should map the result to a Customer object
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login failed
    }


    @Override
    public boolean addNewCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, email, password, dob, address, contact, aadhar, pan, account_no, balance, active_account) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int customerId = generateUniqueCustomerId();
        int accountNo = generateCustomerAccountNo();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPassword());
            stmt.setDate(6, Date.valueOf(customer.getDob()));
            stmt.setString(7, customer.getAddress());
            stmt.setString(8, customer.getContact());
            stmt.setString(9, customer.getAadhar());
            stmt.setString(10, customer.getPan());
            stmt.setString(11, "ACC"+accountNo);
            stmt.setDouble(12, customer.getBalance());
            stmt.setBoolean(13, false);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomerData(Customer customer) {
        String sql = "UPDATE customer SET email=?, contact=?, address=?, active_account=? WHERE customer_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getContact());
            stmt.setString(3, customer.getAddress());
            stmt.setBoolean(4, customer.isActiveAccount());
            stmt.setInt(5, customer.getCustomerId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteCustomer(int customerId) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM customer WHERE customer_id = ?")) {
            
            stmt.setInt(1, customerId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean setActiveAccount(int customerId) {
        try {
            // Step 1: Set all accounts to inactive
            String deactivateAll = "UPDATE customer SET active_account = false";
            try (PreparedStatement stmt = conn.prepareStatement(deactivateAll)) {
                stmt.executeUpdate();
            }

            // Step 2: Activate the selected account
            String activateOne = "UPDATE customer SET active_account = true WHERE customer_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(activateOne)) {
                stmt.setInt(1, customerId);
                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return getCustomerListByQuery(sql);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        String sql = "SELECT * FROM customer WHERE active_account = true";
        return getCustomerListByQuery(sql);
    }

    @Override
    public List<Customer> getAllInactiveCustomers() {
        String sql = "SELECT * FROM customer WHERE active_account = false";
        return getCustomerListByQuery(sql);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Utility method
    private List<Customer> getCustomerListByQuery(String sql) {
        List<Customer> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(extractCustomerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setDob(rs.getDate("dob").toLocalDate());
        customer.setAddress(rs.getString("address"));
        customer.setContact(rs.getString("contact"));
        customer.setAadhar(rs.getString("aadhar"));
        customer.setPan(rs.getString("pan"));
        customer.setAccountNo(rs.getString("account_no"));
        customer.setBalance(rs.getDouble("balance"));
        customer.setActiveAccount(rs.getBoolean("active_account"));
        return customer;
    }
    
	
	private int generateCustomerAccountNo() {
	    Random rand = new Random();
	    int id = 1000 + rand.nextInt(9000);
//	    do {
//	        id = 1000 + rand.nextInt(9000); // Generates 10000–99999
//	    } while (isCustomerExists(id));    // Retry until unique
	    return id;
	}
	
	private int generateUniqueCustomerId() {
		Random rand = new Random();
		int id;
		do {
			id = 10000 + rand.nextInt(90000); // Generates 10000–99999
		} while (isCustomerExists(id));    // Retry until unique
		return id;
	}
	
	private boolean isCustomerExists(int id) {
	    String sql = "SELECT customer_id FROM customer WHERE customer_id = ?";
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
