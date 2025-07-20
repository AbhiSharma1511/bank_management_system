package com.dao;

import java.util.List;

import com.entity.Customer;


public interface CustomerDAO {

	boolean login(int customerId, String password);
	boolean updateCustomerData(Customer customer);
	boolean updateCustomerDataByCustomer(Customer customer);
	boolean addNewCustomer(Customer customer);
	boolean deleteCustomer(int customerId);
	boolean setDeactiveAccount(int customerId); // Set one account active
	List<Customer> getAllCustomers();
	List<Customer> getAllActiveCustomers();
	List<Customer> getAllInactiveCustomers();
	Customer getCustomerById(int customerId);
	boolean updateBalance(int customerId, double newBalance);
	public Customer getCustomerByAccountNo(String accountNo);

}
