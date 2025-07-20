package com.dao;

import java.util.List;

import com.entity.Transaction;

public interface TransactionDAO {
    boolean addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByAccount(String accountNo);
    boolean deleteTransaction(int transactionId);
}
