package com.dao;


import com.entity.Employee;


public interface EmployeeDAO {

    boolean login(String empId, String password);
     Employee getEmployeeData(int empId);
     boolean updateEmployeeData(int id, String contact, String address);
     boolean updatePassword(int empId, String newPassword);
     boolean registerEmployee(Employee employee);

}
