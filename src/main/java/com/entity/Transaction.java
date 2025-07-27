package com.entity;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private String senderAccountNo;
    private String receiverAccountNo;
    private double amount;
    private Timestamp transactionTime;

    public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	// Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public String getSenderAccountNo() {
        return senderAccountNo;
    }
    public void setSenderAccountNo(String senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }
    public String getReceiverAccountNo() {
        return receiverAccountNo;
    }
    public void setReceiverAccountNo(String receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
