package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.Customer;
import com.utils.DBUtil;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean login(int customerId, String password) {
        String sql = "SELECT * FROM customer WHERE customer_id = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	rs.close();
                	conn.close();
                    return true;
                }
                else {
                	throw new Exception();
                }
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return false; // login failed
    }


    @Override
    public boolean addNewCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, email, password, dob, address, contact, aadhar, pan, account_no, balance, active_account) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int customerId = generateUniqueCustomerId();
        int accountNo = generateCustomerAccountNo();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomerData(Customer customer) {
        String sql = "UPDATE customer SET email=?, contact=?, address=?, active_account=? WHERE customer_id=?";
        try (Connection conn = DBUtil.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getContact());
            stmt.setString(3, customer.getAddress());
            stmt.setBoolean(4, customer.isActiveAccount());
            stmt.setInt(5, customer.getCustomerId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
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
    public boolean setDeactiveAccount(int customerId) {
    	try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("update customer set active_account=? WHERE customer_id = ?")) {

               stmt.setBoolean(1, false);
               stmt.setInt(2, customerId);
               int rows = stmt.executeUpdate();
               return rows > 0;
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
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
        try (Connection conn = DBUtil.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Utility method
    private List<Customer> getCustomerListByQuery(String sql) {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(extractCustomerFromResultSet(rs));
            }
        } catch (Exception e) {
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

	@Override
    public boolean updateCustomerDataByCustomer(Customer customer) {
        String sql = "UPDATE customer SET email=?, contact=?, address=? WHERE customer_id=?";
        try (Connection conn = DBUtil.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getContact());
            stmt.setString(3, customer.getAddress());
            stmt.setInt(4, customer.getCustomerId());

            if(stmt.executeUpdate() > 0) {
            	stmt.close();
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
	public boolean updateBalance(int customerId, double newBalance) {

	    String sql = "UPDATE customer SET balance = ? WHERE customer_id = ?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setDouble(1, newBalance);
	        stmt.setInt(2, customerId);

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	    }

	    return false;
	}



	@Override
	public Customer getCustomerByAccountNo(String accountNo) {
	    String sql = "SELECT * FROM customer WHERE account_no = ?";
	    Customer customer = new Customer();
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, accountNo);
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()) {
	        	customer = extractCustomerFromResultSet(rs);
	        	rs.close();
	        	conn.close();
	        	return customer;
	        }
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	    }
	    return null;
	}

}










