package com.dao;

import java.util.List;
import com.entity.Customer;

//List<Customer> getAllCustomer();
//Customer getCustomerById(String empId);
//boolean updateCustomerData(Customer customer);
//boolean addNewCustomer(Customer customer);
//boolean deleteCustomer(String empId);
//boolean setActiveAccount();
//List<Customer> getAllInactiveCustomer();

public interface CustomerDAO {

	Customer login(String empId, String password);
	boolean updateCustomerData(Customer customer);
	boolean addNewCustomer(Customer customer);
	boolean deleteCustomer(int customerId);
	boolean setActiveAccount(int customerId); // Set one account active
	List<Customer> getAllCustomers();
	List<Customer> getAllActiveCustomers();
	List<Customer> getAllInactiveCustomers();
	Customer getCustomerById(int customerId);

}
