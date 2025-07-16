package com.entity;

public class Loan {

    private int loanId;
    private int customerId;
    private String customerName;
    private double loanAmount;
    private String status;

    // Constructors
    public Loan() {}

    public Loan(int loanId, int customerId, String customerName, double loanAmount,String status) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.loanAmount = loanAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
