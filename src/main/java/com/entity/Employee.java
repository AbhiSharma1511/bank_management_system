package com.entity;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private String password;
    private String role;

    // Constructors
    public Employee() {}

    public Employee(int empId, String name, String email, String password, String role) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int i) {
        this.empId = i;
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
