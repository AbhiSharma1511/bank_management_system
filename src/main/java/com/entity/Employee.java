package com.entity;

//CREATE TABLE Employees (
//	    empId INT PRIMARY KEY ,
//	    name VARCHAR(100),
//	    email VARCHAR(100) UNIQUE,
//	    password VARCHAR(100),
//	    role VARCHAR(50),
//	    address VARCHAR(200),
//	    contact VARCHAR(15)
//	);

public class Employee {

	private int empId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String contact;

	public Employee() {
		super();
	}

	public Employee(int empId, String name, String email, String password, String role, String address,
			String contact) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;

		this.contact = contact;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

    // Constructors

}
