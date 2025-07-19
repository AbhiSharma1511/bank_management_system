package com.dao;

import com.entity.Customer;
import com.entity.Employee;
import java.util.List;

public interface EmployeeDAO { 
	
	
    boolean login(String empId, String password);
     Employee getEmployeeData(int empId);
     boolean updateEmployeeData(int id, String contact, String address);
     boolean updatePassword(int empId, String newPassword);

    // customer management apis
    
    
    //loan management
    
    
    
}
