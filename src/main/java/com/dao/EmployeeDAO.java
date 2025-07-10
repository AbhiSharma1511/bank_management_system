package com.dao;

import com.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    Employee login(String empId, String password);
    Employee getEmployeeById(String empId);
    List<Employee> getAllEmployees();
    boolean addEmployee(Employee emp);
    boolean deleteEmployee(String empId);
    boolean isEmployeeIdExists(int id);
}
