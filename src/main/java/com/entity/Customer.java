package com.entity;

import java.time.LocalDate;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private String address;
    private String contact;
    private String aadhar;
    private String pan;
    private String accountNo;
    private double balance;
    private boolean activeAccount;
    
    
    
    
	public Customer() {	};


	public Customer(int customerId, String firstName, String lastName, String email, String password, LocalDate dob,
			String address, String contact, String aadhar, String pan, String accountNo, double balance,
			boolean activeAccount) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.address = address;
		this.contact = contact;
		this.aadhar = aadhar;
		this.pan = pan;
		this.accountNo = accountNo;
		this.balance = balance;
		this.activeAccount = activeAccount;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
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


	public String getAadhar() {
		return aadhar;
	}


	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public boolean isActiveAccount() {
		return activeAccount;
	}


	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}
    
}