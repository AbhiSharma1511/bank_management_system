package com.entity;

import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private String senderAccountNo;
    private String receiverAccountNo;
    private double amount;
    private LocalDate transactionDate;

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
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
