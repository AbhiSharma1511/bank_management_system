package com.entity;

public class Employee {
    private String empId;
    private String name;
    private String email;
    private String password;
    private String role;

    // Constructors
    public Employee() {}

    public Employee(String empId, String name, String email, String password, String role) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
