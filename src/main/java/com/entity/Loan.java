package com.entity;

import java.sql.Timestamp;

public class Loan {

	private int loanId;
    private int customerId;
    private double loanAmount;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(int loanId, int customerId, double loanAmount, String status, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.loanId = loanId;
		this.customerId = customerId;
		this.loanAmount = loanAmount;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


    // Constructors

}
